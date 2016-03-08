package org.sidestudio.app.initalize.configs;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Database 연결, ORM 설정, 트렌잭션 설정
 *
 * @author logan
 * @since 2016-03-04
 */
@Configuration
/*
 * Spring 프레임워크의 어노 테이션 기반 트랜잭션 관리(명시적)를 사용할 수 있도록 한다.
 * <tx:annotation-driven>
 */
@EnableTransactionManagement
@MapperScan(basePackages = {"org.sidestudio.app.repository"}, annotationClass = Repository.class)
public class RepositoryConfig {

    /**
     * Jndi 룩업 설정
     *
     * @author Logan Lee
     * @since 2016-03-05
     */
    @Bean
    public DataSource dataSource() {

        // lookup 사용
        JndiDataSourceLookup jndiDataSourceLookup = new JndiDataSourceLookup();
        jndiDataSourceLookup.setResourceRef(true);

        return jndiDataSourceLookup.getDataSource("jdbc/database");
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    /**
     * myBatis의 {@link org.apache.ibatis.session.SqlSessionFactory}을 생성하는 팩토리빈을 등록한다.
     *
     * @author Logan Lee
     * @since 2016-03-05
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(
            DataSource dataSource, ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        // 마이바티스가 사용한 DataSource를 등록
        factoryBean.setDataSource(dataSource);
        // resources/mybatis/repository 패키지 이하의 모든 XML을 매퍼로 등록
        factoryBean.setMapperLocations(applicationContext.getResources("classpath:mybatis/mappers/**/*.xml"));
        // Mybatis 설정
        settingMybatisConfinguration(factoryBean);
        return factoryBean.getObject();
    }

    /**
     * 마이바티스 설정
     *
     * @author Logan Lee
     * @since 2016-03-05
     * @param sqlSessionFactoryBean
     */
    private void settingMybatisConfinguration(SqlSessionFactoryBean sqlSessionFactoryBean) throws Exception {

        sqlSessionFactoryBean.getObject().getConfiguration().setCacheEnabled(false); // 전역 캐쉬 사용안함
        sqlSessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true); // 언더 스코프 명칭 카멜 명칭으로 변환
    }

    /**
     * 마이바티스 {@link org.apache.ibatis.session.SqlSession} 빈을 등록한다.
     *
     * SqlSessionTemplate은 SqlSession을 구현하고 코드에서 SqlSession를 대체하는 역할을 한다.
     * 쓰레드에 안전하게 작성되었기 때문에 여러 DAO나 매퍼에서 공유 할 수 있다.
     *
     * @author Logan Lee
     * @since 2016-03-05
     */
    @Bean
    public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
