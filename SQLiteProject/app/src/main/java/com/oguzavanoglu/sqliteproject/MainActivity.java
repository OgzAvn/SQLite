package com.oguzavanoglu.sqliteproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            //SQLiteDatabase bizim sınıfımız ve bu sınıftan bir obje türeterek bir veri tabanı oluşturuyoruz.
            SQLiteDatabase database = this.openOrCreateDatabase("Musicians",MODE_PRIVATE,null);//Bu bana bir veri tabanı açıyor şimdi.

            //Table oluşturmamız lazım.
            database.execSQL("CREATE TABLE IF NOT EXISTS musicians(id INTEGER PRIMARY KEY,name VARCHAR ,age INT)");

            //Bundan sonra verileri girmemiz gerekiyor.
            //database.execSQL("INSERT INTO musicians (name,age) VALUES('Oguz',26)"); //Yorum satırına aldık ki tekrar tekrar kaydetmesin.
            //database.execSQL("INSERT INTO musicians (name,age) VALUES('Ertugrul',22)"); //Bir kere çalıştıktan sonra yorum satırına almamız gerek.
            //database.execSQL("INSERT INTO musicians (name,age) VALUES('Tuncay',24)");

            //GÜncelleme Oguz un yaşını 27 yapmak istiyorum:Nasıl yapabiliirm?
            //database.execSQL("UPDATE musicians SET age = 27 WHERE name = 'Oguz'");
            //database.execSQL("UPDATE musicians SET name = 'Tuncay Avanoglu' WHERE id = 3");

            //Delete işlemi nasıl yapılır?
            database.execSQL("DELETE FROM musicians WHERE id=2");

            //Şİmdi bunu kaydettik peki bunu nasıl okuyacağız READ yapacağız. ONu görelim.
            //Cursor cursor = database.rawQuery("SELECT * FROM musicians WHERE name ='Oguz'",null); //Query veri tabanından sorgu yapmak demek.Filitre istemiyoruz null yaptık.
            Cursor cursor = database.rawQuery("SELECT * FROM musicians ",null);
            //Cursor cursor = database.rawQuery("SELECT * FROM musicians WHERE name LIKE 'T%'",null);

            //Benim bu cursor a hangi coloumn(sütünlar) a gideceğimi söylemem lazım.
            int nameIx = cursor.getColumnIndex("name");
            int ageIx = cursor.getColumnIndex("age");
            int idIx = cursor.getColumnIndex("id");

            //Cursor ilerleyebildiği kadar ilerlesin tek tek gezsin bütün verileri ve o sırada benim yapmak istediğim bazı şeyler var
            //yapmak istediğim şey ise o değerleri okumak.
            while (cursor.moveToNext()){
                System.out.println("Name : " + cursor.getString(nameIx));
                System.out.println("Age  :" + cursor.getInt(ageIx));
                System.out.println("id :" + cursor.getInt(idIx));
            }


            cursor.close();


        }catch (Exception e){
            e.printStackTrace();
        }


    }
}