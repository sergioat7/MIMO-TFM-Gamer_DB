package es.upsa.mimo.gamerdb.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import es.upsa.mimo.gamerdb.R;
import es.upsa.mimo.gamerdb.adapters.GamesAdapter;
import es.upsa.mimo.gamerdb.models.ErrorResponse;
import es.upsa.mimo.gamerdb.models.GameListResponse;
import es.upsa.mimo.gamerdb.network.apiclient.CompletionHandler;
import es.upsa.mimo.gamerdb.network.apiclient.GameAPIClient;
import es.upsa.mimo.gamerdb.utils.Constants;

public class MainActivity extends AppCompatActivity {

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
    private int page = 1;

    //MARK: - Lifecycle methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setTitle("");
        this.initializeUI();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if (menu != null) {

            menu.clear();
            getMenuInflater().inflate(R.menu.menu_main, menu);

            SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
            SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
            if (searchManager != null) {
                searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            }
        }
        return true;
    }

    //MARK: - Private functions

    private void initializeUI() {

        gameAPIClient = new GameAPIClient();

        srlGames.setColorSchemeResources(R.color.colorPrimary);
        srlGames.setProgressBackgroundColorSchemeResource(android.R.color.white);
        srlGames.setOnRefreshListener(this::loadGames);

        rvGames.setLayoutManager(new LinearLayoutManager(this));
        rvGames.setAdapter(new GamesAdapter(new ArrayList<>()));
        rvGames.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1) && newState==RecyclerView.SCROLL_STATE_IDLE) {
                    loadGames();
                }
            }
        });

        loadGames();
    }

    private void loadGames() {

        pbPagination.setVisibility(View.VISIBLE);
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

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //TODO load games with query
        }
    }
}