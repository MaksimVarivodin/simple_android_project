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

public class AddDialog extends Dialog {
    private EditText editTextServiceName;
    private EditText editTextDescription;

    private EditText editTextPrice;
    private Button buttonAddNewRecord;
    private OnDataEnteredListener onDataEnteredListener;

    public AddDialog(@NonNull Context context, OnDataEnteredListener listener) {
        super(context);
        onDataEnteredListener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.add_dialog);

        editTextServiceName = findViewById(R.id.setServiceName);
        editTextDescription = findViewById(R.id.setServiceDescription);
        editTextPrice = findViewById(R.id.setServicePrice);
        buttonAddNewRecord = findViewById(R.id.buttonAddNewService);

        buttonAddNewRecord.setOnClickListener(v -> {

            String serviceName = editTextServiceName.getText().toString(),
                    serviceDescription = editTextDescription.getText().toString(),
                    servicePrice = editTextPrice.getText().toString();

            if (serviceName.isEmpty()|| serviceDescription.isEmpty() || servicePrice.isEmpty())
                Toast.makeText(getContext(), "Необхідно заповнити всі поля", Toast.LENGTH_LONG).show();
            else {
                onDataEnteredListener.onDataEntered(serviceName, serviceDescription, servicePrice);
                dismiss();
            }
        });
    }

    public interface OnDataEnteredListener {
        void onDataEntered(String serviceName, String  serviceDescription, String servicePrice);
    }
}
