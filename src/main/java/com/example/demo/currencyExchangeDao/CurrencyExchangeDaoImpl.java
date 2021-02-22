package com.example.demo.currencyExchangeDao;

import com.example.demo.currencyExchangeEntity.CurrencyApiUssage;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

@Repository
public class CurrencyExchangeDaoImpl implements ICurrencyExchangeDao {

    private EntityManager entityManager;

    @Autowired
    public CurrencyExchangeDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public void saveCurrencyExchangeOperation(String appEndpoint, String nbpEndpoint,
                                              String currencyFrom, String currencyTo) {

        Session session = entityManager.unwrap(Session.class);
        CurrencyApiUssage currencyApiUssage = new CurrencyApiUssage(appEndpoint, nbpEndpoint, currencyFrom, currencyTo);

        try {
            session.save(currencyApiUssage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
