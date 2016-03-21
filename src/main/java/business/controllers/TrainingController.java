package business.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import business.wrapper.TrainingWrapper;
import data.daos.CourtDao;
import data.daos.TrainingDao;
import data.daos.UserDao;
import data.entities.Training;
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
}
