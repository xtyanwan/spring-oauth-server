<%--
 * Copyright (c) 2013 Honyee Industry Group Co., Ltd
 * www.honyee.biz
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Honyee Industry Group Co., Ltd ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with Honyee Industry Group Co., Ltd.
--%>
<%--
 * 
 * @author Shengzhao Li
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>

<html>
<head>
    <title>Oauth Approval</title>
</head>
<body><h1>OAuth Approval</h1>

<p>Do you authorize '${authorizationRequest.clientId}' to access your protected resources?</p>

<form id='confirmationForm' name='confirmationForm' action='${pageContext.request.contextPath}/oauth/authorize'
      method='post'>
    <input name='user_oauth_approval' value='true' type='hidden'/>
    <label> <input name='authorize' value='Authorize' type='submit'></label>
</form>
<form id='denialForm' name='denialForm' action='${pageContext.request.contextPath}/oauth/authorize' method='post'>
    <input name='user_oauth_approval' value='false' type='hidden'/>
    <label><input name='deny' value='Deny' type='submit'></label>
</form>
</body>
</html>