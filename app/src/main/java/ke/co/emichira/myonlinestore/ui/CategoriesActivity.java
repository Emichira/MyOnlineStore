package ke.co.emichira.myonlinestore.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import ke.co.emichira.myonlinestore.Constants;
import ke.co.emichira.myonlinestore.R;
import ke.co.emichira.myonlinestore.adapters.CategoryListAdapter;
import ke.co.emichira.myonlinestore.models.Category;
import ke.co.emichira.myonlinestore.services.WalmartService;
import okhttp3.Call;
import okhttp3.Response;

public class CategoriesActivity extends AppCompatActivity {

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRecentSearch;

    @Bind(R.id.categoryTextView) TextView mCategoryView;
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private CategoryListAdapter mAdapter;

    public ArrayList<Category> mCategories = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        String categories =intent.getStringExtra("categories");
        getCategories("categories");

        getCategories(categories);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mRecentSearch = mSharedPreferences.getString(Constants.PREFERENCES_SEARCH_KEY, null);

        if (mRecentSearch != null) {
            getCategories(mRecentSearch);
        }

        mCategoryView.setText("Here are all the categories that match: " + categories);
    }

    public void getCategories(String categories){
        final WalmartService walmartService= new WalmartService();
        walmartService.findCategories(categories, new okhttp3.Callback(){

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                mCategories = walmartService.processResults(response);

                CategoriesActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new CategoryListAdapter(getApplicationContext(), mCategories);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CategoriesActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);

                        for(Category category:mCategories){
                            Log.v("name",category.getName());
                            Log.v("largeImage",category.getLargeImage());
                            Log.v("salePrice",String.valueOf(category.getSalePrice()));
//                            Log.v("itemId",String.valueOf(category.getItemId()));
                            Log.v("categoryPath",category.getCategoryPath());
                            Log.v("url",category.getUrl());
                        }

                    }
                });
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                addToSharedPreferences(query);
                getCategories(query);
                return false;
            }

        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }



    private void addToSharedPreferences(String categories) {
        mEditor.putString(Constants.PREFERENCES_SEARCH_KEY, categories).apply();
    }
}