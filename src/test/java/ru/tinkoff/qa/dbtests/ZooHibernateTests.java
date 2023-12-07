package ru.tinkoff.qa.dbtests;

import jakarta.persistence.PersistenceException;
import org.hibernate.Session;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.tinkoff.qa.hibernate.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ZooHibernateTests {

    @BeforeAll
    static void init() {
        BeforeCreator.createData();

    }

    /**
     * В таблице public.animal ровно 10 записей
     */
    @Test
    public void countRowAnimal() {
        Session session = HibernateSessionFactoryCreator.createSessionFactory().openSession();

        long count = (long) session.createQuery("select count(*) from Animal").uniqueResult();
        assertEquals(10, count, "How much is the fish?");
        session.close();

    }


    /**
     * В таблицу public.animal нельзя добавить строку с индексом от 1 до 10 включительно
     */

    @Test
    public void insertIndexAnimal() {
        try (Session session = HibernateSessionFactoryCreator.createSessionFactory().openSession()) {

            session.beginTransaction();

            for (int i = 1; i <= 10; i++) {
                Animal animal = new Animal(i, "Noname", 4, 3, 2, 3);
                session.persist(animal);
            }

            session.getTransaction().commit();
        } catch (PersistenceException e) {
            System.out.println("Нарушение уникального индекса или первичного ключа");
        }

    }


    /**
     * В таблицу public.workman нельзя добавить строку с name = null
     */
    @Test
    public void insertNullToWorkman() {
        try (Session session = HibernateSessionFactoryCreator.createSessionFactory().openSession()) {

            session.beginTransaction();
            Workman workman = new Workman();
            workman.setAge(43);
            workman.setName(null);
            workman.setPosition(1);
            workman.setId(12);
            session.save(workman);
            session.getTransaction().commit();
        } catch (PersistenceException e) {
            System.out.println("Невозможно добавить null в имя");
        }
    }

    /**
     * Если в таблицу public.places добавить еще одну строку, то в ней будет 6 строк
     */
    @Test
    public void insertPlacesCountRow() {
        Session session = HibernateSessionFactoryCreator.createSessionFactory().openSession();
        session.beginTransaction();
        Places places = new Places();
        places.setId(6);
        places.setRow(3);
        places.setPlace_num(31);
        places.setName("хорошее");
        session.save(places);
        session.getTransaction().commit();
        long count = (long) session.createQuery("select count(*) from Places").uniqueResult();
        assertEquals(6, count);
        session.close();
    }

    /**
     * В таблице public.zoo всего три записи с name 'Центральный', 'Северный', 'Западный'
     */
    @Test
    public void countRowZoo() {
        Session session = HibernateSessionFactoryCreator.createSessionFactory().openSession();

        long count = (long) session.createQuery("select count(*) from  Zoo where name in ('Центральный', 'Северный', 'Западный') ").uniqueResult();
        assertEquals(3, count);
        session.close();

    }
}
