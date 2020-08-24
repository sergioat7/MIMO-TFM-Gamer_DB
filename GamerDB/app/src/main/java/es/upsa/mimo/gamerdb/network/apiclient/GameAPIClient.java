package es.upsa.mimo.gamerdb.network.apiclient;

import java.util.HashMap;
import java.util.Map;
import es.upsa.mimo.gamerdb.models.ErrorResponse;
import es.upsa.mimo.gamerdb.models.GameListResponse;
import es.upsa.mimo.gamerdb.models.GameResponse;
import es.upsa.mimo.gamerdb.network.apiservice.GameAPIService;
import es.upsa.mimo.gamerdb.utils.Constants;
import retrofit2.Call;

public class GameAPIClient {

    private GameAPIService api = APIClient.getRetrofit().create(GameAPIService.class);

    public void getGames(int page, int pageSize, CompletionHandler<GameListResponse> completion) {

        Map<String, Integer> params = new HashMap<>();
        params.put(Constants.pageParam, page);
        params.put(Constants.pageSizeParam, pageSize);

        Call<GameListResponse> request = api.getGames(params);
        APIClient.sendServer(request,completion);
    }

    public void getGame(int gameId, CompletionHandler<GameResponse> completion) {

        Call<GameResponse> request = api.getGame(gameId);
        APIClient.sendServer(request, completion);
    }
}
