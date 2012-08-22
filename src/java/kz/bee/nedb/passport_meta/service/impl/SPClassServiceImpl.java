package kz.bee.nedb.passport_meta.service.impl;

import java.util.List;
import kz.bee.nedb.passport_meta.dao.SPClassDAO;
import kz.bee.nedb.passport_meta.entity.SPClass;
import kz.bee.nedb.passport_meta.service.SPClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SPClassServiceImpl implements SPClassService {
    @Autowired
    private SPClassDAO spClassDAO;
    
    @Override
    @Transactional
    public List<SPClass> list() {
        return spClassDAO.list();
    }

    @Override
    @Transactional
    public SPClass select(Long id) {
        return spClassDAO.select(id);
    }

    @Override
    @Transactional
    public void delete(SPClass spClass) {
        spClassDAO.delete(spClass);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        spClassDAO.delete(id);
    }

    @Override
    @Transactional
    public void save(SPClass spClass) {
        spClassDAO.save(spClass);
    }
}