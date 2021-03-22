package by.twikss.belbankbot.controller;

import by.twikss.belbankbot.model.News;
//import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "BelBankNews", url = "https://belarusbank.by/api/news_info" )
public interface BelBankProxyNews {

    @GetMapping()
    List<News> getAllnews();


}
