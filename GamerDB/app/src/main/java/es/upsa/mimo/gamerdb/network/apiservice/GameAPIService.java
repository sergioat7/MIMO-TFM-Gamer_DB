package es.upsa.mimo.gamerdb.network.apiservice;

import java.util.Map;
import es.upsa.mimo.gamerdb.models.GameListResponse;
import es.upsa.mimo.gamerdb.models.GameResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface GameAPIService {

    @Headers("User-Agent:GamerDB")
    @GET("games")
    Call<GameListResponse> getGames(@QueryMap Map<String, Integer> queryParams);

    @Headers("User-Agent:GamerDB")
    @GET("games/{gameId}")
    Call<GameResponse> getGame(@Path(value = "gameId") int gameId);
}