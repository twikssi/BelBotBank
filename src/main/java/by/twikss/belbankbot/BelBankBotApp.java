package by.twikss.belbankbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
@EnableFeignClients
public class BelBankBotApp {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(BelBankBotApp.class, args);
    }
}
