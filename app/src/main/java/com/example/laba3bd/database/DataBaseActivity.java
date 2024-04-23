package com.example.laba3bd.database;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.math.BigDecimal;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laba3bd.GlobalSettings;
import com.example.laba3bd.MainActivity;
import com.example.laba3bd.R;
import com.example.laba3bd.recycler_view.DbItem;
import com.example.laba3bd.recycler_view.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class DataBaseActivity extends AppCompatActivity {
    private SQLiteDatabase database;
    private List<DbItem> dataList;

    RecyclerView recyclerViewForDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base);
        dataList = new ArrayList<>();
        database = Database.getDatabase();


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        recyclerViewForDB = findViewById(R.id.recyclerViewForDB);

        try {

            Cursor cursor = database.rawQuery(GlobalSettings.selectTableQuery(), null);
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow(GlobalSettings.ServiceId_FIELD));
                    String recordAddedTime = cursor.getString(cursor.getColumnIndexOrThrow(GlobalSettings.ServiceTimeAdded_FIELD));
                    String serviceName = cursor.getString(cursor.getColumnIndexOrThrow(GlobalSettings.ServiceName_FIELD));
                    String description = cursor.getString(cursor.getColumnIndexOrThrow(GlobalSettings.ServiceDescription_FIELD));
                    BigDecimal price = new BigDecimal(cursor.getString(cursor.getColumnIndexOrThrow(GlobalSettings.ServicePrice_FIELD)));

                    dataList.add(new DbItem(id, recordAddedTime, serviceName, description, price));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception e) {
            showAlertDialog();
        }


        if (!dataList.isEmpty()) {
            recyclerViewForDB.setLayoutManager(new LinearLayoutManager(this));
            recyclerViewForDB.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
            MyAdapter adapter = new MyAdapter(dataList);
            recyclerViewForDB.setAdapter(adapter);
        } else showAlertDialog();

    }

    private void showAlertDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DataBaseActivity.this);
        alertDialogBuilder.setTitle("Тут ні на що дивитись")
                .setMessage("В базі даних немає жодного запису")
                .setPositiveButton("Ок", (dialog, which) ->
                        startActivity(new Intent(DataBaseActivity.this, MainActivity.class)));
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
