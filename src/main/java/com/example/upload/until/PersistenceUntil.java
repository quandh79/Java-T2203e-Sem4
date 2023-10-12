package com.example.upload.until;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceUntil {
    private static EntityManagerFactory entityManagerFactory;

    public static synchronized EntityManagerFactory createEntityManagerFactory() {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory("jpaDemo");
        }
        return  entityManagerFactory;
    }
}
