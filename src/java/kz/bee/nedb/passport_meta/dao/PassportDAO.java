/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.bee.nedb.passport_meta.dao;

import java.util.List;
import kz.bee.nedb.passport_meta.entity.Passport;

/**
 *
 * @author zhanat
 */
public interface PassportDAO {
    
    public List<Passport> list(); 
    public Passport select(Long id);
    public void save(Passport passport);
    public void delete(Long id);
    public void delete(Passport passport);
    
}
