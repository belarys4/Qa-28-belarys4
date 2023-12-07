package ru.tinkoff.qa.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryCreator {
    public static SessionFactory createSessionFactory() {
        SessionFactory sessionFactory = new Configuration().configure().
                addAnnotatedClass(Animal.class).
                addAnnotatedClass(Zoo.class).
                addAnnotatedClass(Workman.class).
                addAnnotatedClass(Places.class).
                buildSessionFactory();
        return sessionFactory;
    }
}
