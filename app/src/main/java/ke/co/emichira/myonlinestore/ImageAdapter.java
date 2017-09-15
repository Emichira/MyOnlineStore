package ke.co.emichira.myonlinestore;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by michira on 9/10/17.
 */

public class ImageAdapter extends BaseAdapter{
        private Context mContext;

        public ImageAdapter(Context c) {
            mContext = c;
        }

        public int getCount() {
            return mImageIds.length;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {
                // if it's not recycled, initialize some attributes
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(300, 300));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(8, 8, 8, 8);
            } else {
                imageView = (ImageView) convertView;
            }

            imageView.setImageResource(mImageIds[position]);
            return imageView;
        }

        // references to our images
        private Integer[] mImageIds = {
                R.drawable.bondings,
                R.drawable.brasilianische,
                R.drawable.brasilianisches_haar,
                R.drawable.brazilian_wavy,
                R.drawable.clip_in,
                R.drawable.glatt,
                R.drawable.microlings,
                R.drawable.tressen_mit_lace,
                R.drawable.volkolf,
        };
    }
