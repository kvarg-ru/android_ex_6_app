package android.academy.spb.ex_6_app.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by User on 12.05.2018.
 */

@Dao
public interface NoteDao {

    @Query("SELECT * FROM Note ORDER BY id DESC")
    List<Note> getAll();

    @Query("SELECT * FROM Note WHERE id = :id")
    Note getNodeById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void InsertAll(Note... notes);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void Insert(Note note);

    @Delete
    void delete(Note note);

    @Query("DELETE FROM Note")
    void deleteAll();
}
