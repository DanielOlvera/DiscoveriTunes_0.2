package com.example.daniel.discoveritunes_02.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.daniel.discoveritunes_02.R;
import com.example.daniel.discoveritunes_02.model.ITunesResult;
import com.example.daniel.discoveritunes_02.model.Result;
import com.example.daniel.discoveritunes_02.model.api.RetrofitService;
import com.example.daniel.discoveritunes_02.utils.SearchAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
    @BindView(R.id.m_searchBar)
    EditText searchTxtVw;
    @BindView(R.id.m_searchBtn)
    Button searchBtn;
    @BindView(R.id.m_recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        Log.d(TAG, "onCreate: ");

        setupSpinner();

    }

    /**
     * This method setup the values to be taken for the spinner
     */
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

    /**
     * This method dismiss the start button after clicking and makes visible 
     * the layout that contains the search bar, search button, recyclerview and spinner
     */
    @OnClick(R.id.m_startBtn)
    public void onStartS(){
        startBtn.setVisibility(View.GONE);
        relativeLayout.setVisibility(View.VISIBLE);
        spinner.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.m_searchBtn)
    public void onStartSearch(){
        String term = searchTxtVw.getText().toString();
        String entity = spinner.getSelectedItem().toString();
        Log.d(TAG, "onStartSearch: " + term + entity);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        RetrofitService service = RetrofitService.Factory.create();
        rx.Observable<ITunesResult> call = service.search(term, entity);
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ITunesResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ITunesResult iTunesResult) {
                        List<Result> results = iTunesResult.getResults();
                        recyclerView.setAdapter(new SearchAdapter(results, R.layout.search_list_item, getApplicationContext()));
                    }
                });
    }

}

/*
Previous code to manage the response with only retrofit;
RetrofitService service = RetrofitService.Factory.create();
        Call<ITunesResult> call = service.getSearch(term, entity);
        call.enqueue(new Callback<ITunesResult>() {
            @Override
            public void onResponse(Call<ITunesResult> call, Response<ITunesResult> response) {
                List<Result> result = response.body().getResults();
                recyclerView.setAdapter(new SearchAdapter(result, R.layout.search_list_item, getApplicationContext()));
            }

            @Override
            public void onFailure(Call<ITunesResult> call, Throwable t) {

            }
        });
 */