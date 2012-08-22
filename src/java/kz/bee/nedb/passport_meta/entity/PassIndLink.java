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
@SequenceGenerator(name="seq_pass_ind_link", sequenceName="seq_pass_ind_link", allocationSize=1)

@Entity
@Table(name="pass_ind_link")
public class PassIndLink {
    private Long id;
    private Long indId;
    private Long passId;
    private int datatype;
    private Long measureId;
    private String dataformat;
    private int ordernum;
    private int tag;
    private String dataFormatRmessage;
    private String dataFormatKmessage;
    private String dataFormatEmessage;
    
    @Id
    @GeneratedValue(generator="seq_pass_ind_link", strategy= GenerationType.SEQUENCE)
    @Column(name="id")    
    public Long getId() {
        return id;
    }

    @Column(name="tag")
    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    @Column(name="order_num")
    public int getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(int ordernum) {
        this.ordernum = ordernum;
    }
    
    @Column(name="datatype")
    public int getDatatype() {
        return datatype;
    }

    public void setDatatype(int datatype) {
        this.datatype = datatype;
    }

    @Column(name="dataformat")
    public String getDataformat() {
        return dataformat;
    }

    public void setDataformat(String dataformat) {
        this.dataformat = dataformat;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="ind_id")
    public Long getIndId() {
        return indId;
    }

    public void setIndId(Long indId) {
        this.indId = indId;
    }

    @Column(name="measure_id")
    public Long getMeasureId() {
        return measureId;
    }

    public void setMeasureId(Long measureId) {
        this.measureId = measureId;
    }

    @Column(name="pass_id")
    public Long getPassId() {
        return passId;
    }

    public void setPassId(Long passId) {
        this.passId = passId;
    }

    @Column(name="data_format_rmessage")
    public String getDataFormatRmessage() {
        return dataFormatRmessage;
    }

    public void setDataFormatRmessage(String dataFormatRmessage) {
        this.dataFormatRmessage = dataFormatRmessage;
    }

    @Column(name="data_format_kmessage")
    public String getDataFormatKmessage() {
        return dataFormatKmessage;
    }

    public void setDataFormatKmessage(String dataFormatKmessage) {
        this.dataFormatKmessage = dataFormatKmessage;
    }

    @Column(name="data_format_emessage")
    public String getDataFormatEmessage() {
        return dataFormatEmessage;
    }

    public void setDataFormatEmessage(String dataFormatEmessage) {
        this.dataFormatEmessage = dataFormatEmessage;
    }
    
}
