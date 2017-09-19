package ke.co.emichira.myonlinestore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

public class CategoriesActivity extends AppCompatActivity {

    @Bind(R.id.categoryTextView) TextView mCategoryView;
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private CategoryListAdapter mAdapter;

    public ArrayList<Category> mCategories = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        ButterKnife.bind(this);

//        GridView gridview = (GridView) findViewById(R.id.gridview);
//        gridview.setAdapter(new ImageAdapter(this));
//
//        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View v,
//                                    int position, long id) {
//                Toast.makeText(CategoriesActivity.this, "" + position,
//                        Toast.LENGTH_SHORT).show();
//            }
//        });

        Intent intent = getIntent();
        String categories =intent.getStringExtra("categories");
        getCategories("categories");

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
}