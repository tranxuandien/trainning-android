package com.example.gl.study1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CreateProjectActivity extends AppCompatActivity {
    List<String> listCity=new ArrayList<>();
    TextView cityChoice;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_project);
        cityChoice= (TextView)findViewById(R.id.txtv_city_choice);
        Spinner spinner = (Spinner) findViewById(R.id.spinerCity);
        listCity.add("HÀ NỘI");
        listCity.add("HÀ TÂY");
        listCity.add("HÀ NAM");
        listCity.add("HÀ TĨNH");
//        ListCityAdapter listCityAdapter=new ListCityAdapter(CreateProjectActivity.this,R.layout.list_city_layout,listCity);
//        spinner.setAdapter(listCityAdapter);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.planets_array, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.list_city_layout, R.id.txtv_city, listCity);
//        spinner.setBackgroundColor(getResources().getColor(android.R.color.black));
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String selectedItem = adapterView.getItemAtPosition(position).toString();
                cityChoice.setText(selectedItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
