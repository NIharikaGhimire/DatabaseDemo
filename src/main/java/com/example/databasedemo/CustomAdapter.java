package com.example.databasedemo;

import android.content.Context;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.databasedemo.model.StudentModel;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    Context c;
    private ArrayList<StudentModel>studentModel=new ArrayList<>();




    public CustomAdapter(RecordActivity recordActivity, ArrayList<StudentModel> l) {
        c = recordActivity;
        studentModel=l;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(c).inflate(R.layout.single_item,null);
        MyViewHolder viewHolder = new MyViewHolder(convertView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(studentModel.get(position).getName());
        holder.student_id.setText(String.valueOf(studentModel.get(position).getStdid()));
        holder.faculty.setText(studentModel.get(position).getFaculty());
        holder.sem.setText(String.valueOf(studentModel.get(position).getSem()));

    }
    @Override
    //kati data dhekauna man cha
    public int getItemCount() {
        return studentModel.size();
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name, student_id, faculty, sem;

        public MyViewHolder(View convertView){
            super(convertView);
            name = convertView.findViewById(R.id.name_txt);
            student_id = convertView.findViewById(R.id.studentid_txt);
            faculty = convertView.findViewById(R.id.faculty_txt);
            sem = convertView.findViewById(R.id.semester_txt);






        }
    }



}
