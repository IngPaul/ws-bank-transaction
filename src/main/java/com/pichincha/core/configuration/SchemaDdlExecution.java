package com.pichincha.core.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;

@Component
public class SchemaDdlExecution implements ApplicationRunner
{
    @Autowired
    private DatabaseClient databaseClient;

    @Override
    public void run(ApplicationArguments arg0) throws Exception {
        executeSchema();
    }


    private void executeSchema(){
        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(databaseClient.getConnectionFactory());
        initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("db/schema.sql")));
        initializer.afterPropertiesSet();
    }
}