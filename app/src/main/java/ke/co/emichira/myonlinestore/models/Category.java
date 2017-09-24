package ke.co.emichira.myonlinestore.models;

import org.parceler.Parcel;

import java.util.ArrayList;

/**
 * Created by michira on 9/15/17.
 */

@Parcel
public class Category {
//    public Integer mItemId;
    public String mName;
    public String mSalePrice;
    public String mCategoryPath;
    public String mLargeImage;
    public String mUrl;


    ArrayList<String> mImageEntities = new ArrayList<>();

        // empty constructor needed by the Parceler library:
        public Category() {
        }

        public Category(String name, String salePrice, String categoryPath, String largeImage, String url) {
//            this.mItemId=itemId;
            this.mName = name;
            this.mSalePrice = salePrice;
            this.mCategoryPath = categoryPath;
            this.mLargeImage = largeImage;
            this.mUrl=url;

        }

//        public Integer getItemId(){
//            return mItemId;
//        }

        public String getName() {

            return mName;
        }

        public String getSalePrice() {
            return mSalePrice;
        }

        public String getCategoryPath() {

            return mCategoryPath;
        }

        public String getLargeImage() {

            return mLargeImage;
        }

        public String getUrl(){

            return mUrl;
        }
//        public String getLargeImage(String largeImage) {
//            String largeImage = largeImage.substring(0, largeImage.length() - 5).concat("/o.jpg");
//            return largeImage;
//        }

}
