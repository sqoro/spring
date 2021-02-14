package com.apospiech.springhttpclient.services;


import com.apospiech.springhttpclient.api.domain.Coord;
import com.apospiech.springhttpclient.api.domain.Currency;
import com.apospiech.springhttpclient.api.domain.Weather;
import com.apospiech.springhttpclient.api.domain.Weather_;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.function.BooleanSupplier;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ApiServiceImplTest {
    @Autowired
    ApiService apiService;

    @Test
    public void testGetCurrency() throws Exception {
        Currency[] currencies = apiService.getCurrency(2);
        Assertions.assertEquals(2, currencies.length);
    }

    @Test
    public void testPrice() throws Exception{
        var price = apiService.getUsdList(0);
        Assertions.assertTrue(price);
    }

    @Test
    public void testGetWeather() throws Exception{
        Weather_[] weathers = apiService.getWeather("katowice");
        Assertions.assertEquals(1, weathers.length);
    }

    @Test
    public void testGetWeather2() throws Exception{
        boolean id = apiService.getWeather2("katowice");
        Assertions.assertTrue(id);
    }
}