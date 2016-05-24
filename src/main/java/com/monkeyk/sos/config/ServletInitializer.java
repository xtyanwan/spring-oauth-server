package com.monkeyk.sos.config;

import com.monkeyk.sos.web.filter.CharacterEncodingIPFilter;
import com.opensymphony.sitemesh.webapp.SiteMeshFilter;
import org.springframework.util.ClassUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;
import org.springframework.web.util.Log4jConfigListener;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * 2016/4/3
 * <p/>
 * Config DispatchServlet
 * <p/>
 * Replace web.xml
 *
 * @author Shengzhao Li
 */
public class ServletInitializer extends AbstractDispatcherServletInitializer {


    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }


    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);

//        servletContext.setAttribute("webAppRootKey", "spring-oauth-server");
        servletContext.setInitParameter("webAppRootKey", "spring-oauth-server");
//        servletContext.setInitParameter("contextConfigLocation", "classpath:spring/*.xml");
        servletContext.setInitParameter("log4jConfigLocation", "/WEB-INF/log4j.xml");

        //Add Filters

        CharacterEncodingIPFilter characterEncodingIPFilter = new CharacterEncodingIPFilter();
        characterEncodingIPFilter.setEncoding("UTF-8");
        characterEncodingIPFilter.setForceEncoding(true);
        servletContext.addFilter("encodingFilter", characterEncodingIPFilter).addMappingForUrlPatterns(null, false, "/*");

        DelegatingFilterProxy securityFilter = new DelegatingFilterProxy("springSecurityFilterChain");
        servletContext.addFilter("springSecurityFilterChain", securityFilter).addMappingForUrlPatterns(null, false, "/*");

        SiteMeshFilter siteMeshFilter = new SiteMeshFilter();
        servletContext.addFilter("sitemesh", siteMeshFilter).addMappingForUrlPatterns(null, false, "/*");

        //Add Listeners

        servletContext.addListener(Log4jConfigListener.class);

    }

    @Override
    protected WebApplicationContext createRootApplicationContext() {
        return createServletApplicationContext();
    }

    @Override
    protected WebApplicationContext createServletApplicationContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.scan(ClassUtils.getPackageName(getClass()));
        return context;
    }
}
