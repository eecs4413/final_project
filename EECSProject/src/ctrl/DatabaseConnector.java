package ctrl;

import javax.sql.DataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

// This Class is responsible for holding information about connecting to the database

public class DatabaseConnector {

	public static DataSource retriveDatabaseInfo() {
		MysqlDataSource Mysqlds = new MysqlDataSource();
		Mysqlds.setUrl("jdbc:mysql://localhost:3306/MYSQL");
		Mysqlds.setUser("root");
		Mysqlds.setPassword("admin");
		return Mysqlds;

	}

}
