package com.example.laba3bd.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.laba3bd.R;

public class DeleteDialog extends Dialog {
    private EditText editTextServiceId;

    private Button buttonDeleteRecord;

    private OnIdSelectedListener onIdSelectedListener;

    public DeleteDialog(@NonNull Context context, OnIdSelectedListener onIdSelectedListener) {
        super(context);
        this.onIdSelectedListener = onIdSelectedListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.delete_dialog);

        editTextServiceId = findViewById(R.id.setIdToDelete);
        buttonDeleteRecord = findViewById(R.id.buttonDeleteSelectedFromDB);
        buttonDeleteRecord.setOnClickListener(v->{
            if (editTextServiceId.getText().toString().isEmpty())
            {
                Toast.makeText(getContext(), "Не заповнено відповідне поле", Toast.LENGTH_LONG).show();
            }else{
                onIdSelectedListener.onDataEntered(Integer.parseInt(editTextServiceId.getText().toString()));
                dismiss();
            }

        });
    }

    public interface OnIdSelectedListener{
        void onDataEntered(int serviceId);

    }
}
