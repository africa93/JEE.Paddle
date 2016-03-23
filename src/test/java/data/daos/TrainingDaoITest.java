package data.daos;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import data.entities.Court;
import data.entities.Reserve;
import data.entities.Training;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class TrainingDaoITest {
	@Autowired
	private TrainingDao trainingDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private ReserveDao reserveDao;
	
	@Autowired
	private CourtDao courtDao;
	
	@Test
	public void testAddTraining(){
		List<Training> trainings = trainingDao.findAll();
		//assertTrue(trainings.size() == 2);
		List<Reserve> reserves = reserveDao.findAll();
		int cont =0;
		for(Reserve r:reserves){
			if(r.getCourt().equals(trainings.get(0).getCourt()) && r.getDate().DAY_OF_WEEK==trainings.get(0).getStartDate().DAY_OF_WEEK
					&& r.getDate().HOUR == trainings.get(0).getStartDate().HOUR){
				cont ++;
			}
		}
		assertEquals(27, cont);
	}
	@Test
	public void testAddPupilToTraining(){
		trainingDao.addPupilToTraining(2, userDao.findAll().get(1));
		assertEquals(1,trainingDao.findAll().get(1).getPupils().size());
		assertFalse(trainingDao.addPupilToTraining(3, userDao.findAll().get(0)));
		assertFalse(trainingDao.findById(2).addPupil(userDao.findOne(2)));
	}
	
	@Test 
	public void testDeleteTrainingTest(){
		Calendar init = new GregorianCalendar(2016, Calendar.JANUARY, 4, 17, 00, 00);
    	Calendar end = new GregorianCalendar(2016, Calendar.JUNE, 37, 17, 00, 00);
    	List<Court> courts = courtDao.findAll();
    	Training t = new Training(init, end, courts.get(0), userDao.findByUsernameOrEmail("t0"));
    	trainingDao.addTraining(t);
		trainingDao.deleteTraining(t.hashCode());
		assertNull(trainingDao.findById(t.hashCode()));
	}
	@Test
	public void testDeletePupilFromTraining(){
		trainingDao.deletePupilFromTraining(2, userDao.findAll().get(1));
		assertTrue(trainingDao.findById(2).getPupils().size()==0);
	}
	
	
}
