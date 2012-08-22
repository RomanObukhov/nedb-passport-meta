<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">

<span class="float-left">
    <ul id="menu">
        <li><a id="home" href="${startPath}" title="Главная"><img src="/static/styles/images/home.png" alt="Home" class="home" /></a></li>       
        <li><a id="passport" href="${startPath}passport"><spring:message code="passport.title"/></a></li>
        <li><a id="indicator" href="${startPath}indicator"><spring:message code="indicator.title"/></a></li>
        <li><a id="measure" href="${startPath}measure"><spring:message code="measure.title"/></a></li>
        <li><a id="pass-ind-link" href="${startPath}pass-ind-link"><spring:message code="pass-ind-link.title"/></a></li>
        <li><a id="pass-ind-sp-link" href="${startPath}pass-ind-sp-link"><spring:message code="pass-ind-sp-link.title"/></a></li>
        <li><a id="sp-class" href="${startPath}sp-class"><spring:message code="sp-class.title"/></a></li>
        <li><a id="class-item-tree" href="${startPath}class-item-tree"><spring:message code="class-item-tree.title"/></a></li>
    </ul>
</span>
<span class="float-left" style="margin-left: 30px">
    <ul id="menu">
        <li><a id="org-type-cit-link" href="${startPath}org-type-cit-link"><spring:message code="org-type-cit-link.title"/></a></li>
        <li><a id="org-type-ind-link" href="${startPath}org-type-ind-link"><spring:message code="org-type-ind-link.title"/></a></li>
    </ul>
</span>
