package data.daos;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;

import data.entities.Reserve;
import data.entities.Training;
import data.entities.User;

public class TrainingDaoImpl implements TrainingExtended {

	@Autowired
	private TrainingDao trainingDao;

	@Autowired
	private ReserveDao reserveDao;

	@Override
	public boolean addTraining(Training training) {
		Training t = trainingDao.findByLessonTime(training.getStartDate(), training.getCourt().getId());
		if(t == null){
			trainingDao.save(training);
			Calendar myDate = training.getStartDate();
			while(myDate.getTimeInMillis()<=training.getEndDate().getTimeInMillis()){
				if(reserveDao.findByCourtAndDate(training.getCourt(), myDate)==null){
					reserveDao.save(new Reserve(training.getCourt(), training.getTrainer(), myDate));
				}
				myDate.add(Calendar.DATE, 7);
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean addPupilToTraining(int idTraining, User pupil) {
		Training training = trainingDao.findById(idTraining);
		boolean result = false;
		if(training != null){
			result = training.addPupil(pupil);
			trainingDao.save(training);
			System.out.println(result + training.toString());
		}
		return result;
	}

	@Override
	public boolean deletePupilFromTraining(int idTraining, User pupil) {
		Training training = trainingDao.findById(idTraining);
		if(training != null){
			if(training.isPupil(pupil)){
				training.removePupil(pupil);
				trainingDao.save(training);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean deleteTraining(int idTraining) {
		Training training = trainingDao.findById(idTraining);
		if(training != null){
			trainingDao.delete(training);
			Calendar myDate = training.getStartDate();
			Reserve r;
			while(myDate.getTimeInMillis()<=training.getEndDate().getTimeInMillis()){
				r = reserveDao.findByCourtAndDate(training.getCourt(), myDate);
				if(r!=null){
					reserveDao.delete(r);
				}
				myDate.add(Calendar.DATE, 7);
			}
			return true;
		}

		return false;
	}
}
