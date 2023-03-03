package iss.ibf.ssf_assessment.services;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import iss.ibf.ssf_assessment.models.Quotation;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class QuotationService {
    
    public Quotation getQuotations(List<String> items) throws Exception {

        // Building the JsonArray from a list of items
        Gson gson = new GsonBuilder().create();
        com.google.gson.JsonArray itemsBuilder = gson.toJsonTree(items).getAsJsonArray();

        // Posting the JsonArray to get the quote
        String url = "https://quotation.chuklee.com/quotation";
        
        RequestEntity<String> req = RequestEntity.post(url).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(itemsBuilder.toString(), String.class);

        RestTemplate template = new RestTemplate();

        ResponseEntity<String> resp = template.exchange(req, String.class);;

        String payload = "";
        int statusCode = 500;

        payload = resp.getBody();
        statusCode = resp.getStatusCode().value();

        System.out.printf("Payload: %s\n", payload);

        Quotation quotation = new Quotation();

        Reader reader = new StringReader(payload);
        JsonReader jsonReader = Json.createReader(reader);
        JsonObject orderJson = jsonReader.readObject();

        String quoteId = orderJson.getJsonString("quoteId").toString();
        quotation.setQuoteId(quoteId);
        System.out.printf("QuoteId: %s\n", quoteId);

        JsonArray quotationsArray = orderJson.getJsonArray("quotations");
        for (int i = 0; i < quotationsArray.size(); i++) {
            JsonObject quotationObject = quotationsArray.getJsonObject(i);
            String item = quotationObject.getJsonString("item").toString();
            String unitPrice = quotationObject.getJsonString("unitPrice").toString();
            float unitPriceFloat = Float.parseFloat(unitPrice);
        }

        return quotation;
    }

}
