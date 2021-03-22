package by.twikss.belbankbot.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Currency {

    @JsonProperty("USD_in")
    private Double USD_in;
    @JsonProperty("USD_out")
    private String USD_out;
    @JsonProperty("EUR_in")
    private String EUR_in;
    @JsonProperty("EUR_out")
    private String EUR_out;
    @JsonProperty("RUB_in")
    private String RUB_in;
    @JsonProperty("RUB_out")
    private String RUB_out;

    @JsonProperty("filial_id")
    private String filial_id;
    @JsonProperty("sap_id")
    private String sap_id;
    @JsonProperty("info_worktime")
    private String info_worktime;
    @JsonProperty("street_type")
    private String street_type;
    @JsonProperty("street")
    private String street;
    @JsonProperty("filials_text")
    private String filials_text;
    @JsonProperty("home_number")
    private String home_number;
    @JsonProperty("name")
    private String name;
    @JsonProperty("name_type")
    private String name_type;

    @Override
    public String toString() {
        return
                "USD покупка= " + USD_in + "\n" +
                "USD продажа= " + USD_out + "\n" +
                "EUR покупка= " + EUR_in + "\n"+
                "EUR продажа= " + EUR_out + "\n" +
                "Улица " + street + "\n" +
                "Филиал " + filials_text + "\n" +
                "Город " + name + "\n"
                ;
    }
}
