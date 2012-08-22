/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.bee.nedb.passport_meta.entity.wrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import kz.bee.nedb.passport_meta.entity.SPClass;

/**
 *
 * @author zhanat
 */
public class SPClassWrapper extends SPClass{
       
    public static List<SPClassWrapper> wrapList(List<SPClass> spClasses) {
        if ((spClasses == null) || spClasses.isEmpty()) {
            return null;
        } else {
            ArrayList<SPClassWrapper> spClassWrappers = new ArrayList<SPClassWrapper>();
            for (SPClass spClass: spClasses) {
                spClassWrappers.add(new SPClassWrapper(spClass));
            }
            
            return spClassWrappers;
        }
    }
    
    public static List<SPClass> sort(List<SPClass> spClasses) {
        TreeMap<Long, SPClass> sortedMap = new TreeMap<Long, SPClass>();
        for (SPClass spClass: spClasses) {
            sortedMap.put(spClass.getId(), spClass);
        }
        
        return new ArrayList<SPClass>(sortedMap.values());
    }
    
    public String toOption(Long selectedId) {
        StringBuilder stringBuilder = new StringBuilder("<option ")
                .append(getId().equals(selectedId) ? " selected " : "")                
                .append(" value=\"")
                .append(getId() != null ? getId().toString() : "")
                .append("\">");
        
        stringBuilder
                .append(getRname())
                .append("</option>");
        
        return stringBuilder.toString();
    }
        
    public SPClassWrapper(SPClass spClass) {
        super();
        
        this.setId(spClass.getId());
        this.setRname(spClass.getRname());
        this.setKname(spClass.getKname());
        this.setEname(spClass.getEname());
    }
}
