package business.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import business.wrapper.TrainingWrapper;
import data.daos.CourtDao;
import data.daos.TrainingDao;
import data.daos.UserDao;
import data.entities.Training;
import data.entities.User;
@Controller
public class TrainingController {
	private UserDao userDao;
	private CourtDao courtDao;
	private TrainingDao	trainingDao;
	
	@Autowired
    public void setCourtDao(CourtDao courtDao) {
        this.courtDao = courtDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    
    @Autowired
    public void setTrainingDao(TrainingDao trainingDao) {
        this.trainingDao = trainingDao;
    }
    public boolean createTraining(TrainingWrapper trainingWrapper){
		Training training = new Training();
		training.setStartDate(trainingWrapper.getStartDate());
		training.setEndDate(trainingWrapper.getEndDate());
		training.setCourt(courtDao.findOne(trainingWrapper.getCourt().getCourtId()));
		training.setUser(userDao.findByUsernameOrEmail(trainingWrapper.getTrainer().getUsername()));
		return trainingDao.addTraining(training);
    }
    
    public List<TrainingWrapper> showTrainings(){
    	List<TrainingWrapper> trainings = new ArrayList<TrainingWrapper>();
    	for(Training training : trainingDao.findAll()) {
    		trainings.add(new TrainingWrapper(training));
		}
    	return trainings;
    }
    
    public boolean registerTraining(int trainingID, String pupilName){
    	User pupil = userDao.findByUsernameOrEmail(pupilName);
    	Training training = trainingDao.findById(trainingID);
    	boolean result = false;
    	if(training != null && pupil != null){
    		result = trainingDao.addPupilToTraining(trainingID, pupil);
    	}
    	return result;
    }
    
    public boolean deleteTraining(int trainingID){
    	return trainingDao.deleteTraining(trainingID);
    }
    
    public boolean deleteTrainingPlayer(int trainingID, String pupilName){
    	User pupil = userDao.findByUsernameOrEmail(pupilName);
    	boolean result = false;
    	if(pupil != null){
    		result = trainingDao.deletePupilFromTraining(trainingID, pupil);
    	}
    	return result;
    }
}
