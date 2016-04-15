package area51.sqliteexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class SQLiteManager extends SQLiteOpenHelper {

    private static SQLiteManager instance;

    public static final String DATABASE_NAME = "notes";
    public static final int DATABASE_VERSION = 1;

    public static SQLiteManager getInstance(final Context context) {
        if (instance == null) {
            instance = new SQLiteManager(context.getApplicationContext());
        }
        return instance;
    }

    private SQLiteManager(final Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String creationScript =
                "CREATE TABLE notes(_id INTEGER PRIMARY KEY, title TEXT, content TEXT, creationTimeStamp INTEGER, modificationTimeStamp INTEGER)";
        db.execSQL(creationScript);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        throw new SQLiteException("We are in the database version 1, so this method shouldn't be fired!");
    }

    public ArrayList<Note> getNotes() {
        final String query = "SELECT * FROM notes";
        final Cursor cursor = getReadableDatabase().rawQuery(query, null);
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

    public long insertNote(final Note note) {
        final ContentValues contentValues = new ContentValues();
        contentValues.put("title", note.getTitle());
        contentValues.put("content", note.getContent());
        contentValues.put("creationTimeStamp", note.getCreationTimestamp());
        contentValues.put("modificationTimeStamp", note.getModificationTimestamp());
        return getWritableDatabase().insert("notes", null, contentValues);
    }

}
