package data.daos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import data.entities.Token;
import data.entities.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class UserDaoITest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private DaosService daosService;
    
    @Autowired
    private TokenDao tokenDao;

    @Test
    public void testCreate() {
        assertTrue(userDao.count() >= 8);
    }

    @Test
    public void testFindDistinctByUsernameOrEmail() {
        User u1 = (User) daosService.getMap().get("u1");
        assertEquals(u1, userDao.findByUsernameOrEmail(u1.getUsername()));
        assertEquals(u1, userDao.findByUsernameOrEmail(u1.getEmail()));
        assertNull(userDao.findByUsernameOrEmail("kk"));
    }

    @Test
    public void testFindByTokenValue() {
        User u1 = (User) daosService.getMap().get("u1");
        Token t1 = (Token) daosService.getMap().get("tu1");
        assertEquals(u1, userDao.findByTokenValue(t1.getValue()));
        assertNull(userDao.findByTokenValue("kk"));
    }
    
    @Test 
	public void testFindByValidTokenValue(){
		User u1 = (User) daosService.getMap().get("u1");
		Token t1 = (Token) daosService.getMap().get("tu1");
		assertEquals(u1, userDao.findByValidTokenValue(t1.getValue()));
		assertNull(userDao.findByValidTokenValue("kk"));
	}

	@Test 
	public void testFindByValidTokenValueNotValid(){
		User user = (User) daosService.getMap().get("u4");
		Token t = new Token(user);
		Calendar calendar = Calendar.getInstance();
		calendar.set(2015, Calendar.AUGUST, 13);
		t.setCreationDate(calendar);
		tokenDao.save(t);
		assertNull(userDao.findByValidTokenValue(t.getValue()));
	}
}
