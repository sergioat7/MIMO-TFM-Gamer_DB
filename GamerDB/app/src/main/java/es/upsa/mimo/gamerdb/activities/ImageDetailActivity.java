/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 30/8/2020
 */

package es.upsa.mimo.gamerdb.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import java.util.Objects;
import butterknife.BindView;
import butterknife.ButterKnife;
import es.upsa.mimo.gamerdb.R;
import es.upsa.mimo.gamerdb.activities.base.BaseActivity;
import es.upsa.mimo.gamerdb.customviews.ImageLoading;
import es.upsa.mimo.gamerdb.utils.Constants;

public class ImageDetailActivity extends BaseActivity {

    //MARK: - Public properties

    @BindView(R.id.toolbar_image_detail)
    Toolbar toolbar;
    @BindView(R.id.image_view_game)
    PhotoView ivGame;
    @BindView(R.id.image_loading)
    ImageLoading loading;

    //MARK: - Private properties

    private String imageUrl;

    //MARK: - Lifecycle methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_image_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setTitle(Constants.EMPTY_VALUE);

        imageUrl = getIntent().getStringExtra(Constants.IMAGE_URL);

        this.initializeUI();
    }

    //MARK: - Public methods

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        finish();
        return super.onOptionsItemSelected(item);
    }

    //MARK: - Private methods

    private void initializeUI() {

        loading.setVisibility(View.VISIBLE);
        Picasso
                .get()
                .load(imageUrl)
                .into(ivGame, new Callback() {
                    @Override
                    public void onSuccess() {
                        loading.setVisibility(View.GONE);
                    }
                    @Override
                    public void onError(Exception e) {
                        loading.setVisibility(View.GONE);
                    }
                });
    }
}