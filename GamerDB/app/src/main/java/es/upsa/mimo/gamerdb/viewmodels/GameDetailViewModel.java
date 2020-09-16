/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 12/9/2020
 */

package es.upsa.mimo.gamerdb.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.List;
import es.upsa.mimo.gamerdb.R;
import es.upsa.mimo.gamerdb.models.ErrorResponse;
import es.upsa.mimo.gamerdb.models.GameListResponse;
import es.upsa.mimo.gamerdb.models.GameResponse;
import es.upsa.mimo.gamerdb.models.ScreenshotListResponse;
import es.upsa.mimo.gamerdb.models.ScreenshotResponse;
import es.upsa.mimo.gamerdb.network.apiclient.GameAPIClient;
import es.upsa.mimo.gamerdb.utils.Constants;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class GameDetailViewModel extends ViewModel {

    //MARK: - Private properties

    private SavedStateHandle savedStateHandle;
    private LiveData<Integer> gameId;
    private MutableLiveData<GameResponse> game;
    private MutableLiveData<ErrorResponse> error;
    private LiveData<ArrayList<String>> imagesUrl;
    private GameAPIClient gameAPIClient;

    //MARK: - Lifecycle methods

    public GameDetailViewModel(SavedStateHandle savedStateHandle) {

        this.savedStateHandle = savedStateHandle;
        this.gameId = savedStateHandle.getLiveData(Constants.ATT_GAME_ID_LIVE_DATA, 0);
        game = new MutableLiveData<>();
        error = new MutableLiveData<>();
        imagesUrl = savedStateHandle.getLiveData(Constants.ATT_IMAGES_URL_LIVE_DATA, new ArrayList<>());
        gameAPIClient = new GameAPIClient();
        loadGame();
        loadScreenshots();
        loadGameSeries();
    }

    //MARK: - Public methods

    public int getGameId() {

        Integer value = gameId.getValue();
        return value != null ? value : 0;
    }

    public void setGameId(int gameId) {
        this.savedStateHandle.set(Constants.ATT_GAME_ID_LIVE_DATA, gameId);
    }

    public MutableLiveData<GameResponse> getGame() {
        return game;
    }

    public void setGame(GameResponse game) {
        this.game.setValue(game);
    }

    public LiveData<ErrorResponse> getError() {
        return error;
    }

    public void setError(ErrorResponse error) {
        this.error.setValue(error);
    }

    public LiveData<ArrayList<String>> getImagesUrl() {
        return imagesUrl;
    }

    public void addImagesUrl(List<String> imagesUrl) {

        List<String> currentImagesUrl = this.imagesUrl.getValue();
        if (currentImagesUrl == null) {
            currentImagesUrl = new ArrayList<>();
        }
        currentImagesUrl.addAll(imagesUrl);
        this.savedStateHandle.set(Constants.ATT_IMAGES_URL_LIVE_DATA, currentImagesUrl);
    }
    }

    //MARK: - Private methods

    private void loadGame() {

        gameAPIClient
                .getGame(getGameId())
                .subscribe(new SingleObserver<GameResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onSuccess(GameResponse gameResponse) {
                        setGame(gameResponse);
                    }

                    @Override
                    public void onError(Throwable e) {

                        setError(new ErrorResponse(
                                "",
                                R.string.error_server,
                                "Error in GameDetailViewModel getGame")
                        );
                    }
                });
    }

    private void loadScreenshots() {

        gameAPIClient
                .getScreenshots(getGameId())
                .subscribe(new SingleObserver<ScreenshotListResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(ScreenshotListResponse screenshotListResponse) {

                        List<ScreenshotResponse> screenshots = screenshotListResponse.getResults();
                        List<String> imagesUrl = new ArrayList<>();
                        for (int i = 0; i < screenshots.size(); i++) {
                            imagesUrl.add(screenshots.get(i).getImage());
                        }
                        addImagesUrl(imagesUrl);
                    }

                    @Override
                    public void onError(Throwable e) {

                        setError(new ErrorResponse(
                                "",
                                R.string.error_server,
                                "Error in GameDetailViewModel getScreenshots")
                        );
                    }
                });
    }

    private void loadGameSeries() {

        gameAPIClient
                .getGameSeries(getGameId(), gameSeriesPage, Constants.PAGE_SIZE)
                .subscribe(new SingleObserver<GameListResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onSuccess(GameListResponse gameListResponse) {

                        gameSeriesPage++;
                        //TODO add games
                    }

                    @Override
                    public void onError(Throwable e) {

                        setError(new ErrorResponse(
                                "",
                                R.string.error_server,
                                "Error in GameDetailViewModel getGameSeries")
                        );
                    }
                });
    }
}
