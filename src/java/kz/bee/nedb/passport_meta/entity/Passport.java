package kz.bee.nedb.passport_meta.entity;

import javax.persistence.*;

/**
 * The persistent class for the passport_object database table.
 *
 */
@SequenceGenerator(name = "seq_passport", sequenceName = "seq_passport", allocationSize = 1)
@Entity
@Table(name = "passport")
public class Passport {

    private Long id;
    private Long parId;
    private String ename;
    private String kname;
    private String rname;
    private Long passtype;

    public Passport() {
        this.parId = 0L;
    }

    @Id
    @Column(name="id")
    @GeneratedValue(generator="seq_passport", strategy=GenerationType.SEQUENCE)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "par_id")
    public Long getParId() {
        return parId;
    }

    public void setParId(Long parId) {
        this.parId = parId;
    }

    @Column(name = "ename")
    public String getEname() {
        return this.ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }
    
    @Column(name = "kname")
    public String getKname() {
        return this.kname;
    }

    public void setKname(String kname) {
        this.kname = kname;
    }

    @Column(name = "rname")    
    public String getRname() {
        return this.rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    @Column(name = "pass_type")    
    public Long getPasstype() {
        return passtype;
    }

    public void setPasstype(Long passtype) {
        this.passtype = passtype;
    }
}