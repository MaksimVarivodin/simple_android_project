package com.example.laba3bd;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.laba3bd.database.DataBaseActivity;
import com.example.laba3bd.database.Database;
import com.example.laba3bd.dialogs.AddDialog;
import com.example.laba3bd.dialogs.DeleteDialog;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements AddDialog.OnDataEnteredListener, DeleteDialog.OnIdSelectedListener {
    Button buttonShowDB;
    Button buttonAddToDB;
    Button buttonDeleteFromDB;

    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        buttonShowDB = findViewById(R.id.buttonShowDB);
        buttonAddToDB = findViewById(R.id.buttonAddToDB);
        buttonDeleteFromDB = findViewById(R.id.buttonDeleteFromDB);

        db = Database.getDatabase();
        deleteAllRecords();

        buttonShowDB.setOnClickListener(l ->
                startActivity(new Intent(this, DataBaseActivity.class)));

        buttonAddToDB.setOnClickListener(l -> {
            AddDialog dialog = new AddDialog(this, this);
            dialog.show();
        });

        buttonDeleteFromDB.setOnClickListener(l -> {
            DeleteDialog dialog = new DeleteDialog(this, this);
            dialog.show();

        });


    }

    @Override
    public void onDataEntered(String serviceName, String serviceDescription, String servicePrice) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String currentTime = dateFormat.format(new Date());


        boolean success = addRecord(currentTime, serviceName, serviceDescription, servicePrice);
        if (success) {
            Toast.makeText(this, "Запис успішно додано до бази данних", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Помилка при додаванні запису до бази данних", Toast.LENGTH_LONG).show();
        }
    }


    public boolean addRecord(String currentTime, String serviceName, String serviceDescription, String servicePrice) {
        try {
            ContentValues values = new ContentValues();
            values.put(GlobalSettings.ServiceTimeAdded_FIELD, currentTime);
            values.put(GlobalSettings.ServiceName_FIELD, serviceName);
            values.put(GlobalSettings.ServiceDescription_FIELD, serviceDescription);
            values.put(GlobalSettings.ServicePrice_FIELD, servicePrice);
            long result = db.insert(GlobalSettings.TABLE_NAME, null, values);
            return result != -1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
        // Возвращаем true, если вставка прошла успешно, иначе - false

    }

    public boolean deleteRecord(int serviceId) {
        try {
            if (GlobalSettings.countTableRecordsQuery() > 0 && GlobalSettings.countFoundInTableQuery(serviceId)>=1) {
                db.execSQL(GlobalSettings.deleteByIdQuery(serviceId));
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void deleteAllRecords() {
        this.db.delete(GlobalSettings.TABLE_NAME, null, null);
    }

    @Override
    public void onDataEntered(int serviceId) {
        if (deleteRecord(serviceId)) {
            Toast.makeText(this, String.format("Було видалено запис: %d", serviceId), Toast.LENGTH_LONG).show();
        } else
            Toast.makeText(this, "В базі даних немає жодного запису", Toast.LENGTH_LONG).show();
    }
}