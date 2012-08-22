<%-- 
    Document   : passport
    Created on : Jul 5, 2012, 5:12:44 PM
    Author     : zhanat
--%>

<%@page language="java" contentType="text/html  charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title><spring:message code="org-type-cit-link.title"/></title>

        <jsp:include page="templates/init.jsp"/>
        <jsp:include page="templates/styles.jsp"/> 
        <jsp:include page="templates/scripts.jsp"/>

        <script type="text/javascript">
            jQuery(function(){
                jQuery('#org-type-cit-link').addClass('ahover').css("color", "#ed1e24");
            });            
        </script>
    </head>
    <body>
        <div id="wspace">
            <div class="header">
                <jsp:include page="templates/language.jsp"/>
                <h1><a href="${startPath}"></a></h1>
            </div>
            <div class="menu"><jsp:include page="templates/navigation.jsp"/></div>
            <div id="content">

                <div class="panel">
                    <h2><spring:message code="label.editing-element"/>&nbsp;&Prime;<spring:message code="org-type-cit-link.title"/>&Prime;</h2>
                </div>

                <div class="box round">
                    <form method="post" action="save-org-type-cit-link">
                        <table id="enter"> 
                            <tr>
                                <td><spring:message code="label.id"/></td>
                                <td>
                                    <c:choose>
                                        <c:when test="${empty orgTypeCitLink.id}">
                                            <input type="text" disabled value="<spring:message code="label.new"/>"/>
                                            <input type="hidden" name="id" value=""/>
                                        </c:when>
                                        <c:otherwise>
                                            <input type="text" disabled value="${orgTypeCitLink.id}"/>
                                            <input type="hidden" name="id" value="${orgTypeCitLink.id}"/>
                                        </c:otherwise>
                                    </c:choose>
                            </tr>
                            <tr>
                                <td><spring:message code="class-item-tree.title"/></td>
                                <td>
                                    <c:choose>
                                        <c:when test="${empty orgTypeCitLink.classItemTreeId}">
                                            <select name="classItemTreeId" >
                                                <option value="">...</option>
                                                <c:forEach items="${classItemTrees}" var="item">
                                                    ${item.toOption(0, 0)}
                                                </c:forEach>
                                            </select>
                                        </c:when>
                                        <c:otherwise>
                                            <select name="classItemTreeId" >                                
                                                <option value="">...</option>
                                                <c:forEach items="${classItemTrees}" var="item">
                                                    ${item.toOption(0,orgTypeCitLink.classItemTreeId)}
                                                </c:forEach>
                                            </select>
                                        </c:otherwise>
                                    </c:choose>         
                                </td>
                            </tr>
                            <tr>
                                <td><spring:message code="label.ot-class-item-tree"/></td>
                                <td>
                                    <c:choose>
                                        <c:when test="${empty orgTypeCitLink.otClassItemTreeId}">
                                            <select name="otClassItemTreeId" >
                                                <option value="">...</option> 
                                                <c:forEach items="${otClassItemTrees}" var="item">
                                                    ${item.toOption(0, 0)}
                                                </c:forEach>
                                            </select>
                                        </c:when>
                                        <c:otherwise>
                                            <select name="otClassItemTreeId" >                                
                                                <option value="">...</option>
                                                <c:forEach items="${otClassItemTrees}" var="item">
                                                    ${item.toOption(0,orgTypeCitLink.otClassItemTreeId)}
                                                </c:forEach>
                                            </select>
                                        </c:otherwise>
                                    </c:choose>         
                                </td>
                            </tr>         
                            <tr>
                                <td colspan="2">                        
                                    <span class="float-left">
                                        <input type="submit" value="<spring:message code="label.save"/>"/>
                                    </span>
                                </td>                    
                            </tr>
                        </table>
                    </form>
                </div>                    

                <div class="elembox">
                    <c:choose>
                        <c:when test="${empty wrapList}">
                            <spring:message code="label.no-elements"/>
                        </c:when>
                        <c:otherwise>
                            <table class="datatable">
                                <caption><spring:message code="label.table-of-elements"/></caption>
                                <thead>
                                    <tr>
                                        <th><spring:message code="label.id"/></th>
                                        <th><spring:message code="class-item-tree.title"/></th>
                                        <th><spring:message code="label.ot-class-item-tree"/></th>
                                        <th><spring:message code="label.action"/></th>
                                    </tr>
                                </thead>
                                <c:forEach items="${wrapList}" var="wrap">
                                    ${wrap.toTable(
                                      0,                      
                                      delete                      
                                      )}
                                </c:forEach>
                            </table>
                        </c:otherwise>
                    </c:choose>       
                </div>   
                                    
            </div> <!--   content     -->
            <div id="footer">
                <jsp:include page="templates/footer.jsp"/>
            </div>
        </div> <!--   wspace     -->
    </body>

</html>