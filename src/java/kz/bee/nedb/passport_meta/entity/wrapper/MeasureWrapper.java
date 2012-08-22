/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.bee.nedb.passport_meta.entity.wrapper;

import java.util.ArrayList;
import java.util.List;
import kz.bee.nedb.passport_meta.entity.Measure;

/**
 *
 * @author zhanat
 */
public class MeasureWrapper extends Measure{
    
    public static List<MeasureWrapper> wrapList(List<Measure> measures) {
        if ((measures == null) || measures.isEmpty()) {
            return null;
        } else {
            ArrayList<MeasureWrapper> measureWrappers = new ArrayList<MeasureWrapper>();
            for (Measure measure: measures) {
                measureWrappers.add(new MeasureWrapper(measure));
            }
            
            return measureWrappers;
        }
    }
    
    public String toOption(Long selectedId) {
        StringBuilder stringBuilder = new StringBuilder("<option ")
                .append(getId().equals(selectedId) ? " selected " : "")                
                .append(" value=\"")
                .append(getId() == null ? "" : getId().toString())
                .append("\">");
        
        stringBuilder
                .append(getRname())
                .append("</option>");
        
        return stringBuilder.toString();
    }
        
    public MeasureWrapper(Measure measure) {
        super();
        
        this.setId(measure.getId());
        this.setRname(measure.getRname());
        this.setKname(measure.getKname());
        this.setEname(measure.getEname());
        this.setShortRname(measure.getRname());
        this.setShortKname(measure.getKname());
        this.setShortEname(measure.getEname());
        this.setMultiplier(measure.getMultiplier());
    }
    
    
}
