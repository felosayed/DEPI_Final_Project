package Gym.Management.sqlConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SearchData {
	
	// Database connection details
	static String url = "jdbc:mysql://localhost:3306/gym";
    static String username = "Amr";
    static String password = "Amr_12saber";

    
    public static void searchTrainee(int subID) 
    {
    	// SQL SELECT query
        String query = "SELECT Person.Name, Person.Email, Trainee.Points FROM Person "
        		+ "INNER JOIN Trainee ON Person.Id = Trainee.Id WHERE Trainee.SubscriptionId = " + subID;
        
        try {
            // Establish connection to the database
            Connection connection = DriverManager.getConnection(url, username, password);
            
            // Create a statement to execute the query
            Statement statement = connection.createStatement();
            
            // Execute the query and store the result
            ResultSet resultSet = statement.executeQuery(query);
            
            // Iterate over the result set and print the results
            while (resultSet.next()) {
                String column1 = resultSet.getString("Name");
                String column2 = resultSet.getString("Email");
                String column3 = resultSet.getString("Points");
                
                System.out.println("Name: " + column1 + "\nEmail: " + column2 + "\nPoints: " + column3);
            }
            
            // Close the connection and other resources
            resultSet.close();
            statement.close();
            connection.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //*****************************************************************************************************************//
    public static void searchTrainer(int trainerID) 
    {
    	// SQL SELECT query
        String query = "SELECT Person.Name, Person.Email, Trainer.Rating FROM Person "
        		+ "INNER JOIN Trainer ON Person.Id = Trainer.Id WHERE Trainer.Id = " + trainerID;
        
        try {
            // Establish connection to the database
            Connection connection = DriverManager.getConnection(url, username, password);
            
            // Create a statement to execute the query
            Statement statement = connection.createStatement();
            
            // Execute the query and store the result
            ResultSet resultSet = statement.executeQuery(query);
            
            // Iterate over the result set and print the results
            while (resultSet.next()) {
                String column1 = resultSet.getString("Name");
                String column2 = resultSet.getString("Email");
                String column3 = resultSet.getString("Rating");
                
                System.out.println("Name: " + column1 + "\nEmail: " + column2 + "\nPoints: " + column3);
            }
            
            // Close the connection and other resources
            resultSet.close();
            statement.close();
            connection.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //*****************************************************************************************************************//
    public static boolean checkAdmin(String adminEmail) 
    {
    	String query = "SELECT 1 FROM Person p JOIN Admin a ON p.Id = a.PersonId WHERE p.email = ?";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Set the email parameter in the query
            preparedStatement.setString(1, adminEmail);

            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if a result exists
            if (resultSet.next()) {
                //System.out.println("The email exists in the database.");
                return true;
            } else {
                System.out.println("The email does not exist in the database.");
                return false;
            }

        } catch (SQLException e) {
        	System.out.println("Error occured : " + e.getMessage());
            return false;
        }
    }
    //*****************************************************************************************************************//
    public static boolean checkAdminPass(String adminEmail, String adminPass) 
    {
    	String query = "SELECT Password FROM Person INNER JOIN Admin ON Person.Id = Admin.PersonId WHERE Person.email = ?";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Set the email parameter in the query
            preparedStatement.setString(1, adminEmail);

            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if a result exists
            if (resultSet.next()) {
                //System.out.println("The email exists in the database and password = " + resultSet.getString("Password"));
                if(resultSet.getString("Password").equals(adminPass)) 
                {
                	return true;
                }
                else {
                	return false;
                }
            } else {
                System.out.println("The email does not exist in the database.");
                return false;
            }

        } catch (SQLException e) {
        	System.out.println("Error occured : " + e.getMessage());
            return false;
        }
    }
    //*****************************************************************************************************************//

    
	public static void main(String[] args) {
		
		
		System.out.println(SearchData.checkAdmin("Admin101@gmail.com"));
		System.out.println(SearchData.checkAdminPass("Admin101@gmail.com", "Test123456"));
        
        
	}

}
