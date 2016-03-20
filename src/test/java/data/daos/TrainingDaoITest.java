package data.daos;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;
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
	
	@Test
	public void testAddTraining(){
		List<Training> trainings = trainingDao.findAll();
		assertTrue(trainings.size() == 2);
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
		trainingDao.deleteTraining(1);
		assertNull(trainingDao.findById(1));
		List<Reserve> reserves = reserveDao.findAll();
		//TODO probar que se borran las reservas
		for(Reserve r:reserves){
			
		}
	}
	@Test
	public void testDeletePupilFromTraining(){
		trainingDao.deletePupilFromTraining(2, userDao.findAll().get(0));
		assertTrue(trainingDao.findById(2).getPupils().size()==0);
	}
	
	
}
