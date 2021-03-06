package com.microservice.currencyconversionservice;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CurrencyConversionController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CurrencyExchangeProxy proxy;

    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrency
            (@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
        //using Restclient
        Map<String, String> urivariables = new HashMap<>();
        urivariables.put("from", from);
        urivariables.put("to", to);
        ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",
                CurrencyConversionBean.class, urivariables);

        CurrencyConversionBean response = responseEntity.getBody();
        response.setQuantity(quantity);
        response.setCalculatedAmount(quantity.multiply(response.getConversionMultiple()));
        return response;
        //  return new CurrencyConversionBean(response.getId(),from,to,response.getConversionMultiple(),
        // quantity,quantity.multiply(response.getConversionMultiple()),response.getPort());
    }

    // Using Feign to invoke other microservice
    @GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrencyFeign
    (@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {

        CurrencyConversionBean response = proxy.retrieveExchangeValue(from, to);

        //  response.setQuantity(quantity);
        // response.setCalculatedAmount(quantity.multiply(response.getConversionMultiple()));
        // return response;
        logger.info("{}", response);

        return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(), quantity,
                quantity.multiply(response.getConversionMultiple()), response.getPort());
    }
}
