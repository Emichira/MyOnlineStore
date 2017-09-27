package ke.co.emichira.myonlinestore.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;
import ke.co.emichira.myonlinestore.Constants;
import ke.co.emichira.myonlinestore.R;
import ke.co.emichira.myonlinestore.models.Category;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryDetailFragment extends Fragment implements View.OnClickListener {

    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    @Bind(R.id.productImageView) ImageView mImageView;
    @Bind(R.id.nameTextView) TextView mNameView;
    @Bind(R.id.priceTextView) TextView mPriceView;
    @Bind(R.id.pathTextView) TextView mPathView;
    @Bind(R.id.urlTextView) TextView mUrlView;
    @Bind(R.id.saveItemsButton) Button mSaveItem;


    private Category mCategory;

        public static CategoryDetailFragment newInstance(Category category) {
            // Required empty public constructor
            CategoryDetailFragment categoryDetailFragment = new CategoryDetailFragment();
            Bundle args = new Bundle();
            args.putParcelable("category", Parcels.wrap(category));
            categoryDetailFragment.setArguments(args);
            return categoryDetailFragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mCategory = Parcels.unwrap(getArguments().getParcelable("category"));
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_category_detail, container, false);

            ButterKnife.bind(this, view);

            Picasso.with(view.getContext())
                    .load(mCategory.getLargeImage())
                    .resize(MAX_WIDTH, MAX_HEIGHT)
                    .centerCrop()
                    .into(mImageView);
            mNameView.setText(mCategory.getName());
            mPriceView.setText(String.valueOf(mCategory.getSalePrice()));
            mPathView.setText(mCategory.getCategoryPath());
            mUrlView.setText("Go To Website");

            mUrlView.setOnClickListener(this);
            mSaveItem.setOnClickListener(this);
            return view;
        }

        @Override
        public void onClick(View v) {

            if(v == mUrlView) {

                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(mCategory.getUrl()));
                startActivity(webIntent);
            }

            if(v == mSaveItem) {
                DatabaseReference categoryRef = FirebaseDatabase
                        .getInstance()
                        .getReference(Constants.FIREBASE_CHILD_ITEMS);
                categoryRef.push().setValue(mCategory);
                Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
            }
        }

}

