package com.example.databasedemo;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.databasedemo.dbutil.DBHelper;
import com.example.databasedemo.model.StudentModel;

import java.util.ArrayList;

public class
RecordActivity extends AppCompatActivity {
    private RecyclerView rv;
    private ArrayList<StudentModel> studentModel;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        rv=findViewById(R.id.rv);
        DBHelper db = new DBHelper(this);
        studentModel = db.retrieveData();
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new CustomAdapter(this,studentModel));




    }
}
