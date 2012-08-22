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
@SequenceGenerator(name="seq_org_type_ind_link", sequenceName="seq_org_type_ind_link", allocationSize=1)

@Entity
@Table(name="org_type_ind_link")
public class OrgTypeIndLink {
    private Long id;
    private Long passIndLinkId;
    private Long classItemTreeId;    
    
    @Id
    @GeneratedValue(generator="seq_org_type_ind_link", strategy= GenerationType.SEQUENCE)
    @Column(name="id")    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="class_item_tree_id")   
    public Long getClassItemTreeId() {
        return classItemTreeId;
    }

    public void setClassItemTreeId(Long classItemTreeId) {
        this.classItemTreeId = classItemTreeId;
    }

    @Column(name="pass_ind_link_id")
    public Long getPassIndLinkId() {
        return passIndLinkId;
    }

    public void setPassIndLinkId(Long passIndLinkId) {
        this.passIndLinkId = passIndLinkId;
    }
            
}
