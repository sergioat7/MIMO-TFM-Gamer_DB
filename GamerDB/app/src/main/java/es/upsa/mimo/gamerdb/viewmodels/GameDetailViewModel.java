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
import es.upsa.mimo.gamerdb.models.AchievementListResponse;
import es.upsa.mimo.gamerdb.models.AchievementResponse;
import es.upsa.mimo.gamerdb.models.DeveloperListResponse;
import es.upsa.mimo.gamerdb.models.DeveloperResponse;
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
    private MutableLiveData<List<GameResponse>> gameSeries;
    private MutableLiveData<List<GameResponse>> gamesSuggested;
    private MutableLiveData<List<GameResponse>> gameAdditions;
    private MutableLiveData<List<DeveloperResponse>> developers;
    private MutableLiveData<List<AchievementResponse>> achievements;
    private GameAPIClient gameAPIClient;
    private int gameSeriesPage;
    private int gamesSuggestedPage;
    private int gameAdditionsPage;
    private int developersPage;
    private int achievementsPage;

    //MARK: - Lifecycle methods

    public GameDetailViewModel(SavedStateHandle savedStateHandle) {

        this.savedStateHandle = savedStateHandle;
        gameId = savedStateHandle.getLiveData(Constants.ATT_GAME_ID_LIVE_DATA, 0);
        game = new MutableLiveData<>();
        error = new MutableLiveData<>();
        imagesUrl = savedStateHandle.getLiveData(Constants.ATT_IMAGES_URL_LIVE_DATA, new ArrayList<>());
        gameSeries = Constants.newMutableEmptyList();
        gamesSuggested = Constants.newMutableEmptyList();
        gameAdditions = Constants.newMutableEmptyList();
        developers = Constants.newMutableEmptyList();
        achievements = Constants.newMutableEmptyList();
        gameAPIClient = new GameAPIClient();
        gameSeriesPage = Constants.FIRST_PAGE;
        gamesSuggestedPage = Constants.FIRST_PAGE;
        gameAdditionsPage = Constants.FIRST_PAGE;
        developersPage = Constants.FIRST_PAGE;
        achievementsPage = Constants.FIRST_PAGE;
        loadGame();
        loadScreenshots();
        loadGameSeries();
        loadGamesSuggested();
        loadGameAdditions();
        loadDevelopers();
        loadAchievements();
    }

    //MARK: - Public methods

    public int getGameId() {

        Integer value = gameId.getValue();
        return value != null ? value : 0;
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

    public LiveData<List<GameResponse>> getGameSeries() {
        return gameSeries;
    }

    public void addGameSeries(List<GameResponse> games) {

        List<GameResponse> currentGames = Constants.addElementsToList(
                this.gameSeries.getValue(),
                games,
                true);
        this.gameSeries.setValue(currentGames);
    }

    public LiveData<List<GameResponse>> getGamesSuggested() {
        return gamesSuggested;
    }

    public void addGamesSuggested(List<GameResponse> games) {

        List<GameResponse> currentGames = Constants.addElementsToList(
                this.gamesSuggested.getValue(),
                games,
                true);
        this.gamesSuggested.setValue(currentGames);
    }

    public LiveData<List<GameResponse>> getGameAdditions() {
        return gameAdditions;
    }

    public void addGameAdditions(List<GameResponse> games) {

        List<GameResponse> currentGames = Constants.addElementsToList(
                this.gameAdditions.getValue(),
                games,
                true);
        this.gameAdditions.setValue(currentGames);
    }

    public LiveData<List<DeveloperResponse>> getDevelopers() {
        return developers;
    }

    public void addDevelopers(List<DeveloperResponse> developers) {

        List<DeveloperResponse> currentDevelopers = Constants.addElementsToList(
                this.developers.getValue(),
                developers,
                true);
        this.developers.setValue(currentDevelopers);
    }

    public LiveData<List<AchievementResponse>> getAchievements() {
        return achievements;
    }

    public void addAchievements(List<AchievementResponse> achievements) {

        List<AchievementResponse> currentAchievements = Constants.addElementsToList(
                this.achievements.getValue(),
                achievements,
                true);
        this.achievements.setValue(currentAchievements);
    }

    public void loadGameSeries() {

        gameAPIClient
                .getGameSeries(getGameId(), gameSeriesPage, Constants.PAGE_SIZE)
                .subscribe(new SingleObserver<GameListResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onSuccess(GameListResponse gameListResponse) {

                        gameSeriesPage++;
                        List<GameResponse> results = gameListResponse.getResults();
                        if (gameListResponse.getNext() != null) {
                            results.add(new GameResponse(0));
                        }
                        addGameSeries(results);
                    }

                    @Override
                    public void onError(Throwable e) {

                        setError(new ErrorResponse(
                                Constants.EMPTY_VALUE,
                                R.string.error_server,
                                "Error in GameDetailViewModel getGameSeries")
                        );
                    }
                });
    }

    public void loadGamesSuggested() {

        gameAPIClient
                .getGamesSuggested(getGameId(), gamesSuggestedPage, Constants.PAGE_SIZE)
                .subscribe(new SingleObserver<GameListResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onSuccess(GameListResponse gameListResponse) {

                        gamesSuggestedPage++;
                        List<GameResponse> results = gameListResponse.getResults();
                        if (gameListResponse.getNext() != null) {
                            results.add(new GameResponse(0));
                        }
                        addGamesSuggested(results);
                    }

                    @Override
                    public void onError(Throwable e) {

                        setError(new ErrorResponse(
                                Constants.EMPTY_VALUE,
                                R.string.error_server,
                                "Error in GameDetailViewModel getGamesSuggested")
                        );
                    }
                });
    }

    public void loadGameAdditions() {

        gameAPIClient
                .getGameAdditions(getGameId(), gameAdditionsPage, Constants.PAGE_SIZE)
                .subscribe(new SingleObserver<GameListResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onSuccess(GameListResponse gameListResponse) {

                        gameAdditionsPage++;
                        List<GameResponse> results = gameListResponse.getResults();
                        if (gameListResponse.getNext() != null) {
                            results.add(new GameResponse(0));
                        }
                        addGameAdditions(results);
                    }

                    @Override
                    public void onError(Throwable e) {

                        setError(new ErrorResponse(
                                Constants.EMPTY_VALUE,
                                R.string.error_server,
                                "Error in GameDetailViewModel getGameAdditions")
                        );
                    }
                });
    }

    public void loadDevelopers() {

        gameAPIClient
                .getDevelopers(getGameId(), developersPage, Constants.PAGE_SIZE)
                .subscribe(new SingleObserver<DeveloperListResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onSuccess(DeveloperListResponse developerListResponse) {

                        developersPage++;
                        List<DeveloperResponse> results = developerListResponse.getResults();
                        if (developerListResponse.getNext() != null) {
                            results.add(new DeveloperResponse(0));
                        }
                        addDevelopers(results);
                    }

                    @Override
                    public void onError(Throwable e) {

                        setError(new ErrorResponse(
                                Constants.EMPTY_VALUE,
                                R.string.error_server,
                                "Error in GameDetailViewModel getDevelopers")
                        );
                    }
                });
    }

    public void loadAchievements() {

        gameAPIClient
                .getAchievements(getGameId(), achievementsPage, Constants.PAGE_SIZE)
                .subscribe(new SingleObserver<AchievementListResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onSuccess(AchievementListResponse achievementListResponse) {

                        achievementsPage++;
                        List<AchievementResponse> results = achievementListResponse.getResults();
                        if (achievementListResponse.getNext() != null) {
                            results.add(new AchievementResponse(0));
                        }
                        addAchievements(results);
                    }

                    @Override
                    public void onError(Throwable e) {

                        setError(new ErrorResponse(
                                Constants.EMPTY_VALUE,
                                R.string.error_server,
                                "Error in GameDetailViewModel getAchievements")
                        );
                    }
                });
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
                                Constants.EMPTY_VALUE,
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
                                Constants.EMPTY_VALUE,
                                R.string.error_server,
                                "Error in GameDetailViewModel getScreenshots")
                        );
                    }
                });
    }
}
