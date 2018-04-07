package com.techm.ms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.techm.ms.model.Account;
import com.techm.ms.model.User;

@Service("userService")
public class UserServiceImpl implements UserService {
	
private static final AtomicLong counter = new AtomicLong();
	
	private static List<User> users;
	
	static {
		users= populateDummyUsers();
	}
	
	@Override
	public void saveUser(User user) {
		counter.incrementAndGet();
		users.add(user);	
	}

	@Override
	public List<User> findAllUsers() {
		// TODO Auto-generated method stub
		return users;
	}

	@Override
	public boolean isUserExist(User user) {
		return findById(user.getAccountId())!=null;
	}
	
	
	private static List<User> populateDummyUsers(){
		List<User> users = new ArrayList<User>();
		users.add(new User(121,"User1",18,121));
		users.add(new User(122,"User2",18,122));
		users.add(new User(123,"User3",18,123));
		return users;
	}

	
	public User findById(long id) {
		for(User user : users){
			if(user.getId() == id){
				return user;
			}
		}
		return null;
	}
}
