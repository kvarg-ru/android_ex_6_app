package android.academy.spb.ex_6_app.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by User on 12.05.2018.
 */

@Database(entities = {Note.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NoteDao noteDao();

    // Singleton
    private final static String mDATABASE_NAME = "NotesDB";

    private static AppDatabase mDbInstance;

    public static AppDatabase getInstance(Context context) {
        if (mDbInstance == null) {
            mDbInstance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, mDATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return mDbInstance;
    }

    public static void deleteInstance() {
        mDbInstance = null;
    }

}
