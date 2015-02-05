package org.itstep.android.lesson_29_network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/*
 * Human.java
 *
 * Created by mib on 04.02.15, 20:03
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */

public class Human {

	@SerializedName("name")
	@Expose
	private transient String mName;

	@SerializedName("surname")
	private String mSurname;

	@SerializedName("age")
	private int mAge;

	@SerializedName("pet")
	private Animal mAnimal;

	@SerializedName("collegs")
	private List<Human> mFriends;

	public String getName() {
		return mName;
	}

	public void setName(final String name) {
		mName = name;
	}

	public String getSurname() {
		return mSurname;
	}

	public void setSurname(final String surname) {
		mSurname = surname;
	}

	public int getAge() {
		return mAge;
	}

	public void setAge(final int age) {
		mAge = age;
	}

	public Animal getAnimal() {
		return mAnimal;
	}

	public void setAnimal(final Animal animal) {
		mAnimal = animal;
	}

	public List<Human> getFriends() {
		return mFriends;
	}

	public void setFriends(final List<Human> friends) {
		mFriends = friends;
	}

	@Override
	public String toString() {
		return "Human{" +
				"mName='" + mName + '\'' +
				", mSurname='" + mSurname + '\'' +
				", mAge=" + mAge +
				", mAnimal=" + mAnimal +
				", mFriends=" + mFriends +
				'}';
	}
}