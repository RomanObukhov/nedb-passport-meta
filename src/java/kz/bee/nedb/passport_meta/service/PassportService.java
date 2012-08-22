/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.bee.nedb.passport_meta.service;

import java.util.List;
import kz.bee.nedb.passport_meta.entity.Passport;

/**
 *
 * @author zhanat
 */
public interface PassportService {

    public Passport select(Long id);
    public List<Passport> list();
    public void save(Passport passport);
    public void delete(Passport passport);
    public void delete(Long id);
}
