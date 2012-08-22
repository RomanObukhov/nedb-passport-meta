package kz.bee.nedb.passport_meta.service.impl;

import java.sql.Timestamp;
import java.util.List;
import kz.bee.nedb.passport_meta.dao.ClassItemTreeDAO;
import kz.bee.nedb.passport_meta.entity.ClassItemTree;
import kz.bee.nedb.passport_meta.entity.wrapper.ClassItemTreeWrapper;
import kz.bee.nedb.passport_meta.service.ClassItemTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClassItemTreeServiceImpl implements ClassItemTreeService {
    @Autowired
    private ClassItemTreeDAO classItemTreeDAO;
    
    private void nonTransDelete(ClassItemTree classItemTree) {
        List<ClassItemTree> children = classItemTreeDAO.list(classItemTree.getSpId(), classItemTree.getId());
        if ((children != null) && !children.isEmpty()) {
            for (ClassItemTree child: children) {
                nonTransDelete(child);
            }
        }
        
        classItemTreeDAO.delete(classItemTree);
    }

    @Override
    @Transactional
    public ClassItemTree select(Long id) {
        return classItemTreeDAO.select(id);
    }

    @Override
    @Transactional
    public List<ClassItemTree> list() {
        return classItemTreeDAO.list();
    }

    @Override
    @Transactional
    public List<ClassItemTree> list(Long spId) {
        return classItemTreeDAO.list(spId);
    }
    
    @Override
    @Transactional
    public List<ClassItemTree> list(Long spId, Long parId) {
        return classItemTreeDAO.list(spId, parId);
    }

    @Override
    @Transactional
    public void save(ClassItemTree classItemTree) {
        classItemTreeDAO.save(classItemTree);
    }

    @Override
    @Transactional
    public void save(
            Long id,
            Long parId,
            Long spId,
            String rname,
            String kname,
            String ename,
            Timestamp begDate,
            Timestamp endDate,
            String code,
            Long attr1
    ) {
        ClassItemTree classItemTree = null;
        if (id != null) {
            classItemTree = select(id);
        }
        
        if (classItemTree == null) {
            classItemTree = new ClassItemTree();
        }
        
        classItemTree.setParId(parId);
        classItemTree.setSpId(spId);
        classItemTree.setRname(rname);
        classItemTree.setKname(kname);
        classItemTree.setEname(ename);
        classItemTree.setBegDate(begDate);
        classItemTree.setEndDate(endDate);
        classItemTree.setUpdTime(new Timestamp(System.currentTimeMillis()));
        classItemTree.setCode(code);
        classItemTree.setAttr1(attr1);
        
        classItemTreeDAO.save(classItemTree);
        
        if (classItemTree.getTreeId() == null) {
            classItemTree.setTreeId(classItemTree.getId());
            classItemTreeDAO.save(classItemTree);
        }
    }

    @Override
    @Transactional
    public void delete(ClassItemTree classItemTree) {
        nonTransDelete(classItemTree);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        ClassItemTree classItemTree = classItemTreeDAO.select(id);
        if (classItemTree != null) {
            nonTransDelete(classItemTree);
        }
    }
    
    @Override
    @Transactional
    public ClassItemTree clone(Long id) {
        return classItemTreeDAO.clone(id);
    }

    @Override
    @Transactional
    public List<ClassItemTree> listOrgTypeCitLink() {
        return classItemTreeDAO.listOrgTypeCitLink();
    }
}