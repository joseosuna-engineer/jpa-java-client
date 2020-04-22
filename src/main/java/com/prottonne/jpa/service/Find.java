package com.prottonne.jpa.service;

import com.prottonne.jpa.entity.RootEntity;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class Find {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    private EntityManager entityManager;

    public Find() {
        super();
    }

    public RootEntity findById(Integer guid) {

        try {

            return entityManager.find(RootEntity.class, guid);

        } finally {
            entityManager.flush();
            entityManager.close();
        }

    }

    public RootEntity findBySomeCriteria(String someData, String anotherData) {

        try {

            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<RootEntity> criteriaQuery = criteriaBuilder.createQuery(RootEntity.class);
            Root<RootEntity> root = criteriaQuery.from(RootEntity.class);

            criteriaQuery.
                    select(root).
                    where(
                            criteriaBuilder.
                                    and(
                                            criteriaBuilder.
                                                    equal(root.
                                                            get("oneEntity").get("someData"), someData),
                                            criteriaBuilder.
                                                    equal(root.
                                                            get("manyEntity").get("anotherData"), anotherData)
                                    )
                    );

            TypedQuery<RootEntity> typedQuery = entityManager.createQuery(criteriaQuery);

            List<RootEntity> rootEntityList = typedQuery.getResultList();

            if (null != rootEntityList) {
                return rootEntityList.get(0);
            } else {
                return null;
            }

        } finally {
            entityManager.flush();
            entityManager.close();
        }

    }

}
