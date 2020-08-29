package es.upsa.mimo.gamerdb.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.upsa.mimo.gamerdb.R;
import es.upsa.mimo.gamerdb.adapters.ImagesAdapter;
import es.upsa.mimo.gamerdb.models.ErrorResponse;
import es.upsa.mimo.gamerdb.models.ScreenshotListResponse;
import es.upsa.mimo.gamerdb.models.ScreenshotResponse;
import es.upsa.mimo.gamerdb.network.apiclient.CompletionHandler;
import es.upsa.mimo.gamerdb.network.apiclient.GameAPIClient;
import es.upsa.mimo.gamerdb.utils.Constants;

public class GridImagesActivity extends AppCompatActivity {

    //MARK: - Public properties

    @BindView(R.id.toolbar_grid_images)
    Toolbar toolbar;

    @BindView(R.id.grid_view_images)
    GridView gvImages;

    //MARK: - Private properties

    private int gameId;
    private GameAPIClient gameAPIClient;

    //MARK: - Lifecycle methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_grid_images);
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

        gvImages.setOnItemClickListener((parent, view, position, id) -> {
            //TODO go to image detail
        });

        gameAPIClient = new GameAPIClient();
        loadImages();
    }

    private void loadImages() {

        Context context = this;
        gameAPIClient.getScreenshots(gameId, new CompletionHandler<ScreenshotListResponse>() {
            @Override
            public void success(ScreenshotListResponse screenshotListResponse) {

                List<ScreenshotResponse> screenshots = screenshotListResponse.getResults();
                List<String> imagesUrl = new ArrayList<>();
                for (int i = 0; i < screenshots.size(); i++) {
                    imagesUrl.add(screenshots.get(i).getImage());
                }
                gvImages.setAdapter(new ImagesAdapter(context, imagesUrl));
            }

            @Override
            public void failure(ErrorResponse error) {
                //TODO show error and go back
            }
        });
    }
}