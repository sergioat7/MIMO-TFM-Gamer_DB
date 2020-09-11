/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 27/7/2020
 */

package es.upsa.mimo.gamerdb.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.Objects;
import butterknife.BindView;
import butterknife.ButterKnife;
import es.upsa.mimo.gamerdb.R;
import es.upsa.mimo.gamerdb.activities.base.BaseActivity;
import es.upsa.mimo.gamerdb.adapters.GamesAdapter;
import es.upsa.mimo.gamerdb.network.apiclient.GameAPIClient;
import es.upsa.mimo.gamerdb.utils.Constants;
import es.upsa.mimo.gamerdb.viewmodels.MainViewModel;

public class MainActivity extends BaseActivity implements GamesAdapter.OnItemClickListener {

    //MARK: - Public properties

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout srlGames;

    @BindView(R.id.recycler_view_games)
    RecyclerView rvGames;

    @BindView(R.id.floating_action_button_end_list)
    FloatingActionButton btEndList;

    //MARK: - Private properties

    private MainViewModel viewModel;
    private GamesAdapter gamesAdapter;

    //MARK: - Lifecycle methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_home_white_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("");

        this.initializeUI();
    }

    //MARK: - Interface methods

    @Override
    public void onItemClick(int gameId) {

        Intent intent = new Intent(this, GameDetailActivity.class);
        intent.putExtra(Constants.GAME_ID, gameId);
        startActivity(intent);
    }

    @Override
    public void onLoadMoreItemsClick() {
        viewModel.loadGames();
    }

    //MARK: - Public methods

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if (menu != null) {

            menu.clear();
            getMenuInflater().inflate(R.menu.menu_main, menu);
            setupSearchView(menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home) {

            viewModel.setPosition(Constants.INITIAL_POSITION_LIST);
            btEndList.setVisibility(View.VISIBLE);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //MARK: - Protected methods

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {

            toolbar.collapseActionView();
            srlGames.setRefreshing(true);
            viewModel.searchGames(intent.getStringExtra(SearchManager.QUERY));
        }
    }

    //MARK: - Private methods

    private void initializeUI() {

        viewModel = new ViewModelProvider(
                this,
                new SavedStateViewModelFactory(this.getApplication(), this)
        ).get(MainViewModel.class);
        viewModel
                .getGames()
                .observe(this, gameResponses -> {

                    if (gameResponses.isEmpty()) {
                        gamesAdapter.resetList();
                    } else{
                        gamesAdapter.setGames(gameResponses);
                    }
                    btEndList.setVisibility(View.VISIBLE);
                });
        viewModel
                .getError()
                .observe(this, this::manageError);
        viewModel
                .getPosition()
                .observe(this, position -> rvGames.scrollToPosition(position));
        viewModel
                .getRefreshing()
                .observe(this, refreshing -> srlGames.setRefreshing(refreshing));
        gamesAdapter = new GamesAdapter(viewModel.getGames().getValue(), this);

        srlGames.setColorSchemeResources(R.color.colorPrimary);
        srlGames.setProgressBackgroundColorSchemeResource(android.R.color.white);
        srlGames.setOnRefreshListener(() -> viewModel.reloadGames());

        rvGames.setLayoutManager(new LinearLayoutManager(this));
        rvGames.setAdapter(gamesAdapter);
        rvGames.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    btEndList.setVisibility(View.GONE);
                } else {
                    btEndList.setVisibility(View.VISIBLE);
                }
            }
        });

        btEndList.setOnClickListener(v -> {

            int position = gamesAdapter.getItemCount() - 1;
            viewModel.setPosition(position);
            btEndList.setVisibility(View.GONE);
        });
    }

    private void setupSearchView(Menu menu) {

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        if (searchManager != null) {

            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setIconified(false);
            searchView.setIconifiedByDefault(false);
            searchView.setQueryHint(getResources().getString(R.string.toolbar_search_title));
        }

        int searchPlateId = searchView.getContext().getResources().getIdentifier("android:id/search_plate", null, null);
        View searchPlate = searchView.findViewById(searchPlateId);
        if (searchPlate != null) {

            int searchTextId = searchPlate.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
            TextView searchText = searchPlate.findViewById(searchTextId);
            if (searchText != null) {

                searchText.setTextColor(ContextCompat.getColor(this, R.color.colorSecondary));
                searchText.setHintTextColor(ContextCompat.getColor(this, R.color.colorSecondary));
            }
        }
    }
}