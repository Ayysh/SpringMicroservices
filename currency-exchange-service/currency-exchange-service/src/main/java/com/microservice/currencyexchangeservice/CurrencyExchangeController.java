package com.microservice.currencyexchangeservice;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CurrencyExchangeController {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private Environment environment; // to get port on which application is running

    @Autowired
    private ExchangeValueRepository valueRepository;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
        ExchangeValue exchangeValue = valueRepository.findByFromAndTo(from, to);
        //new ExchangeValue(1000L, from, to ,BigDecimal.valueOf(65));
        exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
        logger.info("{}", exchangeValue);
        return exchangeValue;
    }
}
