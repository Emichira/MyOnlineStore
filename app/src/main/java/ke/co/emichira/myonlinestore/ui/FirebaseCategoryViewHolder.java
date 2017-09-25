package ke.co.emichira.myonlinestore.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import ke.co.emichira.myonlinestore.Constants;
import ke.co.emichira.myonlinestore.R;
import ke.co.emichira.myonlinestore.models.Category;

/**
 * Created by michira on 9/25/17.
 */

public class FirebaseCategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    View mView;
    Context mContext;

    public FirebaseCategoryViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindCategory(Category category) {
        ImageView categoryImageView = (ImageView) mView.findViewById(R.id.imageView);
        TextView nameTextView = (TextView) mView.findViewById(R.id.nameTextView);
        TextView categoryTextView = (TextView) mView.findViewById(R.id.categoryTextView);
        TextView priceTextView = (TextView) mView.findViewById(R.id.priceTextView);

        Picasso.with(mContext)
                .load(category.getUrl())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(categoryImageView);

        nameTextView.setText(category.getName());
        priceTextView.setText("Price: " + "$" + category.getSalePrice());
        categoryTextView.setText("Category: " + category.getCategoryPath());
    }

    @Override
    public void onClick(View view) {
        final ArrayList<Category> categories = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_ITEMS);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    categories.add(snapshot.getValue(Category.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, CategoryDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("categories", Parcels.wrap(categories));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
