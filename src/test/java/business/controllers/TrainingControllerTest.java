package business.controllers;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import business.wrapper.CourtState;
import business.wrapper.TrainingWrapper;
import business.wrapper.UserWrapper;
import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import data.daos.CourtDao;
import data.daos.TrainingDao;
import data.daos.UserDao;
import data.entities.Training;
import data.entities.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class TrainingControllerTest {
	
	@Autowired
	private TrainingController trainingController;
	@Autowired
	private CourtDao courtDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private TrainingDao trainingDao;
	private TrainingWrapper trainingW;
	@Before
	public void init(){
		trainingDao.deleteAll();
		Calendar startDate = new GregorianCalendar(2016, Calendar.FEBRUARY, 4, 11, 00, 00);
		Calendar endDate = new GregorianCalendar(2016, Calendar.MAY, 4, 11, 00, 00);
		CourtState court = new CourtState(courtDao.findAll().get(1).getId(),true);
		User user = userDao.findByUsernameOrEmail("t0");
		UserWrapper trainer = new UserWrapper(user.getUsername(), user.getEmail(), user.getPassword(), user.getBirthDate());
		this.trainingW = new  TrainingWrapper(startDate, endDate, court, trainer);
	}
	
	@Test
	public void createTrainingTest(){
		assertEquals(0, trainingDao.findAll().size());
		boolean result = trainingController.createTraining(trainingW);
		assertEquals(1, trainingDao.findAll().size());
		assertTrue(result);
	}
	
	@Test
	public void showTrainingsTest(){
		trainingController.createTraining(trainingW);
		List<TrainingWrapper> trainings = trainingController.showTrainings();
		assertEquals(1, trainings.size());
	}
	
	@Test
	public void registerTrainingTest(){
		trainingDao.deleteAll();
		trainingController.createTraining(trainingW);
		Training t = trainingDao.findAll().get(0);
		User pupil = userDao.findOne(2);
		trainingController.registerTraining(t.hashCode(), pupil.getUsername());
		assertEquals(1, trainingDao.findById(6).getPupils().size());
	}
	
	@Test
	public void deleteTrainingTest(){
		trainingDao.deleteAll();
		trainingController.createTraining(trainingW);
		Training t = trainingDao.findAll().get(0);
		assertEquals(1, trainingDao.findAll().size());
		trainingController.deleteTraining(t.hashCode());
		assertEquals(0, trainingDao.findAll().size());
	}
	
	@Test
	public void deleteTrainingPlayerTest(){
		trainingDao.deleteAll();
		trainingController.createTraining(trainingW);
		Training t = trainingDao.findAll().get(0);
		User pupil = userDao.findOne(2);
		trainingController.registerTraining(t.hashCode(), pupil.getUsername());
		assertEquals(1, trainingDao.findById(t.hashCode()).getPupils().size());
		trainingController.deleteTrainingPlayer(t.hashCode(), pupil.getUsername());
		assertEquals(0, trainingDao.findById(t.hashCode()).getPupils().size());
	}
}
