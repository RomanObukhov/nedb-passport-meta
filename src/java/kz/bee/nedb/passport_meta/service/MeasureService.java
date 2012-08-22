/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.bee.nedb.passport_meta.service;

import java.util.List;
import kz.bee.nedb.passport_meta.entity.Measure;

/**
 *
 * @author zhanat
 */
public interface MeasureService {
    public List<Measure> list();
    public Measure select(Long id);
    public void delete(Measure measure);
    public void delete(Long id);
    public void save(Measure measure);
}
