package api;

import static org.junit.Assert.*;

import java.net.URI;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import business.api.Uris;
import business.wrapper.AvailableTime;
import business.wrapper.CourtState;
import business.wrapper.TrainingWrapper;
import business.wrapper.UserWrapper;
import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import data.daos.CourtDao;
import data.daos.TrainingDao;
import data.daos.UserDao;
import data.entities.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class TrainingResourceFunctionalITesting {
	private RestService restService = new RestService();
	@Autowired 
	private UserDao userDao;
	@Autowired 
	private CourtDao courtDao;
	@Autowired 
	private TrainingDao trainingDao;
	
	@Test
	public void testCreateTraining(){
		String token = restService.loginAdmin();
		User user = userDao.findByTokenValue(token);
		UserWrapper trainer = new UserWrapper(user.getUsername(), user.getEmail(), user.getPassword(), user.getBirthDate());
		CourtState court = new CourtState(courtDao.findOne(1));
		Calendar startDate = new GregorianCalendar(2016, Calendar.JUNE, 1, 15, 00, 00);
		Calendar endDate = new GregorianCalendar(2016, Calendar.AUGUST, 31, 15, 00, 00);
		TrainingWrapper training = new TrainingWrapper(startDate, endDate, court, trainer);
		new RestBuilder<Object>(RestService.URL).path(Uris.TRAININGS).basicAuth(token, "")
        .body(training).post().build();
		assertEquals(1, trainingDao.count());
	}
	
	@Test 
	public void testShowTrainings(){
		/*String token = restService.registerAndLoginPlayer();
        String trainings = new RestBuilder<String>(RestService.URL).path(Uris.TRAININGS).basicAuth(token, "").get().build();
        LogManager.getLogger(this.getClass()).info("testshowAvailability (" + trainings + ")");
	*/
		URI uri = UriComponentsBuilder.fromHttpUrl(RestService.URL).path(Uris.TRAININGS).build().encode().toUri();
		RequestEntity<Object> requestEntity = new RequestEntity<>(HttpMethod.GET, uri);
		List<TrainingWrapper> response = Arrays.asList(new RestTemplate().exchange(requestEntity, TrainingWrapper[].class).getBody());
		System.out.println(response);
	}
	@After
	public void deleteAll() {
		new RestService().deleteAll();
	}

}
