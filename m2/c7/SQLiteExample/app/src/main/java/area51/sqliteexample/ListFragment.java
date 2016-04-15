package area51.sqliteexample;

import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListFragment extends Fragment {

    private final static int TEST_NOTES_SIZE = 100;
    private ListView listView;
    private ListFragmentInterface listFragmentInterface;

    private SQLiteManager sqLiteManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_list, container, false);
        listView = (ListView) view.findViewById(R.id.listview_elements);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        sqLiteManager = SQLiteManager.getInstance(getActivity());
        listView.setAdapter(new NoteAdapter(getContext(), sqLiteManager.getNotes()));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (listFragmentInterface != null) {
                    listFragmentInterface.onNoteSelected(((NoteAdapter) parent.getAdapter()).getItem(position));
                }
            }
        });
    }


    public void setListFragmentInterface(final ListFragmentInterface listFragmentInterface) {
        this.listFragmentInterface = listFragmentInterface;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add_note:
                addNote();
                return true;
        }
        return false;
    }

    private void addNote() {
        final String title = "New Note";
        final String content = "New note content...";
        final long creationTimeStamp = System.currentTimeMillis();
        final long modificationTimeStamp = System.currentTimeMillis();
        final long id = sqLiteManager.insertNote(new Note(-1, title, content, creationTimeStamp, modificationTimeStamp));
        final Note newNote = new Note(id, title, content, creationTimeStamp, modificationTimeStamp);
        ((NoteAdapter) listView.getAdapter()).add(newNote);
    }

    private ArrayList<Note> getTestData(final int size) {
        final ArrayList<Note> notes = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            final Note note = new Note(
                    (i + 1),
                    "Title " + (i + 1),
                    "Content " + (i + 1),
                    System.currentTimeMillis(),
                    System.currentTimeMillis());
            notes.add(note);
        }
        return notes;
    }

    public static class NoteAdapter extends ArrayAdapter<Note> {

        private final LayoutInflater layoutInflater;

        NoteAdapter(final Context context, final ArrayList<Note> notes) {
            super(context, 0, notes);
            layoutInflater = LayoutInflater.from(getContext());
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            /*
            Esta forma de generar las vistas no es eficiente. Se debería utilizar por ejemplo
            el patrón ViewHolder, de tal forma que se aprovechen las vistas reutilizadas (convertView)
            y se disminuya el uso de operaciones costosas como el "findViewById".
            Fuente: http://developer.android.com/intl/es/training/improving-layouts/smooth-scrolling.html
             */
            final View viewElement = layoutInflater.inflate(R.layout.note_element, parent, false);
            final TextView title = (TextView) viewElement.findViewById(R.id.note_title);
            final TextView date = (TextView) viewElement.findViewById(R.id.note_creation_date);
            final Note note = getItem(position);
            title.setText(note.getTitle());
            date.setText(String.valueOf(note.getCreationTimestamp()));
            return viewElement;
        }
    }

    public interface ListFragmentInterface {

        public void onNoteSelected(final Note note);

    }
}
