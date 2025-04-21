
package application;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.sql.DataSource;

import factory.RmiServerRegistry;
import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;

public class MainServerFactoryApi {
  public static void main(String[] args) {
    try {
      Registry registry = LocateRegistry.createRegistry(1099);

      // call bindServices to register services and set data source
      RmiServerRegistry.bindServices(registry, 1098, getDataSource());
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public static DataSource getDataSource() throws Exception {
    PoolDataSource poolDataSource = PoolDataSourceFactory.getPoolDataSource();
    poolDataSource.setConnectionFactoryClassName("oracle.jdbc.pool.OracleDataSource");
    poolDataSource.setURL("jdbc:oracle:thin:@oracle_db:1521/xepdb1");
    poolDataSource.setUser("dbw_examples");
    poolDataSource.setPassword("dbw_examples");
    return poolDataSource;
  }
}
