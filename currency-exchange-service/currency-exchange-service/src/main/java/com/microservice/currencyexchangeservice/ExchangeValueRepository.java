package com.microservice.currencyexchangeservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeValueRepository extends JpaRepository<ExchangeValue,Long> {

    /**
     * select exchangeva0_.id as id1_0_, exchangeva0_.conversion_multiple as conversi2_0_,
     * exchangeva0_.currency_from as currency3_0_, exchangeva0_.port as port4_0_,
     * exchangeva0_.currency_to as currency5_0_ from exchange_value exchangeva0_
     * where
     * exchangeva0_.currency_from=? and exchangeva0_.currency_to=?
     * @param from
     * @param to
     * @return
     */
    ExchangeValue findByFromAndTo(String from,String to);
}
