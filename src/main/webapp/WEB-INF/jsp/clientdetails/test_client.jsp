<%--
 * 
 * @author Shengzhao Li
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>Test [${clientDetailsDto.clientId}]</title>

    <script src="http://cdn.bootcss.com/angular.js/1.1.5/angular.min.js"></script>

</head>
<body>
<div ng-app>
    <a href="../">Home</a>

    <h2>Test [${clientDetailsDto.clientId}]</h2>

    <p>
        针对不同的<code>grant_type</code>提供不同的测试URL,
        完整的Oauth测试请访问<a href="http://git.oschina.net/mkk/spring-oauth-client" target="_blank">spring-oauth-client</a>项目.
    </p>

    <div ng-controller="TestClientCtrl">
        <c:if test="${clientDetailsDto.containsAuthorizationCode}">
            <div class="panel panel-default">
                <div class="panel-heading">Test [authorization_code]</div>
                <div class="panel-body">
                    <p class="text-muted">输入每一步必要的信息后点击其下面的链接地址.</p>
                    <ol>
                        <li>
                            <p>
                                <code>从 spring-oauth-server获取 'code'</code>
                                <br/>
                                redirect_uri: <input type="text" value="" ng-model="redirectUri" size="70"
                                                     required="required"/>
                                <br/>
                                <a href="${contextPath}/oauth/authorize?client_id={{clientId}}&redirect_uri={{redirectUri}}&response_type=code&scope={{scope}}"
                                   target="_blank">
                                    /oauth/authorize?client_id={{clientId}}&redirect_uri={{redirectUri}}&response_type=code&scope={{scope}}</a>
                            </p>
                        </li>
                        <li>
                            <p>
                                <code>用 'code' 换取 'access_token'</code>
                                <br/>
                                输入第一步获取的code: <input type="text" name="code" value="" ng-model="code"
                                                     required="required"/>
                                <br/>
                                <a href="${contextPath}/oauth/token?client_id={{clientId}}&client_secret={{clientSecret}}&grant_type=authorization_code&code={{code}}&redirect_uri={{redirectUri}}"
                                   target="_blank">/oauth/token?client_id={{clientId}}&client_secret={{clientSecret}}&grant_type=authorization_code&code={{code}}&redirect_uri={{redirectUri}}</a>
                            </p>
                        </li>
                    </ol>
                </div>
            </div>
        </c:if>

        <c:if test="${clientDetailsDto.containsPassword}">
            <div class="panel panel-default">
                <div class="panel-heading">Test [password]</div>
                <div class="panel-body">
                    <p class="text-muted">输入username, password 后点击链接地址.</p>
                    username: <input type="text" required="required" ng-model="username"/>
                    <br/>
                    password: <input type="text" required="required" ng-model="password"/>

                    <p>
                        <a href="${contextPath}/oauth/token?client_id={{clientId}}&client_secret={{clientSecret}}&grant_type=password&scope={{scope}}&username={{username}}&password={{password}}"
                           target="_blank">/oauth/token?client_id={{clientId}}&client_secret={{clientSecret}}&grant_type=password&scope={{scope}}&username={{username}}&password={{password}}</a>
                    </p>
                </div>
            </div>
        </c:if>

        <c:if test="${clientDetailsDto.containsImplicit}">
            <div class="panel panel-default">
                <div class="panel-heading">Test [implicit]</div>
                <div class="panel-body">
                    <p class="text-muted">输入redirect_uri 后点击链接地址. 获取access_token后注意查看redirect_uri的hash部分(#号后边部分)</p>
                    redirect_uri: <input type="text" value="" ng-model="implicitRedirectUri" size="70"
                                         required="required"/>

                    <p>
                        <a href="${contextPath}/oauth/authorize?client_id={{clientId}}&client_secret={{clientSecret}}&response_type=token&scope={{scope}}&&redirect_uri={{implicitRedirectUri}}"
                                >/oauth/authorize?client_id={{clientId}}&client_secret={{clientSecret}}&response_type=token&scope={{scope}}&redirect_uri={{implicitRedirectUri}}</a>
                    </p>
                </div>
            </div>
        </c:if>

        <div class="text-center">
            <a href="${contextPath}/client_details" class="btn btn-default">Back</a>
        </div>
    </div>
</div>

<script>
    var TestClientCtrl = ["$scope", function ($scope) {
        $scope.clientId = "${clientDetailsDto.clientId}";
        $scope.clientSecret = "${clientDetailsDto.clientSecret}";
        $scope.scope = "${clientDetailsDto.scope}";

        $scope.redirectUri = "${empty clientDetailsDto.webServerRedirectUri?'http://localhost:8080/spring-oauth-server/unity/dashboard.htm':clientDetailsDto.webServerRedirectUri}";
        $scope.implicitRedirectUri = "${empty clientDetailsDto.webServerRedirectUri?pageContext.request.requestURI:clientDetailsDto.webServerRedirectUri}";

        $scope.username = "mobile";
        $scope.password = "mobile";

    }];
</script>
</body>
</html>