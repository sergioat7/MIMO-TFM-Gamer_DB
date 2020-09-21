/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 30/8/2020
 */

package es.upsa.mimo.gamerdb.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;
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
    ImageView ivGame;
    @BindView(R.id.image_loading)
    ImageLoading loading;

    //MARK: - Private properties

    private String imageUrl;
    private ScaleGestureDetector sgdZoom;
    private float scaleFactor = Constants.INITIAL_ZOOM;
    private float posX;
    private float posY;
    private float lastTouchX;
    private float lastTouchY;
    private int activePointerId = -1;

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

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        sgdZoom.onTouchEvent(event);
        int action = event.getAction();

        switch (action & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN: {

                lastTouchX = event.getX();
                lastTouchY = event.getY();
                activePointerId = event.getPointerId(0);
                break;
            }

            case MotionEvent.ACTION_POINTER_UP: {

                int pointerIndex = (event.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK) >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
                int pointerId = event.getPointerId(pointerIndex);
                if (pointerId == activePointerId) {

                    // This was our active pointer going up. Choose a new
                    // active pointer and adjust accordingly.
                    int newPointerIndex = pointerIndex == 0 ? 1 : 0;
                    lastTouchX = event.getX(newPointerIndex);
                    lastTouchY = event.getY(newPointerIndex);
                    activePointerId = event.getPointerId(newPointerIndex);
                }
                break;
            }

            case MotionEvent.ACTION_MOVE: {

                int pointerIndex = event.findPointerIndex(activePointerId);
                float x = event.getX(pointerIndex);
                float y = event.getY(pointerIndex);

                // Only move if the ScaleGestureDetector isn't processing a gesture.
                if (!sgdZoom.isInProgress()) {

                    float dx = x - lastTouchX;
                    float dy = y - lastTouchY;

                    posX += dx;
                    posY += dy;

                    ivGame.setTranslationX(posX);
                    ivGame.setTranslationY(posY);
                }

                lastTouchX = x;
                lastTouchY = y;

                break;
            }
        }

        return true;
    }

    //MARK: - Private methods

    private void initializeUI() {

        sgdZoom = new ScaleGestureDetector(this, new ScaleGestureDetector.SimpleOnScaleGestureListener() {
            @Override
            public boolean onScale(ScaleGestureDetector detector) {

                scaleFactor *= sgdZoom.getScaleFactor();
                scaleFactor = Math.max(1f, Math.min(scaleFactor, Constants.MAX_ZOOM));
                ivGame.setScaleX(scaleFactor);
                ivGame.setScaleY(scaleFactor);
                return true;
            }
        });

        loading.setVisibility(View.VISIBLE);
        Picasso.get().load(imageUrl).into(ivGame, new Callback() {
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