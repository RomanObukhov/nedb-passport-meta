/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.bee.nedb.passport_meta.entity.wrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import kz.bee.nedb.passport_meta.entity.PassIndSpLink;
import kz.bee.nedb.passport_meta.entity.SPClass;

/**
 *
 * @author zhanat
 */
public class PassIndSpLinkWrapper extends PassIndSpLink {
    private List<PassIndSpLinkWrapper> children = new ArrayList<PassIndSpLinkWrapper>();
    private PassIndLinkWrapper passIndLinkWrapper = null;
    private SPClass spClass = null;

    public static List<PassIndSpLinkWrapper> wrapList(List<PassIndSpLink> passIndSpLinks, List<PassIndLinkWrapper> passIndLinkWrappers, List<SPClass> spClasses) {

        if ((passIndSpLinks == null) || passIndSpLinks.isEmpty()) {
            return null;
        } else {
            HashMap<Long, PassIndLinkWrapper> passIndLinkWrapperMap = new HashMap<Long, PassIndLinkWrapper>();
            for (PassIndLinkWrapper passIndLinkWrapper : passIndLinkWrappers) {
                passIndLinkWrapperMap.put(passIndLinkWrapper.getId(), passIndLinkWrapper);
            }
            HashMap<Long, SPClass> spClassMap = new HashMap<Long, SPClass>();
            for (SPClass spClass : spClasses) {
                spClassMap.put(spClass.getId(), spClass);
            }

            ArrayList<PassIndSpLinkWrapper> passIndSpLinkWrappers = new ArrayList<PassIndSpLinkWrapper>();
            for (PassIndSpLink passIndSpLink : passIndSpLinks) {
                passIndSpLinkWrappers.add(new PassIndSpLinkWrapper(passIndSpLink,
                        passIndLinkWrapperMap.get(passIndSpLink.getPassIndLinkId()),
                        spClassMap.get(passIndSpLink.getSpId())));
            }

            return passIndSpLinkWrappers;
        }
    }
    public static List<PassIndSpLinkWrapper> buildTree(List<PassIndSpLinkWrapper> passIndSpLinkWrappers, List<SPClass> spClasses) {
                
        if ((passIndSpLinkWrappers == null) || passIndSpLinkWrappers.isEmpty()) {
            return null;
        } else {
            TreeMap<Long, PassIndSpLinkWrapper> passIndSpLinkWrapperMap = new TreeMap<Long, PassIndSpLinkWrapper>();
            for (PassIndSpLinkWrapper passIndSpLinkWrapper: passIndSpLinkWrappers) {
                if ((passIndSpLinkWrapper != null) && (passIndSpLinkWrapper.getId() != null)) {
                    passIndSpLinkWrapperMap.put(passIndSpLinkWrapper.getId(), new PassIndSpLinkWrapper(passIndSpLinkWrapper));
                }
            }
            
            ArrayList<PassIndSpLinkWrapper> result = new ArrayList<PassIndSpLinkWrapper>();
            for (PassIndSpLinkWrapper passIndSpLinkWrapper: passIndSpLinkWrapperMap.values()) {
                if (passIndSpLinkWrapper.getParId() == null) {
                    result.add(passIndSpLinkWrapper);
                } else {
                    PassIndSpLinkWrapper parentpassIndSpLinkWrapper = passIndSpLinkWrapperMap.get(passIndSpLinkWrapper.getParId());
                    if (parentpassIndSpLinkWrapper != null) {
                        parentpassIndSpLinkWrapper.children.add(passIndSpLinkWrapper);
                    }
                }
            }
            
            return result;
        }
    }

    public static List<PassIndSpLinkWrapper> availableItems(List<PassIndSpLinkWrapper> passIndSpLinkWrappers, Long excludedId) {
        if ((passIndSpLinkWrappers == null) || passIndSpLinkWrappers.isEmpty() || (excludedId == null)) {
            return null;
        } else {

            ArrayList<PassIndSpLinkWrapper> result = new ArrayList<PassIndSpLinkWrapper>();

            for (PassIndSpLinkWrapper passIndSpLinkWrapper : passIndSpLinkWrappers) {
                if (!excludedId.equals(passIndSpLinkWrapper.getId())) {
                    result.add(passIndSpLinkWrapper);
                    List<PassIndSpLinkWrapper> children = availableItems(passIndSpLinkWrapper.children, excludedId);
                    if ((children != null) && !children.isEmpty()) {
                        result.addAll(children);
                    }
                }
            }

            return result;
        }
    }

    public String toOption(int level, Long selectedId, String horizontal, String vertical) {
        StringBuilder stringBuilder = new StringBuilder("<option ")
                .append(getId().equals(selectedId) ? " selected " : "")
                .append(" value=\"")
                .append(getId() == null ? "" : getId().toString())
                .append("\">");

        for (int i = 0; i < level; i++) {
            stringBuilder.append("&nbsp;&nbsp;");
        }

        if (level > 0) {
            stringBuilder.append("|-&nbsp;");
        }

        stringBuilder
                .append(passIndLinkWrapper.getPassport()!=null ? passIndLinkWrapper.getPassport().getRname() : "")
                .append(passIndLinkWrapper.getIndicator()!=null ? "&nbsp;&nbsp;|&nbsp;&nbsp;" + passIndLinkWrapper.getIndicator().getRname() : "")
                .append(passIndLinkWrapper.getMeasure()!=null ? "&nbsp;&nbsp;|&nbsp;&nbsp;" + passIndLinkWrapper.getMeasure().getRname() : "")
                .append("&nbsp;&nbsp;|&nbsp;&nbsp;")
                .append(getSpClass().getRname())
                .append(getOrient()!=null ? ("&nbsp;&nbsp;|&nbsp;&nbsp;" + (!getOrient() ? horizontal : vertical)) : "")
                .append("</option>");

        if ((children != null) && !children.isEmpty()) {
            for (PassIndSpLinkWrapper child : children) {
                stringBuilder
                        .append(child.toOption((level + 1), selectedId, horizontal, vertical));
            }
        }
        return stringBuilder.toString();
    }
    
    public String toTable(int level, String editString, String deleteString, String horizontal, String vertical) {
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
                .append(getParId() == null ? "" : getParId().toString())
                .append("</td><td><a href=\"?id=")
                .append(getId() == null ? "" : getId().toString())
                .append("\">");

        if (level > 0) {
            stringBuilder.append("&nbsp;&nbsp;|-&nbsp;&nbsp;");
        }
        stringBuilder
                .append(passIndLinkWrapper.getPassport()!=null ? passIndLinkWrapper.getPassport().getRname() : "")
                .append(passIndLinkWrapper.getIndicator()!=null ? "&nbsp;&nbsp;|&nbsp;&nbsp;" + passIndLinkWrapper.getIndicator().getRname() : "")
                .append(passIndLinkWrapper.getMeasure()!=null ? "&nbsp;&nbsp;|&nbsp;&nbsp;" + passIndLinkWrapper.getMeasure().getRname() : "")
                .append("</a>")
                .append("</td><td><a href=\"?id=")
                .append(getId() == null ? "" : getId().toString())
                .append("\">")
                .append(getSpClass().getRname())
                .append("</a>")
                .append("</td><td><a href=\"?id=")
                .append(getId() == null ? "" : getId().toString())
                .append("\">")
                .append(getOrient()!=null ? (!getOrient() ? horizontal : vertical) : "")
                .append("</a></td><td><a href=\"delete-pass-ind-sp-link?id=")
                .append(getId() == null ? "" : getId().toString())
                .append("\">")
                .append(deleteString)
                .append("</a></td></tr>");
               
        if ((children != null) && !children.isEmpty()) {
            for (PassIndSpLinkWrapper child: children) {
                stringBuilder.append(child.toTable(level + 1, editString, deleteString, horizontal, vertical));
            }
        }
        
        return stringBuilder.toString();
    }
    
    public PassIndSpLinkWrapper(PassIndSpLink passIndSpLink) {
        super();
        this.setId(passIndSpLink.getId());
        this.setParId(passIndSpLink.getParId());
        this.setPassIndLinkId(passIndSpLink.getPassIndLinkId());
        this.setSpId(passIndSpLink.getSpId());
        this.setOrient(passIndSpLink.getOrient());
    }

    public PassIndSpLinkWrapper(PassIndSpLink passIndSpLink, PassIndLinkWrapper passIndLinkWrapper, SPClass spClass) {
        super();
        
        this.setId(passIndSpLink.getId());
        this.setParId(passIndSpLink.getParId());
        this.setPassIndLinkId(passIndSpLink.getPassIndLinkId());
        this.setSpId(passIndSpLink.getSpId());
        this.setOrient(passIndSpLink.getOrient());
        this.passIndLinkWrapper = passIndLinkWrapper;
        this.spClass = spClass;
    }

    public PassIndSpLinkWrapper(PassIndSpLinkWrapper passIndSpLinkWrapper) {
        super();
        this.setId(passIndSpLinkWrapper.getId());
        this.setParId(passIndSpLinkWrapper.getParId());
        this.setPassIndLinkId(passIndSpLinkWrapper.getPassIndLinkId());
        this.setSpId(passIndSpLinkWrapper.getSpId());
        this.setOrient(passIndSpLinkWrapper.getOrient());
        this.passIndLinkWrapper = passIndSpLinkWrapper.passIndLinkWrapper;
        this.spClass = passIndSpLinkWrapper.spClass;
    }

    public PassIndLinkWrapper getPassIndLinkWrapper() {
        return passIndLinkWrapper;
    }

    public void setPassIndLinkWrapper(PassIndLinkWrapper passIndLinkWrapper) {
        this.passIndLinkWrapper = passIndLinkWrapper;
    }

    public SPClass getSpClass() {
        return spClass;
    }

    public void setSpClass(SPClass spClass) {
        this.spClass = spClass;
    }

    public List<PassIndSpLinkWrapper> getChildren() {
        return children;
    }

    public void setChildren(List<PassIndSpLinkWrapper> children) {
        this.children = children;
    }

}
