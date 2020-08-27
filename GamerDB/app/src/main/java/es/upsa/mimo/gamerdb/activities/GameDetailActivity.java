package es.upsa.mimo.gamerdb.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import java.util.List;
import java.util.Objects;
import butterknife.BindView;
import butterknife.ButterKnife;
import es.upsa.mimo.gamerdb.R;
import es.upsa.mimo.gamerdb.customviews.ImageLoading;
import es.upsa.mimo.gamerdb.fragments.popups.PopupVideoFragment;
import es.upsa.mimo.gamerdb.models.DeveloperResponse;
import es.upsa.mimo.gamerdb.models.ErrorResponse;
import es.upsa.mimo.gamerdb.models.GameResponse;
import es.upsa.mimo.gamerdb.models.GenreResponse;
import es.upsa.mimo.gamerdb.models.PlatformResponse;
import es.upsa.mimo.gamerdb.models.PublisherResponse;
import es.upsa.mimo.gamerdb.models.TagResponse;
import es.upsa.mimo.gamerdb.network.apiclient.CompletionHandler;
import es.upsa.mimo.gamerdb.network.apiclient.GameAPIClient;
import es.upsa.mimo.gamerdb.utils.Constants;

public class GameDetailActivity extends AppCompatActivity {

    //MARK: - Public properties

    @BindView(R.id.toolbar_game_detail)
    Toolbar toolbar;
    @BindView(R.id.image_view_game)
    ImageView ivGame;
    @BindView(R.id.image_loading)
    ImageLoading imageLoading;
    @BindView(R.id.scroll_view_games)
    ScrollView svGames;
    @BindView(R.id.text_view_name)
    TextView tvName;
    @BindView(R.id.text_view_rating)
    TextView tvRating;
    @BindView(R.id.button_watch_video)
    Button btWatchVideo;
    @BindView(R.id.button_view_images)
    Button btViewImages;
    @BindView(R.id.text_view_description)
    TextView tvDescription;
    @BindView(R.id.button_show_more_text)
    Button btShowMoreText;
    @BindView(R.id.text_view_platforms)
    TextView tvPlatforms;
    @BindView(R.id.text_view_released_date)
    TextView tvReleaseDate;
    @BindView(R.id.text_view_genres)
    TextView tvGenres;
    @BindView(R.id.text_view_age_rating)
    TextView tvAgeRating;
    @BindView(R.id.text_view_developer)
    TextView tvDeveloper;
    @BindView(R.id.text_view_publisher)
    TextView tvPublisher;
    @BindView(R.id.text_view_website)
    TextView tvWebsite;
    @BindView(R.id.text_view_tags)
    TextView tvTags;

    //MARK: - Private properties

    private int gameId;
    private GameAPIClient gameAPIClient;
    private GameResponse game;

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

        btWatchVideo.setOnClickListener(v -> {
            watchVideo();
        });

        btViewImages.setOnClickListener(v -> {
            viewImages();
        });

        btShowMoreText.setOnClickListener(v -> {

            tvDescription.setMaxLines(Constants.maxLines);
            btShowMoreText.setVisibility(View.GONE);
        });

        gameAPIClient = new GameAPIClient();
        loadGame();
    }

    private void loadGame() {

        imageLoading.setVisibility(View.VISIBLE);
        gameAPIClient.getGame(gameId, new CompletionHandler<GameResponse>() {
            @Override
            public void success(GameResponse gameResponse) {

                game = gameResponse;
                Picasso.get().load(gameResponse.getBackgroundImage()).into(ivGame, new Callback() {
                    @Override
                    public void onSuccess() {
                        imageLoading.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Exception e) {
                        imageLoading.setVisibility(View.GONE);
                    }
                });
                tvName.setText(gameResponse.getName());
                tvRating.setText(String.valueOf(gameResponse.getRating()));
                tvDescription.setText(gameResponse.getDescription());
                List<PlatformResponse> platforms = gameResponse.getPlatforms();
                String platformsText = "";
                if (platforms != null) {
                    for (int i = 0; i < platforms.size(); i++) {

                        platformsText += platforms.get(i).getPlatform().getName();
                        platformsText += ", ";
                    }
                    platformsText = platformsText.isEmpty() ? "" : platformsText.substring(0, platformsText.length() - 2);
                }
                tvPlatforms.setText(platformsText);
                tvReleaseDate.setText(gameResponse.getReleased());
                List<GenreResponse> genres = gameResponse.getGenres();
                String genresText = "";
                if (genres != null) {
                    for (int i = 0; i < genres.size(); i++) {

                        genresText += genres.get(i).getName();
                        genresText += ", ";
                    }
                    genresText = genresText.isEmpty() ? "" : genresText.substring(0, genresText.length() - 2);
                }
                tvGenres.setText(genresText);
                if (gameResponse.getEsrbRating() != null) {
                    tvAgeRating.setText(gameResponse.getEsrbRating().getName());
                }
                List<DeveloperResponse> developers = gameResponse.getDevelopers();
                String developersText = "";
                if (developers != null) {
                    for (int i = 0; i < developers.size(); i++) {

                        developersText += developers.get(i).getName();
                        developersText += ", ";
                    }
                    developersText = developersText.isEmpty() ? "" : developersText.substring(0, developersText.length() - 2);
                }
                tvDeveloper.setText(developersText);
                List<PublisherResponse> publishers = gameResponse.getPublishers();
                String publishersText = "";
                if (publishers != null) {
                    for (int i = 0; i < publishers.size(); i++) {

                        publishersText += publishers.get(i).getName();
                        publishersText += ", ";
                    }
                    publishersText = publishersText.isEmpty() ? "" : publishersText.substring(0, publishersText.length() - 2);
                }
                tvPublisher.setText(publishersText);
                tvWebsite.setText(gameResponse.getWebsite());
                //TODO show stores
                List<TagResponse> tags = gameResponse.getTags();
                String tagsText = "";
                if (tags != null) {
                    for (int i = 0; i < tags.size(); i++) {

                        tagsText += tags.get(i).getName();
                        tagsText += ", ";
                    }
                    tagsText = tagsText.isEmpty() ? "" : tagsText.substring(0, tagsText.length() - 2);
                }
                tvTags.setText(tagsText);
            }

            @Override
            public void failure(ErrorResponse error) {
                //TODO show error and go back
            }
        });
    }

    private void watchVideo() {

        String videoUrl = "";
        if (game != null && game.getClip() != null) {
            videoUrl = game.getClip().getVideo();
        }

        //TODO choose option
        //OPCION 1
//        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(bvideoUrl)));

        //OPCION 2
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentByTag("videoPopup");//TODO move to Constants
        if (prev != null) {
            transaction.remove(prev);
        }
        transaction.addToBackStack(null);
        PopupVideoFragment dialogFragment = new PopupVideoFragment(videoUrl);
        dialogFragment.setCancelable(true);
        dialogFragment.show(transaction, "videoPopup");//TODO move to Constants
    }

    private void viewImages() {
        //TODO view images
    }
}