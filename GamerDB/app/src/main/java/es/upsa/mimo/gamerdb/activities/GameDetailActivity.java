/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 19/8/2020
 */

package es.upsa.mimo.gamerdb.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import butterknife.BindView;
import butterknife.ButterKnife;
import es.upsa.mimo.gamerdb.R;
import es.upsa.mimo.gamerdb.activities.base.BaseActivity;
import es.upsa.mimo.gamerdb.adapters.AchievementsAdapter;
import es.upsa.mimo.gamerdb.adapters.DevelopersAdapter;
import es.upsa.mimo.gamerdb.adapters.GamesAdapter;
import es.upsa.mimo.gamerdb.adapters.OnItemClickListener;
import es.upsa.mimo.gamerdb.customviews.ImageLoading;
import es.upsa.mimo.gamerdb.fragments.popups.PopupVideoDialogFragment;
import es.upsa.mimo.gamerdb.models.DeveloperResponse;
import es.upsa.mimo.gamerdb.models.GameResponse;
import es.upsa.mimo.gamerdb.models.GenreResponse;
import es.upsa.mimo.gamerdb.models.PlatformResponse;
import es.upsa.mimo.gamerdb.models.PublisherResponse;
import es.upsa.mimo.gamerdb.models.StoreResponse;
import es.upsa.mimo.gamerdb.models.TagResponse;
import es.upsa.mimo.gamerdb.utils.Constants;
import es.upsa.mimo.gamerdb.viewmodels.GameDetailViewModel;

public class GameDetailActivity extends BaseActivity implements OnItemClickListener {

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
    @BindView(R.id.text_view_game_description)
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
    @BindView(R.id.linear_layout_stores)
    LinearLayout llStores;
    @BindView(R.id.text_view_tags)
    TextView tvTags;
    @BindView(R.id.text_view_game_series_title)
    TextView tvGameSeriesTitle;
    @BindView(R.id.recycler_view_game_series)
    RecyclerView rvGameSeries;
    @BindView(R.id.text_view_games_suggested_title)
    TextView tvGamesSuggestedTitle;
    @BindView(R.id.recycler_view_games_suggested)
    RecyclerView rvGamesSuggested;
    @BindView(R.id.text_view_game_additions_title)
    TextView tvGameAdditionsTitle;
    @BindView(R.id.recycler_view_game_additions)
    RecyclerView rvGameAdditions;
    @BindView(R.id.text_view_developers_title)
    TextView tvDevelopersTitle;
    @BindView(R.id.recycler_view_developers)
    RecyclerView rvDevelopers;
    @BindView(R.id.text_view_achievements_title)
    TextView tvAchievementsTitle;
    @BindView(R.id.recycler_view_achievements)
    RecyclerView rvAchievements;

    //MARK: - Private properties

    private GameDetailViewModel viewModel;
    private GamesAdapter gameSeriesAdapter;
    private GamesAdapter gamesSuggestedAdapter;
    private GamesAdapter gameAdditionsAdapter;
    private DevelopersAdapter developersAdapter;
    private AchievementsAdapter achievementsAdapter;

    //MARK: - Lifecycle methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setTitle(Constants.EMPTY_VALUE);

        int gameId = getIntent().getIntExtra(Constants.GAME_ID, 0);
        this.initializeUI(gameId);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.onDestroy();
    }

    //MARK: - Interface methods

    @Override
    public void onItemClick(View v, int gameId) {

        ViewParent parent = v.getParent().getParent().getParent().getParent();
        if (
                rvGameSeries.equals(parent) ||
                rvGamesSuggested.equals(parent) ||
                rvGameAdditions.equals(parent)
        ) {

            Intent intent = new Intent(this, GameDetailActivity.class);
            intent.putExtra(Constants.GAME_ID, gameId);
            startActivity(intent);
        }
    }

    @Override
    public void onLoadMoreItemsClick(View v) {

        ViewParent parent = v.getParent().getParent();
        if (rvGameSeries.equals(parent)) {
            viewModel.loadGameSeries();
        } else if (rvGamesSuggested.equals(parent)) {
            viewModel.loadGamesSuggested();
        } else if (rvGameAdditions.equals(parent)) {
            viewModel.loadGameAdditions();
        } else if (rvDevelopers.equals(parent)) {
            viewModel.loadDevelopers();
        } else if (rvAchievements.equals(parent)) {
            viewModel.loadAchievements();
        }
    }

    //MARK: - Public methods

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        finish();
        return super.onOptionsItemSelected(item);
    }

    //MARK: - Private methods

    private void initializeUI(int gameId) {

        Bundle extraParameters = new Bundle();
        extraParameters.putInt(Constants.ATT_GAME_ID_LIVE_DATA, gameId);

        viewModel = new ViewModelProvider(
                this,
                new SavedStateViewModelFactory(
                        this.getApplication(),
                        this,
                        extraParameters)
        ).get(GameDetailViewModel.class);
        viewModel
                .getGame()
                .observe(this, this::fillData);
        viewModel
                .getError()
                .observe(this, this::manageError);
        viewModel
                .getImagesUrl()
                .observe(this, imagesUrl -> btViewImages.setVisibility(imagesUrl.isEmpty() ? View.GONE : View.VISIBLE));
        viewModel
                .getGameSeries()
                .observe(this, gameResponses -> {

                    if (gameResponses.isEmpty()) {
                        gameSeriesAdapter.resetList();
                    } else {
                        gameSeriesAdapter.setGames(gameResponses);
                    }
                    tvGameSeriesTitle.setVisibility(gameResponses.isEmpty() ? View.GONE : View.VISIBLE);
                });
        viewModel
                .getGamesSuggested()
                .observe(this, gameResponses -> {

                    if (gameResponses.isEmpty()) {
                        gamesSuggestedAdapter.resetList();
                    } else {
                        gamesSuggestedAdapter.setGames(gameResponses);
                    }
                    tvGamesSuggestedTitle.setVisibility(gameResponses.isEmpty() ? View.GONE : View.VISIBLE);
                });
        viewModel
                .getGameAdditions()
                .observe(this, gameResponses -> {

                    if (gameResponses.isEmpty()) {
                        gameAdditionsAdapter.resetList();
                    } else {
                        gameAdditionsAdapter.setGames(gameResponses);
                    }
                    tvGameAdditionsTitle.setVisibility(gameResponses.isEmpty() ? View.GONE : View.VISIBLE);
                });
        viewModel
                .getDevelopers()
                .observe(this, developerResponses -> {

                    if (developerResponses.isEmpty()) {
                        developersAdapter.resetList();
                    } else {
                        developersAdapter.setDevelopers(developerResponses);
                    }
                    tvDevelopersTitle.setVisibility(developerResponses.isEmpty() ? View.GONE : View.VISIBLE);
                });
        viewModel
                .getAchievements()
                .observe(this, achievementResponses -> {

                    if (achievementResponses.isEmpty()) {
                        achievementsAdapter.resetList();
                    } else {
                        achievementsAdapter.setAchievements(achievementResponses);
                    }
                    tvAchievementsTitle.setVisibility(achievementResponses.isEmpty() ? View.GONE : View.VISIBLE);
                });

        gameSeriesAdapter = new GamesAdapter(
                viewModel.getGameSeries().getValue(),
                this,
                false
        );
        gamesSuggestedAdapter = new GamesAdapter(
                viewModel.getGamesSuggested().getValue(),
                this,
                false
        );
        gameAdditionsAdapter = new GamesAdapter(
                viewModel.getGameAdditions().getValue(),
                this,
                false
        );
        developersAdapter = new DevelopersAdapter(
                viewModel.getDevelopers().getValue(),
                this
        );
        achievementsAdapter = new AchievementsAdapter(
                viewModel.getAchievements().getValue(),
                this
        );

        imageLoading.setVisibility(View.VISIBLE);

        btWatchVideo.setOnClickListener(v -> watchVideo());
        btWatchVideo.setVisibility(View.GONE);
        btViewImages.setOnClickListener(v -> viewImages());
        btViewImages.setVisibility(View.GONE);

        btShowMoreText.setOnClickListener(v -> {

            tvDescription.setMaxLines(Constants.MAX_LINES);
            btShowMoreText.setVisibility(View.GONE);
        });

        rvGameSeries.setLayoutManager(new LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false)
        );
        rvGameSeries.setAdapter(gameSeriesAdapter);

        rvGamesSuggested.setLayoutManager(new LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false)
        );
        rvGamesSuggested.setAdapter(gamesSuggestedAdapter);

        rvGameAdditions.setLayoutManager(new LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false)
        );
        rvGameAdditions.setAdapter(gameAdditionsAdapter);

        rvDevelopers.setLayoutManager(new LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false)
        );
        rvDevelopers.setAdapter(developersAdapter);

        rvAchievements.setLayoutManager(new LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false)
        );
        rvAchievements.setAdapter(achievementsAdapter);
    }

    private void fillData(GameResponse game) {

        String imageUrl = game.getBackgroundImage();
        if (imageUrl != null) {

            Picasso
                    .get()
                    .load(game.getBackgroundImage())
                    .fit()
                    .centerCrop()
                    .error(R.drawable.error_image_2)
                    .into(ivGame, new Callback() {
                        @Override
                        public void onSuccess() {
                            imageLoading.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError(Exception e) {
                            imageLoading.setVisibility(View.GONE);
                        }
                    });
        } else {

            imageLoading.setVisibility(View.GONE);
            ivGame.setImageResource(R.drawable.no_image);
        }

        tvName.setText(game.getName());
        tvRating.setText(String.valueOf(game.getRating()));

        btWatchVideo.setVisibility(game.getClip() != null ? View.VISIBLE : View.GONE);

        String description = game.getDescription();
        tvDescription.setText(description);
        tvDescription.setVisibility(description.isEmpty() ? View.GONE : View.VISIBLE);
        btShowMoreText.setVisibility(description.isEmpty() ? View.GONE : View.VISIBLE);

        List<PlatformResponse> platforms = game.getPlatforms();
        StringBuilder platformsText = new StringBuilder();
        if (platforms != null) {
            for (int i = 0; i < platforms.size(); i++) {

                platformsText.append(platforms.get(i).getPlatform().getName());
                platformsText.append(Constants.NEXT_VALUE_SEPARATOR);
            }
        }
        platformsText = new StringBuilder((platformsText.length() == 0) ? Constants.NO_VALUE : platformsText.substring(0, platformsText.length() - 2));
        tvPlatforms.setText(platformsText.toString());

        String releasedDate;
        try {
            Date date = Constants.stringToDate(game.getReleased(), Constants.DATE_FORMAT);
            releasedDate = Constants.dateToString(date, Constants.DATE_FORMAT_TO_SHOW);
            assert releasedDate != null;
            releasedDate = releasedDate.substring(0,1).toUpperCase() + releasedDate.substring(1);
        } catch (Exception ignored) {
            releasedDate = Constants.NO_VALUE;
        }
        tvReleaseDate.setText(releasedDate);

        List<GenreResponse> genres = game.getGenres();
        StringBuilder genresText = new StringBuilder();
        if (genres != null) {
            for (int i = 0; i < genres.size(); i++) {

                genresText.append(genres.get(i).getName());
                genresText.append(Constants.NEXT_VALUE_SEPARATOR);
            }
        }
        genresText = new StringBuilder((genresText.length() == 0) ? Constants.NO_VALUE : genresText.substring(0, genresText.length() - 2));
        tvGenres.setText(genresText.toString());

        String ageRating = Constants.NO_VALUE;
        if (game.getEsrbRating() != null) {
            ageRating = game.getEsrbRating().getName();
        }
        tvAgeRating.setText(ageRating);

        List<DeveloperResponse> developers = game.getDevelopers();
        StringBuilder developersText = new StringBuilder();
        if (developers != null) {
            for (int i = 0; i < developers.size(); i++) {

                developersText.append(developers.get(i).getName());
                developersText.append(Constants.NEXT_VALUE_SEPARATOR);
            }
        }
        developersText = new StringBuilder((developersText.length() == 0) ? Constants.NO_VALUE : developersText.substring(0, developersText.length() - 2));
        tvDeveloper.setText(developersText.toString());

        List<PublisherResponse> publishers = game.getPublishers();
        StringBuilder publishersText = new StringBuilder();
        if (publishers != null) {
            for (int i = 0; i < publishers.size(); i++) {

                publishersText.append(publishers.get(i).getName());
                publishersText.append(Constants.NEXT_VALUE_SEPARATOR);
            }
        }
        publishersText = new StringBuilder((publishersText.length() == 0) ? Constants.NO_VALUE : publishersText.substring(0, publishersText.length() - 2));
        tvPublisher.setText(publishersText.toString());

        String website = Constants.NO_VALUE;
        if (game.getWebsite() != null && !game.getWebsite().isEmpty()) {
            website = game.getWebsite();
        }
        tvWebsite.setText(website);

        llStores.removeAllViews();
        List<StoreResponse> stores = game.getStores();
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
        }

        List<TagResponse> tags = game.getTags();
        StringBuilder tagsText = new StringBuilder();
        if (tags != null) {
            for (int i = 0; i < tags.size(); i++) {

                tagsText.append(tags.get(i).getName());
                tagsText.append(Constants.NEXT_VALUE_SEPARATOR);
            }
            tagsText = new StringBuilder((tagsText.length() == 0) ? Constants.NO_VALUE : tagsText.substring(0, tagsText.length() - 2));
        }
        tvTags.setText(tagsText.toString());
    }

    private void watchVideo() {

        String videoUrl = Constants.EMPTY_VALUE;
        GameResponse game = viewModel.getGame().getValue();
        if (game != null && game.getClip() != null) {
            videoUrl = game.getClip().getVideo();
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentByTag(Constants.VIDEO_POPUP_ID);
        if (prev != null) {
            transaction.remove(prev);
        }
        transaction.addToBackStack(null);
        PopupVideoDialogFragment dialogFragment = new PopupVideoDialogFragment(videoUrl);
        dialogFragment.setCancelable(true);
        dialogFragment.show(transaction, Constants.VIDEO_POPUP_ID);
    }

    private void viewImages() {

        ArrayList<String> imagesUrl = viewModel.getImagesUrl().getValue();
        Intent intent = new Intent(this, GridImagesActivity.class);
        intent.putExtra(Constants.IMAGES_URL, imagesUrl);
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