package com.prottonne.jpa.service;

import com.prottonne.jpa.entity.ManyEntity;
import com.prottonne.jpa.entity.OneEntity;
import com.prottonne.jpa.entity.RootEntity;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class Update {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    private EntityManager entityManager;

    public Update() {
        super();
    }

    public void updateById(Integer guid) {

        try {

            RootEntity rootEntity = entityManager.find(RootEntity.class, guid);

            if (null != rootEntity) {
                LocalDateTime now = LocalDateTime.now();
                rootEntity.setUpdated(now);

                entityManager.merge(rootEntity);
                entityManager.flush();
            }

        } finally {
            entityManager.close();
        }

    }

    public void updateAll(String someData) {

        try {

            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<OneEntity> criteriaQuery = criteriaBuilder.createQuery(OneEntity.class);
            Root<OneEntity> root = criteriaQuery.from(OneEntity.class);

            criteriaQuery.
                    select(root).
                    where(
                            criteriaBuilder.
                                    equal(
                                            root.get("someData"), someData
                                    )
                    );

            TypedQuery<OneEntity> typedQuery = entityManager.createQuery(criteriaQuery);

            List<OneEntity> oneEntityList = typedQuery.getResultList();

            if (null != oneEntityList) {
                oneEntityList.forEach(oneEntity -> {

                    oneEntity.setEmail("updated data");

                });

                entityManager.merge(oneEntityList);
                entityManager.flush();
            }

        } finally {
            entityManager.close();
        }

    }

    public void updateAllChildren(Integer guid) {

        try {

            RootEntity rootEntity = entityManager.find(RootEntity.class, guid);

            if (null != rootEntity) {
                List<ManyEntity> manyEntityList = rootEntity.getManyEntity();

                if (null != manyEntityList) {
                    manyEntityList.forEach(manyEntity -> {
                        manyEntity.setSomeData("updated data");

                    });

                    LocalDateTime now = LocalDateTime.now();
                    rootEntity.setUpdated(now);

                    entityManager.merge(rootEntity);
                    entityManager.flush();
                }
            }

        } finally {
            entityManager.close();
        }

    }

}
