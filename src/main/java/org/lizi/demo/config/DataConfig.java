package org.lizi.demo.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.lizi.demo.dao.cache.RedisDao;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.beans.PropertyVetoException;
import java.io.IOException;

/**
 * Created by touch on 2017/1/18.
 */
@Configuration
@MapperScan("org.lizi.demo.dao")
@EnableTransactionManagement
public class DataConfig {
    @Bean
    public ComboPooledDataSource dataSource(){
        ComboPooledDataSource dataSource=new ComboPooledDataSource();
        try {
            dataSource.setDriverClass("com.mysql.jdbc.Driver");
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/redpackets");
        dataSource.setUser("root");
        dataSource.setPassword("568355");
        dataSource.setMaxPoolSize(30);//连接池最大最小连接数量
        dataSource.setMinPoolSize(10);
        dataSource.setAutoCommitOnClose(false);//关闭连接后不自动commit
        dataSource.setCheckoutTimeout(1000);//获取连接超时时间
        dataSource.setAcquireRetryAttempts(2);//获取连接失败重试次数
        return dataSource;
    }
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean() throws IOException {
        SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
        bean.setDataSource(dataSource());//注入连接池
        bean.setConfigLocation(new ClassPathResource("/mybatis-config.xml"));//配置mybatis全局配置文件
        bean.setTypeAliasesPackage("org.lizi.demo.domain");
        return bean;
    }
    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(){
        DataSourceTransactionManager transactionManager=new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource());
        return transactionManager;
    }
    @Bean
    public RedisDao redisDao(){
        return new RedisDao("127.0.0.1",6379);
    }
}
