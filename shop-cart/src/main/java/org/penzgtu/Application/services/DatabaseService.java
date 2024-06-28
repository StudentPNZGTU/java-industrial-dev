package org.penzgtu.Application.services;

import org.springframework.stereotype.Service;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;

@Service
public class DatabaseService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Object[]> executeQuery(String sql) {
        Query query = entityManager.createNativeQuery(sql);
        return query.getResultList();
    }

    public List<String> executeQueryForStrings(String sql) {
        Query query = entityManager.createNativeQuery(sql);
        return query.getResultList();
    }

    public int executeUpdate(String sql) {
        Query query = entityManager.createNativeQuery(sql);
        return query.executeUpdate();
    }
}