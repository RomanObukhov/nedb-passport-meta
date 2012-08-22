/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.bee.nedb.passport_meta.service.impl;

import java.util.List;
import kz.bee.nedb.passport_meta.dao.MeasureDAO;
import kz.bee.nedb.passport_meta.entity.Measure;
import kz.bee.nedb.passport_meta.service.MeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author zhanat
 */
@Service
public class MeasureServiceImpl implements MeasureService{
    @Autowired
    private MeasureDAO measureDAO;
    
    @Override
    @Transactional
    public List<Measure> list() {
        return measureDAO.list();
    }

    @Override
    @Transactional
    public Measure select(Long id) {
        return measureDAO.select(id);
    }

    @Override
    @Transactional
    public void delete(Measure measure) {
        measureDAO.delete(measure);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        measureDAO.delete(id);
    }

    @Override
    @Transactional
    public void save(Measure measure) {
        measureDAO.save(measure);
    }    
}
