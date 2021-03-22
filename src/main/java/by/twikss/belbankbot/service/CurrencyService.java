package by.twikss.belbankbot.service;

import by.twikss.belbankbot.controller.BelBankProxyCurrency;
import by.twikss.belbankbot.model.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class CurrencyService {

    @Autowired
    private BelBankProxyCurrency belBankProxyCurrency;

    public List<Currency> getCurrenccyWithCity(String city){
        String url = "kursExchange?city=" + city;
        return belBankProxyCurrency.getCurrency(url);
    }

}
