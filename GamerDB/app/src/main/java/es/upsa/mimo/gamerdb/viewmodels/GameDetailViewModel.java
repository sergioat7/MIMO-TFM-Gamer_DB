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

import es.upsa.mimo.gamerdb.models.ErrorResponse;
import es.upsa.mimo.gamerdb.models.GameResponse;
import es.upsa.mimo.gamerdb.network.apiclient.GameAPIClient;
import es.upsa.mimo.gamerdb.utils.Constants;

public class GameDetailViewModel extends ViewModel {

    //MARK: - Private properties

    private SavedStateHandle savedStateHandle;
    private MutableLiveData<GameResponse> game;
    private MutableLiveData<ErrorResponse> error;
    private LiveData<ArrayList<String>> imagesUrl;
    private GameAPIClient gameAPIClient;

    //MARK: - Lifecycle methods

    public GameDetailViewModel(SavedStateHandle savedStateHandle) {

        this.savedStateHandle = savedStateHandle;
        game = new MutableLiveData<>();
        error = new MutableLiveData<>();
        imagesUrl = savedStateHandle.getLiveData(Constants.ATT_IMAGES_URL_LIVE_DATA, new ArrayList<>());
        gameAPIClient = new GameAPIClient();
        loadGame();
    }

    //MARK: - Public methods

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

    public void setImagesUrl(ArrayList<String> imagesUrl) {
        this.savedStateHandle.set(Constants.ATT_IMAGES_URL_LIVE_DATA, imagesUrl);
    }

    public void loadGame() {
        //TODO load game
    }
}
