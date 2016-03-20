package data.entities;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import data.daos.UserDao;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class TrainingTest {
	/*@Autowired
	private TrainingDao trainingDao;
	@Autowired
	private UserDao userDao;
	
	@Test
	public void testAddPupil(){
		assertTrue(trainingDao.findAll().get(0).addPupil(userDao.findAll().get(1)));
		assertFalse(trainingDao.findAll().get(0).addPupil(userDao.findAll().get(0)));
	}
	
	@Test
	public void testIsPupil(){
		assertTrue(trainingDao.findAll().get(0).isPupil(userDao.findAll().get(0)));
		assertFalse(trainingDao.findAll().get(0).isPupil(userDao.findAll().get(1)));
	}*/

}
