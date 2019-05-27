package com.example.lab5;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.lab5.model.db.ToDoItem;
import com.example.lab5.model.db.ToDoItemContract;
import com.example.lab5.model.db.ToDoItemDbHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ListView toDoListView;
    private ToDoArrayAdapter toDoArrayAdapter;
    private ToDoItemDbHelper dbHelper;
    private ArrayList<ToDoItem> toDoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolBar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolBar);

        dbHelper = new ToDoItemDbHelper(this);
        toDoListView = findViewById(R.id.toDo_list);


        toDoList = new ArrayList<>();
        //putToDoItemToDb("testi", "desci", new Date());


        getToDoItemsFromDb();

        toDoArrayAdapter = new ToDoArrayAdapter(this, toDoList);
        toDoListView.setAdapter(toDoArrayAdapter);

    }

    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }

    private void putToDoItemToDb(String name, String description, Date date){
        // Gets the data repository in write mode
        SQLiteDatabase db = dbHelper.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(ToDoItemContract.ToDoItemEntry.COLUMN_NAME_NAME, name);
        values.put(ToDoItemContract.ToDoItemEntry.COLUMN_NAME_DESCRIPTION, description);
        values.put(ToDoItemContract.ToDoItemEntry.COLUMN_NAME_DATE, date.toString());

// Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(ToDoItemContract.ToDoItemEntry.TABLE_NAME, null, values);


        ToDoItem item = new ToDoItem("testi title", "testi kuvaus", new Date());
    }

    private void getToDoItemsFromDb(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                ToDoItemContract.ToDoItemEntry.COLUMN_NAME_NAME,
                ToDoItemContract.ToDoItemEntry.COLUMN_NAME_DESCRIPTION,
                ToDoItemContract.ToDoItemEntry.COLUMN_NAME_DATE
        };
        // Filter results WHERE "title" = 'My Title'
        //        String selection = FeedEntry.COLUMN_NAME_TITLE + " = ?";
        //        String[] selectionArgs = { "My Title" };
        String selection = null;
        String[] selectionArgs = null;

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                BaseColumns._ID + " ASC";

        Cursor cursor = db.query(
                ToDoItemContract.ToDoItemEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        toDoList.clear();

        while(cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow(ToDoItemContract.ToDoItemEntry.COLUMN_NAME_NAME));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(ToDoItemContract.ToDoItemEntry.COLUMN_NAME_DESCRIPTION));
            String dateString = cursor.getString(cursor.getColumnIndexOrThrow(ToDoItemContract.ToDoItemEntry.COLUMN_NAME_DATE));
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            Date date = new Date();
            try {
                date = format.parse(dateString);
            } catch (Exception ex){

            }
            ToDoItem currentItem = new ToDoItem(name, description, date);
            toDoList.add(currentItem);
        }

        cursor.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu, this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.appbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.action_add:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
