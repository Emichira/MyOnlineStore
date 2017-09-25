package ke.co.emichira.myonlinestore.models;

import org.parceler.Parcel;

import java.util.ArrayList;

/**
 * Created by michira on 9/15/17.
 */

@Parcel
public class Category {
//    public Integer mItemId;
    public String name;
    public String salePrice;
    public String categoryPath;
    public String largeImage;
    public String url;


    ArrayList<String> mImageEntities = new ArrayList<>();

        // empty constructor needed by the Parceler library:
        public Category() {
        }

        public Category(String name, String salePrice, String categoryPath, String largeImage, String url) {
//            this.mItemId=itemId;
            this.name = name;
            this.salePrice = salePrice;
            this.categoryPath = categoryPath;
            this.largeImage = largeImage;
            this.url=url;

        }

//        public Integer getItemId(){
//            return mItemId;
//        }

        public String getName() {

            return name;
        }

        public String getSalePrice() {
            return salePrice;
        }

        public String getCategoryPath() {

            return categoryPath;
        }

        public String getLargeImage() {

            return largeImage;
        }

        public String getUrl(){

            return url;
        }
//        public String getLargeImage(String largeImage) {
//            String largeImage = largeImage.substring(0, largeImage.length() - 5).concat("/o.jpg");
//            return largeImage;
//        }

}
