package ke.co.emichira.myonlinestore.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import ke.co.emichira.myonlinestore.ui.CategoryDetailFragment;
import ke.co.emichira.myonlinestore.models.Category;


/**
 * Created by michira on 9/19/17.
 */

public class CategoryPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Category> mCategories;

    public CategoryPagerAdapter(FragmentManager fm, ArrayList<Category> categories) {
        super(fm);
        mCategories = categories;
    }

    @Override
    public Fragment getItem(int position) {
        return CategoryDetailFragment.newInstance(mCategories.get(position));
    }

    @Override
    public int getCount() {
        return mCategories.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mCategories.get(position).getName();
    }
}