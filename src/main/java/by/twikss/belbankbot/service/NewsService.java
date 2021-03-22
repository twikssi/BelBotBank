package by.twikss.belbankbot.service;

import by.twikss.belbankbot.controller.BelBankProxyNews;
import by.twikss.belbankbot.model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class NewsService {

    @Autowired
    BelBankProxyNews belBankProxyNews;

    public List<News> getAllNews() {
        return belBankProxyNews.getAllnews();
    }

}
