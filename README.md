#spring-oauth-server


<strong>Spring与Oauth2的整合示例</strong>

项目用Maven管理


使用的技术与版本号
<ol>
 <li>Spring (3.1.1.RELEASE)</li>
 <li>Spring Security (3.1.0.RELEASE)</li>
 <li>MyBatis (3.2.1)</li>
 <li>spring-security-oauth2 (1.0.5.RELEASE)</li>
</ol>
<hr/>

<p>
<strong>如何使用?</strong>
<ol>
<li>
项目是Maven管理的, 需要本地安装maven(开发用的maven版本号为3.1.0), 还有MySql(开发用的mysql版本号为5.5)
</li>
<li>
<a href="http://git.oschina.net/shengzhao/spring-oauth-server/repository/archive?ref=master">下载</a>(或clone)项目到本地
</li>
<li>
创建MySQL数据库(如数据库名oauth2), 并运行相应的SQL脚本(脚本文件位于others/database目录),
<br/>
   运行脚本的顺序: initial_db.ddl -> oauth.ddl -> initial_data.ddl
</li>
<li>
修改<a href="http://git.oschina.net/shengzhao/spring-oauth-server/blob/master/src/main/resources/spring-oauth-server.properties">spring-oauth-server.properties</a>(位于src/resources目录)中的数据库连接信息(包括username, password等)
</li>
<li>
将本地项目导入到IDE(如Intellij IDEA)中,配置Tomcat(或类似的servelt运行服务器), 并启动Tomcat(默认端口为8080)
<br/>
   另: 也可通过maven package命令将项目编译为war文件(spring-oauth-server.war),
         将war放在Tomcat中并启动(注意: 这种方式需要将spring-oauth-server.properties加入到classpath中并正确配置数据库连接信息).
</li>
<li>
参考<a href="http://git.oschina.net/shengzhao/spring-oauth-server/blob/master/others/oauth_test.txt">oauth_test.txt</a>(位于others目录)的内容并测试之(也可在浏览器中访问相应的地址,如: http://localhost:8080/spring-oauth-server).
</li>
</ol>
</p>

<hr/>
<strong>帮助与改进</strong>
<ol>
<li>
<p>
 与该项目相关的博客请访问 <a target="_blank" href="http://blog.csdn.net/monkeyking1987/article/details/16828059">http://blog.csdn.net/monkeyking1987/article/details/16828059</a>
</p>
</li>
<li>
<p>
 如果在使用过程中遇到特殊的问题(如:如何将oauth_code存入数据库),请访问项目的 <a href="http://git.oschina.net/shengzhao/spring-oauth-server/wikis/pages">Wiki</a> 
 与 <a href="http://git.oschina.net/shengzhao/spring-oauth-server/attach_files">附件</a>. 
 <br/>
 我会把大家反馈的问题解决办法添加在这里.
 <br/>
 若在这两个地方没有找到解决办法的,
 欢迎发邮件到<a href="mailto:shengzhao@shengzhaoli.com">shengzhao@shengzhaoli.com</a>一起讨论.
</p>
</li>

<li>
<p>
 如果在使用项目的过程中发现任何的BUG或者更好的提议, 建议将其提交到项目的 <a href="http://git.oschina.net/shengzhao/spring-oauth-server/issues">Issues</a> 中, 
 我会一直关注并不断改进项目. 
</p>
</li>
</ol>

<hr/>
<strong>功能扩展</strong>
<ol>
    <li>
        <code>oauth_code存入数据库的配置</code>,  请下载文件 <a href="https://git.oschina.net/shengzhao/spring-oauth-server/attach_files/download?i=4858&u=http%3A%2F%2Ffiles.git.oschina.net%2Fgroup1%2FM00%2F00%2F31%2FcHwGbFQXzC-AeseiAAfnNw23X70580.jpg%3Ftoken%3De81934223d99a0fddc02639017b568a6%26ts%3D1421151523%26filename%3Doauth_code%E5%AD%98%E5%85%A5%E6%95%B0%E6%8D%AE%E5%BA%93%E7%9A%84%E9%85%8D%E7%BD%AE.jpg">oauth_code存入数据库的配置.jpg</a>
    </li>
    <li>
        <code>改变token过期的时间的配置</code>, 请下载文件<a href="https://git.oschina.net/shengzhao/spring-oauth-server/attach_files/download?i=6589&u=http%3A%2F%2Ffiles.git.oschina.net%2Fgroup1%2FM00%2F00%2F43%2FcHwGbFRpuk6ANN2CAANJ-Rkiz_c649.jpg%3Ftoken%3D686e6d5b1e9ab04446dbfeb977c3b7a1%26ts%3D1421151523%26filename%3D%E6%94%B9%E5%8F%98token%E8%BF%87%E6%9C%9F%E7%9A%84%E6%97%B6%E9%97%B4%E7%9A%84%E9%85%8D%E7%BD%AE.jpg">改变token过期的时间的配置.jpg</a>
    </li>
    <li>
        <code>自定义 grant_type</code>, 默认情况支持的grant_type包括 [password,authorization_code,refresh_token,implicit], 若不需要其中的某些grant_type,
        则可以修改 oauth_client_details 表中的 authorized_grant_types 字段的值;
        <br/>
        若想把整个Oauth服务修改来只支持某些grant_type, 请修改 <i>security.xml</i>文件中的
        <label>oauth2:authorization-server</label> 中的内容,将对应的 grant_type 注释或删掉即可
    </li>
</ol>

<hr/>
<p>
 关注更多我的开源项目请访问 <a href="http://andaily.com/my_projects.html">http://andaily.com/my_projects.html</a>
</p>
<p>
 若需更多的技术支持请联系 <a href="mailto:monkeyk@shengzhaoli.com">monkeyk@shengzhaoli.com</a>
</p>