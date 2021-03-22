package by.twikss.belbankbot;

import by.twikss.belbankbot.constants.BotPhrases;
import by.twikss.belbankbot.constants.NamesButtons;
import by.twikss.belbankbot.keyboards.GetNewsAndCurrencyInlineKeyboard;
import by.twikss.belbankbot.keyboards.GetContactKeyboard;
import by.twikss.belbankbot.model.Currency;
import by.twikss.belbankbot.model.News;
import by.twikss.belbankbot.registration.beans.Client;
import by.twikss.belbankbot.registration.dao.ClientDaoServer;
import by.twikss.belbankbot.service.CurrencyService;
import by.twikss.belbankbot.service.NewsService;
import by.twikss.belbankbot.validates.ValidateService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendContact;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

import static by.twikss.belbankbot.constants.BotState.*;


@Component
public class BelBankBotOnUpdate extends TelegramLongPollingBot {
    private String text;
    private Long chatId;
    private Client client;

    @Autowired
    private ClientDaoServer clientDaoServer;

    @Autowired
    private GetContactKeyboard getContactKeyboard;
    @Autowired
    private GetNewsAndCurrencyInlineKeyboard getNewsAndCurrencyInlineKeyboard;

    @Autowired
    private NewsService newsService;
    @Autowired
    private CurrencyService currencyService;
    @Autowired
    private ValidateService validateService;


    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            text = update.getMessage().getText();
            chatId = update.getMessage().getChatId();
            client = clientDaoServer.findByChatId(chatId);


            if (text.equals("/start")) {

                client.setBot_state(REG_NAME);
                client.setChat_id(chatId);
                clientDaoServer.saveClient(client);
                sendMessage(BotPhrases.SAY_WRITE_NAME, chatId);
            } else {
                switch (client.getBot_state()) {
                    case REG_NAME:

                        client.setName(text);
                        if (validateService.validateClient(client) != null){
                            sendMessage(new SendMessage().
                                    setText(validateService.validateClient(client)).setChatId(chatId));
                            sendMessage(BotPhrases.SAY_INPUT_RIGHT_NAME, chatId);
                        } else{

                            client.setBot_state(REG_SURNAME);
                            sendMessage(BotPhrases.SAY_WRITE_SURNAME, chatId);
                            clientDaoServer.saveClient(client);

                        }
                        break;
                    case REG_SURNAME:
                        client.setSur_name(text);

                        if (validateService.validateClient(client) != null){
                            sendMessage(new SendMessage().
                                    setText(validateService.validateClient(client)).setChatId(chatId));
                            sendMessage(BotPhrases.SAY_INPUT_RIGHT_SURNAME, chatId);
                        } else{
                            client.setBot_state(REG_LASTNAME);
                            sendMessage(BotPhrases.SAY_INPUT_RIGHT_LASTNAME, chatId);
                            clientDaoServer.saveClient(client);
                        }


                        break;
                    case REG_LASTNAME:
                        client.setLast_name(text);

                        if (validateService.validateClient(client) != null){
                            sendMessage(new SendMessage().
                                    setText(validateService.validateClient(client)).setChatId(chatId));
                            sendMessage(BotPhrases.SAY_WRITE_LAST_NAME, chatId);
                        } else{
                            client.setBot_state(REG_PHONE);
                            clientDaoServer.saveClient(client);
                            sendMessage(new SendMessage().
                                    setReplyMarkup(getContactKeyboard.getContactKeyboard()).
                                    setChatId(chatId).setText(BotPhrases.SAY_TAKE_PHONE));
                        }



                        break;
                    case USER:
                        deleteMessage(new DeleteMessage().
                                setChatId(chatId).
                                setMessageId(update.getMessage().getMessageId()));
                        break;
                    case CURRENCY:
                        client.setBot_state(USER);
                        clientDaoServer.saveClient(client);

                        List<Currency> currencies = currencyService.getCurrenccyWithCity(text);

                        if (currencies.size() != 0){
                            sendMessage(new SendMessage().
                                    setChatId(chatId).
                                    setText(currencies.get(0).toString())
                            );
                            sendMessage(new SendMessage().
                                    setChatId(chatId).
                                    setText(currencies.get(1).toString())
                            );
                            sendMessage(new SendMessage().
                                    setChatId(chatId).
                                    setText(currencies.get(2).toString())
                            );
                            sendMessage(getNewsAndCurrencyInlineKeyboard.getMenuKeyboard(chatId));
                        } else {
                            sendMessage(new SendMessage().
                                    setChatId(chatId).
                                    setText(BotPhrases.SAY_WRONG_CITY)
                            );
                            sendMessage(getNewsAndCurrencyInlineKeyboard.getMenuKeyboard(chatId));
                        }

                        break;
                    default:
                        break;
                }
            }
        } else if (update.hasCallbackQuery()) {

            switch (update.getCallbackQuery().getData()) {
                case NamesButtons.GET_NEWS:
                    List<News> news = newsService.getAllNews();
                    sendMessage(new SendMessage().setText(news.get(0).toString()).setChatId(chatId));
                    sendMessage(new SendMessage().setText(news.get(1).toString()).setChatId(chatId));
                    sendMessage(new SendMessage().setText(news.get(2).toString()).setChatId(chatId));
                    sendMessage(getNewsAndCurrencyInlineKeyboard.getMenuKeyboard(chatId));
                    break;

                case NamesButtons.GET_CURRENCY:
                    client.setBot_state(CURRENCY);
                    clientDaoServer.saveClient(client);
                    sendMessage(new SendMessage().setText(BotPhrases.SAY_CHOICE_CITY_CURRENCY).setChatId(chatId));
                    break;
                default:
                    break;
            }
        } else {
            client.setPhone_number(update.getMessage().getContact().getPhoneNumber());
            client.setBot_state(USER);
            clientDaoServer.saveClient(client);
            sendMessage(new SendMessage().
                    setChatId(chatId).
                    setText(BotPhrases.SAY_REGISTERED).setReplyMarkup(new ReplyKeyboardRemove()));
            sendMessage(getNewsAndCurrencyInlineKeyboard.getMenuKeyboard(chatId));
        }
    }



    public void sendMessage(String text, Long chat_id) {
        try {
            execute(new SendMessage()
                    .setText(text)
                    .setChatId(chat_id));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void deleteMessage(DeleteMessage deleteMessage) {
        try {
            execute(deleteMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String getBotUsername() {
        return "EnglishAndrewBot";
    }

    @Override
    public String getBotToken() {
        return "1332611656:AAESQWdBeCTWH_pdFtK2zBncACfTNWObANk";
    }
}
