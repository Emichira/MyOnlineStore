package ke.co.emichira.myonlinestore.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import ke.co.emichira.myonlinestore.models.Category;
import ke.co.emichira.myonlinestore.R;
import ke.co.emichira.myonlinestore.ui.CategoryDetailActivity;

/**
 * Created by michira on 9/16/17.
 */

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.CategorytViewHolder>{

    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    private ArrayList<Category> mCategories = new ArrayList<>();
    private Context mContext;

    public CategoryListAdapter(Context context, ArrayList<Category> categories) {
        mContext = context;
        mCategories = categories;
    }

    @Override
    public CategoryListAdapter.CategorytViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_list_item, parent, false);
        CategorytViewHolder viewHolder = new CategorytViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CategoryListAdapter.CategorytViewHolder holder, int position) {
        holder.bindCategory(mCategories.get(position));
    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }

    public class CategorytViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.productImageView) ImageView mproductImageView;
        @Bind(R.id.nameTextView) TextView mNameTextView;
        @Bind(R.id.priceTextView) TextView mPriceTextView;

        private Context mContext;


        public CategorytViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
//            Log.d("click listener", "working!");
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, CategoryDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("categories", Parcels.wrap(mCategories));
            mContext.startActivity(intent);
        }

        public void bindCategory(Category category) {
            Picasso.with(mContext)
                    .load(category.getLargeImage())
                    .resize(MAX_WIDTH, MAX_HEIGHT)
                    .centerCrop()
                    .into(mproductImageView);
            mNameTextView.setText(category.getName());
            mPriceTextView.setText("Price $ " + category.getSalePrice());
        }
    }
}

