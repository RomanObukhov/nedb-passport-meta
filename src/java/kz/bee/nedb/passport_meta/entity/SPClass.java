package kz.bee.nedb.passport_meta.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@SequenceGenerator(name="seq_sp_class", sequenceName="seq_sp_class", allocationSize=10)

@Entity
@Table(name="sp_class")
public class SPClass {
    private Long id;
    private String rname;
    private String kname;
    private String ename;
    private Long tag;

    @Id
    @GeneratedValue(generator="seq_sp_class", strategy= GenerationType.SEQUENCE)
    @Column(name="id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="rname")
    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    @Column(name="kname")
    public String getKname() {
        return kname;
    }

    public void setKname(String kname) {
        this.kname = kname;
    }

    @Column(name="ename")
    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    @Column(name="tag")
    public Long getTag() {
        return tag;
    }

    public void setTag(Long tag) {
        this.tag = tag;
    }
    
    public String getNameByLang(String lang) {
        if ("kk".equals(lang)) {
            return kname;
        } else if ("en".equals(lang)) {
            return ename;
        } else {
            return rname;
        }
    }
}