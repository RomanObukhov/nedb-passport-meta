<%-- 
    Document   : passindsplink
    Created on : Jul 5, 2012, 5:12:44 PM
    Author     : zhanat
--%>

<%@page language="java" contentType="text/html  charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title><spring:message code="pass-ind-sp-link.title"/></title>

        <jsp:include page="templates/init.jsp"/>
        <jsp:include page="templates/styles.jsp"/>
        <jsp:include page="templates/scripts.jsp"/>

        <script type="text/javascript">
            jQuery(function(){
                jQuery('#pass-ind-sp-link').addClass('ahover').css("color", "#ed1e24");
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
            <div class="content">

                <div class="panel">
                    <h2><spring:message code="label.editing-element"/>&nbsp;&Prime;<spring:message code="pass-ind-sp-link.title"/>&Prime;</h2>
                </div>
                <div class="box round">
                    <form method="post" action="save-pass-ind-sp-link">
                        <table id="enter"> 
                            <tr>
                                <td><spring:message code="label.id"/></td>
                                <td>
                                    <c:choose>
                                        <c:when test="${empty passIndSpLink.id}">
                                            <input type="text" disabled value="<spring:message code="label.new"/>"/>
                                            <input type="hidden" name="id" value=""/>
                                        </c:when>
                                        <c:otherwise>
                                            <input type="text" disabled value="${passIndSpLink.id}"/>  
                                            <input type="hidden" name="id" value="${passIndSpLink.id}"/>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                            <!--            par_id-->
                            <tr>
                                <td><spring:message code="label.parId"/></td>
                                <td>
                                    <c:choose>
                                        <c:when test="${empty passIndSpLink.parId}">
                                            <select name="parId" >
                                                <option value="">...</option>
                                                <c:forEach items="${wrapTree}" var="item">
                                                    ${item.toOption(0, 0, horizontal, vertical)}
                                                </c:forEach>
                                            </select>
                                        </c:when>
                                        <c:otherwise>
                                            <select name="parId" >                                
                                                <option value="">...</option>
                                                <c:forEach items="${wrapTree}" var="item">
                                                    ${item.toOption(0,passIndSpLink.parId, horizontal, vertical)}
                                                </c:forEach>
                                            </select>
                                        </c:otherwise>
                                    </c:choose>         
                            </tr>

                            <!--            pass-ind-link-->
                            <tr>
                                <td><spring:message code="pass-ind-link.title"/></td>
                                <td>
                                    <c:choose>
                                        <c:when test="${empty passIndSpLink.passIndLinkId}">
                                            <select name="passIndLinkId" >
                                                <option value="">...</option>
                                                <c:forEach items="${passIndLinkWrapList}" var="item">
                                                    ${item.toOption(0)}
                                                </c:forEach>
                                            </select>
                                        </c:when>
                                        <c:otherwise>
                                            <select name="passIndLinkId" >
                                                <option value="">...</option>
                                                <c:forEach items="${passIndLinkWrapList}" var="item">
                                                    ${item.toOption(passIndSpLink.passIndLinkId)}
                                                </c:forEach>
                                            </select>
                                        </c:otherwise>
                                    </c:choose>         
                                </td>
                            </tr>
                            <!--            spClass--> 
                            <tr>
                                <td><spring:message code="sp-class.title"/></td>
                                <td>
                                    <c:choose>
                                        <c:when test="${empty passIndSpLink.spId}">
                                            <select name="spId" >
                                                <option value="">...</option>
                                                <c:forEach items="${spClassList}" var="item">
                                                    ${item.toOption(0)}
                                                </c:forEach>
                                            </select>
                                        </c:when>
                                        <c:otherwise>
                                            <select name="spId" >
                                                <option value="">...</option>
                                                <c:forEach items="${spClassList}" var="item">
                                                    ${item.toOption(passIndSpLink.spId)}
                                                </c:forEach>
                                            </select>
                                        </c:otherwise>
                                    </c:choose>         
                                </td>
                            </tr> 
                            <tr>
                                <td><spring:message code="label.orient"/></td>
                                <td>
                                    <select name="orient">
                                        <option value=""
                                                <c:if test="${empty passIndSpLink.orient}">
                                                    selected
                                                </c:if>
                                                >...</option>
                                        <option value="false"
                                                <%--<c:if test="${(not empty passIndSpLink.orient) && (not passIndSpLink.orient)}">--%>
                                                <c:if test="${(not empty passIndSpLink.orient) && (passIndSpLink.orient == 0)}">
                                                    selected
                                                </c:if>
                                                ><spring:message code="item.horizontal"/></option>
                                        <option value="true"
                                                <%--<c:if test="${(not empty passIndSpLink.orient) && (passIndSpLink.orient)}">--%>
                                                <c:if test="${(not empty passIndSpLink.orient) && (passIndSpLink.orient == 1)}">
                                                    selected
                                                </c:if>
                                                ><spring:message code="item.vertical"/></option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <span class="float-left"><input type="submit" value="<spring:message code="label.save"/>" /></span>
                                </td>                    
                            </tr>
                        </table>
                    </form>   
                </div>                    

                <div class="elembox ">
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
                                        <th><spring:message code="label.parId"/></th>
                                        <th><spring:message code="pass-ind-link.title"/></th>
                                        <th><spring:message code="sp-class.title"/></th>
                                        <th><spring:message code="label.orient"/></th>
                                        <th><spring:message code="label.action"/></th>
                                    </tr>
                                </thead>
                                <c:forEach items="${wrapList}" var="wrap">
                                    ${wrap.toTable(
                                      0,                      
                                      edit,
                                      delete,
                                      horizontal,
                                      vertical
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