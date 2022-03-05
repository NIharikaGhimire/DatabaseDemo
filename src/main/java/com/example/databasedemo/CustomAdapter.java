package com.example.databasedemo;

import android.app.Dialog;
import android.content.Context;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.databasedemo.dbutil.DBHelper;
import com.example.databasedemo.model.StudentModel;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    Context c;
    AlertDialog alert;
    private ArrayList<StudentModel> studentModel = new ArrayList<>();






    public CustomAdapter(RecordActivity recordActivity, ArrayList<StudentModel> l) {
        c = recordActivity;
        studentModel = l;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(c).inflate(R.layout.single_item, null);
        MyViewHolder viewHolder = new MyViewHolder(convertView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(studentModel.get(position).getName());
        holder.student_id.setText(String.valueOf(studentModel.get(position).getStdid()));
        holder.faculty.setText(studentModel.get(position).getFaculty());
        holder.sem.setText(String.valueOf(studentModel.get(position).getSem()));



        holder.view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {



                createDialog();
                return true;
            }

            private void createDialog() {

                AlertDialog.Builder alertLayout = new AlertDialog.Builder(c);
                View view = LayoutInflater.from(c).inflate(R.layout.edit_delete, null);
                alertLayout.setView(view);
                alert = alertLayout.create();
                alert.show();



                EditText etName = view.findViewById(R.id.name_edit1);
                EditText etStudentId = view.findViewById(R.id.studentid1_edit);
                Button btnedit = view.findViewById(R.id.edit_btn);
                Button btndelete = view.findViewById(R.id.delete_btn);


                etName.setText(studentModel.get(holder.getAdapterPosition()).getName());
                etStudentId.setText(String.valueOf(studentModel.get(holder.getAdapterPosition()).getStdid()));

                btnedit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        StudentModel sm = new StudentModel();
                        sm.setStdid(studentModel.get(holder.getAdapterPosition()).getStdid());
                        sm.setName(etName.getText().toString());
                        sm.setStdid(Integer.parseInt(etStudentId.getText().toString()));
                        DBHelper db = new DBHelper(c);
                        db.updateRecord(sm);


                        alert.dismiss();




                    }
                });

                btndelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DBHelper db = new DBHelper(c);
                        db.deleteRecord(studentModel.get(holder.getAdapterPosition()).getStdid());
                        Toast.makeText(c,"Record deleted successfully",Toast.LENGTH_SHORT).show();
                        studentModel.remove(studentModel.get(holder.getAdapterPosition()));
                        notifyDataSetChanged();
                        alert.dismiss();




                    }
                });
            }
        });




    }

    @Override
    //kati data dhekauna man cha
    public int getItemCount() {
        return studentModel.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, student_id, faculty, sem;
        View view;



        public MyViewHolder(View convertView) {
            super(convertView);
            name = convertView.findViewById(R.id.name_txt);
            student_id = convertView.findViewById(R.id.studentid_txt);
            faculty = convertView.findViewById(R.id.faculty_txt);
            sem = convertView.findViewById(R.id.semester_txt);
            view = convertView;



        }
    }


}
