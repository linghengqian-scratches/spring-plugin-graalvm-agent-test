package com.lingh;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.creator.DataSourceProperty;
import com.baomidou.dynamic.datasource.creator.DefaultDataSourceCreator;
import com.baomidou.dynamic.datasource.creator.hikaricp.HikariCpConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(classes = AddRemoveDatasourceApplication.class, webEnvironment = RANDOM_PORT)
public class AddRemoveDatasourceTest {
    @Autowired
    DataSource dataSource;
    @Autowired
    DefaultDataSourceCreator dataSourceCreator;

    @Test
    void testAddAndRemoveDataSource() {
        HikariCpConfig hikariCpConfig = new HikariCpConfig();
        hikariCpConfig.setConnectionTestQuery("select 1");
        DataSourceProperty dataSourceProperty = new DataSourceProperty();
        dataSourceProperty.setHikari(hikariCpConfig);
        dataSourceProperty.setPoolName("slave_1");
        dataSourceProperty.setUsername("sa");
        dataSourceProperty.setPassword("");
        dataSourceProperty.setUrl("jdbc:h2:mem:test1;MODE=MySQL");
        dataSourceProperty.setDriverClassName("org.h2.Driver");
        DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
        ds.addDataSource(dataSourceProperty.getPoolName(), dataSourceCreator.createDataSource(dataSourceProperty));
        assertThat(ds.getDataSources().keySet()).contains("slave_1");
        ds.removeDataSource("slave_1");
        assertThat(ds.getDataSources().keySet()).doesNotContain("slave_1");
    }
}

@SpringBootApplication
class AddRemoveDatasourceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AddRemoveDatasourceApplication.class, args);
    }
}
