package com.FBook.DAO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.FBook.entity.FacebookUser;

public class FacebookDAOHibernate implements FacebookDAOInterface {
	
	private SessionFactory sf;
	
	public FacebookDAOHibernate() {
		sf = new Configuration().configure().buildSessionFactory();
	}
	//Create Profile
	public int createProfile(FacebookUser fu) {
		System.out.println("create profile dao hibernate called");
		int i=0;
		Session s = sf.openSession();
		Transaction t = s.beginTransaction();
		s.save(fu);
		t.commit();
		
		i=1;
		return i;
	}
	//Login Profile
	public boolean loginProfile(FacebookUser fu) {
		System.out.println("login profile dao hibernate called");
		boolean b= false;
		Session s = sf.openSession();
		//Query q = s.createQuery("from FacebookUser fu where fu.email="+fu.getEmail()+"fu.password="+fu.getPassword());
		Query q = s.createNamedQuery("loginData");
		q.setParameter("email", fu.getEmail());
		q.setParameter("password", fu.getPassword());
		
		List<FacebookUser> ff = q.getResultList();
		if(ff.size()>0) {
			b= true;
		}
		return b;
	}
	//View My Profile
	public FacebookUser viewMyProfile(FacebookUser fu) {
		System.out.println("view my profile dao hibernate called");
		
		Session s = sf.openSession();
		Query q = s.createQuery("from FacebookUser fu where fu.email= :email");
		q.setParameter("email", fu.getEmail());
		FacebookUser ff = (FacebookUser)q.getSingleResult();
		return ff;
	}
	//Search Profile by email
	public FacebookUser searchProfileByEmail(FacebookUser fu) {
		System.out.println("Search profile by email dao hibernate called");
		Session s = sf.openSession();
		Query q = s.createQuery("from FacebookUser fu where fu.email= :email");
		q.setParameter("email", fu.getEmail());
		FacebookUser ff = (FacebookUser)q.getSingleResult();
		return ff;
	}
	//Search Profile by name
	public ArrayList<ArrayList<String>> searchProfileByName(FacebookUser fu) {
		System.out.println("Search profile by name dao hibernate called");
		Session s = sf.openSession();
		List q = s.createQuery("from FacebookUser fu where fu.name= :name").setParameter("name", fu.getName()).list();
		
		FacebookUser ff = null;
		ArrayList<ArrayList<String> > allProfile = new ArrayList<ArrayList<String> >();
		int i=0;
		Iterator it = q.iterator();
		while(it.hasNext()) {
			Object o = (Object)it.next();
			ff = (FacebookUser)o;
			allProfile.add(new ArrayList<String>());
			allProfile.get(i).add(0,ff.getName());
			allProfile.get(i).add(1,ff.getEmail());
			allProfile.get(i).add(2,ff.getAddress());
			i++;
		}
		return allProfile;
	}
	//View all profile
	public ArrayList<ArrayList<String>> viewAllProfile(FacebookUser fu) {
		System.out.println("view all profile dao hibernate called");
		Session s = sf.openSession();
		List q = s.createQuery("from FacebookUser fu").list();
		
		FacebookUser ff = null;
		ArrayList<ArrayList<String> > allProfile = new ArrayList<ArrayList<String> >();
		int i=0;
		Iterator it = q.iterator();
		while(it.hasNext()) {
			Object o = (Object)it.next();
			ff = (FacebookUser)o;
			allProfile.add(new ArrayList<String>());
			allProfile.get(i).add(0,ff.getName());
			allProfile.get(i).add(1,ff.getEmail());
			allProfile.get(i).add(2,ff.getAddress());
			i++;
		}		
		//System.out.println(allProfile);
		return allProfile;
	}
	
	//edit profile
	public int editMyProfile(FacebookUser fu) {
		System.out.println("edit my profile dao hibernate called");
		int i=0;
		Session s = sf.openSession();
		Transaction t = s.beginTransaction();
		Query q = s.createQuery("update FacebookUser fu set fu.name=:name, fu.password=:password, fu.address=:address where fu.email=:email");
		q.setParameter("name", fu.getName());
		q.setParameter("password", fu.getPassword());
		q.setParameter("address", fu.getAddress());
		q.setParameter("email", fu.getEmail());
		i=q.executeUpdate();
		t.commit();
		i=1;
		return i;
	}
	//delete my profile
	public int deleteMyProfile(FacebookUser fu) {
		System.out.println("delete my profile dao hibernate called");
		int i=0;
		Session s = sf.openSession();
		Transaction t = s.beginTransaction();
		Query q = s.createQuery("delete from FacebookUser fu where fu.email=:email");
		q.setParameter("email", fu.getEmail());
		//Query q=s.createQuery("delete entity from FacebookUser fu where fu.email='"+fu.getEmail()+"'");

		i=q.executeUpdate();
		t.commit();
		i=1;
		return i;
	}
	

}
