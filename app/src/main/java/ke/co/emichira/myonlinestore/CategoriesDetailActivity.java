package ke.co.emichira.myonlinestore;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import ke.co.emichira.myonlinestore.R;
import ke.co.emichira.myonlinestore.CategoryPagerAdapter;
import ke.co.emichira.myonlinestore.Category;


import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by michira on 9/19/17.
 */

public class CategoriesDetailActivity extends AppCompatActivity {
    @Bind(R.id.viewPager) ViewPager mViewPager;
    private CategoryPagerAdapter adapterViewPager;
    ArrayList<Category> mCategories = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_detail);
        ButterKnife.bind(this);

        mCategories = Parcels.unwrap(getIntent().getParcelableExtra("categories"));

        int startingPosition = Integer.parseInt(getIntent().getStringExtra("position"));

        adapterViewPager = new CategoryPagerAdapter(getSupportFragmentManager(), mCategories);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}