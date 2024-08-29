package com.bixpos.batch.db.construct;

import com.bixpos.batch.db.properties.DataSourceProperties;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

public class MybatisConfig {
    public static final String BASE_PACKAGE = "com.bixpos.batch";
}

@Configuration
@MapperScan(basePackages = MybatisConfig.BASE_PACKAGE, annotationClass = Mapper.class, sqlSessionFactoryRef = "masterSqlSessionFactory")
class MasterMyBatisConfig {

    @Value("${spring.application.accdtdb}")
    private String accdtDb;
    
    @Value("${spring.application.smsdb}")
    private String smsDb;

    @Primary
    @Bean(name = "masterSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(BasicDataSource dataSource) throws Exception {
        return SqlSessionFactoryBuilder.build(dataSource, accdtDb, smsDb);
    }

    @Bean(name = "masterTransactionManager")
    public PlatformTransactionManager transactionManager(BasicDataSource dataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
        transactionManager.setGlobalRollbackOnParticipationFailure(false);
        return transactionManager;
    }

    @Primary
    @Bean(name = "masterDataSource", destroyMethod = "")
    public BasicDataSource dataSource(DataSourceProperties dataSourceProperties) {
        return DataSourceBuilder.build(dataSourceProperties);
    }

    @Primary
    @ConfigurationProperties("spring.datasource.master")
    @Bean(name = "masterDataSourceProperties")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

}