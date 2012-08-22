/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.bee.nedb.passport_meta.service;

import java.util.List;
import kz.bee.nedb.passport_meta.entity.PassIndSpLink;

/**
 *
 * @author zhanat
 */
public interface PassIndSpLinkService {
    public List<PassIndSpLink> list();
    public PassIndSpLink select(Long id);
    public void delete(PassIndSpLink passIndSpLink);
    public void delete(Long id);
    public void save(PassIndSpLink passIndSpLink);    
    
}
