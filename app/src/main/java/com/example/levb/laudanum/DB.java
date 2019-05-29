package com.example.levb.laudanum;


import android.content.Context;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DB {
    static final String ATTR_TITLE="title";
    static final String ATTR_IMG="img";
    static int[] values={88,98,45};
    static String[] title={"Act create goodness","Life light","Faith"};
    static int[] img={R.drawable.bl3,R.drawable.bl2,R.drawable.bl};


    private static final String DB_NAME = "mydb";
    private static final int DB_VERSION = 1;
    private static final String DB_TABLE = "mytab";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_IMG = "img";
    public static final String COLUMN_TITLE = "title";


    static final String DB_CREATE =
            "create table " + DB_TABLE + "("
                    + COLUMN_ID + " integer primary key autoincrement,"
                    + COLUMN_TITLE + " text,"
                    + COLUMN_IMG + " integer" + ");";

    private final Context mCtx;
    public DB(Context context){
        mCtx=context;
    }
    private DBHelper mdbhelper;
    private SQLiteDatabase mDb;

    public void open(){
        mdbhelper=new DBHelper(mCtx,DB_NAME,null,DB_VERSION);
        mDb=mdbhelper.getWritableDatabase();

    }
    public void close(){
        if(mdbhelper!=null) mdbhelper.close();
    }

    public Cursor getDataAll(){
        return mDb.query(DB_TABLE,null,null,null,null,null,null,null);
    }
    public void addRec(String title,int img){
        ContentValues cv=new ContentValues();
        cv.put(ATTR_TITLE,title);
        cv.put(ATTR_IMG,img);
        mDb.insert(DB_TABLE,null,cv);
    }
    public void delRec(){
        mDb.delete(DB_TABLE,null,null);
    }

    public class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context,String name,SQLiteDatabase.CursorFactory factory,int version){
            super(context,name,factory,version);
        }

        @Override
        public void onCreate(SQLiteDatabase db){
            db.execSQL(DB_CREATE);
            ContentValues cv;
            cv=new ContentValues();
            for(int i=0;i<values.length;i++){

                cv.put(ATTR_TITLE,title[i]);
                cv.put(ATTR_IMG,img[i]);
                db.insert(DB_TABLE,null,cv);
            }

        }
        @Override
        public void onUpgrade(SQLiteDatabase db,int old,int newVers){

        }

    }

}
