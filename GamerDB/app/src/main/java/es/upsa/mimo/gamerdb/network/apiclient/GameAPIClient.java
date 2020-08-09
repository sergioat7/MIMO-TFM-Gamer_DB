package es.upsa.mimo.gamerdb.network.apiclient;

import java.util.HashMap;
import java.util.Map;
import es.upsa.mimo.gamerdb.models.ErrorResponse;
import es.upsa.mimo.gamerdb.models.GameListResponse;
import es.upsa.mimo.gamerdb.network.apiservice.GameAPIService;
import retrofit2.Call;

public class GameAPIClient {

    private GameAPIService api = APIClient.getRetrofit().create(GameAPIService.class);

    public void getGames(int page, int pageSize, CompletionHandler<GameListResponse> completion) {

        Map<String, Integer> params = new HashMap<>();
        params.put("page", page);
        params.put("page_size", pageSize);
        Call<GameListResponse> request = api.getGames(params);

        APIClient.sendServer(request,completion);
    }
}
