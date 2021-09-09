package com.FBook.DAO;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.FBook.entity.FacebookUser;
import com.FBook.utility.DAOFactory;

public class FacebookDAOHibernateTest {
	FacebookDAOInterface fd;
	@Before
	public void setUp() throws Exception {
		fd = DAOFactory.createObjectHibernate();
	}

	@After
	public void tearDown() throws Exception {
		fd = null;
	}

	@Test
	public void testCreateProfile() {
		FacebookUser fu = new FacebookUser();
		fu.setName("test");
		fu.setPassword("test");
		fu.setEmail("test@gmail.com");
		fu.setAddress("cud");
		int i = fd.createProfile(fu);
		assert i>0:"create profile test failed";
	}

	@Test
	public void testLoginProfile() {
		FacebookUser fu = new FacebookUser();
		fu.setEmail("test@gmail.com");
		fu.setPassword("test");
		boolean b = fd.loginProfile(fu);
		assert b==true:"login profile failed";
	}

	/*@Test
	public void testViewMyProfile() {
		FacebookUser fu = new FacebookUser();
		fu.setEmail("test@gmail.com");
		FacebookUser ff=fd.viewMyProfile(fu);
		assert ff!=null:"view profile failed";
	}

	@Test
	public void testSearchProfileByEmail() {
		FacebookUser fu = new FacebookUser();
		fu.setEmail("test@gmail.com");
		FacebookUser ff = fd.searchProfileByEmail(fu);
		assert ff!=null:"view email profile failed";
	}*/

	@Test
	public void testSearchProfileByName() {
		FacebookUser fu = new FacebookUser();
		fu.setName("test");
		ArrayList<ArrayList<String>> allProfile = fd.searchProfileByName(fu);
		assert allProfile!=null:"view name profile failed";
	}

	@Test
	public void testViewAllProfile() {
		FacebookUser fu = new FacebookUser();
		ArrayList<ArrayList<String>> allProfile = fd.viewAllProfile(fu);
		assert allProfile!=null:"view all profile test failed";
	}

	@Test
	public void testEditMyProfile() {
		FacebookUser fu = new FacebookUser();
		fu.setEmail("test@gmail.com");
		fu.setName("test1");
		fu.setPassword("test1");
		fu.setAddress("test_cud");
		int i = fd.editMyProfile(fu);
		assert i>0:"edit profile test failed";
	}

	@Test
	public void testDeleteMyProfile() {
		FacebookUser fu = new FacebookUser();
		fu.setEmail("test@gmail.com");
		int i = fd.deleteMyProfile(fu);
		assert i>0:"delete test failed";
	}

}
