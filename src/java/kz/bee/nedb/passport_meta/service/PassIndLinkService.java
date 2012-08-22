/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.bee.nedb.passport_meta.service;

import java.util.List;
import kz.bee.nedb.passport_meta.entity.PassIndLink;

/**
 *
 * @author zhanat
 */
public interface PassIndLinkService {
    public List<PassIndLink> list();
    public PassIndLink select(Long id);
    public void delete(PassIndLink passIndLink);
    public void delete(Long id);
    public void save(PassIndLink passIndLink);
}
