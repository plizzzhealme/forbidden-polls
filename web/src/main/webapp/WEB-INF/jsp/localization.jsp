<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>

<fmt:setBundle basename="locale" var="locale"/>

<fmt:message bundle="${locale}" key="user.email" var="email"/>
<fmt:message bundle="${locale}" key="user.name" var="name"/>
<fmt:message bundle="${locale}" key="user.password" var="password"/>
<fmt:message bundle="${locale}" key="user.confirm-password" var="confirm_password"/>
<fmt:message bundle="${locale}" key="user.birthday" var="birthday"/>
<fmt:message bundle="${locale}" key="user.country" var="country"/>
<fmt:message bundle="${locale}" key="user.gender" var="gender"/>
<fmt:message bundle="${locale}" key="user.gender.female" var="female"/>
<fmt:message bundle="${locale}" key="user.gender.male" var="male"/>
<fmt:message bundle="${locale}" key="user.gender.other" var="other"/>


<fmt:message bundle="${locale}" key="sign-in" var="sign_in"/>
<fmt:message bundle="${locale}" key="project-name" var="project_name"/>
<fmt:message bundle="${locale}" key="to-main-page" var="to_main_page"/>
<fmt:message bundle="${locale}" key="sign-up" var="sign_up"/>
<fmt:message bundle="${locale}" key="authorization" var="authorization"/>
<fmt:message bundle="${locale}" key="start-page.message" var="start_page_message"/>
<fmt:message bundle="${locale}" key="registration" var="registration"/>