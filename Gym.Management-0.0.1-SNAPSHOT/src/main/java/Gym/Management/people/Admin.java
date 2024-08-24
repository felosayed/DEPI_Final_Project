package Gym.Management.people;


import Gym.Management.facility.Equipment;
import Gym.Management.facility.GymHall;
import Gym.Management.mainRun.Logs;
import Gym.Management.papers.ExercisePlan;
import Gym.Management.sqlConnection.SQLConnection;

public class Admin extends Person implements AdminControl{

	public Admin(String personName, int personAge, String personEmail, String personPhone, String personPassword) {
		super(personName, personAge, personEmail, personPhone, personPassword);
	}

	@Override
	public void addHall(GymHall hall) {
		if(hall == null) {
			System.out.println("Invalid Data for a Hall");
		}
		else {
			//Admin.allHalls.add(hall);
			SQLConnection.insertGymHall(hall);
			Logs.addLogs(this, hall.getGymHallName(), "insert");
		}
	}

	@Override
	public void removeHall(int hallID) {
		if(hallID <= 0) {
			System.out.println("This Hall doesn't exist");
		}
		else {
			//Admin.allHalls.remove(hall);
			SQLConnection.removeHall(hallID);
			Logs.addLogs(this, hallID, "delete");
		}
	}

	@Override
	public void addEquipment(int hallID, Equipment equipment) {
		if(hallID <= 0 || equipment == null) {
			System.out.println("An Error occurred");
		}
		else {
			//index = Admin.allHalls.indexOf(hall);
			//Admin.allEquipments.get(Admin.allHalls.indexOf(hall)).add(equipment);
			SQLConnection.insertEquipment(equipment);
			SQLConnection.addEqToHall(equipment, hallID);
			Logs.addLogs(this, equipment.getEquipmentName(), "add to "+hallID);
		}
	}

	@Override
	public void removeEquipment(int eqID) {

		SQLConnection.removeEquipment(eqID);
		Logs.addLogs(this, eqID, "remove");
	}

	@Override
	public void addTrainer(Trainer trainer) {
		
		SQLConnection.insertTrainer(trainer);
		Logs.addLogs(this, trainer.getPersonEmail(), "insert");
	}

	@Override
	public void removeTrainer(int trainerID) {
		
		SQLConnection.removeTrainer(trainerID);
		Logs.addLogs(this, trainerID, "remove");
	}

	@Override
	public void assigneTrainer(Trainer trainer, GymHall hall) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addTrainee(Trainee trainee) {
		
		SQLConnection.insertTrainee(trainee);
		Logs.addLogs(this, trainee.getPersonEmail(), "insert");
	}

	@Override
	public void removeTrainee(int traineeID) {
		
		SQLConnection.removeTrainee(traineeID);
		Logs.addLogs(this, traineeID, "remove");
	}

	@Override
	public void changeTraineePlan(Trainee trainee, ExercisePlan plan) {
		// TODO Auto-generated method stub
		
	}

	//////////////////Additional Methods//////////////////////////
	Utils checkdiscount = (t -> {if(t.getPoints() >= 100) {
									return t.getSubcription().getPrice()*t.getSubcription().getDiscount();
									}
								else{
									return t.getSubcription().getPrice();
									}
						});
	
}
