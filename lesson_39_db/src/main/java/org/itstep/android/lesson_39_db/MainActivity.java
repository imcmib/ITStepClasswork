package org.itstep.android.lesson_39_db;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity
        extends ActionBarActivity
        implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private EditText mEditText;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = (EditText) findViewById(R.id.edit_text);
        mTextView = (TextView) findViewById(R.id.text_view);

        findViewById(R.id.add_button).setOnClickListener(this);
        findViewById(R.id.read_button).setOnClickListener(this);
        findViewById(R.id.remove_button).setOnClickListener(this);
        findViewById(R.id.update_button).setOnClickListener(this);
    }

    @Override
    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.add_button:
                addRow();
                break;
            case R.id.read_button:
                readRow();
                break;
            case R.id.remove_button:
                removeRow();
                break;
            case R.id.update_button:
                updateRow();
                break;
            default:
                Log.w(TAG, "Unhandled onClick event for view: "
                        + getResources().getResourceName(view.getId()));
                break;
        }
    }

    private void addRow() {
        final String value = mEditText.getText().toString();

        final ContentValues data = new ContentValues();
        data.put(TestTable.COLUMN_TEXT, value);
        data.put(TestTable.COLUMN_NUMBER, new Random().nextInt(100));

        final long insert = DBHelper.getInstance().getDatabase()
                                    .insert(TestTable.TABLE_NAME, null, data);

        Toast.makeText(this, String.format("%d inseted", insert),
                Toast.LENGTH_SHORT).show();
    }

    private void readRow() {
        final Cursor cursor = DBHelper.getInstance().getDatabase()
                                      .query(TestTable.TABLE_NAME, // table name
                                              null, // columns
                                              null, // selection
                                              null, // selectionArgs
                                              null, // groupBy
                                              null, // having
                                              null);// orderBy

        mTextView.setText("");

        final int textIndex = cursor.getColumnIndex(TestTable.COLUMN_TEXT);
        final int numberIndex = cursor.getColumnIndex(TestTable.COLUMN_NUMBER);

        while (cursor.moveToNext()) {
            final String value = cursor.getString(textIndex);
            final int number = cursor.getInt(numberIndex);

            if (mTextView.getText().length() > 0) {
                mTextView.append("\n");
            }
            mTextView.append(String.format("Value: %s, number: %d", value, number));
        }
        cursor.close();
    }

    private void removeRow() {
        final String value = mEditText.getText().toString();

        final String where = TestTable.COLUMN_TEXT + "=?";
        final String[] whereArgs = new String[] { value };

        final int delete = DBHelper.getInstance().getDatabase()
                                   .delete(TestTable.TABLE_NAME, where, whereArgs);

        Toast.makeText(this, String.format("%d rows deleted", delete),
                Toast.LENGTH_SHORT).show();
    }

    private void updateRow() {
        final String value = mEditText.getText().toString();

        final ContentValues data = new ContentValues();
        data.put(TestTable.COLUMN_NUMBER, new Random().nextInt(255));

        final String where = TestTable.COLUMN_TEXT + "=?";
        final String[] whereArgs = new String[] { value };

        DBHelper.getInstance()
                .getDatabase()
                .update(TestTable.TABLE_NAME, data, where, whereArgs);
    }
}