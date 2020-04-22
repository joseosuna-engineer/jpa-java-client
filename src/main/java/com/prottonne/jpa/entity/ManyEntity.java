package com.prottonne.jpa.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "many_entity")
@NamedQueries({
    @NamedQuery(name = "ManyEntity.findAll", query = "SELECT e FROM ManyEntity e")})
public class ManyEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "many_entity_id_seq")
    @SequenceGenerator(name = "many_entity_id_seq", sequenceName = "many_entity_id_seq")
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Size(max = 50)
    @Column(name = "someData")
    private String someData;

    @JoinColumn(name = "guid", referencedColumnName = "guid")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private RootEntity guid;

    public ManyEntity() {
    }

    public ManyEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSomeData() {
        return someData;
    }

    public void setSomeData(String someData) {
        this.someData = someData;
    }

    public RootEntity getGuid() {
        return guid;
    }

    public void setGuid(RootEntity guid) {
        this.guid = guid;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + Objects.hashCode(this.id);
        hash = 73 * hash + Objects.hashCode(this.guid);
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
        final ManyEntity other = (ManyEntity) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ManyEntity{" + "id=" + id + ", someData=" + someData + '}';
    }

}
