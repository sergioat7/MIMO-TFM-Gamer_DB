package es.upsa.mimo.gamerdb.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import java.util.Objects;
import butterknife.BindView;
import butterknife.ButterKnife;
import es.upsa.mimo.gamerdb.R;
import es.upsa.mimo.gamerdb.models.ErrorResponse;
import es.upsa.mimo.gamerdb.models.GameResponse;
import es.upsa.mimo.gamerdb.network.apiclient.CompletionHandler;
import es.upsa.mimo.gamerdb.network.apiclient.GameAPIClient;
import es.upsa.mimo.gamerdb.utils.Constants;

public class GameDetailActivity extends AppCompatActivity {

    //MARK: - Public properties

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    //MARK: - Private properties

    private int gameId;
    private GameAPIClient gameAPIClient;

    //MARK: - Lifecycle methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setTitle("");

        int gameId = getIntent().getIntExtra(Constants.gameId, 0);
        if (gameId > 0) {
            this.gameId = gameId;
        }

        this.initializeUI();
    }

    //MARK: - Private functions

    private void initializeUI() {

        gameAPIClient = new GameAPIClient();

        //TODO initialize elements

        loadGame();
    }

    private void loadGame() {

        gameAPIClient.getGame(gameId, new CompletionHandler<GameResponse>() {
            @Override
            public void success(GameResponse gameResponse) {
                //TODO show data
            }

            @Override
            public void failure(ErrorResponse error) {
                //TODO show error and go back
            }
        });
    }
}