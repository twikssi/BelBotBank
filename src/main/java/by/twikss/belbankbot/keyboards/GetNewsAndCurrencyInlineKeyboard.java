package by.twikss.belbankbot.keyboards;

import by.twikss.belbankbot.constants.BotPhrases;
import by.twikss.belbankbot.constants.NamesButtons;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetNewsAndCurrencyInlineKeyboard {

    public InlineKeyboardMarkup getNewsAndCurrencyKeyboard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();

        inlineKeyboardButton1.setText("Получить новости");
        inlineKeyboardButton1.setCallbackData(NamesButtons.GET_NEWS);

        inlineKeyboardButton2.setText("Валюты по городам");
        inlineKeyboardButton2.setCallbackData(NamesButtons.GET_CURRENCY);

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();

        keyboardButtonsRow1.add(inlineKeyboardButton1);
        keyboardButtonsRow2.add(inlineKeyboardButton2);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);

        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }

    public SendMessage getMenuKeyboard(Long chatId){
        return new SendMessage().
                setChatId(chatId).
                setReplyMarkup(getNewsAndCurrencyKeyboard()).
                setText("Menu");
    }
}
