/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 10/9/2020
 */

package es.upsa.mimo.gamerdb.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.List;
import es.upsa.mimo.gamerdb.models.GameResponse;
import es.upsa.mimo.gamerdb.utils.Constants;

public class MainViewModel extends ViewModel {

    private SavedStateHandle savedStateHandle;
    private LiveData<List<GameResponse>> games;
    private LiveData<Integer> page;
    private LiveData<String> query;

    public MainViewModel(SavedStateHandle savedStateHandle) {

        this.savedStateHandle = savedStateHandle;
        games = savedStateHandle.getLiveData(Constants.ATT_GAMES_LIVE_DATA, new ArrayList<>());
        page = savedStateHandle.getLiveData(Constants.ATT_PAGE_LIVE_DATA, Constants.FIRST_PAGE);
        query = savedStateHandle.getLiveData(Constants.ATT_QUERY_LIVE_DATA, null);
        //TODO load games for first time
    }

    public LiveData<List<GameResponse>> getGames() {
        return games;
    }

    public void addGames(List<GameResponse> games) {

        List<GameResponse> currentGames = this.games.getValue();
        if (currentGames == null) {
            currentGames = new ArrayList<>();
        }
        currentGames.addAll(games);
        this.savedStateHandle.set(Constants.ATT_GAMES_LIVE_DATA, currentGames);
    }

    public void resetList() {
        this.savedStateHandle.set(Constants.ATT_GAMES_LIVE_DATA, new ArrayList<>());
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
}