package ke.co.emichira.myonlinestore;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by michira on 9/15/17.
 */

public class WalmartService {
    public static void findCategoriesButton(String Category, Callback callback) {

        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.WALMART_BASE_URL).newBuilder();
//        urlBuilder.addQueryParameter(Constants.QUERY_PARAMETER, category);
        String url = urlBuilder.build().toString();

        Request request= new Request.Builder()
                .header("Authorization", Constants.WALMART_API_Key)
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

}
