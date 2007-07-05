package persistence;

import java.io.FileOutputStream;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.BeforeTest;


public class DataSetExporter {
	
    private static ApplicationContext appContext;
    private static DataSourceDatabaseTester datasourceTester;

	static {
//		 load spring stuff
      appContext = new ClassPathXmlApplicationContext(
              new String[] { "cloudwatch-test.xml" });
      datasourceTester = new DataSourceDatabaseTester((DataSource) appContext.getBean("dataSource"));
	}
    
    protected void export() throws Exception {
		// database connection
		IDatabaseConnection connection = getConnection();

		// partial database export
//		QueryDataSet partialDataSet = new QueryDataSet(connection);
//		partialDataSet.addTable("FOO", "SELECT * FROM TABLE WHERE COL='VALUE'");
//		partialDataSet.addTable("BAR");
//		FlatXmlDataSet.write(partialDataSet,
//				new FileOutputStream("partial.xml"));

		// full database export
		IDataSet fullDataSet = connection.createDataSet();
		FlatXmlDataSet.write(fullDataSet, new FileOutputStream("full.xml"));

		// dependent tables database export: export table X and all tables that
		// have a PK which is a FK on X, in the right order for insertion
//		String[] depTableNames = TablesDependencyHelper.getAllDependentTables(
//				connection, "X");
//		IDataSet depDataset = connection.createDataSet(depTableNames);
//		FlatXmlDataSet
//				.write(depDataSet, new FileOutputStream("dependents.xml"));
	}
	
	protected IDatabaseConnection getConnection() throws Exception {
		return datasourceTester.getConnection();
	}

	public static void main(String[] args) {
		DataSetExporter exp = new DataSetExporter();
		try {
			exp.export();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
