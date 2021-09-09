 package com.FBook.utility;

import com.FBook.DAO.FacebookDAOHibernate;
import com.FBook.DAO.FacebookDAOInterface;

public class DAOFactory {

	public static FacebookDAOInterface createObjectHibernate() {
		// TODO Auto-generated method stub
		return new FacebookDAOHibernate();
	}

}
