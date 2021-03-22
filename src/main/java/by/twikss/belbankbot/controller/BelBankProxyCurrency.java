package by.twikss.belbankbot.controller;

import by.twikss.belbankbot.model.Currency;
import by.twikss.belbankbot.model.News;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "BelBankCurrency", url = "https://belarusbank.by/api/" )
public interface BelBankProxyCurrency {

    @GetMapping("/{city}")
    List<Currency> getCurrency(@PathVariable String city);

}
