package com.example.test020323.db;

public class StudentContractReader {
    private StudentContractReader() {}

    public static final String DATABASE_NAME = "Student.db";
    public static final int DATABASE_VERSION = 1;
    public static final String LOG_TAG = "LOG";

    public static class StudentEntry {
        public static final String TABLE_NAME = "student";

        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_AGE = "age";
        public static final String COLUMN_PHONE = "phone";
        public static final String COLUMN_EMAIL = "email";

    }

}
