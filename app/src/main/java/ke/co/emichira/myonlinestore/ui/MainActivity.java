package ke.co.emichira.myonlinestore.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import ke.co.emichira.myonlinestore.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = MainActivity.class.getSimpleName();

    @Bind(R.id.findCategoriesButton) Button mFindCategoriesButton;
    @Bind(R.id.appNameTextView) TextView mAppNameTextView;
    @Bind(R.id.signInButton) Button mSignInButton;
//    @Bind(R.id.signUpButton) Button mSignUpButton;
    @Bind(R.id.savedCategoriesButton) Button mSavedRestaurantsButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface ostrichFont = Typeface.createFromAsset(getAssets(), "fonts/ostrich-regular.ttf");
        mAppNameTextView.setTypeface(ostrichFont);

//        mFindCategoriesButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String categories = mFindCategoriesButton.getText().toString();
//                Intent intent = new Intent(MainActivity.this, CategoriesActivity.class);
//                intent.putExtra("categories", categories);
//                startActivity(intent);            }
//        });

        mFindCategoriesButton.setOnClickListener(this);
        mSavedRestaurantsButton.setOnClickListener(this);


        mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignInActivity.class);
                startActivity(intent);            }
        });

//        mSignUpButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
//                startActivity(intent);            }
//        });

    }

    @Override
    public void onClick(View v) {

            if(v == mFindCategoriesButton) {
//                String categories = mFindCategoriesButton.getText().toString();
                Intent intent = new Intent(MainActivity.this, CategoriesActivity.class);
//                intent.putExtra("categories", categories);
                startActivity(intent);
            }

        if (v == mSavedRestaurantsButton) {
            Intent intent = new Intent(MainActivity.this, SavedCategoriesActivity.class);
            startActivity(intent);
        }
    }
}
