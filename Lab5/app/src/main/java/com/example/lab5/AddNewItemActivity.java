package com.example.lab5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

public class AddNewItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_item);

        final Button button = findViewById(R.id.button_save);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText editTextTitle = findViewById(R.id.editText_toDoItem_name);
                final EditText editTextDescription = findViewById(R.id.editText_toDoItem_description);
                final EditText editTextDate = findViewById(R.id.editText_toDoItem_date);

                String title = editTextTitle.getText().toString();
                String description = editTextDescription.getText().toString();
                String date = editTextDate.getText().toString();



            }
        });

    }
}
