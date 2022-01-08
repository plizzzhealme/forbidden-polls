<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<fmt:setLocale value="${sessionScope.locale}"/>

<fmt:setBundle basename="locale" var="locale"/>

<fmt:message bundle="${locale}" key="registration-page.back-button" var="back"/>
<fmt:message bundle="${locale}" key="registration-page.birthday" var="birthday"/>
<fmt:message bundle="${locale}" key="registration-page.confirm-password" var="confirm"/>
<fmt:message bundle="${locale}" key="registration-page.country" var="country"/>
<fmt:message bundle="${locale}" key="registration-page.email" var="email"/>
<fmt:message bundle="${locale}" key="registration-page.gender" var="gender"/>
<fmt:message bundle="${locale}" key="registration-page.gender.female" var="female"/>
<fmt:message bundle="${locale}" key="registration-page.gender.male" var="male"/>
<fmt:message bundle="${locale}" key="registration-page.gender.other" var="other"/>
<fmt:message bundle="${locale}" key="registration-page.name" var="name"/>
<fmt:message bundle="${locale}" key="registration-page.password" var="password"/>
<fmt:message bundle="${locale}" key="registration-page.registration-button" var="reg"/>
<fmt:message bundle="${locale}" key="registration-page.title" var="title"/>