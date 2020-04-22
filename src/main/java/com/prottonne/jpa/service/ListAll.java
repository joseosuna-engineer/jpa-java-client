package com.prottonne.jpa.service;

import com.prottonne.jpa.entity.ManyEntity;
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
public class ListAll {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    private EntityManager entityManager;

    public ListAll() {
        super();
    }

    public List<ManyEntity> listChildrenById(Integer guid) {

        try {

            RootEntity rootEntity = entityManager.find(RootEntity.class, guid);
            return rootEntity.getManyEntity();

        } finally {
            entityManager.flush();
            entityManager.close();
        }

    }

    public List<RootEntity> listBySomeCriteria(String someData, String anotherData) {

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

            return typedQuery.getResultList();

        } finally {
            entityManager.flush();
            entityManager.close();
        }

    }

}
