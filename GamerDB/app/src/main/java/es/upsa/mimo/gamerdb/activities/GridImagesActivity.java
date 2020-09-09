/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 29/8/2020
 */

package es.upsa.mimo.gamerdb.activities;

import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import java.util.List;
import java.util.Objects;
import butterknife.BindView;
import butterknife.ButterKnife;
import es.upsa.mimo.gamerdb.R;
import es.upsa.mimo.gamerdb.activities.base.BaseActivity;
import es.upsa.mimo.gamerdb.adapters.ImagesAdapter;
import es.upsa.mimo.gamerdb.utils.Constants;

public class GridImagesActivity extends BaseActivity {

    //MARK: - Public properties

    @BindView(R.id.toolbar_grid_images)
    Toolbar toolbar;
    @BindView(R.id.grid_view_images)
    GridView gvImages;

    //MARK: - Private properties

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

        imagesUrl = getIntent().getStringArrayListExtra(Constants.IMAGES_URL);

        this.initializeUI();
    }

    //MARK: - Private methods

    private void initializeUI() {

        gvImages.setOnItemClickListener((parent, view, position, id) -> {

            Intent intent = new Intent(this, ImageDetailActivity.class);
            intent.putExtra(Constants.IMAGE_URL, imagesUrl.get(position));
            startActivity(intent);
        });

        gvImages.setAdapter(new ImagesAdapter(GridImagesActivity.this, imagesUrl));
    }
}