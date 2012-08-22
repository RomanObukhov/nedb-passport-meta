package kz.bee.nedb.passport_meta.entity.wrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kz.bee.nedb.passport_meta.entity.ClassItemTree;
import kz.bee.nedb.passport_meta.entity.OrgTypeCitLink;

public class OrgTypeCitLinkWrapper extends OrgTypeCitLink {

    private ClassItemTree classItemTree = null;
    private ClassItemTree otClassItemTree = null;

    public static List<OrgTypeCitLinkWrapper> wrapList(List<OrgTypeCitLink> orgTypeCitLinks, List<ClassItemTree> classItemTrees, List<ClassItemTree> otClassItemTrees) {
        if ((orgTypeCitLinks == null) || orgTypeCitLinks.isEmpty()) {
            return null;
        } else {
            HashMap<Long, ClassItemTree> classItemTreeMap = new HashMap<Long, ClassItemTree>();
            for (ClassItemTree classItemTree : classItemTrees) {
                classItemTreeMap.put(classItemTree.getId(), classItemTree);
            }
            HashMap<Long, ClassItemTree> otClassItemTreeMap = new HashMap<Long, ClassItemTree>();
            for (ClassItemTree classItemTree : otClassItemTrees) {
                otClassItemTreeMap.put(classItemTree.getId(), classItemTree);
            }

            ArrayList<OrgTypeCitLinkWrapper> orgTypeCitLinkWrappers = new ArrayList<OrgTypeCitLinkWrapper>();
            for (OrgTypeCitLink orgTypeCitLink : orgTypeCitLinks) {
                orgTypeCitLinkWrappers.add(new OrgTypeCitLinkWrapper(orgTypeCitLink,
                        classItemTreeMap.get(orgTypeCitLink.getClassItemTreeId()),
                        otClassItemTreeMap.get(orgTypeCitLink.getOtClassItemTreeId())));
            }

            return orgTypeCitLinkWrappers;
        }
    }

    public String toTable(int level, String deleteString) {
        StringBuilder stringBuilder = new StringBuilder("<tr>");
        
        stringBuilder
                .append("<td>");
        for (int i = 0; i < level; i++) {
            stringBuilder.append("&nbsp;&nbsp;");
        }
        
        if (level > 0) {
            stringBuilder.append("|-&nbsp;");
        }
        
        stringBuilder
                .append(getId() == null ? "" : getId().toString())
                .append("</td><td>")
                .append("<a href=\"?id=")
                .append(getId() == null ? "" : getId().toString())
                .append("\">")
                .append(getClassItemTree().getRname())
                .append("</a>")
                .append("</td><td><a href=\"?id=")
                .append(getId() == null ? "" : getId().toString())
                .append("\">")
                .append(getOtClassItemTree().getRname())
                .append("</a></td><td><a href=\"delete-org-type-cit-link?id=")
                .append(getId() == null ? "" : getId().toString())
                .append("\">")
                .append(deleteString)
                .append("</a></td></tr>");
                              
        return stringBuilder.toString();
    }

    public OrgTypeCitLinkWrapper(OrgTypeCitLink orgTypeCitLink) {
        super();
        this.setId(orgTypeCitLink.getId());
        this.setClassItemTreeId(orgTypeCitLink.getClassItemTreeId());
        this.setOtClassItemTreeId(orgTypeCitLink.getOtClassItemTreeId());
    }

    public OrgTypeCitLinkWrapper(OrgTypeCitLink orgTypeCitLink, ClassItemTree classItemTree, ClassItemTree otClassItemTree) {
        super();
        this.setId(orgTypeCitLink.getId());
        this.setClassItemTreeId(orgTypeCitLink.getClassItemTreeId());
        this.setOtClassItemTreeId(orgTypeCitLink.getOtClassItemTreeId());
        this.classItemTree = classItemTree;
        this.otClassItemTree = otClassItemTree;
    }

    public ClassItemTree getClassItemTree() {
        return classItemTree;
    }

    public void setClassItemTree(ClassItemTree classItemTree) {
        this.classItemTree = classItemTree;
    }

    public ClassItemTree getOtClassItemTree() {
        return otClassItemTree;
    }

    public void setOtClassItemTree(ClassItemTree otClassItemTree) {
        this.otClassItemTree = otClassItemTree;
    }
}