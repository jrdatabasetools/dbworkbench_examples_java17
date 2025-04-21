/*
 * Copyright (c) jr-database-tools GmbH, Switzerland, 2015-2025. All rights reserved.
 */

package application;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.sql.DataSource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import factory.RmiServerRegistry;
import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;

@Configuration
public class MainServerSpringApi {
  public static void main(String[] args) {
    System.setProperty("port", "1098");

    try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext()) {
      ctx.register(MainServerSpringApi.class);
      ctx.register(RmiServerRegistry.class);
      ctx.scan("impl");
      ctx.refresh();

      RmiServerRegistry rmiServerRegistry = ctx.getBean(RmiServerRegistry.class);
      rmiServerRegistry.bindServices();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  @Bean
  public Registry getRegistry() throws RemoteException {
    return LocateRegistry.createRegistry(1099);
  }

  @Bean
  public DataSource getDataSource() throws Exception {
    PoolDataSource poolDataSource = PoolDataSourceFactory.getPoolDataSource();
    poolDataSource.setConnectionFactoryClassName("oracle.jdbc.pool.OracleDataSource");
    poolDataSource.setURL("jdbc:oracle:thin:@oracle_db:1521/xepdb1");
    poolDataSource.setUser("dbw_examples");
    poolDataSource.setPassword("dbw_examples");
    return poolDataSource;
  }
}
