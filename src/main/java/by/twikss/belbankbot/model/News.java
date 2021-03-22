package by.twikss.belbankbot.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.jsoup.Jsoup;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class News {

    @JsonProperty("name_ru")
    private String name_ru;
    @JsonProperty("name_be")
    private String name_be;
    @JsonProperty("html_ru")
    private String html_ru;
    @JsonProperty("html_be")
    private String html_be;
    @JsonProperty("img")
    private String img;
    @JsonProperty("start_date")
    private String start_date;

    @Override
    public String toString() {
        return "Новости" + "\n" +
                Jsoup.parse(name_ru).text() + "\n" +
                Jsoup.parse(html_ru).text()  + "\n" +
                img  + "\n" +
                start_date
                ;
    }

}
