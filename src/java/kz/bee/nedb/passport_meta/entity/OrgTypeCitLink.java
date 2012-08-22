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
@SequenceGenerator(name="seq_org_type_cit_link", sequenceName="seq_org_type_cit_link", allocationSize=1)

@Entity
@Table(name="org_type_cit_link")
public class OrgTypeCitLink {
    private Long id;
    private Long classItemTreeId;
    private Long otClassItemTreeId;
    
    @Id
    @GeneratedValue(generator="seq_org_type_cit_link", strategy= GenerationType.SEQUENCE)
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

    @Column(name="ot_class_item_tree_id")
    public Long getOtClassItemTreeId() {
        return otClassItemTreeId;
    }

    public void setOtClassItemTreeId(Long otClassItemTreeId) {
        this.otClassItemTreeId = otClassItemTreeId;
    }
        
}
