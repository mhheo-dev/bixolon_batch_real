package com.bixpos.batch.db.construct;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * SqlSessionFactoryBuilder
 */
public class SqlSessionFactoryBuilder {
    private static final String CONFIG_LOCATION_PATH = "classpath:/mybatis-config.xml";
    private static final String MAPPER_LOCATIONS_PATH = "classpath:/mapper/**/*.xml";
    private static final String TYPE_ALIASES_PACKAGE = "com.bixpos.batch";
    private static final String TYPE_HANDLERS_PACKAGE = "com.bixpos.batch";

    /**
     * SqlSessionFactory 설정
     * @param dataSource DataSource 정보
     * @return SqlSessionFactory
     * @throws Exception 예외가 발생할 경우 던짐
     */
    public static SqlSessionFactory build(DataSource dataSource, String accdtDb, String smsDb) throws Exception {
        PathMatchingResourcePatternResolver pathResolver = new PathMatchingResourcePatternResolver();

        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setTypeAliasesPackage(TYPE_ALIASES_PACKAGE);
        sessionFactoryBean.setTypeHandlersPackage(TYPE_HANDLERS_PACKAGE);
        sessionFactoryBean.setConfigLocation(pathResolver.getResource(CONFIG_LOCATION_PATH));
        sessionFactoryBean.setMapperLocations(pathResolver.getResources(MAPPER_LOCATIONS_PATH));
        
        Properties configurationProperties = new Properties();
        configurationProperties.put("accdtdb", accdtDb);
        configurationProperties.put("smsdb", smsDb);
        sessionFactoryBean.setConfigurationProperties(configurationProperties);

        return sessionFactoryBean.getObject();
    }
}