package es.upsa.mimo.gamerdb.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Objects;
import butterknife.BindView;
import butterknife.ButterKnife;
import es.upsa.mimo.gamerdb.R;
import es.upsa.mimo.gamerdb.adapters.GamesAdapter;
import es.upsa.mimo.gamerdb.models.ErrorResponse;
import es.upsa.mimo.gamerdb.models.GameListResponse;
import es.upsa.mimo.gamerdb.network.apiclient.CompletionHandler;
import es.upsa.mimo.gamerdb.network.apiclient.GameAPIClient;
import es.upsa.mimo.gamerdb.utils.Constants;

public class MainActivity extends AppCompatActivity implements GamesAdapter.OnItemClickListener {

    //MARK: - Public properties

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout srlGames;

    @BindView(R.id.recycler_view_games)
    RecyclerView rvGames;

    @BindView(R.id.progress_bar_pagination)
    ProgressBar pbPagination;

    //MARK: - Private properties

    private GameAPIClient gameAPIClient;
    private int page = Constants.firstPage;

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

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
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

            rvGames.scrollToPosition(0);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(int gameId) {

        Intent intent = new Intent(this, GameDetailActivity.class);
        intent.putExtra(Constants.gameId, gameId);
        startActivity(intent);
    }

    //MARK: - Private functions

    private void initializeUI() {

        srlGames.setColorSchemeResources(R.color.colorPrimary);
        srlGames.setProgressBackgroundColorSchemeResource(android.R.color.white);
        srlGames.setOnRefreshListener(this::reloadGames);

        rvGames.setLayoutManager(new LinearLayoutManager(this));
        rvGames.setAdapter(new GamesAdapter(new ArrayList<>(), this));
        rvGames.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    loadGames();
                }
            }
        });

        gameAPIClient = new GameAPIClient();
        loadGames();
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

    private void loadGames() {

        if (page > 1) {
            pbPagination.setVisibility(View.VISIBLE);
        }

        gameAPIClient.getGames(page, Constants.pageSize, new CompletionHandler<GameListResponse>() {
            @Override
            public void success(GameListResponse gameListResponse) {

                GamesAdapter adapter = (GamesAdapter) rvGames.getAdapter();
                if (adapter != null) {
                    adapter.addGames(gameListResponse.getResults());
                }
                srlGames.setRefreshing(false);
                page++;
                pbPagination.setVisibility(View.GONE);
            }

            @Override
            public void failure(ErrorResponse error) {

                srlGames.setRefreshing(false);
                pbPagination.setVisibility(View.GONE);
            }
        });
    }

    private void reloadGames() {

        page = Constants.firstPage;
        GamesAdapter adapter = (GamesAdapter) rvGames.getAdapter();
        if (adapter != null) {
            adapter.resetList();
        }
        loadGames();
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //TODO load games with query
        }
    }
}