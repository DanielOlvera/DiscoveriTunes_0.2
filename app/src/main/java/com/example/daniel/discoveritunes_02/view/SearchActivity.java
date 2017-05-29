package com.example.daniel.discoveritunes_02.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.daniel.discoveritunes_02.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity {

    private static final String TAG = SearchActivity.class.getSimpleName();

    @BindView(R.id.m_toolBar)
    Toolbar toolbar;
    @BindView(R.id.m_spinnerTbr)
    Spinner spinner;
    @BindView(R.id.m_startBtn)
    Button startBtn;
    @BindView(R.id.m_recyclerLayout)
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        setupSpinner();

    }

    public void setupSpinner(){
        ArrayAdapter<String> entitiesAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_expandable_list_item_1,
                getResources().getStringArray(R.array.entities_array));
        entitiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(entitiesAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(SearchActivity.this, spinner.getSelectedItem().toString(),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @OnClick(R.id.m_startBtn)
    public void onStartSearch(){
        startBtn.setVisibility(View.GONE);
        relativeLayout.setVisibility(View.VISIBLE);
        spinner.setVisibility(View.VISIBLE);
    }
}
