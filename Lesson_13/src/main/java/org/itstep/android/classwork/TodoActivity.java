package org.itstep.android.classwork;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/*
 * TodoActivity.java
 *
 * Created by mib on 19.11.14, 20:32
 *
 * Copyright(c) 2014 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class TodoActivity extends Activity implements AdapterView.OnItemClickListener {

	private static final String TAG = TodoActivity.class.getSimpleName();

	private TodoAdapter mAdapter;

	public static void startActivity(Activity context) {
	    final Intent intent = new Intent(context, TodoActivity.class);

	    context.startActivity(intent);
	}

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_todo);

		final List<Note> notes = new ArrayList<Note>();

		mAdapter = new TodoAdapter(this, R.layout.list_item_note, notes);

		final ListView listView = (ListView) findViewById(R.id.listView);
		listView.setAdapter(mAdapter);
		listView.setOnItemClickListener(this);

		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(final MenuItem item) {
		switch (item.getItemId()) {
			case R.id.action_add_note:
				addNote(null);
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {
		final Note note = mAdapter.getItem(position);
		if (note != null) {
			showEditNoteDialog(note);
		} else {
			showNoteNotFoundDialog();
		}
	}

	private void showNoteNotFoundDialog() {
		final AlertDialog alertDialog = new AlertDialog.Builder(this)
				.setTitle(R.string.title_warning)
				.setIcon(R.drawable.ic_launcher)
				.setMessage(R.string.message_note_not_found)
				.setPositiveButton(android.R.string.ok, null)
				.create();
		alertDialog.show();
	}

	private void showEditNoteDialog(final Note note) {
		final AlertDialog.Builder builder = new AlertDialog.Builder(this)
				.setTitle(note.getName())
				.setIcon(R.drawable.ic_launcher)
				.setMessage(R.string.message_note_actions)
				.setPositiveButton(R.string.label_button_edit, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(final DialogInterface dialog, final int which) {
						addNote(note);
					}
				})
				.setNegativeButton(R.string.label_button_delete, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(final DialogInterface dialog, final int which) {
						mAdapter.remove(note);
					}
				})
				.setNeutralButton(android.R.string.cancel, null);

		final AlertDialog alertDialog = builder.create();
		alertDialog.show();
	}

	private void addNote(final Note note) {
		final View view = getLayoutInflater().inflate(R.layout.dialog_add_note, null);

		final EditText nameEditText = (EditText) view.findViewById(R.id.nameEditText);
		final EditText descriptionEditText = (EditText) view.findViewById(R.id.descriptionEditText);

		if (note != null) {
			nameEditText.setText(note.getName());
			descriptionEditText.setText(note.getDescription());
		}

		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Add Note")
				.setIcon(R.drawable.ic_launcher)
				.setView(view)
				.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(final DialogInterface dialog, final int which) {
						final String name = nameEditText.getText().toString();
						final String description = descriptionEditText.getText().toString();

						if (note == null) {
							final Note newNote = new Note(name, description);

							mAdapter.add(newNote);
						} else {
							note.setName(name);
							note.setDescription(description);

							mAdapter.notifyDataSetChanged();
						}
					}
				})
				.setNegativeButton(android.R.string.cancel, null);

		final AlertDialog alertDialog = builder.create();
		alertDialog.show();
	}
}