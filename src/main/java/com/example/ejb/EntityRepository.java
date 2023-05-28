package com.example.ejb;

import com.example.domain.Persistable;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public abstract class EntityRepository<E extends Persistable<ID>, ID extends Serializable> {
    abstract EntityManager getEntityManager();

    private Class<E> entityClass;

    public EntityRepository() {
        Type genericSuperClass = getClass().getGenericSuperclass();

        ParameterizedType parametrizedType = null;
        while (parametrizedType == null) {
            if ((genericSuperClass instanceof ParameterizedType)) {
                parametrizedType = (ParameterizedType) genericSuperClass;
            } else {
                genericSuperClass = ((Class<?>) genericSuperClass).getGenericSuperclass();
            }
        }

        this.entityClass = (Class<E>) parametrizedType.getActualTypeArguments()[0];
    }

    public List<E> findAll() {
        CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
        // create query
        CriteriaQuery<E> query = cb.createQuery(this.entityClass);
        // set the root class
        Root<E> root = query.from(this.entityClass);
        //perform query
        return this.getEntityManager().createQuery(query).getResultList();
    }

    public E findById(ID id) {
        E entity = null;
        try {
            entity = this.getEntityManager().find(this.entityClass, id);
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        return entity;
    }


    public Optional<E> findOptionalById(ID id) {
        E entity = null;
        try {
            entity = this.getEntityManager().find(this.entityClass, id);
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(entity);
    }

    public E save(E entity) {
        if (entity.getId() != null) {
            return this.getEntityManager().merge(entity);
        } else {
            this.getEntityManager().persist(entity);
            return entity;
        }
    }

    public void saveAll(Collection<E> entities) {
        entities.forEach(this::save);
    }

    public void deleteById(ID id) {
        E entity = this.findById(id);
        this.getEntityManager().remove(entity);
    }

    public void deleteAll() {
        CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
        // create query for deletion
        CriteriaDelete<E> query = cb.createCriteriaDelete(this.entityClass);
        // set the root class
        Root<E> root = query.from(this.entityClass);
        //perform query
        this.getEntityManager().createQuery(query).executeUpdate();
    }

}