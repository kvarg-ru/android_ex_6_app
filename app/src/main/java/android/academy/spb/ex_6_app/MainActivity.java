package android.academy.spb.ex_6_app;

import android.academy.spb.ex_6_app.db.AppDatabase;
import android.academy.spb.ex_6_app.db.Note;
import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mNotesRecyclerView;
    private NotesAdapter mNotesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNotesRecyclerView = findViewById(R.id.notes_recycler_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //linearLayoutManager.setReverseLayout(true);
        //linearLayoutManager.setStackFromEnd(true);
        mNotesRecyclerView.setLayoutManager(linearLayoutManager);

        mNotesAdapter = new NotesAdapter();
        mNotesRecyclerView.setAdapter(mNotesAdapter);

        mNotesAdapter.updateAll(getNoteList());

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

                Note note = (Note) viewHolder.itemView.getTag();

                mNotesAdapter.deleteNote(note);
                AppDatabase.getInstance(MainActivity.this).noteDao().delete(note);

                Toast.makeText(MainActivity.this, "Swipe!", Toast.LENGTH_SHORT).show();
            }
        });

        itemTouchHelper.attachToRecyclerView(mNotesRecyclerView);

        final EditText titleEditText  = findViewById(R.id.title_et);
        final EditText bodyEditText   = findViewById(R.id.body_et);

        Button insertButton = findViewById(R.id.insert_btn);
        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!TextUtils.isEmpty(titleEditText.getText()) && !TextUtils.isEmpty(bodyEditText.getText())) {
                    Note note = new Note();
                    note.setTitle(titleEditText.getText().toString());
                    note.setBody(bodyEditText.getText().toString());
                    AppDatabase.getInstance(MainActivity.this).noteDao().Insert(note);
                    mNotesAdapter.insertNote(note);
                    mNotesRecyclerView.scrollToPosition(0);
                }
            }
        });

    }

    private List<Note> getNoteList() {

        //AppDatabase.getInstance(this).noteDao().deleteAll();
        /*AppDatabase.getInstance(this).noteDao().InsertAll(createNote("Title11", "Body1"),
                                        createNote("Title22", "Body2"),
                                        createNote("Title33", "Body3")
        );*/

        return AppDatabase.getInstance(this).noteDao().getAll();

        /*
        List<Note> noteList = new ArrayList<>();
        noteList.add(createNote("Title1", "Body1"));
        noteList.add(createNote("Title2", "Body2"));
        noteList.add(createNote("Title3", "Body3"));
        return noteList;
        */

    }

    private Note createNote(String title, String body) {

        Note note = new Note();
        note.setTitle(title);
        note.setBody(body);
        return note;

    }
}
