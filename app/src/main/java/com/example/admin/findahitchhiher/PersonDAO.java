package com.example.admin.findahitchhiher;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface PersonDAO {

    @Insert
    void insert(Person person);

    @Query("DELETE FROM Person")
    void deleteAll();

    @Query("SELECT * from Person ORDER BY first_name ASC")
    List<Person> getAllPersons();



}
