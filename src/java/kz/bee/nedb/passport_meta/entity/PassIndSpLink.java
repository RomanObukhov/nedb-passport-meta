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
@SequenceGenerator(name="seq_pass_ind_sp_link", sequenceName="seq_pass_ind_sp_link", allocationSize=1)

@Entity
@Table(name="pass_ind_sp_link")
public class PassIndSpLink {
    private Long id;
    private Long parId;
    private Long passIndLinkId;
    private Long spId;
    private Boolean orient;
    
    public PassIndSpLink() {
        
    }
    
    @Id
    @GeneratedValue(generator="seq_pass_ind_sp_link", strategy= GenerationType.SEQUENCE)
    @Column(name="id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="par_id")
    public Long getParId() {
        return parId;
    }

    public void setParId(Long parId) {
        this.parId = parId;
    }

    @Column(name="pass_ind_link_id")
    public Long getPassIndLinkId() {
        return passIndLinkId;
    }

    public void setPassIndLinkId(Long passIndLinkId) {
        this.passIndLinkId = passIndLinkId;
    }

    @Column(name="sp_id")
    public Long getSpId() {
        return spId;
    }

    public void setSpId(Long spId) {
        this.spId = spId;
    }

    @Column(name="orient")
    public Boolean getOrient() {
        return orient;
    }

    public void setOrient(Boolean orient) {
        this.orient = orient;
    }
    
}
