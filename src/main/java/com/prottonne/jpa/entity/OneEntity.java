package com.prottonne.jpa.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "one_entity")
@NamedQueries({
    @NamedQuery(name = "OneEntity.findAll", query = "SELECT e FROM OneEntity e")})
public class OneEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "guid")
    private Integer guid;

    @JoinColumn(name = "guid", referencedColumnName = "guid", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private RootEntity rootEntity;

    @Column(name = "integer_data")
    private Integer IntegerData;

    @Column(name = "boolean_data")
    private Boolean BooleanData;

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "double_data")
    private Double DoubleData;

    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "email")
    private String email;

    @Column(name = "big_integer_data")
    private BigInteger BigIntegerData;

    @Column(name = "character_data")
    private Character CharacterData;

    public OneEntity() {
    }

    public OneEntity(Integer guid) {
        this.guid = guid;
    }

    public Integer getGuid() {
        return guid;
    }

    public void setGuid(Integer guid) {
        this.guid = guid;
    }

    public RootEntity getRootEntity() {
        return rootEntity;
    }

    public void setRootEntity(RootEntity rootEntity) {
        this.rootEntity = rootEntity;
    }

    public Integer getIntegerData() {
        return IntegerData;
    }

    public void setIntegerData(Integer IntegerData) {
        this.IntegerData = IntegerData;
    }

    public Boolean getBooleanData() {
        return BooleanData;
    }

    public void setBooleanData(Boolean BooleanData) {
        this.BooleanData = BooleanData;
    }

    public Double getDoubleData() {
        return DoubleData;
    }

    public void setDoubleData(Double DoubleData) {
        this.DoubleData = DoubleData;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigInteger getBigIntegerData() {
        return BigIntegerData;
    }

    public void setBigIntegerData(BigInteger BigIntegerData) {
        this.BigIntegerData = BigIntegerData;
    }

    public Character getCharacterData() {
        return CharacterData;
    }

    public void setCharacterData(Character CharacterData) {
        this.CharacterData = CharacterData;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.guid);
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
        final OneEntity other = (OneEntity) obj;
        if (!Objects.equals(this.guid, other.guid)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OneEntity{" + "guid=" + guid + ", IntegerData=" + IntegerData + ", BooleanData=" + BooleanData + ", DoubleData=" + DoubleData + ", email=" + email + ", BigIntegerData=" + BigIntegerData + ", CharacterData=" + CharacterData + '}';
    }

}
