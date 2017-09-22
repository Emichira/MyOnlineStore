package ke.co.emichira.myonlinestore;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class CategoryDetailActivity extends AppCompatActivity {
    @Bind(R.id.viewPager) ViewPager mViewPager;
    private CategoryPagerAdapter adapterViewPager;
    ArrayList<Category> mCategories = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_detail);
        ButterKnife.bind(this);

        mCategories = Parcels.unwrap(getIntent().getParcelableExtra("categories"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new CategoryPagerAdapter(getSupportFragmentManager(), mCategories);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}




