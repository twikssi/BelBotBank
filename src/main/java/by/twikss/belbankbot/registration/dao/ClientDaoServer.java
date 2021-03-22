package by.twikss.belbankbot.registration.dao;

import by.twikss.belbankbot.registration.beans.Client;
import by.twikss.belbankbot.registration.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientDaoServer {

    @Autowired
    private ClientRepository clientRepository;

    public void saveClient(Client client) {
        clientRepository.save(client);
    }

    public Client findByChatId(Long chatId){
        List<Client> listClients = clientRepository.findAll();
        return listClients.stream().filter(a -> a.getChat_id().equals(chatId)).findAny().orElse(new Client());
    }

//    public void updateClient(Client updateClient, Long chatId) {
//
//        Client client = findByChatId(chatId);
//
//        if (updateClient.getName() != null){
//            client.setName(updateClient.getName());
//        }
//        if (updateClient.getSur_name() != null){
//            client.setSur_name(updateClient.getSur_name());
//        }
//        if (updateClient.getLast_name() != null){
//            client.setLast_name(updateClient.getLast_name());
//        }
//        if (updateClient.getPhone_number() != null){
//            client.setPhone_number(updateClient.getPhone_number());
//        }
//
//        clientRepository.save(client);
//    }
}
