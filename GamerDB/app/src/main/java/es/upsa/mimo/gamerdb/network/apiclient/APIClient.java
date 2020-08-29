package es.upsa.mimo.gamerdb.network.apiclient;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import es.upsa.mimo.gamerdb.R;
import es.upsa.mimo.gamerdb.models.ErrorResponse;
import es.upsa.mimo.gamerdb.utils.Constants;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    private static Gson getGson() {
        return new GsonBuilder()
                .registerTypeAdapter(
                        Date.class,
                        (JsonDeserializer<Date>) (json, typeOfT, context) -> Constants.stringToDate(json.toString(), Constants.dateFormat)
                )
                .setDateFormat(Constants.dateFormat)
                .serializeNulls()
                .create();
    }

    private static OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(Constants.connectTimeout, TimeUnit.SECONDS)
                .readTimeout(Constants.readTimeout, TimeUnit.SECONDS)
                .writeTimeout(Constants.writeTimeout, TimeUnit.SECONDS)
                .build();
    }

    public static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(Constants.baseEndpoint)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(getGson()))
                .build();
    }

    public static <T> void sendServer(Call<T> request, final CompletionHandler<T> completion) {

        request.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {

                if (response.isSuccessful() && response.body() != null) {
                    completion.success(response.body());
                } else {
                    completion.failure(
                            new ErrorResponse("",
                                    R.string.error_server,
                                    "Error in APIClient onResponse block"));
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                completion.failure(
                        new ErrorResponse("",
                                R.string.error_server,
                                "Error in APIClient onFailure block")
                );
            }
        });
    }
}
