package Gym.Management.sqlConnection;

public class TestDatabaseCreate {

	public static void main(String[] args) {

		SQLConnection.createDB();
		SQLConnection.createTables();
		
//		SQLConnection.deleteDB();
	}

}
