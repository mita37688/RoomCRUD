package com.example.sqliteandroid_roomdb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StudentDao {
    @Query("SELECT * FROM Student")
    List<Student> getAll();

    @Query("SELECT * FROM Student WHERE id IN (:userIds)")
    List<Student> loadAllByIds(int[] userIds);

    @Insert
    void insertAll(Student... users);

    @Delete
    void delete(Student student);

    @Update
    void update(Student student);
}
