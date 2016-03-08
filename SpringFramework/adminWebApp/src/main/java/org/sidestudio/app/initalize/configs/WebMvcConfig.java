package org.sidestudio.app.initalize.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

/**
 * 해당 Dispatcherservlet에 대한 설정
 *
 * @author logan
 * @since 2016-03-03
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"org.sidestudio.app.controllers"})
public class WebMvcConfig extends WebMvcConfigurerAdapter{

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/").addResourceLocations("/resources/**");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    // TilesView Resolver 설정
    @Bean
    public UrlBasedViewResolver urlBasedViewResolver() {

        UrlBasedViewResolver urlBasedViewResolver = new UrlBasedViewResolver();
        urlBasedViewResolver.setViewClass(TilesView.class);
        urlBasedViewResolver.setOrder(1);
        return urlBasedViewResolver;
    }

    // Tiles3 설정
    @Bean
    public TilesConfigurer tilesConfigurer() {

        TilesConfigurer tilesConfigurer = new TilesConfigurer();
        tilesConfigurer.setDefinitions(new String[] {
            "/WEB-INF/tiles/definitions/*.xml"
        });
        tilesConfigurer.setCheckRefresh(true);

        return tilesConfigurer;
    }


    // InternalResourceViewResolver 설정
    @Bean
    public ViewResolver viewResolver () {

        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/WEB-INF/views/");
        internalResourceViewResolver.setSuffix(".jsp");
        internalResourceViewResolver.setOrder(2);

        return internalResourceViewResolver;
    }
}
