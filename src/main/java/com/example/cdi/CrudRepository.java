package com.example.cdi;

import com.example.domain.Persistable;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CrudRepository<E extends Persistable<ID>, ID extends Serializable> {
    EntityManager entityManager();

    private Class<E> entityClass() {
        Type genericSuperClass = getClass().getGenericSuperclass();

        ParameterizedType parametrizedType = null;
        while (parametrizedType == null) {
            if ((genericSuperClass instanceof ParameterizedType)) {
                parametrizedType = (ParameterizedType) genericSuperClass;
            } else {
                genericSuperClass = ((Class<?>) genericSuperClass).getGenericSuperclass();
            }
        }

        return  (Class<E>) parametrizedType.getActualTypeArguments()[0];
    }

    default List<E> findAll() {
        CriteriaBuilder cb = this.entityManager().getCriteriaBuilder();
        // create query
        CriteriaQuery<E> query = cb.createQuery(this.entityClass());
        // set the root class
        Root<E> root = query.from(this.entityClass());
        //perform query
        return this.entityManager().createQuery(query).getResultList();
    }

    default E findById(ID id) {
        E entity = null;
        try {
            entity = this.entityManager().find(this.entityClass(), id);
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        return entity;
    }


    default Optional<E> findOptionalById(ID id) {
        E entity = null;
        try {
            entity = this.entityManager().find(this.entityClass(), id);
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(entity);
    }

    @Transactional
    default E save(E entity) {
        if (entity.getId() != null) {
            return this.entityManager().merge(entity);
        } else {
            this.entityManager().persist(entity);
            return entity;
        }
    }

    @Transactional
    default void saveAll(Collection<E> entities) {
        entities.forEach(this::save);
    }

    @Transactional
    default void deleteById(ID id) {
        E entity = this.findById(id);
        this.entityManager().remove(entity);
    }

    @Transactional
    default void deleteAll() {
        CriteriaBuilder cb = this.entityManager().getCriteriaBuilder();
        // create query for deletion
        CriteriaDelete<E> query = cb.createCriteriaDelete(this.entityClass());
        // set the root class
        Root<E> root = query.from(this.entityClass());
        //perform query
        this.entityManager().createQuery(query).executeUpdate();
    }

}