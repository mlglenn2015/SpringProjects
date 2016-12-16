package com.example.project.common.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * JPA Entity for the SAMPLE table.
 *
 * @author mlglenn.
 */
@Entity
@Table(name = "SAMPLE")
public class SampleEntity implements Serializable {

    private static final long serialVersionUID = 6375132574786208156L;

    @Id
    @Column(name = "INDEX", nullable = false)
    private Long index;

    @Basic
    @Column(name = "COLUMN1", nullable = true, length = 25)
    private String column1;

    @Basic
    @Column(name = "COLUMN2", nullable = true, length = 25)
    private String column2;

    protected SampleEntity() {
        // no-args constructor required by JPA spec
        // this one is protected since it shouldn't be used directly
    }

    public SampleEntity(Long index, String column1, String column2) {
        this.index = index;
        this.column1 = column1;
        this.column2 = column2;
    }

    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
    }

    public String getColumn1() {
        return column1;
    }

    public void setColumn1(String column1) {
        this.column1 = column1;
    }

    public String getColumn2() {
        return column2;
    }

    public void setColumn2(String column2) {
        this.column2 = column2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SampleEntity that = (SampleEntity) o;

        if (!getIndex().equals(that.getIndex())) return false;
        if (getColumn1() != null ? !getColumn1().equals(that.getColumn1()) : that.getColumn1() != null) return false;
        return getColumn2() != null ? getColumn2().equals(that.getColumn2()) : that.getColumn2() == null;

    }

    @Override
    public int hashCode() {
        int result = getIndex().hashCode();
        result = 31 * result + (getColumn1() != null ? getColumn1().hashCode() : 0);
        result = 31 * result + (getColumn2() != null ? getColumn2().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SampleEntity{" +
                "index=" + index +
                ", column1='" + column1 + '\'' +
                ", column2='" + column2 + '\'' +
                '}';
    }
}
