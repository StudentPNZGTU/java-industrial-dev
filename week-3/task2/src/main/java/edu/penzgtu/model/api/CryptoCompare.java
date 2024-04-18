package edu.penzgtu.model.api;

import edu.penzgtu.errors.DataLoadException;
import edu.penzgtu.utils.ResourceReader;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import lombok.NonNull;

import java.io.IOException;

@NonNull
@Getter
@Setter
public class CryptoCompare {
    private String API_URL;
    private String currency1;
    private String currency2;

    private static final Logger LOGGER = LogManager.getLogger(CryptoCompare.class);

    public CryptoCompare(String cur1, String cur2) {
        this.currency1 = cur1;
        this.currency2 = cur2;
        try {
            this.API_URL = ResourceReader.readConfig("config.properties").getProperty("api.url");
        } catch (IOException e) {
            LOGGER.error("Error reading data from file: config.properties");
            throw new DataLoadException(e);
        } catch (NullPointerException e) {
            LOGGER.error("Error reading the configuration. The API_URL is: {}", this.API_URL);
            throw new DataLoadException(e);
        }
    }

    public String buildRequestUrl() {
        return API_URL +
                "&fsym=" + this.currency1 +
                "&tsym=" + this.currency2;
    }

    public boolean validateResponse(String URL) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(URL, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            String responseBody = response.getBody();
            assert responseBody != null;
            return !responseBody.contains("Error");
        } else {
            LOGGER.error("Error occurred while validating cryptocurrency: {}", response.getStatusCode());
            return false;
        }
    }

    public boolean validateCurrency(String currency) {
        String url = "https://min-api.cryptocompare.com/data/price?";
        return validateResponse(url + "&fsym=USD&tsyms=" + currency);
    }
}