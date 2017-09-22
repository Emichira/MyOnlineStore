package ke.co.emichira.myonlinestore;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by michira on 9/15/17.
 */

public class WalmartService {
    public static void findCategories(String categories, Callback callback) {

        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.WALMART_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.WALMART_QUERY, categories);
        urlBuilder.addQueryParameter(Constants.API_KEY_BASE,Constants.WALMART_API_KEY);
        String url = urlBuilder.build().toString();

        Request request= new Request.Builder()
                .header("Authorization", Constants.WALMART_API_KEY)
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Category> processResults(Response response) {
        ArrayList<Category> categories = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject categoriesJSON = new JSONObject(jsonData);
                JSONArray itemsJSON = categoriesJSON.getJSONArray("items");
                for (int i = 0; i < itemsJSON.length(); i++) {
                    JSONObject categoryJSON = itemsJSON.getJSONObject(i);
//                    Integer itemId = categoriesJSON.getInt("itemId");
                    String name = categoryJSON.getString("name");
                    String salePrice = categoryJSON.getString("salePrice");
                    String categoryPath = categoryJSON.getString("categoryPath");
                    String largeImage = categoryJSON.getString("largeImage");
                    String url=categoryJSON.getString("productUrl");

                    Category category = new Category(name, salePrice, categoryPath, largeImage, url);
                    categories.add(category);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return categories;
    }


}
