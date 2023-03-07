package com.example.test020323.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.test020323.db.StudentContractReader;
import com.example.test020323.db.StudentDbOpenHelper;
import com.example.test020323.domain.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {

    private final StudentDbOpenHelper openHelper;

    public StudentDaoImpl(Context context) {
        this.openHelper = new StudentDbOpenHelper(context);
    }

    @Override
    public long insert(Student student) {

        SQLiteDatabase sqLiteDatabase = openHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(
                StudentContractReader.StudentEntry.COLUMN_AGE, student.getAge()
        );

        contentValues.put(
                StudentContractReader.StudentEntry.COLUMN_NAME, student.getName()
        );

        contentValues.put(
                StudentContractReader.StudentEntry.COLUMN_EMAIL, student.getEmail()
        );

        contentValues.put(
                StudentContractReader.StudentEntry.COLUMN_PHONE, student.getPhone()
        );


        long index = sqLiteDatabase.insert(
            StudentContractReader.StudentEntry.TABLE_NAME,
                null,
                contentValues
        );

        sqLiteDatabase.close();

        Log.i(StudentContractReader.LOG_TAG, "insert : " + index);

        return index;
    }

    @Override
    public List<Student> findAll() {
        SQLiteDatabase sqLiteDatabase = openHelper.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(
                StudentContractReader.StudentEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        List<Student> students = new ArrayList<>();

        if (cursor.moveToFirst()) {
            int id = cursor.getColumnIndex(StudentContractReader.StudentEntry.COLUMN_ID);
            int name = cursor.getColumnIndex(StudentContractReader.StudentEntry.COLUMN_NAME);
            int age = cursor.getColumnIndex(StudentContractReader.StudentEntry.COLUMN_AGE);
            int phone = cursor.getColumnIndex(StudentContractReader.StudentEntry.COLUMN_PHONE);
            int email = cursor.getColumnIndex(StudentContractReader.StudentEntry.COLUMN_EMAIL);

            do {
                Student student = new Student(
                        cursor.getLong(id),
                        cursor.getString(name),
                        cursor.getInt(age),
                        cursor.getString(phone),
                        cursor.getString(email)
                );
                students.add(student);
            } while (cursor.moveToNext());
        }

        cursor.close();
        sqLiteDatabase.close();

        Log.i(StudentContractReader.LOG_TAG, "In db : \n" + students);

        return students;
    }

    @Override
    public Student findById(long id) {
        SQLiteDatabase sqLiteDatabase = openHelper.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(
                StudentContractReader.StudentEntry.TABLE_NAME,
                null,
                StudentContractReader.StudentEntry.COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null
        );

        cursor.moveToFirst();
        int columnIndexId = cursor.getColumnIndex(StudentContractReader.StudentEntry.COLUMN_ID);
        int columnIndexName = cursor.getColumnIndex(StudentContractReader.StudentEntry.COLUMN_NAME);
        int columnIndexAge = cursor.getColumnIndex(StudentContractReader.StudentEntry.COLUMN_AGE);
        int columnIndexPhone = cursor.getColumnIndex(StudentContractReader.StudentEntry.COLUMN_PHONE);
        int columnIndexEmail = cursor.getColumnIndex(StudentContractReader.StudentEntry.COLUMN_EMAIL);

        Student student = new Student(
                cursor.getLong(columnIndexId),
                cursor.getString(columnIndexName),
                cursor.getInt(columnIndexAge),
                cursor.getString(columnIndexPhone),
                cursor.getString(columnIndexEmail)
        );

        return student;
    }

    @Override
    public int update(long id, Student newStudent) {
        SQLiteDatabase sqLiteDatabase = openHelper.getWritableDatabase();


        ContentValues contentValues = new ContentValues();

        contentValues.put(
                StudentContractReader.StudentEntry.COLUMN_AGE, newStudent.getAge()
        );

        contentValues.put(
                StudentContractReader.StudentEntry.COLUMN_NAME, newStudent.getName()
        );

        contentValues.put(
                StudentContractReader.StudentEntry.COLUMN_EMAIL, newStudent.getEmail()
        );

        contentValues.put(
                StudentContractReader.StudentEntry.COLUMN_PHONE, newStudent.getPhone()
        );

        return sqLiteDatabase.update(
                StudentContractReader.StudentEntry.TABLE_NAME,
                contentValues,
                "id = ?",
                new String[] {id + ""}
        );
    }

    @Override
    public int delete(long id) {

        SQLiteDatabase sqLiteDatabase = openHelper.getWritableDatabase();

        return sqLiteDatabase.delete(
                StudentContractReader.StudentEntry.TABLE_NAME,
                "id = ?",
                new String[] {id + ""}
        );
    }
}
