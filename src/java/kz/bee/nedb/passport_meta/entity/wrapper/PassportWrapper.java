/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.bee.nedb.passport_meta.entity.wrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import kz.bee.nedb.passport_meta.entity.Passport;

/**
 *
 * @author zhanat
 */
public class PassportWrapper extends Passport{    
    private List<PassportWrapper> children = new ArrayList<PassportWrapper>();

    public static List<PassportWrapper> wrapList(List<Passport> passports) {
        if ((passports == null) || passports.isEmpty()) {
            return null;
        } else {
            ArrayList<PassportWrapper> passportWrappers = new ArrayList<PassportWrapper>();
            for (Passport passport: passports) {
                passportWrappers.add(new PassportWrapper(passport));
            }
            
            return passportWrappers;
        }
    }

    public static List<PassportWrapper> buildTree(List<PassportWrapper> passportWrappers) {
        if ((passportWrappers == null) || passportWrappers.isEmpty()) {
            return null;
        } else {
            TreeMap<Long, PassportWrapper> passportWrapperMap = new TreeMap<Long, PassportWrapper>();
            for (PassportWrapper parentPassportWrapper: passportWrappers) {
                if ((parentPassportWrapper != null) && (parentPassportWrapper.getId() != null)) {
                    passportWrapperMap.put(parentPassportWrapper.getId(), new PassportWrapper(parentPassportWrapper));
                }
            }
            
            ArrayList<PassportWrapper> result = new ArrayList<PassportWrapper>();
            for (PassportWrapper passportWrapper: passportWrapperMap.values()) {
                if (passportWrapper.getParId() == null) {
                    result.add(passportWrapper);
                } else {
                    PassportWrapper parentClassItemTreeWrapper = passportWrapperMap.get(passportWrapper.getParId());
                    if (parentClassItemTreeWrapper != null) {
                        parentClassItemTreeWrapper.children.add(passportWrapper);
                    }
                }
            }
            
            return result;
        }
    }
    
    public static List<PassportWrapper> availableItems(List<PassportWrapper> passportWrappers, Long excludedId) {
        if ((passportWrappers == null) || passportWrappers.isEmpty() || (excludedId == null)) {
            return null;
        } else {
            TreeMap<Long, PassportWrapper> passportWrapperMap = new TreeMap<Long, PassportWrapper>();
            for (PassportWrapper parentPassportWrapper: passportWrappers) {
                if ((parentPassportWrapper != null) && (parentPassportWrapper.getId() != null)) {
                    passportWrapperMap.put(parentPassportWrapper.getId(), new PassportWrapper(parentPassportWrapper));
                }
            }
            
            ArrayList<PassportWrapper> result = new ArrayList<PassportWrapper>();
            for (PassportWrapper passportWrapper: passportWrapperMap.values()) {
                if (!excludedId.equals(passportWrapper.getId())) {
                    if (passportWrapper.getParId() == null) {
                        result.add(passportWrapper);
                    } else {
                        PassportWrapper parentPassportWrapper = passportWrapperMap.get(passportWrapper.getParId());
                        if (parentPassportWrapper != null) {
                            parentPassportWrapper.children.add(passportWrapper);
                        }
                    }
                }
            }
            
            return result;
        }
    }
    
    public String toOption(int level, Long selectedId) {
        StringBuilder stringBuilder = new StringBuilder("<option ")
                .append(getId().equals(selectedId) ? " selected " : "")                
                .append(" value=\"")
                .append(getId() == null ? "" : getId().toString())
                .append("\">");
        
        for (int i = 0; i < level; i++) {
            stringBuilder.append("&nbsp;&nbsp;");
        }
        
        if (level > 0) {
            stringBuilder.append("|-");
        }
        
        stringBuilder
                .append(getRname())
                .append("</option>");
                
        if ((children != null) && !children.isEmpty()) {
            for (PassportWrapper child: children) {
                stringBuilder.append(child.toOption((level + 1), selectedId));
            }
        }
        
        return stringBuilder.toString();
    }
    
    public String toTable(int level, String editString, String deleteString) {
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
                .append("</td><td>");

        if (level > 0) {
            stringBuilder.append("&nbsp;&nbsp;|-&nbsp;&nbsp;");
        }
        stringBuilder
//                .append(getRname())
//                .append("</td><td>")
//                .append(getKname())
//                .append("</td><td>")
//                .append(getEname())
                 .append("<a href=\"?id=")
                .append(getId() == null ? "" : getId().toString())
                .append("\">")
                .append(getRname())
                .append("</a>")
                .append("</td><td><a href=\"?id=")
                .append(getId() == null ? "" : getId().toString())
                .append("\">")
                .append(getPasstype())
                .append("</a></td><td><a href=\"delete-passport?id=")
                .append(getId() == null ? "" : getId().toString())
                .append("\">")
                .append(deleteString)
                .append("</a></td></tr>");
               
        if ((children != null) && !children.isEmpty()) {
            for (PassportWrapper child: children) {
                stringBuilder.append(child.toTable(level + 1, editString, deleteString));
            }
        }
        
        return stringBuilder.toString();
    }
    
    public PassportWrapper(Passport passport) {
        super();
        
        this.setId(passport.getId());
        this.setParId(passport.getParId());
        this.setRname(passport.getRname());
        this.setKname(passport.getKname());
        this.setEname(passport.getEname());
        this.setPasstype(passport.getPasstype());
    }
    
    public PassportWrapper(PassportWrapper passportWrapper) {
        super();
        
        this.setId(passportWrapper.getId());
        this.setParId(passportWrapper.getParId());
        this.setRname(passportWrapper.getRname());
        this.setKname(passportWrapper.getKname());
        this.setEname(passportWrapper.getEname());
        this.setPasstype(passportWrapper.getPasstype());
    }

    public List<PassportWrapper> getChildren() {
        return children;
    }

    public void setChildren(List<PassportWrapper> children) {
        this.children = children;
    }
    
}
