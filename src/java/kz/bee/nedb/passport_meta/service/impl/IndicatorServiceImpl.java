/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.bee.nedb.passport_meta.service.impl;

import java.util.List;
import kz.bee.nedb.passport_meta.dao.IndicatorDAO;
import kz.bee.nedb.passport_meta.entity.Indicator;
import kz.bee.nedb.passport_meta.service.IndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author zhanat
 */
@Service
public class IndicatorServiceImpl implements IndicatorService{
    
    @Autowired
    IndicatorDAO indicatorDAO;;     

    @Transactional
    @Override
    public List<Indicator> list() {
        return indicatorDAO.list();
    }

    @Transactional
    @Override
    public Indicator select(Long id) {
        return indicatorDAO.select(id);
    }
    
    @Transactional
    @Override
    public void save(Indicator indicator) {        
        indicatorDAO.save(indicator);
    }
    
    @Transactional
    @Override
    public void delete(Indicator indicator) {        
        indicatorDAO.delete(indicator);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        indicatorDAO.delete(id);
    }
    
}
