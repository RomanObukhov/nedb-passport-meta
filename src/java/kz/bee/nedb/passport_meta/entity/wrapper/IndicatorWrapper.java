/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.bee.nedb.passport_meta.entity.wrapper;

import java.util.ArrayList;
import java.util.List;
import kz.bee.nedb.passport_meta.entity.Indicator;

/**
 *
 * @author zhanat
 */
public class IndicatorWrapper extends Indicator{
    
    public static List<IndicatorWrapper> wrapList(List<Indicator> indicators) {
        if ((indicators == null) || indicators.isEmpty()) {
            return null;
        } else {
            ArrayList<IndicatorWrapper> indicatorWrappers = new ArrayList<IndicatorWrapper>();
            for (Indicator indicator: indicators) {
                indicatorWrappers.add(new IndicatorWrapper(indicator));
            }
            
            return indicatorWrappers;
        }
    }
    
    public String toOption(Long selectedId) {
        
        StringBuilder stringBuilder = new StringBuilder("<option ")
                .append(getId().equals(selectedId) ? " selected " : "")                
                .append(" value=\"")
                .append(getId() == null ? "" : getId().toString())
                .append("\">");                
        
        stringBuilder
                .append(getId())
                .append("&nbsp;&nbsp;|&nbsp;&nbsp;")
                .append(getRname())
                .append("</option>");
        
        return stringBuilder.toString();
    }
        
    public IndicatorWrapper(Indicator indicator) {
        super();
        
        this.setId(indicator.getId());
        this.setRname(indicator.getRname());
        this.setKname(indicator.getKname());
        this.setEname(indicator.getEname());
    }
    
}
