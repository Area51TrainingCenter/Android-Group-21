package area51.sqliteexample;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by alumno on 14/04/16.
 */
public class MyContentProvider extends ContentProvider {

    private SQLiteManager sqLiteManager;

    private static final UriMatcher URI_MATCHER;

    private static final int NOTE = 1;
    private static final int NOTE_ID = 2;

    static {
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        URI_MATCHER.addURI(NoteContract.URI.getAuthority(), NoteContract.URI.getPath(), NOTE);
        URI_MATCHER.addURI(NoteContract.URI.getAuthority(), NoteContract.URI.getPath() + "/#", NOTE_ID);
    }

    @Override
    public boolean onCreate() {
        sqLiteManager = SQLiteManager.getInstance(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        final int match = URI_MATCHER.match(uri);
        if (match != UriMatcher.NO_MATCH) {
            if (match == NOTE_ID) {
                selection = "_id=" + uri.getLastPathSegment();
            }
            return sqLiteManager
                    .getReadableDatabase()
                    .query("notes", projection, selection, selectionArgs, null, null, sortOrder);
        }
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (URI_MATCHER.match(uri)) {
            case NOTE:
                return NoteContract.MIME_DIR;
            case NOTE_ID:
                return NoteContract.MIME_ITEM;
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        if (URI_MATCHER.match(uri) == NOTE) {
            final long id = sqLiteManager
                    .getWritableDatabase()
                    .insert("notes", null, values);
            return id != -1 ? ContentUris.withAppendedId(uri, id) : null;
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
