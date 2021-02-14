package com.example.banking_app.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHandler extends SQLiteOpenHelper {
    private String BANKHOLDERS = "HOLDERS";
    private String HISTORY = "PASSBOOK";

    public DataBaseHandler(@Nullable Context context) {
        super(context, "User.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + BANKHOLDERS +
                " (PHONENUMBER INTEGER PRIMARY KEY ," +
                "HOLDER_NAME TEXT," +
                "HOLDER_EMAIL VARCHAR," +
                "ACCOUNT_NO VARCHAR," +
                "BANK_NAME VARCHAR," +
                "IFSC_CODE VARCHAR," +
                "ACCOUNT_BALANCE DECIMAL)");

        db.execSQL("CREATE TABLE " + HISTORY +" (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "DATE_OF_TRANSACTION TEXT," +
                "FROM_USER TEXT," +
                "TO_USER TEXT," +
                "AMOUNT DECIMAL," +
                "TRANSACTION_STATUS TEXT," +
                "TRANSACTION_ID TEXT)");

        String Chintan = "INSERT INTO HOLDERS VALUES(" +
                                "911234567890," +
                                "'Chintan'," +
                                "'chintan@gmail.com'," +
                                "'12xxxxxx423'," +
                                "'Devani Bank'," +
                                "'CHIN011145'," +
                                "531.25)",

                Prince = "INSERT INTO HOLDERS VALUES(" +
                            "912345678910," +
                            "'Prince'," +
                            "'prince@gmail.com'," +
                            "'96xxxxxx412'," +
                            "'Prince Bank'," +
                            "'PRIN123456'," +
                            "3301.31)",

                Kirtan = "INSERT INTO HOLDERS VALUES(" +
                            "914512367445," +
                            "'Kirtan'," +
                            "'kirtsn@hotmail.com'," +
                            "'87xxxxxx742'," +
                            "'Kirtan Bank'," +
                            "'KIRT789450'," +
                            "1010.3)",

                Ankush = "INSERT INTO HOLDERS VALUES(" +
                            "914578551428," +
                            "'Ankush'," +
                            "'ankush@yahoo.com'," +
                            "'98xxxxxx123'," +
                            "'Ankush Bank'," +
                            "'ANKU0123456'," +
                            "1550.1)",

                Jemis = "INSERT INTO HOLDERS VALUES(" +
                            "919977882244," +
                            "'Jemis'," +
                            "'jemisnhai@gmail.com'," +
                            "'23xxxxxx561'," +
                            "'Jemisbhai Bank'," +
                            "'JEMS0123456'," +
                            "9001.99)",

                Krish = "INSERT INTO HOLDERS VALUES(" +
                            "917412589631," +
                            "'Krish'," +
                            "'krish@ghelu.com'," +
                            "'11xxxxxx888'," +
                            "'Krish Bank'," +
                            "'KRIS0123456'," +
                            "30551.12)",

                Bhargav = "INSERT INTO HOLDERS VALUES(" +
                            "913692581478," +
                            "'Bhargav'," +
                            "'bhargav@diyora.com'," +
                            "'88xxxxxx777'," +
                            "'Bhargav Bank'," +
                            "'BHAR0123456'," +
                            "3881.13)",

                Rakshil = "INSERT INTO HOLDERS VALUES(" +
                        "918521479630," +
                        "'Rakshil'," +
                        "'rakshil@keavdiya.com'," +
                        "'01xxxxxx745'," +
                        "'Rakshil Bank'," +
                        "'UBER0123456'," +
                        "9994.94)",

                Archil = "INSERT INTO HOLDERS VALUES(" +
                        "9112458789632," +
                        "'Archil'," +
                        "'archil@kat.com'," +
                        "'99xxxxxx451'," +
                        "'Archil Bank'," +
                        "'ARCh0123456'," +
                        "45787.56)",

                Kevin = "INSERT INTO HOLDERS VALUES(" +
                        "919632587410," +
                        "'Kevin'," +
                        "'kevin@khunt.com'," +
                        "'54xxxxxx631'," +
                        "'Kevin Bank'," +
                        "'KEVN0123456'," +
                        "78454.33)";

        db.execSQL(Chintan);
        db.execSQL(Prince);
        db.execSQL(Kirtan);
        db.execSQL(Ankush);
        db.execSQL(Jemis);
        db.execSQL(Krish);
        db.execSQL(Bhargav);
        db.execSQL(Rakshil);
        db.execSQL(Archil);
        db.execSQL(Kevin);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ BANKHOLDERS);
        db.execSQL("DROP TABLE IF EXISTS "+ HISTORY);
        onCreate(db);
    }

    public Cursor showAllHolders(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM HOLDERS", null);
        return cursor;
    }

    public Cursor holderdata(String holderPhoneNumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM HOLDERS where PHONENUMBER = " + holderPhoneNumber, null);
        return cursor;
    }

    public Cursor removeHolder(String holderPhoneNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM HOLDERS " +
                                        "EXCEPT SELECT * FROM HOLDERS " +
                                        "WHERE PHONENUMBER = " + holderPhoneNumber, null);
        return cursor;
    }

    public void updateBalance(String holderPhoneNumber, String updatedBalance){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE HOLDERS " +
                "SET ACCOUNT_BALANCE = " + updatedBalance +
                " WHERE PHONENUMBER = " + holderPhoneNumber);
    }

    public Cursor getPassbook(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM PASSBOOK", null);
        return cursor;
    }

    public boolean transactionStatement(String date, String fromUser, String toUser, String amount, String transactionStatus, long id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("DATE_OF_TRANSACTION", date);
        values.put("FROM_USER", fromUser);
        values.put("TO_USER", toUser);
        values.put("AMOUNT", amount);
        values.put("TRANSACTION_STATUS", transactionStatus);
        values.put("TRANSACTION_ID", Long.toString(id));

        Long result = db.insert(HISTORY, null, values);

        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
