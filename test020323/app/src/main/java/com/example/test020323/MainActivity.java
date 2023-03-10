package com.example.test020323;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.test020323.adapter.StudentAdapter;
import com.example.test020323.dao.StudentDao;
import com.example.test020323.dao.StudentDaoImpl;
import com.example.test020323.domain.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText etName, etAge, etPhone, etEmail;
    private RecyclerView recyclerView;
    private AppCompatButton btnAdd;
    private StudentDao studentDao;
    private StudentAdapter adapter;
    private List<Student> studentList = new ArrayList<>();

    private ItemTouchHelper.SimpleCallback simpleCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studentDao = new StudentDaoImpl(this);
        recyclerView = findViewById(R.id.rv_student);
        studentList.addAll(studentDao.findAll());

        etName = findViewById(R.id.et_name);
        etAge = findViewById(R.id.et_age);
        etPhone = findViewById(R.id.et_phone);
        etEmail = findViewById(R.id.et_email);

        adapter = new StudentAdapter(LayoutInflater.from(this), studentList);

        recyclerView.setAdapter(adapter);

        btnAdd = findViewById(R.id.btn_add);

        simpleCallback = new ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT
        ) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Student student = studentList.get(position);

                if (direction == ItemTouchHelper.LEFT) {
                    studentDao.delete(student.getId());
                    studentList.clear();
                    studentList.addAll(studentDao.findAll());
                    adapter.notifyDataSetChanged();
                }
            }

        };

        ItemTouchHelper touchHelper = new ItemTouchHelper(simpleCallback);
        touchHelper.attachToRecyclerView(recyclerView);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Student student = new Student(
                        etName.getText().toString(),
                        Integer.parseInt(etAge.getText().toString()),
                        etPhone.getText().toString(),
                        etEmail.getText().toString()
                );

                studentDao.insert(student);
                studentList.clear();
                studentList.addAll(studentDao.findAll());
                adapter.notifyDataSetChanged();

            }
        });
    }
}