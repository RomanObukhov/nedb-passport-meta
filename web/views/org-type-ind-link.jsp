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
        <title><spring:message code="org-type-ind-link.title"/></title>

        <jsp:include page="templates/init.jsp"/>
        <jsp:include page="templates/styles.jsp"/> 
        <jsp:include page="templates/scripts.jsp"/>

        <script type="text/javascript">
            jQuery(function(){
                jQuery('#org-type-ind-link').addClass('ahover').css("color", "#ed1e24");
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
                    <h2><spring:message code="label.editing-element"/>&nbsp;&Prime;<spring:message code="org-type-ind-link.title"/>&Prime;</h2>
                </div>

                <div class="elembox">
                    <c:choose>
                        <c:when test="${empty wrapList}">
                            <spring:message code="label.no-elements"/>
                        </c:when>
                        <c:otherwise>                            
                                    ${wrapList}               
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