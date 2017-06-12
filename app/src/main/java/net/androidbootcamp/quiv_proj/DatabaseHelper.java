package net.androidbootcamp.quiv_proj;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Name
    private static final String DATABASE_NAME = "quizproject.db";

    // Table Names
    private static final String TABLE_teacher= "teacher";
    private static final String TABLE_course= "course";

    // Column of teacher
    private static final String id_teacher = "id_teacher";
    private static final String name_teacher = "name_teacher";
    private static final String password_teacher = "password_teacher";


    // Column of course
    private static final String id_course= "id_course";
    private static final String name_course = "name_teacher";
    private static final String id_teacher_course = "id_teacher";

    // Table Create  teacher
    private static final String Create_Table_teacher= "CREATE TABLE "
            + TABLE_teacher + "(" + id_teacher + " INTEGER PRIMARY KEY," + name_teacher
            + " TEXT," + password_teacher + " TEXT)";

    //  Table create course
    private static final String Create_Table_Course= "CREATE TABLE "
            + TABLE_course + "(" + id_course + " INTEGER PRIMARY KEY," + name_course
            + " TEXT,"+" FOREIGN KEY ("+id_teacher_course+") REFERENCES "+TABLE_teacher+" ("+id_teacher+"))";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Create_Table_teacher);
        sqLiteDatabase.execSQL(Create_Table_Course);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_teacher);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_course);
        onCreate(sqLiteDatabase);
    }
    public boolean insertData(int id_teacher_in,String name_teacher_in,int password_teacher_in ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(id_teacher,id_teacher_in);
        contentValues.put(name_teacher,name_teacher_in);
        contentValues.put(password_teacher,password_teacher_in);

        long result = db.insert(TABLE_teacher,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_teacher,null);
        return res;
    }

    public boolean updateData(int id_teacher_in,String name_teacher_in,int password_teacher_in ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(id_teacher,id_teacher_in);
        contentValues.put(name_teacher,name_teacher_in);
        contentValues.put(password_teacher,password_teacher_in);

        db.update(TABLE_teacher, contentValues, "id_teacher = ?",new String[] { id_teacher });
        return true;
    }



}























/*
public class databaseHelper extends SQLiteOpenHelper{
    public  static final String DB_name = "quizSystem.sqlite";
    public  static final String DB_path = "/data/data/net.androidbootcamp.quiv_proj/database/";
    public Context context ;
    private SQLiteDatabase database ;

    public databaseHelper(Context context) {
        super(context,DB_name,null,1);
    }

   @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void openDatabase(){
        String db_path = context.getDatabasePath(DB_name).getPath();
        if (database != null && database.isOpen()){
            return;
        }
        database =SQLiteDatabase.openDatabase(db_path,null,SQLiteDatabase.OPEN_READWRITE);
    }
    public void closeDB(){
        if (database!= null){
            database.close();
        }
    }
public List<Product> getListP()

    {
        Product prodect= null;
        List

        openDatabase();
        Cursor cursor =database.rawQuery("SELECT * From techer",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            prodect = new Product(cursor.getInt(0),cursor.getString(1),cursor.getInt(2));
            Product


        }

    }*/


