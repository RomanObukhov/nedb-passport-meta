package kz.bee.nedb.passport_meta.entity.wrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kz.bee.nedb.passport_meta.entity.Indicator;
import kz.bee.nedb.passport_meta.entity.Measure;
import kz.bee.nedb.passport_meta.entity.PassIndLink;
import kz.bee.nedb.passport_meta.entity.Passport;

public class PassIndLinkWrapper extends PassIndLink{
    private Passport passport = null;
    private Indicator indicator = null;
    private Measure measure = null;    

    public static List<PassIndLinkWrapper> wrapList(List<PassIndLink> passIndLinks, List<Passport> passports, List<Indicator> indicators, List<Measure> measures) {
        if ((passIndLinks == null) || passIndLinks.isEmpty()) {
            return null;
        } else {
            HashMap<Long, Passport> passportMap = new HashMap<Long, Passport>();
            for (Passport passport: passports) {
                passportMap.put(passport.getId(), passport);
            }
            HashMap<Long, Indicator> indicatorMap = new HashMap<Long, Indicator>();
            for (Indicator indicator: indicators) {
                indicatorMap.put(indicator.getId(), indicator);
            }
            HashMap<Long, Measure> measureMap = new HashMap<Long, Measure>();
            for (Measure measure: measures) {
                measureMap.put(measure.getId(), measure);
            }
            
            ArrayList<PassIndLinkWrapper> passIndLinkWrappers = new ArrayList<PassIndLinkWrapper>();
            for (PassIndLink passIndLink: passIndLinks) {
                passIndLinkWrappers.add(new PassIndLinkWrapper(passIndLink, 
                        passportMap.get(passIndLink.getPassId()),
                        indicatorMap.get(passIndLink.getIndId()),
                        measureMap.get(passIndLink.getMeasureId())));
            }
            
            return passIndLinkWrappers;
        }
    }
    
    public String toOption(Long selectedId) {
        
        StringBuilder stringBuilder = new StringBuilder("<option ")
                .append(getId().equals(selectedId) ? " selected " : "")                
                .append(" value=\"")
                .append(getId() == null ? "" : getId().toString())
                .append("\">");
        
        stringBuilder
                .append(passport.getRname())
                .append("&nbsp;&nbsp;|&nbsp;&nbsp;")
                .append(indicator.getRname())
                .append("&nbsp;&nbsp;|&nbsp;&nbsp;")
                .append(measure!=null ? measure.getRname() : "")
                .append("</option>");
        
        return stringBuilder.toString();
    }
    
    public PassIndLinkWrapper(PassIndLink passIndLink) {
        super();
        this.setId(passIndLink.getId());
        this.setPassId(passIndLink.getPassId());
        this.setIndId(passIndLink.getIndId());
        this.setMeasureId(passIndLink.getMeasureId());
        this.setDatatype(passIndLink.getDatatype());
        this.setDataformat(passIndLink.getDataformat());
        this.setOrdernum(passIndLink.getOrdernum());
        this.setDataFormatEmessage(passIndLink.getDataFormatEmessage());
        this.setDataFormatRmessage(passIndLink.getDataFormatRmessage());
        this.setDataFormatKmessage(passIndLink.getDataFormatKmessage());
    }
    
    public PassIndLinkWrapper(PassIndLink passIndLink, Passport passport, Indicator indicator, Measure measure) {
        super();
        this.setId(passIndLink.getId());
        this.setPassId(passIndLink.getPassId());
        this.setIndId(passIndLink.getIndId());
        this.setMeasureId(passIndLink.getMeasureId());
        this.setDatatype(passIndLink.getDatatype());
        this.setDataformat(passIndLink.getDataformat());
        this.setOrdernum(passIndLink.getOrdernum());
        this.setDataFormatEmessage(passIndLink.getDataFormatEmessage());
        this.setDataFormatRmessage(passIndLink.getDataFormatRmessage());
        this.setDataFormatKmessage(passIndLink.getDataFormatKmessage());
        this.passport = passport;
        this.indicator = indicator;
        this.measure = measure;
    }

    public Measure getMeasure() {
        return measure;
    }

    public void setMeasure(Measure measure) {
        this.measure = measure;
    }

    public Indicator getIndicator() {
        return indicator;
    }

    public void setIndicator(Indicator indicator) {
        this.indicator = indicator;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }
    
}