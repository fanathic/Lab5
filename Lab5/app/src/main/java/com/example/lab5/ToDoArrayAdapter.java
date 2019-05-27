package com.example.lab5;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lab5.model.db.ToDoItem;

import java.util.ArrayList;
import java.util.List;

public class ToDoArrayAdapter extends ArrayAdapter<ToDoItem> {

    private Context mContext;
    private List<ToDoItem> toDoList = new ArrayList<>();

    public ToDoArrayAdapter(@NonNull Context context, ArrayList<ToDoItem> list) {
        super(context, 0, list);
        mContext = context;
        toDoList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);

        ToDoItem currentToDoItem = toDoList.get(position);

        if(currentToDoItem != null) {
            TextView title = (TextView) listItem.findViewById(R.id.textView_title_list);
            title.setText(currentToDoItem.getTitle());


            TextView description = (TextView) listItem.findViewById(R.id.textView_description_list);
            description.setText(currentToDoItem.getDesription());

            TextView date = (TextView) listItem.findViewById(R.id.textView_date_list);
            date.setText(currentToDoItem.getDueDate().toString());
        }

        return listItem;
    }

}
