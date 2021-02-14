package com.apospiech.springhttpclient.services;

import com.apospiech.springhttpclient.api.domain.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ApiService {
    public String crypto = "https://pro-api.coinmarketcap.com/v1/fiat/map?limit=";
    public String crypto2 = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest?id=1";
    public String weather = "https://api.openweathermap.org/data/2.5/weather?appid=d3c03381ffdc9e78d7cf1d5b7845ab96&q=";
    public String API_KEY = "e2fcce01-4a0c-4d3c-bcd8-b887156dfa95";


    public HttpEntity request;
    public RestTemplate restTemplate;

    public ApiService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public Currency[] getCurrency(Integer limit) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-CMC_PRO_API_KEY", API_KEY);
        request = new HttpEntity(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                crypto + limit,
                HttpMethod.GET,
                request,
                String.class

        );
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(response.getBody());
        List<Currency> values = new ArrayList();
        json.get("data").forEach(entry -> values.add(mapper.convertValue(entry, Currency.class)));
        return values.toArray(new Currency[0]);
    }

    public boolean getUsdList(Integer min) throws IOException{
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-CMC_PRO_API_KEY", API_KEY);
        request = new HttpEntity(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                crypto2,
                HttpMethod.GET,
                request,
                String.class
        );

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode json = mapper.readTree(response.getBody());
        List<USD> usdResponseList = new ArrayList();
        JsonNode test = json.get("data").get("1").get("quote").get("USD").get("price");
        double price = Double.parseDouble(test.toString());
        return price > min;
    }

    public Weather_[] getWeather(String city) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        request = new HttpEntity(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                weather + city,
                HttpMethod.GET,
                request,
                String.class
        );
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(response.getBody());
        List<Weather_> values = new ArrayList();

        json.get("weather").forEach(entry -> values.add(mapper.convertValue(entry, Weather_.class)));

        return values.toArray(new Weather_[0]);
    }

    public boolean getWeather2(String city) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        request = new HttpEntity(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                weather + city,
                HttpMethod.GET,
                request,
                String.class
        );
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(response.getBody());
        List<Weather> values = new ArrayList();


        json.get("id").forEach(entry -> values.add(mapper.convertValue(entry, Weather.class)));

        System.out.println(json.get("id") != null);
        return json.get("id") != null;
    }

}