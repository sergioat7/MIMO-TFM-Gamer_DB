/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 10/9/2020
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
import es.upsa.mimo.gamerdb.models.PlatformListResponse;
import es.upsa.mimo.gamerdb.models.PlatformObjectResponse;
import es.upsa.mimo.gamerdb.network.apiclient.GameAPIClient;
import es.upsa.mimo.gamerdb.network.apiclient.PlatformAPIClient;
import es.upsa.mimo.gamerdb.utils.Constants;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class MainViewModel extends ViewModel {

    //MARK: - Private properties

    private SavedStateHandle savedStateHandle;
    private MutableLiveData<List<GameResponse>> games;
    private MutableLiveData<ErrorResponse> error;
    private MutableLiveData<List<PlatformObjectResponse>> platforms;
    private LiveData<Integer> page;
    private LiveData<String> query;
    private LiveData<Integer> position;
    private LiveData<Boolean> refreshing;
    private int platformPage;
    private GameAPIClient gameAPIClient;
    private PlatformAPIClient platformAPIClient;

    //MARK: - Lifecycle methods

    public MainViewModel(SavedStateHandle savedStateHandle) {

        this.savedStateHandle = savedStateHandle;
        games = new MutableLiveData<>();
        resetGames();
        error = new MutableLiveData<>();
        platforms = new MutableLiveData<>();
        page = savedStateHandle.getLiveData(Constants.ATT_PAGE_LIVE_DATA, Constants.FIRST_PAGE);
        query = savedStateHandle.getLiveData(Constants.ATT_QUERY_LIVE_DATA, null);
        position = savedStateHandle.getLiveData(Constants.ATT_POSITION_LIVE_DATA, Constants.INITIAL_POSITION_LIST);
        refreshing = savedStateHandle.getLiveData(Constants.ATT_REFRESHING_LIVE_DATA, true);
        platformPage = Constants.FIRST_PAGE;
        gameAPIClient = new GameAPIClient();
        platformAPIClient = new PlatformAPIClient();
        loadGames();
        loadPlatforms();
    }

    //MARK: - Public methods

    public LiveData<List<GameResponse>> getGames() {
        return games;
    }

    public void addGames(List<GameResponse> games) {

        List<GameResponse> currentGames = this.games.getValue();
        if (currentGames == null) {
            currentGames = new ArrayList<>();
        }
        currentGames.addAll(games);
        this.games.setValue(currentGames);
    }

    public void resetGames() {
        this.games.setValue(new ArrayList<>());
    }

    public LiveData<ErrorResponse> getError() {
        return error;
    }

    public void setError(ErrorResponse error) {
        this.error.setValue(error);
    }

    public LiveData<List<PlatformObjectResponse>> getPlatforms() {
        return platforms;
    }

    public void addPlatforms(List<PlatformObjectResponse> platforms) {

        List<PlatformObjectResponse> currentPlatforms = this.platforms.getValue();
        if (currentPlatforms == null) {
            currentPlatforms = new ArrayList<>();
        }
        currentPlatforms.addAll(platforms);
        this.platforms.setValue(currentPlatforms);
    }

    public int getPage() {

        Integer value = page.getValue();
        return value != null ? value : Constants.FIRST_PAGE;
    }

    public void setPage(int page) {
        this.savedStateHandle.set(Constants.ATT_PAGE_LIVE_DATA, page);
    }

    public String getQuery() {
        return query.getValue();
    }

    public void setQuery(String query) {
        this.savedStateHandle.set(Constants.ATT_QUERY_LIVE_DATA, query);
    }

    public LiveData<Integer> getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.savedStateHandle.set(Constants.ATT_POSITION_LIVE_DATA, position);
    }

    public LiveData<Boolean> getRefreshing() {
        return refreshing;
    }

    public void setRefreshing(boolean refreshing) {
        this.savedStateHandle.set(Constants.ATT_REFRESHING_LIVE_DATA, refreshing);
    }

    public void loadGames() {

        gameAPIClient
                .getGamesObserver(getPage(), Constants.PAGE_SIZE, getQuery())
                .subscribe(new SingleObserver<GameListResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(GameListResponse gameListResponse) {

                        addGames(gameListResponse.getResults());
                        int page = getPage();
                        if (page == 1) {
                            setPosition(Constants.INITIAL_POSITION_LIST);
                        }
                        setPage(page + 1);
                        setRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {

                        setRefreshing(false);
                        setError(new ErrorResponse(
                                "",
                                R.string.error_server,
                                "Error in MainViewModel getGames")
                        );
                    }
                });
    }

    public void loadPlatforms() {

        platformAPIClient
                .getPlatformsObserver(platformPage, Constants.PAGE_SIZE)
                .subscribe(new SingleObserver<PlatformListResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onSuccess(PlatformListResponse platformListResponse) {

                        platformPage++;
                        addPlatforms(platformListResponse.getResults());
                    }

                    @Override
                    public void onError(Throwable e) {

                        setError(new ErrorResponse(
                                "",
                                R.string.error_server,
                                "Error in MainViewModel getPlatforms")
                        );
                    }
                });
    }

    public void reloadGames() {

        setPage(Constants.FIRST_PAGE);
        setQuery(null);
        resetGames();
        loadGames();
    }

    public void searchGames(String query) {

        setPage(Constants.FIRST_PAGE);
        setQuery(query);
        resetGames();
        loadGames();
    }
}