package com.example.config;

import com.example.blog.mapper.BlogMapper;
import com.example.cart.dao.CartMapper;
import com.example.product.dao.CategoryMapper;
import com.example.product.dao.ImageMapper;
import com.example.product.dao.ProductCategoryMapper;
import com.example.product.dao.ProductMapper;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
public class MyBatisConfig {
    // Tạo bean dataSource
    @Bean
    public DriverManagerDataSource dataSource() throws IOException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        Properties properties = new Properties();
        InputStream user_props = this.getClass()
                .getResourceAsStream("/application.properties");
        properties.load(user_props);
        dataSource.setUrl(properties.getProperty("spring.datasource.url"));
        dataSource.setUsername(properties.getProperty("spring.datasource.username"));
        dataSource.setPassword(properties.getProperty("spring.datasource.password"));
        return dataSource;
    }

    // đọc thông tin file cấu hình MyBatis
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
        Resource resource = new ClassPathResource("SqlMapConfig.xml");
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource());
        sqlSessionFactory.setConfigLocation(resource);
        return sqlSessionFactory;
    }

    // scan tất cả những mapper
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.example.user.dao");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        return mapperScannerConfigurer;
    }

    // Có thể tạo @Bean cho mỗi interface mapper thay vì scan toàn bộ
    @Bean (name = "productMapper")
    public ProductMapper productMapper() throws Exception {
        SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory().getObject());
        return sessionTemplate.getMapper(ProductMapper.class);
    }

    @Bean (name = "productCategoryMapper")
    public ProductCategoryMapper productCategoryMapper() throws Exception {
        SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory().getObject());
        return sessionTemplate.getMapper(ProductCategoryMapper.class);
    }
    @Bean (name = "imageMapper")
    public ImageMapper imageMapperMapper() throws Exception {
        SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory().getObject());
        return sessionTemplate.getMapper(ImageMapper.class);
    }

    @Bean (name = "categoryMapper")
    public CategoryMapper categoryMapper() throws Exception {
        SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory().getObject());
        return sessionTemplate.getMapper(CategoryMapper.class);
    }

    @Bean (name = "cartMapper")
    public CartMapper cartMapper() throws Exception {
        SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory().getObject());
        return sessionTemplate.getMapper(CartMapper.class);
    }
    
    @Bean (name = "blogMapper")
    public BlogMapper blogMapper() throws Exception {
        SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory().getObject());
        return sessionTemplate.getMapper(BlogMapper.class);
    }
}
