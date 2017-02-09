package org.lizi.demo.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

/**
 * Created by touch on 2017/1/13.
 */
@Configuration
@EnableWebMvc
@ComponentScan("org.lizi.demo.web")
public class WebConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware{
    @Autowired
    public ApplicationContext applicationContext;

    /**
     * 视图解析器
     * @param templateEngine
     * @return
     */
    @Bean
    public ViewResolver viewResolver(TemplateEngine templateEngine){
        ThymeleafViewResolver resolver=new ThymeleafViewResolver();
        resolver.setCharacterEncoding("UTF-8");
        resolver.setTemplateEngine(templateEngine);
        return resolver;
    }

    /**
     * 模板引擎，处理模板并渲染试图
     * @param iTemplateResolver
     * @return
     */
    @Bean
    public TemplateEngine templateEngine(ITemplateResolver iTemplateResolver){
        SpringTemplateEngine templateEngine=new SpringTemplateEngine();
        templateEngine.setTemplateResolver(iTemplateResolver);
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    /**
     * 加载模板
     * @return
     */
    @Bean
    public ITemplateResolver iTemplateResolver(){
        SpringResourceTemplateResolver resourceTemplateResolver=new SpringResourceTemplateResolver();
        resourceTemplateResolver.setApplicationContext(applicationContext);
        resourceTemplateResolver.setPrefix("/WEB-INF/views/");
        resourceTemplateResolver.setSuffix(".html");
        resourceTemplateResolver.setCharacterEncoding("UTF-8");
        resourceTemplateResolver.setTemplateMode(TemplateMode.HTML);
        return resourceTemplateResolver;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        super.addViewControllers(registry);
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }
}
