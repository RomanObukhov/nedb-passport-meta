/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.bee.nedb.passport_meta.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import kz.bee.nedb.passport_meta.dao.ClassItemTreeDAO;
import kz.bee.nedb.passport_meta.dao.IndicatorDAO;
import kz.bee.nedb.passport_meta.dao.OrgTypeIndLinkDAO;
import kz.bee.nedb.passport_meta.dao.PassIndLinkDAO;
import kz.bee.nedb.passport_meta.dao.PassportDAO;
import kz.bee.nedb.passport_meta.entity.ClassItemTree;
import kz.bee.nedb.passport_meta.entity.Indicator;
import kz.bee.nedb.passport_meta.entity.OrgTypeIndLink;
import kz.bee.nedb.passport_meta.entity.PassIndLink;
import kz.bee.nedb.passport_meta.entity.Passport;
import kz.bee.nedb.passport_meta.service.OrgTypeIndLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author zhanat
 */
@Service
public class OrgTypeIndLinkServiceImpl implements OrgTypeIndLinkService {

    @Autowired
    private OrgTypeIndLinkDAO orgTypeIndLinkDAO;
    @Autowired
    private ClassItemTreeDAO classItemTreeDAO;
    @Autowired
    private PassIndLinkDAO passIndLinkDAO;
    @Autowired
    private PassportDAO passportDAO;
    @Autowired
    private IndicatorDAO indicatorDAO;

    @Override
    @Transactional
    public List<OrgTypeIndLink> list() {
        return orgTypeIndLinkDAO.list();
    }

    @Override
    @Transactional
    public OrgTypeIndLink select(Long id) {
        return orgTypeIndLinkDAO.select(id);
    }

    @Override
    @Transactional
    public void delete(OrgTypeIndLink orgTypeIndLink) {
        orgTypeIndLinkDAO.delete(orgTypeIndLink);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        orgTypeIndLinkDAO.delete(id);
    }

    @Override
    @Transactional
    public void save(OrgTypeIndLink orgTypeIndLink) {
        orgTypeIndLinkDAO.save(orgTypeIndLink);
    }

    @Override
    @Transactional
    public void edit(Long passIndLinkId, Map<String, String[]> params) {

        HashMap<Long, ClassItemTree> classHashMap = new HashMap<Long, ClassItemTree>();
        for (ClassItemTree classItemTree : classItemTreeDAO.list(80L)) {
            classHashMap.put(classItemTree.getId(), classItemTree);
        }

        orgTypeIndLinkDAO.clear(passIndLinkId);

        for (Map.Entry<String, String[]> entry : params.entrySet()) {
            String name = entry.getKey();
            if (name.startsWith("field")) {

                String cit = "field";
                name = name.substring(cit.length() + 1);

                Long classItemTreeId;
                int pos = name.indexOf('-');
                if (pos == -1) {
                    try {
                        classItemTreeId = Long.valueOf(name);
                        name = "";
                    } catch (Exception ex) {
                        classItemTreeId = null;
                    }
                } else {
                    try {
                        classItemTreeId = Long.valueOf(name.substring(0, pos));
                        name = name.substring(pos + 1);
                    } catch (Exception ex) {
                        classItemTreeId = null;
                    }
                }

                ClassItemTree classItemTree = classHashMap.get(classItemTreeId);
                if (classItemTree == null) {
                    continue;
                }

                OrgTypeIndLink orgTypeIndLink = new OrgTypeIndLink();
                orgTypeIndLink.setPassIndLinkId(passIndLinkId);
                orgTypeIndLink.setClassItemTreeId(classItemTreeId);
                orgTypeIndLinkDAO.save(orgTypeIndLink);

            }

        }

    }

    @Override
    @Transactional
    public String toTable() {

        TreeMap<String, ClassItemTree> classHashMap = new TreeMap<String, ClassItemTree>();
        for (ClassItemTree classItemTree : classItemTreeDAO.list(80L)) {
            classHashMap.put(classItemTree.getCode(), classItemTree);
        }
        List<ClassItemTree> classItemTrees = new ArrayList<ClassItemTree>();
        for (ClassItemTree classItemTree : classHashMap.values()) {
            classItemTrees.add(classItemTree);
        }

        List<PassIndLink> passIndLinks = passIndLinkDAO.list();

        StringBuilder stringBuilder = new StringBuilder("<table class=\"datatable\"><caption>Таблица элементов</caption><tr>")
                .append("<thead><th>Связь (Паспорт - Показатель)</th>");
        for (ClassItemTree classItemTree : classItemTrees) {
            stringBuilder.append("<th>")
                    .append(classItemTree.getCode())
                    .append("</th>");
        }
        stringBuilder.append("<th>Действие</th><thead>");

        for (PassIndLink passIndLink : passIndLinks) {
            Passport passport = passportDAO.select(passIndLink.getPassId());
            Indicator indicator = indicatorDAO.select(passIndLink.getIndId());
//            List<OrgTypeIndLink> orgTypeIndLinks = orgTypeIndLinkDAO.list(passIndLink.getId());


//            HashMap<Long, OrgTypeIndLink> orgTypeIndLinkMap = new HashMap<Long, OrgTypeIndLink>();
//            for (OrgTypeIndLink orgTypeIndLink: orgTypeIndLinks) {
//                orgTypeIndLinkMap.put(orgTypeIndLink.getClassItemTreeId(), orgTypeIndLink);
//            }

            HashSet<Long> orgTypeIndLinkSet = new HashSet<Long>();
            for (OrgTypeIndLink orgTypeIndLink : orgTypeIndLinkDAO.list(passIndLink.getId())) {
                orgTypeIndLinkSet.add(orgTypeIndLink.getClassItemTreeId());
            }

            stringBuilder
                    .append("<form action=\"edit-org-type-ind-link\" method=\"post\"><tr>")
                    .append("<input type=\"hidden\" value=\"")
                    .append(passIndLink.getId())
                    .append("\" name=\"passIndLinkId\" />")
                    .append("<td>")
                    .append(passport.getRname())
                    .append("&nbsp;&raquo;&nbsp;")
                    .append(indicator.getRname())
                    .append("</td>");

            for (ClassItemTree classItemTree : classItemTrees) {
                stringBuilder.append("<td>")
                        .append("<input type=\"checkbox\" name=\"field-")
                        .append(classItemTree.getId())
                        .append("\" ");

                if (orgTypeIndLinkSet.contains(classItemTree.getId())) {
                    stringBuilder.append("checked ");
                }

                stringBuilder.append("/></td>");
            }

            stringBuilder
                    .append("<td><input type=\"submit\" value=\"save\"/></td>")
                    .append("</tr></form>");
        }

        return stringBuilder.append("</table>").toString();
    }
}
