package kz.bee.nedb.passport_meta.entity;

import javax.persistence.*;

/**
 * The persistent class for the passport_object database table.
 *
 */
@SequenceGenerator(name = "seq_indicator", sequenceName = "seq_indicator", allocationSize=1)

@Entity
@Table(name = "indicator")
public class Indicator {

    private Long id;
    private String ename;
    private String kname;   
    private String rname;
//    private Long passId;

    public Indicator() {
        
    }
    
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_indicator")    
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    @Column(name="pass_id")    
//    public Long getPassId() {
//        return passId;
//    }
//
//    public void setPassId(Long passId) {
//        this.passId = passId;
//    }

    @Column(name="ename")    
    public String getEname() {
        return this.ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    @Column(name="kname")    
    public String getKname() {
        return this.kname;
    }

    public void setKname(String kname) {
        this.kname = kname;
    }

    @Column(name="rname")    
    public String getRname() {
        return this.rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }
}
