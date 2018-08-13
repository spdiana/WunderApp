package com.example.di.apptestwunder.view;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Toast;


import com.example.di.apptestwunder.R;
import com.example.di.apptestwunder.adapter.VehiclesListAdapter;
import com.example.di.apptestwunder.model.Vehicles;
import com.example.di.apptestwunder.model.VehiclesList;
import com.example.di.apptestwunder.presenter.PoiPresenter;
import com.example.di.apptestwunder.util.Util;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements PoiView {

    private RecyclerView recyclerView;
    private VehiclesListAdapter adapter;
    private ProgressDialog mDialog;
    private PoiPresenter presenter;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        presenter = new PoiPresenter(this);

        mDialog = new ProgressDialog(this);
        mDialog.setMessage("Loading Data...");
        mDialog.setCancelable(false);
        mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mDialog.setIndeterminate(true);
        mDialog.show();

        initView();
    }

    void initView() {
        if (Util.isNetworkAvailable(this)) {
            presenter.getFromBaseUrl();
        } else {
            Toast.makeText(this, "No connection", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void showSuccess(Call<VehiclesList> call, Response<VehiclesList> response) {
        generatePoiList( response.body().getVehiclesList());
        mDialog.dismiss();
    }



    @Override
    public void showFailure(Call<VehiclesList> call, Throwable t) {
        Toast.makeText(this, "Get list repo failed", Toast.LENGTH_SHORT).show();
        //Log.wtf("Error URL Called", t.getMessage() + "Something went wrong...Please try later!");
        mDialog.dismiss();
    }

    private void generatePoiList(ArrayList<Vehicles> empDataList) {
        recyclerView =  findViewById(R.id.recycler_row);

        adapter = new VehiclesListAdapter(this, empDataList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                adapter.getFilter().filter(query);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
        return true;
    }



    @Override
    public void onBackPressed() {
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }
}
