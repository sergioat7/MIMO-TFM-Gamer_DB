package es.upsa.mimo.gamerdb.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import java.util.Objects;
import butterknife.BindView;
import butterknife.ButterKnife;
import es.upsa.mimo.gamerdb.R;
import es.upsa.mimo.gamerdb.customviews.ImageLoading;
import es.upsa.mimo.gamerdb.utils.Constants;

public class ImageDetailActivity extends AppCompatActivity {

    //MARK: - Public properties

    @BindView(R.id.toolbar_image_detail)
    Toolbar toolbar;
    @BindView(R.id.image_view_game)
    ImageView ivGame;
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
        setTitle("");

        imageUrl = getIntent().getStringExtra(Constants.imageUrl);

        this.initializeUI();
    }

    //MARK: - Private functions

    private void initializeUI() {

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