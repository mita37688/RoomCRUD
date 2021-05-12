package com.example.sqliteandroid_roomdb;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Student {

    @PrimaryKey
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    public Student(String name) {
        this.name = name;
    }

    @Ignore
    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "[" + id + "] -  " + name;
    }
}
