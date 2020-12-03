package com.aditya.databasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //creating an SQL Lite Data base which can store large amounts of data and is a robust system that can store data
        //SQLiteDatabase myDatabase = this.openOrCreateDatabase("Users = name of the database",MODE_PRIVATE = only this app can access this database, exceptions);
        //this will create a new database for us and if the data base is already created and ready to roll then it will open the existing database in the file system
        SQLiteDatabase myDatabase = this.openOrCreateDatabase("Users",MODE_PRIVATE,null);
        //after we've successfully created a database then after that we have to create a table for storing the data
        //myDatabase.execSQL(); will execute the sql that we've created
        //sql is an entire different language and it allows us to interact with the databases
        //sql is all capital letters except for the name of the databases,variables ,table names  and stuff like that
        //CREATE TABLE IF NOT EXISTS = make a table inside this database as long as it does not exist
        //users() = name of the table
        //users(properties of a table named user)
        /*users(name VARCHAR, age INT(3)) -> VARCHAR = collection of characters similar to String
                                          -> age INT(number of digits an integer can hold)
         */
        myDatabase.execSQL("CREATE TABLE IF NOT EXISTS users (name VARCHAR, age INT(3))");
        //ADDING DATA INSIDE OF DATABASE
        //putting information inside the users table
        //use single quotes when using VARCHAR in sql example:- ' '
        //name = 'Aditya Kumar'
        //age = 23
        myDatabase.execSQL("INSERT INTO users (name,age) VALUES('Aditya Kumar', 23)");
        myDatabase.execSQL("INSERT INTO users (name,age) VALUES('Leah Gotti', 23)");
        myDatabase.execSQL("INSERT INTO users (name,age) VALUES('Mia melano', 20)");
        myDatabase.execSQL("INSERT INTO users (name,age) VALUES('Natasha Malkova', 26)");
        myDatabase.execSQL("INSERT INTO users (name,age) VALUES('Rienna belle', 32)");
        myDatabase.execSQL("INSERT INTO users (name,age) VALUES('Lexi belle', 24)");

        //PULLING DATA OUT OF A DATABASE
        //Cursor c allows us to pull data out of a database
        //rawQuery() = pull data out of a database here myDatabase
        //Cursor c = myDatabase.rawQuery("SELECT * FROM users", null);
        //SELECT * FROM users = select everything from the table users
        Cursor c = myDatabase.rawQuery("SELECT * FROM users", null);
        //if we want to access the name and the age inside the table then we have to get the indexes of the name and age present inside the users table
        //c.getColumnIndex("name") = it will use cursor c to get the column index for name
        int nameindex = c.getColumnIndex("name");
        //c.getColumnIndex("age") = it will use cursor c to get the column index for age
        int ageindex = c.getColumnIndex("age");
        //setting the cursor to the starting position
        c.moveToFirst();

        //after we've set our cursor to starting position we have to loop through each row inside the table
        while(!c.isAfterLast()){
            Log.i("nameStored",c.getString(nameindex)+" = "+c.getString(ageindex));
            c.moveToNext(); //its like i++ but for sql it is essentially updating the cursor inside the users table
        }
    }
}