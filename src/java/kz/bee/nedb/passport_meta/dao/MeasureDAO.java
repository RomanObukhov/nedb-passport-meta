/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.bee.nedb.passport_meta.dao;

import java.util.List;
import kz.bee.nedb.passport_meta.entity.Measure;

/**
 *
 * @author zhanat
 */
public interface MeasureDAO {
    
    public List<Measure> list();
    public Measure select(Long id);
    public void save(Measure measure);
    public void delete(Measure measure);
    public void delete(Long id);    
}
