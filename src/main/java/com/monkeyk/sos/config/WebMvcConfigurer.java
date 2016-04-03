package com.monkeyk.sos.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * 2016/4/3
 * <p/>
 * Replace mkk-servlet.xml
 *
 * @author Shengzhao Li
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.monkeyk.sos.web"})
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }


    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        registry.viewResolver(viewResolver);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/index.jsp*").addResourceLocations("/index.jsp");
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

}
