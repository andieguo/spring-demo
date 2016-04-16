package com.andieguo.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.andieguo.spring.bean.User;
import com.andieguo.spring.dao.UserDao;

import junit.framework.TestCase;

/**
 * UserDaoTest
 */
public class UserDaoTest extends TestCase {
	
	private UserDao userDao;
	
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		userDao = (UserDao) context.getBean("userDao");
		System.out.println(userDao);
	}
	
	public void testSave(){
		int sum = userDao.save(new User("andy", 26, 1));
		assertEquals(sum, 1);
	}
	
	public void testFindAll(){
		for(User u : userDao.findAll()){
			System.out.println(u);
		}
	}
	
	public void testFindById(){
		System.out.println(userDao.findById(1));
	}
	
	public void testUpdate(){
		User u = userDao.findById(1);
		System.out.println(u);
		u.setAge(20);
		u.setName("jack");
		u.setGender(0);
		int sum = userDao.update(u);
		assertEquals(sum, 1);
	}
	
	public void testDelete(){
		int sum = userDao.delete(2);
		assertEquals(sum, 1);
	}
}
