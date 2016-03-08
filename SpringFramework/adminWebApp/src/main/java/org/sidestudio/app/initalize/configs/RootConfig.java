package org.sidestudio.app.initalize.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;

/**
 * 서블릿 전체에 설정
 *
 * @author logan
 * @since 2016-03-03
 */
@Configuration
@Import({RepositoryConfig.class, SpringSecurityConfig.class})
@ComponentScan(basePackages = {"org.sidestudio.app"}, excludeFilters = {@ComponentScan.Filter(Controller.class), @ComponentScan.Filter(Configuration.class)})
public class RootConfig {
}
