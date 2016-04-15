package area51.sqliteexample;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
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

    private ListView listView;
    private ListFragmentInterface listFragmentInterface;

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
        listView.setAdapter(new NoteAdapter(getContext(), getNotes()));
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

    private ArrayList<Note> getNotes() {
        final ContentResolver contentResolver = getActivity().getContentResolver();
        final Cursor cursor = contentResolver.query(NoteContract.URI, null, null, null, null);
        final ArrayList<Note> notes = new ArrayList<>();
        while (cursor.moveToNext()) {
            final long id = cursor.getLong(cursor.getColumnIndex("_id"));
            final String title = cursor.getString(cursor.getColumnIndex("title"));
            final String content = cursor.getString(cursor.getColumnIndex("content"));
            final long creationTimeStamp = cursor.getLong(cursor.getColumnIndex("creationTimeStamp"));
            final long modificationTimeStamp = cursor.getLong(cursor.getColumnIndex("modificationTimeStamp"));
            notes.add(new Note(id, title, content, creationTimeStamp, modificationTimeStamp));
        }
        cursor.close();
        return notes;
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
        final String title = "New Title";
        final String content = "My new content...";
        final long creationTimestamp = System.currentTimeMillis();
        final long modificationTimestamp = System.currentTimeMillis();
        final ContentResolver contentResolver = getActivity().getContentResolver();
        final ContentValues contentValues = new ContentValues();
        contentValues.put(NoteContract.TITLE, title);
        contentValues.put(NoteContract.CONTENT, content);
        contentValues.put(NoteContract.CREATION_TIMESTAMP, creationTimestamp);
        contentValues.put(NoteContract.MODIFICATION_TIMESTAMP, modificationTimestamp);
        final Uri newNoteUri = contentResolver.insert(NoteContract.URI, contentValues);
        final long id = ContentUris.parseId(newNoteUri);
        ((NoteAdapter) listView.getAdapter()).add(new Note(id, title, content, creationTimestamp, modificationTimestamp));
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
