package org.itstep.android.lesson_29_network;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;

/*
 * JsonActivity.java
 *
 * Created by mib on 04.02.15, 20:05
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class JsonActivity extends ActionBarActivity {

	private static final String TAG = JsonActivity.class.getSimpleName();

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, JsonActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_json);

		findViewById(R.id.saveButton).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View v) {
				createAndSerializeHuman();
			}
		});
		findViewById(R.id.loadButton).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View v) {
				loadAndDeserialize();
			}
		});
	}

	private void loadAndDeserialize() {
		try {
			final FileInputStream fis = openFileInput("human.json");

			final InputStreamReader isr = new InputStreamReader(fis);

			final BufferedReader br = new BufferedReader(isr);

			final StringBuilder builder = new StringBuilder();

			String str;
			while ((str = br.readLine()) != null) {
				builder.append(str);
			}
			fis.close();

			final String json = builder.toString();
			Log.i(TAG, json);

			final Gson gson = new GsonBuilder()
					.excludeFieldsWithoutExposeAnnotation()
					.registerTypeAdapter(Date.class, new TypeAdapter<Date>() {
				@Override
				public void write(final JsonWriter out, final Date value) throws IOException {

				}

				@Override
				public Date read(final JsonReader in) throws IOException {
					return null;
				}
			}).create();

			final Human human = gson.fromJson(json, Human.class);
			Log.i(TAG, human.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void createAndSerializeHuman() {
		final ArrayList<Human> friends = generateFriend();

		Human human = new Human();
		human.setName("Petya");
		human.setSurname("Vasichkin");
		human.setAge(44);
		human.setFriends(friends);
		human.setAnimal(new Animal("Dog"));

		final String json = new Gson().toJson(human);
		Log.i(TAG, json);

		try {
			final FileOutputStream fos = openFileOutput("human.json", Context.MODE_PRIVATE);
			fos.write(json.getBytes());
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private ArrayList<Human> generateFriend() {
		ArrayList<Human> friends = new ArrayList<>();
		for (int i = 0; i < 5; i ++) {
			Human friend = new Human();
			friend.setName(String.format("Friend %d", i));
			friend.setAge(i + 20);

			friends.add(friend);
		}
		return friends;
	}
}