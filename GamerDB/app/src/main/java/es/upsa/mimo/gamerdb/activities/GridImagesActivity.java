/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 29/8/2020
 */

package es.upsa.mimo.gamerdb.activities;

import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import butterknife.BindView;
import butterknife.ButterKnife;
import es.upsa.mimo.gamerdb.R;
import es.upsa.mimo.gamerdb.activities.base.BaseActivity;
import es.upsa.mimo.gamerdb.adapters.ImagesAdapter;
import es.upsa.mimo.gamerdb.models.ErrorResponse;
import es.upsa.mimo.gamerdb.models.ScreenshotListResponse;
import es.upsa.mimo.gamerdb.models.ScreenshotResponse;
import es.upsa.mimo.gamerdb.network.apiclient.CompletionHandler;
import es.upsa.mimo.gamerdb.network.apiclient.GameAPIClient;
import es.upsa.mimo.gamerdb.utils.Constants;

public class GridImagesActivity extends BaseActivity {

    //MARK: - Public properties

    @BindView(R.id.toolbar_grid_images)
    Toolbar toolbar;
    @BindView(R.id.grid_view_images)
    GridView gvImages;

    //MARK: - Private properties

    private int gameId;
    private GameAPIClient gameAPIClient;
    private List<String> imagesUrl;

    //MARK: - Lifecycle methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_grid_images);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setTitle("");

        int gameId = getIntent().getIntExtra(Constants.GAME_ID, 0);
        if (gameId > 0) {
            this.gameId = gameId;
        }

        this.initializeUI();
    }

    //MARK: - Private methods

    private void initializeUI() {

        gvImages.setOnItemClickListener((parent, view, position, id) -> {

            Intent intent = new Intent(this, ImageDetailActivity.class);
            intent.putExtra(Constants.IMAGE_URL, imagesUrl.get(position));
            startActivity(intent);
        });

        gameAPIClient = new GameAPIClient();
        loadImages();
    }

    private void loadImages() {

        gameAPIClient.getScreenshots(gameId, new CompletionHandler<ScreenshotListResponse>() {
            @Override
            public void success(ScreenshotListResponse screenshotListResponse) {

                List<ScreenshotResponse> screenshots = screenshotListResponse.getResults();
                imagesUrl = new ArrayList<>();
                for (int i = 0; i < screenshots.size(); i++) {
                    imagesUrl.add(screenshots.get(i).getImage());
                }
                gvImages.setAdapter(new ImagesAdapter(GridImagesActivity.this, imagesUrl));
            }

            @Override
            public void failure(ErrorResponse error) {
                manageError(error);
            }
        });
    }
}