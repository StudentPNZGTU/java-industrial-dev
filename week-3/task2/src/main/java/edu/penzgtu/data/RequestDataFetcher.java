package edu.penzgtu.data;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import edu.penzgtu.model.api.CryptoResponse;
import edu.penzgtu.utils.JsonUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestDataFetcher implements RequestDataFetcherImpl {

    private String jsonString;

    private static final Logger LOGGER = LogManager.getLogger(RequestDataFetcher.class);

    @Override
    public void fetchApiJsonString(String requestURL) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(requestURL, String.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            LOGGER.error(new StringBuilder()
                    .append("error receiving the url request body:\n")
                    .append("status response=").append(response.getStatusCode())
                    .append("\nurl=").append(requestURL)
            );
        }
        this.jsonString = response.getBody();
    }

    public ArrayList<CryptoResponse> parseJsonToResponseList() {
        ArrayList<CryptoResponse> cryptoResponses = new ArrayList<>();
        JsonElement jsonElement = JsonUtils.parseStringToJsonElement(this.jsonString);
        if (jsonElement.isJsonObject()) {
            JsonObject dataObject = jsonElement.getAsJsonObject().getAsJsonObject("Data");
            if (dataObject != null && dataObject.isJsonObject()) {
                for (JsonElement element : dataObject.getAsJsonArray("Data")) {
                    cryptoResponses.add(
                            new CryptoResponse(
                                    element.getAsJsonObject().get("time").getAsLong(),
                                    element.getAsJsonObject().get("open").getAsDouble()
                            )
                    );
                }
            } else {
                LOGGER.error("Error parsing json object: {}", dataObject);
            }
        } else{
            LOGGER.error("Error parsing json: {}", jsonElement);
        }
        return cryptoResponses;
    }
}
