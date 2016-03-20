package data.daos;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import data.entities.Authorization;
import data.entities.Court;
import data.entities.Reserve;
import data.entities.Role;
import data.entities.Token;
import data.entities.Training;
import data.entities.User;
import data.services.DataService;

@Service
public class DaosService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private TokenDao tokenDao;

    @Autowired
    private AuthorizationDao authorizationDao;

    @Autowired
    private CourtDao courtDao;

    @Autowired
    private ReserveDao reserveDao;

    @Autowired
    private DataService genericService;
    
    @Autowired
    private TrainingDao trainingDao;

    private Map<String, Object> map;

    @PostConstruct
    public void populate() {
        map = new HashMap<>();
        User[] users = this.createPlayers(0, 4);
        for (User user : users) {
            map.put(user.getUsername(), user);
        }
        for (Token token : this.createTokens(users)) {
            map.put("t" + token.getUser().getUsername(), token);
        }
        for (User user : this.createPlayers(4, 4)) {
            map.put(user.getUsername(), user);
        }
        this.createCourts(1, 4);
        Calendar date = Calendar.getInstance();
        date.add(Calendar.DAY_OF_YEAR, 1);
        date.set(Calendar.HOUR_OF_DAY, 9);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        for (int i = 0; i < 4; i++) {
            date.add(Calendar.HOUR_OF_DAY, 1);
            reserveDao.save(new Reserve(courtDao.findOne(i+1), users[i], date));
        }
        
        User[] trainers= this.createTrainers(0, 2);
        this.createTrainings(trainers);
    }
    
    public User[] createTrainers(int initial, int size){
    	User[] trainers = new User[size];
    	for(int i=0; i<size; i++){
    		trainers[i] = new User("t" + (i + initial), "t" + (i + initial) + "@gmail.com", "p", Calendar.getInstance());
            userDao.save(trainers[i]);
            authorizationDao.save(new Authorization(trainers[i], Role.TRAINER));
    	}
    	return trainers;
    }
    
    public Training[] createTrainings(User[] trainers){
    	Training[] trainings = new Training[2];
    	Calendar init = new GregorianCalendar(2016, Calendar.JANUARY, 1, 17, 00, 00);
    	Calendar end = new GregorianCalendar(2016, Calendar.JUNE, 30, 17, 00, 00);
    	List<Court> courts = courtDao.findAll();
    	trainingDao.addTraining(new Training(init, end, courts.get(0), trainers[0]));
    	
    	trainingDao.addPupilToTraining(1, userDao.findAll().get(0));
    	
    	Calendar init1 = new GregorianCalendar(2016, Calendar.FEBRUARY, 1, 17, 00, 00);
    	Calendar end1 = new GregorianCalendar(2016, Calendar.MAY, 1, 17, 00, 00);
    	trainingDao.addTraining(new Training(init1, end1, courts.get(1), trainers[1]));
    	
    	return trainings;
    }
    

    public User[] createPlayers(int initial, int size) {
        User[] users = new User[size];
        for (int i = 0; i < size; i++) {
            users[i] = new User("u" + (i + initial), "u" + (i + initial) + "@gmail.com", "p", Calendar.getInstance());
            userDao.save(users[i]);
            authorizationDao.save(new Authorization(users[i], Role.PLAYER));
        }
        return users;
    }

    public List<Token> createTokens(User[] users) {
        List<Token> tokenList = new ArrayList<>();
        Token token;
        for (User user : users) {
            token = new Token(user);
            tokenDao.save(token);
            tokenList.add(token);
        }
        return tokenList;
    }

    public void createCourts(int initial, int size) {
        for (int id = 0; id < size; id++) {
            courtDao.save(new Court(id + initial));
        }
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void deleteAll() {
        genericService.deleteAllExceptAdmin();
    }
}
