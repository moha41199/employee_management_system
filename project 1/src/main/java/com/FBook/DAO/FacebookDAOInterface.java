package com.FBook.DAO;

import java.util.ArrayList;
import java.util.List;

import com.FBook.entity.FacebookUser;

public interface FacebookDAOInterface {

	int createProfile(FacebookUser fu);

	boolean loginProfile(FacebookUser fu);

	FacebookUser viewMyProfile(FacebookUser fu);

	FacebookUser searchProfileByEmail(FacebookUser fu);

	ArrayList<ArrayList<String>> searchProfileByName(FacebookUser fu);

	ArrayList<ArrayList<String>> viewAllProfile(FacebookUser fu);

	int editMyProfile(FacebookUser fu);

	int deleteMyProfile(FacebookUser fu);

}
