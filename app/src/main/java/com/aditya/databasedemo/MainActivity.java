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

        try {
            //creating an SQL Lite Data base which can store large amounts of data and is a robust system that can store data
            //SQLiteDatabase myDatabase = this.openOrCreateDatabase("Users = name of the database",MODE_PRIVATE = only this app can access this database, exceptions);
            //this will create a new database for us and if the data base is already created and ready to roll then it will open the existing database in the file system
            SQLiteDatabase myDatabase = this.openOrCreateDatabase("Users", MODE_PRIVATE, null);
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
            /*
            myDatabase.execSQL("INSERT INTO users (name,age) VALUES('Aditya Kumar', 23)");
            myDatabase.execSQL("INSERT INTO users (name,age) VALUES('Leah Gotti', 23)");
            myDatabase.execSQL("INSERT INTO users (name,age) VALUES('Mia melano', 20)");
            myDatabase.execSQL("INSERT INTO users (name,age) VALUES('Natasha Malkova', 26)");
            myDatabase.execSQL("INSERT INTO users (name,age) VALUES('Rienna belle', 32)");
            myDatabase.execSQL("INSERT INTO users (name,age) VALUES('Lexi belle', 24)");
            myDatabase.execSQL("INSERT INTO users (name,age) VALUES('Melody marks', 17)");
            myDatabase.execSQL("INSERT INTO users (name,age) VALUES('yui Hanato', 18)");
            myDatabase.execSQL("INSERT INTO users (name,age) VALUES('Victoria Daniels', 19)");
            myDatabase.execSQL("INSERT INTO users (name,age) VALUES('Isabella', 16)");
            myDatabase.execSQL("INSERT INTO users (name,age) VALUES('Marry Queen', 20)");
            myDatabase.execSQL("INSERT INTO users (name,age) VALUES('Marry Queen', 21)");
            myDatabase.execSQL("INSERT INTO users (name,age) VALUES('Marry Queen', 22)");

             */

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
            while (!c.isAfterLast()) {
                Log.i("nameStored", c.getString(nameindex) + " = " + c.getInt(ageindex));
                c.moveToNext(); //its like i++ but for sql it is essentially updating the cursor inside the users table
            }

            //getting names and age of people who's age is less than 20
            //PULLING DATA OUT OF A DATABASE
            //Cursor c allows us to pull data out of a database
            //rawQuery() = pull data out of a database here myDatabase
            //Cursor c = myDatabase.rawQuery("SELECT * FROM users", null);
            //SELECT * FROM users WHERE age <20 = select ony those who's age is less than 20 from the table users ***
            //SELECT * FROM users WHERE = 'Aditya Kumar' -> it will only give us the users who's name is Aditya Kumar from the database
           //SELECT * FROM users WHERE = 'Aditya Kumar' AND age = 43 -> it will only give us the users who's name is Aditya Kumar and id 43 years old from the database
            // SELECT * FROM users WHERE name LIKE 'N%' -> it will get the users who's name starts with an N from the database
            //SELECT * FROM users WHERE name LIKE '%a%' -> it will get the users who's name contains letter 'a' it doesn't matter where that 'a' is present in their name
            //SELECT * FROM users WHERE name LIKE '%a%' LIMIT 1-> it does the same thing as above but limits the result to 1
            Cursor c2 = myDatabase.rawQuery("SELECT * FROM users WHERE age <20", null);
            //if we want to access the name and the age inside the table then we have to get the indexes of the name and age present inside the users table
            //c.getColumnIndex("name") = it will use cursor c to get the column index for name
            int nameindex2 = c2.getColumnIndex("name");
            //c.getColumnIndex("age") = it will use cursor c to get the column index for age
            int ageindex2 = c2.getColumnIndex("age");
            //setting the cursor to the starting position
            c2.moveToFirst();

            //after we've set our cursor to starting position we have to loop through each row inside the table
            while (!c2.isAfterLast()) {
                Log.i("LessThan20stored", c2.getString(nameindex2) + " = " + c2.getInt(ageindex2));
                c2.moveToNext(); //its like i++ but for sql it is essentially updating the cursor inside the users table
            }

            //it will delete all the users who's name is Marry Queen from the database
            /*
            myDatabase.execSQL("DELETE FROM users WHERE name = 'Marry Queen'");
             */

            //in order to delete a particular Marry Queen from a database we will be needing a unique id for that user
            //this property of giving a unique id to the user names inside of the database have to be updated in the database
            //the example code below demonstrates the concept clearly
            //id INTEGER PRIMARY KEY = it gives primary key i.e a unique key to each of the users present inside of that database
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS newUsers (name VARCHAR, age INT(3), id INTEGER PRIMARY KEY)");

            //adding data to the newUsers table
            /*
            myDatabase.execSQL("INSERT INTO newUsers (name,age) VALUES('Aditya Kumar', 23)");
            myDatabase.execSQL("INSERT INTO newUsers (name,age) VALUES('Leah Gotti', 23)");
            myDatabase.execSQL("INSERT INTO newUsers (name,age) VALUES('Mia melano', 20)");
            myDatabase.execSQL("INSERT INTO newUsers (name,age) VALUES('Natasha Malkova', 26)");
            myDatabase.execSQL("INSERT INTO newUsers (name,age) VALUES('Rienna belle', 32)");
            myDatabase.execSQL("INSERT INTO newUsers (name,age) VALUES('Lexi belle', 24)");
            myDatabase.execSQL("INSERT INTO newUsers (name,age) VALUES('Melody marks', 17)");
            myDatabase.execSQL("INSERT INTO newUsers (name,age) VALUES('yui Hanato', 18)");
            myDatabase.execSQL("INSERT INTO newUsers (name,age) VALUES('Victoria Daniels', 19)");
            myDatabase.execSQL("INSERT INTO newUsers (name,age) VALUES('Isabella', 16)");
            myDatabase.execSQL("INSERT INTO newUsers (name,age) VALUES('Marry Queen', 20)");
            myDatabase.execSQL("INSERT INTO newUsers (name,age) VALUES('Marry Queen', 21)");
            myDatabase.execSQL("INSERT INTO newUsers (name,age) VALUES('Marry Queen', 22)");
             */

            //getting names and age of people who's age is less than 20
            //PULLING DATA OUT OF A DATABASE
            //Cursor c allows us to pull data out of a database
            //rawQuery() = pull data out of a database here myDatabase
            //Cursor c = myDatabase.rawQuery("SELECT * FROM users", null);
            //SELECT * FROM users WHERE age <20 = select ony those who's age is less than 20 from the table users ***
            //SELECT * FROM users WHERE = 'Aditya Kumar' -> it will only give us the users who's name is Aditya Kumar from the database
            //SELECT * FROM users WHERE = 'Aditya Kumar' AND age = 43 -> it will only give us the users who's name is Aditya Kumar and id 43 years old from the database
            // SELECT * FROM users WHERE name LIKE 'N%' -> it will get the users who's name starts with an N from the database
            //SELECT * FROM users WHERE name LIKE '%a%' -> it will get the users who's name contains letter 'a' it doesn't matter where that 'a' is present in their name
            //SELECT * FROM users WHERE name LIKE '%a%' LIMIT 1-> it does the same thing as above but limits the result to 1
            Cursor c3 = myDatabase.rawQuery("SELECT * FROM newUsers", null);
            //if we want to access the name and the age inside the table then we have to get the indexes of the name and age present inside the users table
            //c.getColumnIndex("name") = it will use cursor c to get the column index for name
            int nameindex3 = c3.getColumnIndex("name");
            //c.getColumnIndex("age") = it will use cursor c to get the column index for age
            int ageindex3 = c3.getColumnIndex("age");
            //adding a unique id to each of the user name present inside of the table
            int idindex = c3.getColumnIndex("id");
            //setting the cursor to the starting position
            c3.moveToFirst();

            //after we've set our cursor to starting position we have to loop through each row inside the table
            while (!c3.isAfterLast()) {
                Log.i("idIndex", c3.getString(nameindex3) + " = " + c3.getInt(ageindex3)+" id -> "+c3.getInt(idindex));
                c3.moveToNext(); //its like i++ but for sql it is essentially updating the cursor inside the users table
            }
            //deleting Marry Queen who's unique ID is 12 in the newUsers table from the database
            myDatabase.execSQL("DELETE FROM newUsers WHERE id = 13");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}