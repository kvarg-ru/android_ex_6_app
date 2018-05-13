package android.academy.spb.ex_6_app;

import android.academy.spb.ex_6_app.db.Note;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 12.05.2018.
 */

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteHolder> {

    private List<Note> noteList = new ArrayList<>();

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View viewItem = layoutInflater.inflate(R.layout.item_view, parent, false);
        return new NoteHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {

        Note note = noteList.get(position);

        holder.itemView.setTag(note);

        holder.title_tv.setText(note.getTitle());
        holder.body_tv.setText(note.getBody());

    }

    @Override
    public int getItemCount() {

        return noteList.size();

    }

    public void updateAll(List<Note> noteList) {
        this.noteList = noteList;
        notifyDataSetChanged();
    }

    public void deleteNote(Note note) {

        int position = noteList.indexOf(note);
        noteList.remove(position);

        notifyItemRemoved(position);
    }

    public void insertNote(Note note) {

        noteList.add(0, note);

        notifyItemInserted(0);

    }

    class NoteHolder extends RecyclerView.ViewHolder {

        public TextView title_tv;
        public TextView body_tv;

        public NoteHolder(View itemView) {
            super(itemView);
            title_tv = itemView.findViewById(R.id.title_tv);
            body_tv = itemView.findViewById(R.id.body_tv);
        }

    }

}
