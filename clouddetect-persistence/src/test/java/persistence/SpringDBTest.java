package persistence;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.ext.hsqldb.HsqldbDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.BeforeMethod;

public abstract class SpringDBTest {

	protected static ApplicationContext appContext;

	private static DataSourceDatabaseTester datasourceTester;

	static {
		// load spring stuff
		appContext = new ClassPathXmlApplicationContext(
				new String[] { "cloudwatch-test.xml" });
		datasourceTester = new DataSourceDatabaseTester((DataSource) appContext
				.getBean("dataSource"));
	}
	
	
	@BeforeMethod
	protected void before() throws Exception {
		//		 initialize your database connection here
       IDatabaseConnection connection = getConnection();
       
       // configure the connection for hsqldb datatypes (otherwise boolean is not recognized)
       DatabaseConfig config = connection.getConfig();
       config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY,
               new HsqldbDataTypeFactory());

       // initialize your dataset here
       IDataSet dataSet = getDataSet();

       try
       {
    	   // ugly hack to solve referential integrity probs
    	   
//    	   connection.getConnection().createStatement().execute("SET CONSTRAINTS ALL DEFERRED;");
    	   connection.getConnection().createStatement().execute("SET REFERENTIAL_INTEGRITY FALSE");
    	   DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
    	   connection.getConnection().createStatement().execute("SET REFERENTIAL_INTEGRITY TRUE");
//    	   connection.getConnection().createStatement().execute("SET CONSTRAINTS;");
       } catch (DatabaseUnitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       finally
       {
           try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       }
	}

	protected IDatabaseConnection getConnection() throws Exception {
		return datasourceTester.getConnection();
	}

	/*
	 * IDataSet expectedDataSet = new FlatXmlDataSet(new
	 * File("expectedDataSet.xml"));
	 */
	protected abstract IDataSet getDataSet() throws Exception;
	
	protected IDataSet getFlatXmlDataSet(String file) throws Exception {
		return new FlatXmlDataSet(this.getClass().getResourceAsStream(file), false);	
	}
}
