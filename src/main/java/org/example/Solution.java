package org.example;


import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;


public class Solution {
    public static void main(String[] args) throws Exception {
        List<Animal> results = new ArrayList<>();
        try {
            SessionFactory sessionFactory = MySessionFactory.getSessionFactory();
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(new Animal("Tom", 5, "Cat"));
            session.saveOrUpdate(new Animal("Jerry", 3, "Mouse"));
            session.saveOrUpdate(new Animal("Spike", 7, null));
            transaction.commit();
            CriteriaBuilder criteriaBuilder = (CriteriaBuilder) session.getCriteriaBuilder();
            CriteriaQuery <Animal> criteriaQuery = criteriaBuilder.createQuery(Animal.class);
            criteriaQuery.select(criteriaQuery.from(Animal.class));
            Query <Animal> query = session.createQuery(criteriaQuery.toString());
            results = query.getResultList();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        results.forEach(System.out::println);
    }
}