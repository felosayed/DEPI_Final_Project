package Gym.Management.sqlConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import Gym.Management.papers.ExercisePlan;
import Gym.Management.papers.Subscription;
import Gym.Management.people.Trainee;
import Gym.Management.people.Trainer;

public class SearchData {
	
	// Database connection details
	static String url = "jdbc:mysql://localhost:3306/gym";
    static String username = "Amr";
    static String password = "Amr_12saber";

    
    public static Trainee searchTrainee(String traineeEmail) 
    {
    	String tname = "";
    	int tage = 0;
    	String tphone = "";
    	String tpassword = "";
    	int sID = 0;
    	String sType = "";
    	LocalDate sStartDate = null;
    	LocalDate sEndDate = null;
    	int exID = 0;
    	int exDuration = 0;
    	int tpoints = 0;
    	
    	// SQL SELECT query
        String query = "SELECT Person.Name, Person.Age, Person.PhoneNumber, Person.Password,"
        		+ " Trainee.SubscriptionId, Trainee.ExercisePlanId, Trainee.Points"
        		+ "FROM Person INNER JOIN Trainee ON Person.Id = Trainee.PersonId WHERE Person.Email = " + traineeEmail;
        
        
        try {
            // Establish connection to the database
            Connection connection = DriverManager.getConnection(url, username, password);
            
            // Create a statement to execute the query
            Statement statement = connection.createStatement();
            
            // Execute the query and store the result
            ResultSet resultSet = statement.executeQuery(query);
            
            // Iterate over the result set and print the results
            while (resultSet.next()) {
                tname = resultSet.getString("Name");
                tage = resultSet.getInt("Agg");
                tphone = resultSet.getString("PhoneNumber");
                tpassword = resultSet.getString("Password");
                sID = resultSet.getInt("SubscriptionId");
                exID = resultSet.getInt("ExercisePlanId");
                tpoints = resultSet.getInt("Points");
            }
            String querySub = "SELECT Subscription.Type, Subscription.StartDate, Subscription.EndDate"
            		+ " FROM Subscription WHERE Subscription.Id = " + sID;
            
            String queryExplan = "SELECT ExercisePlan.Duration"
            		+ " FROM ExercisePlan WHERE ExercisePlan.Id = " + exID;
            
            resultSet = statement.executeQuery(querySub);
            while (resultSet.next()) {
            	sType = resultSet.getString("Type");
            	sStartDate = resultSet.getDate("StartDate").toLocalDate();
            	sEndDate = resultSet.getDate("EndDate").toLocalDate();
            }
            resultSet = statement.executeQuery(queryExplan);
            while (resultSet.next()) {
            	exDuration = resultSet.getInt("Duration");
            }
            Subscription sub = new Subscription(sType);
            sub.setStartDate(sStartDate);
            sub.setEndtDate(sEndDate);
            ExercisePlan plan = new ExercisePlan(exDuration);
            Trainee t = new Trainee(tname, tage, traineeEmail, tphone, tpassword, sub, plan);
            t.setPoints(tpoints);
            // Close the connection and other resources
            resultSet.close();
            statement.close();
            connection.close();
            return t;
        } catch (Exception e) {
        	System.out.println(e.getMessage());
            return null;
        }
    }
    
    //*****************************************************************************************************************//
    public static Trainer searchTrainer(String trainerEmail) 
    {
    	String tname = "";
    	int tage = 0;
    	double tsalary = 0.0;
    	String tphone = "";
    	String tpassword = "";
    	int tWH = 0;
    	int tAssignedHall = 0;
    	// SQL SELECT query
        String query = "SELECT Person.Name, Person.Age, Person.PhoneNumber, Person.Password"
        		+ " Trainer.Salary, Trainer.WorkingHours, Trainer.GymHallId FROM Person "
        		+ "INNER JOIN Trainer ON Person.Id = Trainer.PersonId WHERE Trainer.Email = " + trainerEmail;
        
        try {
            // Establish connection to the database
            Connection connection = DriverManager.getConnection(url, username, password);
            
            // Create a statement to execute the query
            Statement statement = connection.createStatement();
            
            // Execute the query and store the result
            ResultSet resultSet = statement.executeQuery(query);
            
            // Iterate over the result set and print the results
            while (resultSet.next()) {
            	tname = resultSet.getString("Name");
            	tage = resultSet.getInt("Age");
            	tphone = resultSet.getString("PhoneNumber");
            	tpassword = resultSet.getString("Password");
            	tsalary = resultSet.getDouble("Salary");
            	tWH = resultSet.getInt("WorkingHours");
            	tAssignedHall = resultSet.getInt("GymHallId");
            }
            Trainer t = new Trainer(tname, tage, tsalary, trainerEmail, tphone, tpassword, tWH, tAssignedHall);
            // Close the connection and other resources
            resultSet.close();
            statement.close();
            connection.close();
            return t;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
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
