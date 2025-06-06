package currency.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SupportedCurrencies {
    private Map<String, CurrencyInfo> supportedCurrenciesMap;

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CurrencyInfo {
        private String currencyCode;
        private String currencyName;
        private String status;
        private String availableUntil;
        private String icon;
    }
}
