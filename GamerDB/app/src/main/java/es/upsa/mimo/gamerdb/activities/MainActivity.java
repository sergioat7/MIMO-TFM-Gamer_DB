package es.upsa.mimo.gamerdb.activities;

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
import android.widget.SearchView;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import es.upsa.mimo.gamerdb.R;
import es.upsa.mimo.gamerdb.adapters.GamesAdapter;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout srlGames;

    @BindView(R.id.recycler_view_games)
    RecyclerView rvGames;

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

        srlGames.setColorSchemeResources(R.color.colorPrimary);
        srlGames.setProgressBackgroundColorSchemeResource(android.R.color.white);
        srlGames.setOnRefreshListener(this::loadGames);

        rvGames.setLayoutManager(new LinearLayoutManager(this));

        loadGames();
    }

    private void loadGames() {

        rvGames.setAdapter(new GamesAdapter(new ArrayList<>()));//TODO fill with data

        srlGames.setRefreshing(false);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //TODO load games with query
        }
    }
}