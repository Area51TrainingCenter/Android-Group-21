package area51.sqliteexample;

import android.net.Uri;

/**
 * Created by alumno on 14/04/16.
 */
public class NoteContract {

    public static final Uri URI = Uri.parse("content://pe.area51.sqliteexample.ContentProvider/note");

    public static final String ID = "_id";
    public static final String TITLE = "title";
    public static final String CONTENT = "content";
    public static final String CREATION_TIMESTAMP = "creationTimeStamp";
    public static final String MODIFICATION_TIMESTAMP = "modificationTimeStamp";

    public static final String MIME_DIR = "vnd.android.cursor.dir/pe.area51.sqliteexample.Note";
    public static final String MIME_ITEM = "vnd.android.cursor.item/pe.area.51.sqliteexample.Note";

}
