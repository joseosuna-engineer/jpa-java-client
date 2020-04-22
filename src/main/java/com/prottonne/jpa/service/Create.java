package com.prottonne.jpa.service;

import com.prottonne.jpa.entity.ManyEntity;
import com.prottonne.jpa.entity.OneEntity;
import com.prottonne.jpa.entity.RootEntity;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class Create {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    private EntityManager entityManager;

    public Create() {
        super();
    }

    private Integer getGuid() {
        Query query = entityManager.createNativeQuery("SELECT nextval('root_entity_id_seq')");
        BigInteger sequence = (BigInteger) query.getSingleResult();
        return sequence.intValue();
    }

    public RootEntity create() {

        try {

            Integer guid = getGuid();
            LocalDateTime now = LocalDateTime.now();

            RootEntity rootEntity = new RootEntity(guid, now, now);

            entityManager.persist(rootEntity);
            entityManager.flush();

            return rootEntity;

        } finally {

            entityManager.close();
        }

    }

    public RootEntity createWithChild() {

        try {

            Integer guid = getGuid();
            LocalDateTime now = LocalDateTime.now();

            RootEntity rootEntity = new RootEntity(guid, now, now);

            OneEntity oneEntity = new OneEntity(guid);
            oneEntity.setBooleanData(Boolean.TRUE);

            rootEntity.setOneEntity(oneEntity);

            entityManager.persist(rootEntity);
            entityManager.flush();

            return rootEntity;

        } finally {

            entityManager.close();
        }

    }

    public RootEntity createWithChildren() {

        try {

            Integer guid = getGuid();
            LocalDateTime now = LocalDateTime.now();

            RootEntity rootEntity = new RootEntity(guid, now, now);

            List<ManyEntity> manyEntityList = new ArrayList();

            ManyEntity manyEntity1 = new ManyEntity();
            manyEntity1.setGuid(rootEntity);
            manyEntityList.add(manyEntity1);

            ManyEntity manyEntity2 = new ManyEntity();
            manyEntity2.setGuid(rootEntity);
            manyEntityList.add(manyEntity2);

            rootEntity.setManyEntity(manyEntityList);

            entityManager.persist(rootEntity);
            entityManager.flush();

            return rootEntity;

        } finally {

            entityManager.close();
        }

    }

}
