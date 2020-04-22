package com.prottonne.jpa.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@Entity
@Table(name = "root_entity")
@NamedQueries({
    @NamedQuery(name = "RootEntity.findAll", query = "SELECT e FROM RootEntity e")})
public class RootEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "guid")
    private Integer guid;

    @Basic(optional = false)
    @NotNull
    @Column(name = "created")
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private LocalDateTime created;

    @Basic(optional = false)
    @NotNull
    @Column(name = "updated")
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private LocalDateTime updated;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "oneEntity", fetch = FetchType.LAZY)
    @JsonManagedReference
    private OneEntity oneEntity;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "guid", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<ManyEntity> manyEntity;

    public RootEntity() {
    }

    public RootEntity(Integer guid) {
        this.guid = guid;
    }

    public RootEntity(Integer guid, LocalDateTime created, LocalDateTime updated) {
        this.guid = guid;
        this.created = created;
        this.updated = updated;
    }

    public Integer getGuid() {
        return guid;
    }

    public void setGuid(Integer guid) {
        this.guid = guid;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public OneEntity getOneEntity() {
        return oneEntity;
    }

    public void setOneEntity(OneEntity oneEntity) {
        this.oneEntity = oneEntity;
    }

    public List<ManyEntity> getManyEntity() {
        return manyEntity;
    }

    public void setManyEntity(List<ManyEntity> manyEntity) {
        this.manyEntity = manyEntity;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.guid);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RootEntity other = (RootEntity) obj;
        if (!Objects.equals(this.guid, other.guid)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RootEntity{" + "guid=" + guid + ", created=" + created + ", updated=" + updated + ", oneEntity=" + oneEntity + ", manyEntity=" + manyEntity + '}';
    }

}
