/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.bee.nedb.passport_meta.entity;

import javax.persistence.*;

/**
 *
 * @author zhanat
 */
@SequenceGenerator(name="seq_measure", sequenceName="seq_measure", allocationSize=1)

@Entity
@Table(name="measure")
public class Measure {
    private Long id;
    private String rname;
    private String kname;
    private String ename;
    private String shortRname;
    private String shortKname;
    private String shortEname;
    private Double multiplier;
    private Long parId;

    public Measure() {
        
    }
    
    @Column(name="ename")
    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    @Id
    @GeneratedValue(generator="seq_measure", strategy= GenerationType.SEQUENCE)
    @Column(name="id")    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="kname")
    public String getKname() {
        return kname;
    }

    public void setKname(String kname) {
        this.kname = kname;
    }

    @Column(name="multiplier")
    public Double getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(Double multiplier) {
        this.multiplier = multiplier;
    }

    @Column(name="par_id")
    public Long getParId() {
        return parId;
    }

    public void setParId(Long parId) {
        this.parId = parId;
    }

    @Column(name="rname")
    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    @Column(name="short_ename")
    public String getShortEname() {
        return shortEname;
    }

    public void setShortEname(String shortEname) {
        this.shortEname = shortEname;
    }

    @Column(name="short_kname")
    public String getShortKname() {
        return shortKname;
    }

    public void setShortKname(String shortKname) {
        this.shortKname = shortKname;
    }

    @Column(name="short_rname")
    public String getShortRname() {
        return shortRname;
    }

    public void setShortRname(String shortRname) {
        this.shortRname = shortRname;
    }
        
}
