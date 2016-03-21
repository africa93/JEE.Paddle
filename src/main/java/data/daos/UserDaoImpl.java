package data.daos;

import org.springframework.beans.factory.annotation.Autowired;

import data.entities.Token;
import data.entities.User;

public class UserDaoImpl implements UserExtended{
	
	@Autowired 
	private TokenDao tokenDao;
	
	@Autowired 
	private UserDao userDao;

	/*@Override
	public User findByValidTokenValue(String usernameOrEmailOrTokenValue) {
		User user = userDao.findByTokenValue(usernameOrEmailOrTokenValue);
		if(user != null){
			Token token = tokenDao.findByUser(user);
			if(token != null){
				if(token.isValid()){
					return user;
				}
			}
		}
		return null;
	}*/

}
