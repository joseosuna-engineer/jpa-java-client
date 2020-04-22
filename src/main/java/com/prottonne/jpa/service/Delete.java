package com.prottonne.jpa.service;

import com.prottonne.jpa.entity.ManyEntity;
import com.prottonne.jpa.entity.RootEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class Delete {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    private EntityManager entityManager;

    public Delete() {
        super();
    }

    public void deleteById(Integer guid) {

        try {

            RootEntity rootEntity = entityManager.find(RootEntity.class, guid);

            if (null != rootEntity) {
                entityManager.remove(rootEntity);
            }

        } finally {
            entityManager.flush();
            entityManager.close();
        }

    }

    public void deleteAll(String someData) {

        try {

            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<ManyEntity> criteriaQuery = criteriaBuilder.createQuery(ManyEntity.class);
            Root<ManyEntity> root = criteriaQuery.from(ManyEntity.class);

            criteriaQuery.
                    select(root).
                    where(
                            criteriaBuilder.
                                    equal(
                                            root.get("someData"), someData
                                    )
                    );

            TypedQuery<ManyEntity> typedQuery = entityManager.createQuery(criteriaQuery);

            List<ManyEntity> manyEntityList = typedQuery.getResultList();

            if (null != manyEntityList) {
                manyEntityList.forEach(manyEntity -> {
                    entityManager.remove(manyEntity);
                });
            }

        } finally {
            entityManager.flush();
            entityManager.close();
        }

    }

    public void deleteAllChildren(Integer guid) {

        try {

            RootEntity rootEntity = entityManager.find(RootEntity.class, guid);

            if (null != rootEntity) {
                List<ManyEntity> manyEntityList = rootEntity.getManyEntity();

                if (null != manyEntityList) {
                    manyEntityList.forEach(manyEntity -> {
                        entityManager.remove(manyEntity);
                    });
                }
            }

        } finally {
            entityManager.flush();
            entityManager.close();
        }

    }

}
