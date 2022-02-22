package com.example.databasedemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.databasedemo.dbutil.DBHelper;
import com.example.databasedemo.model.StudentModel;

public class MainActivity extends AppCompatActivity {
    private Button add, view;
//array lsit ma recycler view ko database ma

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add= findViewById(R.id.add_btn);
        view = findViewById(R.id.view_btn);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAddDialog();

            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RecordActivity.class));


            }
        });
    }

    private void createAddDialog(){
        AlertDialog.Builder alertLayout = new AlertDialog.Builder(MainActivity.this);
        View view=getLayoutInflater().inflate(R.layout.activity_add,null);
        alertLayout.setView(view);




        EditText etName = view.findViewById(R.id.name_edit);
        EditText etStudentId = view.findViewById(R.id.studentid_edit);
        EditText etFaculty = view.findViewById(R.id.faculty_edit);
        EditText etSemester = view.findViewById(R.id.semester_edit);
        Button btnsave = view.findViewById(R.id.save_btn);

        AlertDialog alert = alertLayout.create();
        alert.show();

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StudentModel studentModel = new StudentModel();
                studentModel.setName(etName.getText().toString());
                studentModel.setStdid(Integer.parseInt(etStudentId.getText().toString()));
                studentModel.setFaculty(etFaculty.getText().toString());
                studentModel.setSem(Integer.parseInt(etSemester.getText().toString()));

                insertData(studentModel);
                alert.dismiss();







            }
        });


    }
    private void insertData(StudentModel studentModel){

        if(dataValid(studentModel)){
            DBHelper db = new DBHelper(MainActivity.this);
            db.insertDataToDb(studentModel);
            Toast.makeText(MainActivity.this, "Record added successful", Toast.LENGTH_SHORT).show();
        } else{


        }
    }

    private boolean dataValid(StudentModel studentModel){
        boolean valid = true;
        if(TextUtils.isEmpty(studentModel.getName())){
            valid = false;
        }
        return valid;

    }
}