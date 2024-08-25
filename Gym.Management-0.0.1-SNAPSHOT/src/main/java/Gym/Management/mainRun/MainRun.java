package Gym.Management.mainRun;

import java.util.Scanner;

import Gym.Management.facility.Equipment;
import Gym.Management.facility.GymHall;
import Gym.Management.papers.ExcelSheets;
import Gym.Management.papers.ExercisePlan;
import Gym.Management.papers.Subscription;
import Gym.Management.people.Admin;
import Gym.Management.people.Trainee;
import Gym.Management.people.Trainer;
import Gym.Management.sqlConnection.SQLConnection;
import Gym.Management.sqlConnection.SearchData;

public class MainRun {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		String email = "";
		String pass = "";
		int choice = 0;
		int subChoice = 0;

		//GymHall hall1 = new GymHall("Main Hall");

		System.out.println("Please Enter your Email : ");
		email = scanner.nextLine();
		System.out.println("Please Enter your Password : ");
		pass = scanner.nextLine();
		//		System.out.println(SearchData.checkAdmin(email));
		//		System.out.println(SearchData.checkAdminPass(email, pass));
		if(SearchData.checkAdmin(email) && SearchData.checkAdminPass(email, pass)) 
		{
			Admin admin = new Admin("Amr", 25, email, "01563248975", pass);
			while(choice != 6 && subChoice != 5) {
				System.out.println("Hello Admin : " + admin.getPersonName());
				System.out.println("Please Choose the data you want to deal with : ");
				System.out.println("1-Trainer");
				System.out.println("2-Trainee");
				System.out.println("3-Gym Hall");
				System.out.println("4-Gym Equipment");
				System.out.println("5-Attendance");
				System.out.println("6-Exit Program");
				choice = scanner.nextInt();
				scanner.nextLine();
				switch (choice) {
				case 1: {
					System.out.println("Please Choose the command you want to do on a Trainer : ");
					System.out.println("1-Add Trainer");
					System.out.println("2-Remove Trainer");
					System.out.println("3-Update Trainer Data");
					System.out.println("4-Back");
					System.out.println("5-Exit Program");
					subChoice = scanner.nextInt();
					scanner.nextLine();
					switch (subChoice) {
					case 1: {
						clearTerminal();
						System.out.println("Please Enter Trainer Name : ");
						String tName = scanner.nextLine();
						System.out.println("Please Enter Trainer Age : ");
						int tAge = scanner.nextInt();
						scanner.nextLine();
						System.out.println("Please Enter Trainer Salary : ");
						double tSalary = scanner.nextDouble();
						scanner.nextLine();
						System.out.println("Please Enter Trainer Email : ");
						String tEmail = scanner.nextLine();
						System.out.println("Please Enter Trainer Phone : ");
						String tPhone = scanner.nextLine();
						System.out.println("Please Enter Trainer Password : ");
						String tPassword = scanner.nextLine();
						System.out.println("Please Enter Trainer Working Hours : ");
						int tWorkingHours = scanner.nextInt();
						scanner.nextLine();
						System.out.println("Please Enter Trainer Hall number : ");
						int tHallno = scanner.nextInt();
						scanner.nextLine();

						Trainer trainer = new Trainer(tName, tAge, tSalary, tEmail, tPhone, tPassword, tWorkingHours, tHallno);
						admin.addTrainer(trainer);
						break;
					}
					case 2: {
						clearTerminal();
						//Remove Trainer
						System.out.println("Please Enter Trainer ID : ");
						int tID = scanner.nextInt();
						scanner.nextLine();
						admin.removeTrainer(tID);
						break;
					}
					case 3: {
						//Update Trainer Data
						
						break;
					}
					case 4: {
						//Back
						
						break;
					}
					case 5: {
						//Exit program
						
						break;
					}
					default: {
						System.out.println("Invalid Choice " + subChoice);
						break;
					}
					}
					break;
				}
				case 2: {
					System.out.println("Please Choose the command you want to do on a Trainee : ");
					System.out.println("1-Add Trainee");
					System.out.println("2-Remove Trainee");
					System.out.println("3-Update Trainee Data");
					System.out.println("4-Back");
					System.out.println("5-Exit Program");
					subChoice = scanner.nextInt();
					scanner.nextLine();
					switch (subChoice) {
					case 1: {
						clearTerminal();
						//Add Trainee
						System.out.println("Please Enter Trainee Name : ");
						String tName = scanner.nextLine();
						System.out.println("Please Enter Trainee Age : ");
						int tAge = scanner.nextInt();
						scanner.nextLine();
						System.out.println("Please Enter Trainee Email : ");
						String tEmail = scanner.nextLine();
						System.out.println("Please Enter Trainee Phone : ");
						String tPhone = scanner.nextLine();
						System.out.println("Please Enter Trainee Password : ");
						String tPassword = scanner.nextLine();
						System.out.println("Please Enter Trainee Subscription Type : ");
						String tSub = scanner.nextLine();
						System.out.println("Please Enter Exercise Plan Duration : ");
						int exPlanDuration = scanner.nextInt();
						scanner.nextLine();

						Subscription sub = new Subscription(tSub);
						ExercisePlan exPlan = new ExercisePlan(exPlanDuration);
						exPlan.addStep("Do 4*13 Pushup");
						exPlan.addStep("Do 4*13 Caplecross");
						exPlan.addStep("Do 3*13 Butterfly");

						Trainee trainee101 = new Trainee(tName, tAge, tEmail, tPhone, tPassword, sub, exPlan);

						SQLConnection.insertExercisePlan(exPlan);
						SQLConnection.insertSubscription(sub);
						SQLConnection.insertTrainee(trainee101);
						break;
					}
					case 2: {
						clearTerminal();
						//Remove Trainee
						System.out.println("Please Enter Trainee ID : ");
						int tID = scanner.nextInt();
						scanner.nextLine();
						admin.removeTrainee(tID);
						break;
					}
					case 3: {
						//Update Trainee Data
						
						break;
					}
					case 4: {
						//Back
						
						break;
					}
					case 5: {
						//Exit Program
						
						break;
					}
					default: {
						System.out.println("Invalid Choice " + subChoice);
						break;
					}
					}
					break;
				}
				case 3: {
					System.out.println("Please Choose the command you want to do on a Gym Hall : ");
					System.out.println("1-Add Hall");
					System.out.println("2-Remove Hall");
					System.out.println("3-Update Hall Data");
					System.out.println("4-Back");
					System.out.println("5-Exit Program");
					subChoice = scanner.nextInt();
					scanner.nextLine();
					switch (subChoice) {
					case 1: {
						clearTerminal();
						//Add Hall
						System.out.println("Please Enter Hall Name : ");
						String hallName = scanner.nextLine();
						
						GymHall hall = new GymHall(hallName);
						admin.addHall(hall);
						break;
					}
					case 2: {
						clearTerminal();
						//Remove Hall
						System.out.println("Please Enter Hall ID : ");
						int hallID = scanner.nextInt();
						scanner.nextLine();
						admin.removeHall(hallID);
						break;
					}
					case 3: {
						//Update Hall Data
						
						break;
					}
					case 4: {
						//Back
						
						break;
					}
					case 5: {
						//Exit Program
						
						break;
					}
					default: {
						System.out.println("Invalid Choice " + subChoice);
						break;
					}
					}
					break;
				}
				case 4: {
					System.out.println("Please Choose the command you want to do on Equipment : ");
					System.out.println("1-Add Equipment");
					System.out.println("2-Remove Equipment");
					System.out.println("3-Update Equipment Data");
					System.out.println("4-Back");
					System.out.println("5-Exit Program");
					subChoice = scanner.nextInt();
					scanner.nextLine();
					switch (subChoice) {
					case 1: {
						clearTerminal();
						//Add Equipment
						System.out.println("Please Enter Equipment Name : ");
						String eqName = scanner.nextLine();
						System.out.println("Please Enter Equipment Type : ");
						String eqType = scanner.nextLine();
						System.out.println("Please Enter Hall ID : ");
						int hallID = scanner.nextInt();
						scanner.nextLine();
						
						Equipment eq = new Equipment(eqName, eqType);
						admin.addEquipment(hallID, eq);
						break;
					}
					case 2: {
						clearTerminal();
						//Remove Equipment
						System.out.println("Please Enter Equipment ID : ");
						int eqID = scanner.nextInt();
						scanner.nextLine();
						
						admin.removeEquipment(eqID);
						break;
					}
					case 3: {
						//Update Hall Data
						
						break;
					}
					case 4: {
						//Back
						
						break;
					}
					case 5: {
						//Exit Program
						
						break;
					}
					default: {
						System.out.println("Invalid Choice " + subChoice);
						break;
					}
					}
					break;
				}
				case 5: {
					//Attendance
					System.out.println("Please Choose the data you want to deal with : ");
					System.out.println("1-Trainer");
					System.out.println("2-Trainee");
					System.out.println("3-Search");
					System.out.println("4-Back");
					System.out.println("5-Exit Program");
					subChoice = scanner.nextInt();
					scanner.nextLine();
					switch (subChoice) {
					case 1: {
						//Attendance for Trainers
						System.out.println("Please Enter Trainer Email : ");
						String tEmail = scanner.nextLine();
						Trainer trainer = SearchData.searchTrainer(tEmail);
						ExcelSheets.takeAttendace(trainer);
						break;
					}
					case 2: {
						//Attendance for Trainees
						System.out.println("Please Enter Trainee Email : ");
						String tEmail = scanner.nextLine();
						Trainee trainee = SearchData.searchTrainee(tEmail);
						ExcelSheets.takeAttendace(trainee);
						break;
					}
					case 3: {
						//Search
						System.out.println("1-Trainer");
						System.out.println("2-Trainee");
						System.out.println("3-Back");
						int subChoiceSearch = scanner.nextInt();
						scanner.nextLine();
						switch (subChoiceSearch) {
						case 1: {
							System.out.println("Please Enter Trainer Email : ");
							String tEmail = scanner.nextLine();
							Trainer trainer = SearchData.searchTrainer(tEmail);
							System.out.println(trainer);
							break;
						}
						case 2: {
							System.out.println("Please Enter Trainee Email : ");
							String tEmail = scanner.nextLine();
							Trainee trainee = SearchData.searchTrainee(tEmail);
							System.out.println(trainee);
							break;
						}
						case 3: {
							//Back
							break;
						}
						default: {
							System.out.println("Invalid Choice " + subChoice);
							break;
						}
						}
						break;
					}
					case 4: {
						//Back
						
						break;
					}
					case 5: {
						//Exit
						
						break;
					}
					default: {
						System.out.println("Invalid Choice " + subChoice);
						break;
					}
					}
					break;
				}
				case 6: {
					//Exit Program
					
					break;
				}
				default:
					System.out.println("Invalid Choice: " + choice);
				}
			}
			System.out.println("Exiting Program :)");
		}

		scanner.close();
	}


	public static void clearTerminal() 
	{
		//		System.out.print("\033[" + "2J");
	}
	
}



