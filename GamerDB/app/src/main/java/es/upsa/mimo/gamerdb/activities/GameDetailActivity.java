/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 19/8/2020
 */

package es.upsa.mimo.gamerdb.activities;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import butterknife.BindView;
import butterknife.ButterKnife;
import es.upsa.mimo.gamerdb.R;
import es.upsa.mimo.gamerdb.activities.base.BaseActivity;
import es.upsa.mimo.gamerdb.customviews.ImageLoading;
import es.upsa.mimo.gamerdb.fragments.popups.PopupVideoDialogFragment;
import es.upsa.mimo.gamerdb.models.DeveloperResponse;
import es.upsa.mimo.gamerdb.models.ErrorResponse;
import es.upsa.mimo.gamerdb.models.GameResponse;
import es.upsa.mimo.gamerdb.models.GenreResponse;
import es.upsa.mimo.gamerdb.models.PlatformResponse;
import es.upsa.mimo.gamerdb.models.PublisherResponse;
import es.upsa.mimo.gamerdb.models.StoreResponse;
import es.upsa.mimo.gamerdb.models.TagResponse;
import es.upsa.mimo.gamerdb.network.apiclient.CompletionHandler;
import es.upsa.mimo.gamerdb.network.apiclient.GameAPIClient;
import es.upsa.mimo.gamerdb.utils.Constants;

public class GameDetailActivity extends BaseActivity {

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
    @BindView(R.id.text_view_platforms_title)
    TextView tvPlatformsTitle;
    @BindView(R.id.text_view_platforms)
    TextView tvPlatforms;
    @BindView(R.id.text_view_released_date_title)
    TextView tvReleaseDateTitle;
    @BindView(R.id.text_view_released_date)
    TextView tvReleaseDate;
    @BindView(R.id.text_view_genres_title)
    TextView tvGenresTitle;
    @BindView(R.id.text_view_genres)
    TextView tvGenres;
    @BindView(R.id.text_view_age_rating_title)
    TextView tvAgeRatingTitle;
    @BindView(R.id.text_view_age_rating)
    TextView tvAgeRating;
    @BindView(R.id.text_view_developer_title)
    TextView tvDeveloperTitle;
    @BindView(R.id.text_view_developer)
    TextView tvDeveloper;
    @BindView(R.id.text_view_publisher_title)
    TextView tvPublisherTitle;
    @BindView(R.id.text_view_publisher)
    TextView tvPublisher;
    @BindView(R.id.text_view_website_title)
    TextView tvWebsiteTitle;
    @BindView(R.id.text_view_website)
    TextView tvWebsite;
    @BindView(R.id.text_view_stores_title)
    TextView tvStoresTitle;
    @BindView(R.id.linear_layout_stores)
    LinearLayout llStores;
    @BindView(R.id.text_view_tags_title)
    TextView tvTagsTitle;
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

        int gameId = getIntent().getIntExtra(Constants.GAME_ID, 0);
        if (gameId > 0) {
            this.gameId = gameId;
        }

        this.initializeUI();
    }

    //MARK: - Private methods

    private void initializeUI() {

        btWatchVideo.setOnClickListener(v -> watchVideo());

        btViewImages.setOnClickListener(v -> viewImages());

        btShowMoreText.setOnClickListener(v -> {

            tvDescription.setMaxLines(Constants.MAX_LINES);
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

                String description = gameResponse.getDescription();
                tvDescription.setText(description);
                tvDescription.setVisibility(description.isEmpty() ? View.GONE : View.VISIBLE);
                btShowMoreText.setVisibility(description.isEmpty() ? View.GONE : View.VISIBLE);

                List<PlatformResponse> platforms = gameResponse.getPlatforms();
                StringBuilder platformsText = new StringBuilder();
                if (platforms != null) {
                    for (int i = 0; i < platforms.size(); i++) {

                        platformsText.append(platforms.get(i).getPlatform().getName());
                        platformsText.append(Constants.nextValueSeparator);
                    }
                }
                platformsText = new StringBuilder((platformsText.length() == 0) ? Constants.emptyValue : platformsText.substring(0, platformsText.length() - 2));
                tvPlatforms.setText(platformsText.toString());
                tvPlatforms.setVisibility(platformsText.length() == 0 ? View.GONE : View.VISIBLE);
                tvPlatformsTitle.setVisibility(platformsText.length() == 0 ? View.GONE : View.VISIBLE);

                String releasedDate;
                try {
                    Date date = Constants.stringToDate(gameResponse.getReleased(), Constants.DATE_FORMAT);
                    releasedDate = Constants.dateToString(date, Constants.DATE_FORMAT_TO_SHOW);
                    assert releasedDate != null;
                    releasedDate = releasedDate.substring(0,1).toUpperCase() + releasedDate.substring(1);
                } catch (Exception ignored) {
                    releasedDate = Constants.emptyValue;
                }
                tvReleaseDate.setText(releasedDate);
                tvReleaseDate.setVisibility(releasedDate.isEmpty() ? View.GONE : View.VISIBLE);
                tvReleaseDateTitle.setVisibility(releasedDate.isEmpty() ? View.GONE : View.VISIBLE);

                List<GenreResponse> genres = gameResponse.getGenres();
                StringBuilder genresText = new StringBuilder();
                if (genres != null) {
                    for (int i = 0; i < genres.size(); i++) {

                        genresText.append(genres.get(i).getName());
                        genresText.append(Constants.nextValueSeparator);
                    }
                }
                genresText = new StringBuilder((genresText.length() == 0) ? Constants.emptyValue : genresText.substring(0, genresText.length() - 2));
                tvGenres.setText(genresText.toString());
                tvGenres.setVisibility(genresText.length() == 0 ? View.GONE : View.VISIBLE);
                tvGenresTitle.setVisibility(genresText.length() == 0 ? View.GONE : View.VISIBLE);

                String ageRating = Constants.emptyValue;
                if (gameResponse.getEsrbRating() != null) {
                    ageRating = gameResponse.getEsrbRating().getName();
                }
                tvAgeRating.setText(ageRating);
                tvAgeRating.setVisibility(ageRating.isEmpty() ? View.GONE : View.VISIBLE);
                tvAgeRatingTitle.setVisibility(ageRating.isEmpty() ? View.GONE : View.VISIBLE);

                List<DeveloperResponse> developers = gameResponse.getDevelopers();
                StringBuilder developersText = new StringBuilder();
                if (developers != null) {
                    for (int i = 0; i < developers.size(); i++) {

                        developersText.append(developers.get(i).getName());
                        developersText.append(Constants.nextValueSeparator);
                    }
                }
                developersText = new StringBuilder((developersText.length() == 0) ? Constants.emptyValue : developersText.substring(0, developersText.length() - 2));
                tvDeveloper.setText(developersText.toString());
                tvDeveloper.setVisibility(developersText.length() == 0 ? View.GONE : View.VISIBLE);
                tvDeveloperTitle.setVisibility(developersText.length() == 0 ? View.GONE : View.VISIBLE);

                List<PublisherResponse> publishers = gameResponse.getPublishers();
                StringBuilder publishersText = new StringBuilder();
                if (publishers != null) {
                    for (int i = 0; i < publishers.size(); i++) {

                        publishersText.append(publishers.get(i).getName());
                        publishersText.append(Constants.nextValueSeparator);
                    }
                }
                publishersText = new StringBuilder((publishersText.length() == 0) ? Constants.emptyValue : publishersText.substring(0, publishersText.length() - 2));
                tvPublisher.setText(publishersText.toString());
                tvPublisher.setVisibility(publishersText.length() == 0 ? View.GONE : View.VISIBLE);
                tvPublisherTitle.setVisibility(publishersText.length() == 0 ? View.GONE : View.VISIBLE);

                String website = gameResponse.getWebsite();
                tvWebsite.setText(website);
                tvWebsite.setVisibility(website.isEmpty() ? View.GONE : View.VISIBLE);
                tvWebsiteTitle.setVisibility(website.isEmpty() ? View.GONE : View.VISIBLE);

                llStores.removeAllViews();
                List<StoreResponse> stores = gameResponse.getStores();
                if (stores != null) {
                    for (int i = 0; i < stores.size(); i++) {
                        String storeName;
                        String storeUrl;
                        String storeSlug;
                        StoreResponse store = stores.get(i);
                        if (store != null) {
                            storeUrl = store.getUrl();
                            if (store.getStore() != null) {
                                storeName = store.getStore().getName();
                                storeSlug = store.getStore().getSlug();

                                Button button = getStoreButton(storeName, storeUrl);
                                int imageId = Constants.getStoreImageId(storeSlug);
                                button.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, imageId, 0);

                                View separator = new View(GameDetailActivity.this);
                                separator.setLayoutParams(new ViewGroup.LayoutParams(
                                        Constants.STORE_BUTTON_SEPARATOR_WIDTH,
                                        ViewGroup.LayoutParams.MATCH_PARENT
                                ));

                                llStores.addView(button);
                                llStores.addView(separator);
                            }
                        }
                    }
                }
                if (llStores.getChildCount() > 0) {
                    llStores.removeViewAt(llStores.getChildCount() - 1);
                    llStores.setVisibility(View.VISIBLE);
                    tvStoresTitle.setVisibility(View.VISIBLE);
                } else {
                    llStores.setVisibility(View.GONE);
                    tvStoresTitle.setVisibility(View.GONE);
                }

                List<TagResponse> tags = gameResponse.getTags();
                StringBuilder tagsText = new StringBuilder();
                if (tags != null) {
                    for (int i = 0; i < tags.size(); i++) {

                        tagsText.append(tags.get(i).getName());
                        tagsText.append(Constants.nextValueSeparator);
                    }
                    tagsText = new StringBuilder((tagsText.length() == 0) ? Constants.emptyValue : tagsText.substring(0, tagsText.length() - 2));
                }
                tvTags.setText(tagsText.toString());
                tvTags.setVisibility(tagsText.length() == 0 ? View.GONE : View.VISIBLE);
                tvTagsTitle.setVisibility(tagsText.length() == 0 ? View.GONE : View.VISIBLE);
            }

            @Override
            public void failure(ErrorResponse error) {
                manageError(error);
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
//        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl)));

        //OPCION 2
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentByTag("videoPopup");//TODO move to Constants
        if (prev != null) {
            transaction.remove(prev);
        }
        transaction.addToBackStack(null);
        PopupVideoDialogFragment dialogFragment = new PopupVideoDialogFragment(videoUrl);
        dialogFragment.setCancelable(true);
        dialogFragment.show(transaction, "videoPopup");//TODO move to Constants
    }

    private void viewImages() {

        Intent intent = new Intent(this, GridImagesActivity.class);
        intent.putExtra(Constants.GAME_ID, gameId);
        startActivity(intent);
    }

    private Button getStoreButton(String text, String url) {

        Button button = new Button(this, null, 0, R.style.GameDetailStoreButton);
        int height = (int) (Constants.STORE_BUTTON_HEIGHT * getResources().getDisplayMetrics().density);
        button.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                height
        ));
        button.setTag(url);
        button.setText(text);
        button.setOnClickListener(v -> openStoreUrl(v.getTag().toString()));
        return button;
    }

    private void openStoreUrl(String url) {

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }
}