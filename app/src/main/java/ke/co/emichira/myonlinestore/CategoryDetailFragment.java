package ke.co.emichira.myonlinestore;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryDetailFragment extends Fragment implements View.OnClickListener {
        @Bind(R.id.imageViewFragment) ImageView mImageView;
        @Bind(R.id.nameView) TextView mNameView;
        @Bind(R.id.priceView) TextView mPriceView;
        @Bind(R.id.pathView) TextView mPathView;
        @Bind(R.id.urlView) TextView mUrlView;

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

            Picasso.with(view.getContext()).load(mCategory.getLargeImage()).into(mImageView);
            mNameView.setText(mCategory.getName());
            mPriceView.setText(String.valueOf(mCategory.getSalePrice()));
            mPathView.setText(mCategory.getCategoryPath());
            mUrlView.setText("Go To Website");

            mUrlView.setOnClickListener(this);
            return view;
        }

        @Override
        public void onClick(View v) {

            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(mCategory.getUrl()));
            startActivity(webIntent);
        }

}

