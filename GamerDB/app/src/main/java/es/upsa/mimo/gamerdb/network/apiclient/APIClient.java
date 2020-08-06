package es.upsa.mimo.gamerdb.network.apiclient;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.concurrent.TimeUnit;
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
                .registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                    @Override
                    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        return Constants.stringToDate(json.toString());
                    }
                })
                .setDateFormat(Constants.dateFormat)
                .serializeNulls()
                .create();
    }

    private static OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();
    }

    public static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(Constants.baseEndpoint)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(getGson()))
                .build();
    }

    public static <T, U> void sendServer(Call<T> request, final CompletionHandler<T, U> completion) {

        request.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {

                if (response.isSuccessful() && response.body() != null) {
                    completion.success(response.body());
                } else {
                    completion.failure(null);//TODO
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                completion.failure(null);//TODO
            }
        });
    }
}
