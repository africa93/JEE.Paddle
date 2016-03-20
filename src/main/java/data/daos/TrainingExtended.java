package data.daos;
import data.entities.Training;
import data.entities.User;

public interface TrainingExtended {
	public boolean addTraining(Training training);
	public boolean addPupilToTraining(int idTraining, User pupil);
	public boolean deletePupilFromTraining(int idTraining, User pupil);
	public boolean deleteTraining(int idTraining);
	
}
