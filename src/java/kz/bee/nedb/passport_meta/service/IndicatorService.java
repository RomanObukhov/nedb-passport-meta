/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.bee.nedb.passport_meta.service;

import java.util.List;
import kz.bee.nedb.passport_meta.entity.Indicator;

/**
 *
 * @author zhanat
 */
public interface IndicatorService {
    
        public List<Indicator> list();
        public Indicator select(Long id);
        public void save(Indicator indicator); 
        public void delete(Indicator indicator); 
        public void delete(Long id);
    }
