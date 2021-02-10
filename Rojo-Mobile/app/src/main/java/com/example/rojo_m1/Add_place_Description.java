package com.example.rojo_m1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class Add_place_Description extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextInputEditText location_name, description_name;
    Spinner spinner;
    Button create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_place__description);

        spinner = findViewById(R.id.spinner);
        create = findViewById(R.id.button_create);
        location_name = findViewById(R.id.location_name);
        description_name = findViewById(R.id.description_name);
        spinner.setOnItemSelectedListener(this);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        String size1 = spinner.getSelectedItem().toString();

        int spinner_pos = spinner.getSelectedItemPosition();
        String[] size_values = getResources().getStringArray(R.array.Spinner_values);
        final int value = Integer.valueOf(size_values[spinner_pos]);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Add_place_Description.this, Addplace.class);
                intent.putExtra("LOCATION_NAME", location_name.getText().toString());
                intent.putExtra("DESCRIPTION", description_name.getText().toString());
                intent.putExtra("CATEGORY", ""+value);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


}