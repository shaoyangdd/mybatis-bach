package com.example.mybatisbach.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @Author kangshaofei
 * @Description
 * @Date 2018/1/30
 **/
@Configuration
public class TransactionConfig{


    @Bean
    public DataSourceTransactionManager transactionManager(@Qualifier("mybatisMasterDataSource")DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "mybatisMasterDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource mybatisMasterDataSource() {
//        return new DriverManagerDataSource("jdbc:mysql://localhost:3306/test","root","root");
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "mybatisMasterSqlSessionFactory")
    public SqlSessionFactory mybatisMasterSqlSessionFactory(@Qualifier("mybatisMasterDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapping/*.xml"));

        // 设置MyBatis分页插件
//        PageInterceptor pageInterceptor = this.initPageInterceptor();
//        bean.setPlugins(new Interceptor[]{pageInterceptor});

        return bean.getObject();
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer mScannerConfigurer = new MapperScannerConfigurer();
        mScannerConfigurer.setSqlSessionFactoryBeanName("mybatisMasterSqlSessionFactory");
//        mScannerConfigurer.setBasePackage("com.my.boot.test.entity");
        mScannerConfigurer.setBasePackage("com.example.mybatisbach.dao");
        return mScannerConfigurer;
    }

    @Bean(name = "mybatisMasterTransactionManager")
    public DataSourceTransactionManager mybatisMasterTransactionManager(@Qualifier("mybatisMasterDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "mybatisMasterSqlSessionTemplate")
    public SqlSessionTemplate mybatisMasterSqlSessionTemplate(@Qualifier("mybatisMasterSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

//    public PageInterceptor initPageInterceptor(){
//        PageInterceptor pageInterceptor = new PageInterceptor();
//        Properties properties = new Properties();
//        properties.setProperty("helperDialect", "mysql");
//        properties.setProperty("offsetAsPageNum", "true");
//        properties.setProperty("rowBoundsWithCount", "true");
//        pageInterceptor.setProperties(properties);
//        return pageInterceptor;
//    }


}
