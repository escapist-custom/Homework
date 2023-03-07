package com.example.test020323.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test020323.R;
import com.example.test020323.domain.Student;

import org.w3c.dom.Text;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<Student> studentList;
    private LayoutInflater inflater;

    public StudentAdapter(LayoutInflater inflater, List<Student> studentList) {
        this.studentList = studentList;
        this.inflater = inflater;
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {

        final TextView tvId, tvName, tvAge, tvPhone, tvEmail;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvId = itemView.findViewById(R.id.tv_student_id);
            tvName = itemView.findViewById(R.id.tv_student_name);
            tvAge = itemView.findViewById(R.id.tv_student_age);
            tvPhone = itemView.findViewById(R.id.tv_student_phone);
            tvEmail = itemView.findViewById(R.id.tv_student_email);
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.student_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Student student = studentList.get(position);

        ((MyViewHolder)holder).tvId.setText(student.getId() + "");
        ((MyViewHolder)holder).tvName.setText(student.getName());
        ((MyViewHolder)holder).tvAge.setText(student.getAge() + "");
        ((MyViewHolder)holder).tvPhone.setText(student.getPhone());
        ((MyViewHolder)holder).tvEmail.setText(student.getEmail());

    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }
}
