/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 6/8/2020
 */

package es.upsa.mimo.gamerdb.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.lifecycle.MutableLiveData;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import es.upsa.mimo.gamerdb.R;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Constants {

    // MARK: - Retrofit constants

    public static String BASE_ENDPOINT = "https://api.rawg.io/api/";
    public static int CONNECT_TIMEOUT = 60;
    public static int READ_TIMEOUT = 30;
    public static int WRITE_TIMEOUT = 15;
    public static String PAGE_PARAM = "page";
    public static String PAGE_SIZE_PARAM = "page_size";
    public static String SEARCH_PARAM = "search";
    public static String ORDERING_PARAM = "ordering";
    public static String PLATFORMS_PARAM = "platforms";
    public static String GENRES_PARAM = "genres";
    public static String ASCENDING_VALUE = "";
    public static String DESCENDING_VALUE = "-";
    public static Scheduler SUBSCRIBER_SCHEDULER = Schedulers.io();
    public static Scheduler OBSERVER_SCHEDULER = AndroidSchedulers.mainThread();
    public static int FIRST_PAGE = 1;
    public static int PAGE_SIZE = 20;
    public static String BASE_YOUTUBE_URL = "https://www.youtube.com/embed/";

    // MARK: Date format

    public static String DATE_FORMAT = "yyyy-MM-dd";
    public static String DATE_FORMAT_TO_SHOW = "MMMM d, yyyy";

    public static String dateToString(Date date, String dateFormat) {

        try {
            return new SimpleDateFormat(dateFormat, Locale.ENGLISH).format(date);
        } catch (Exception e) {
            if (e.getMessage() != null) {
                Log.e("Constants", e.getMessage());
            }
            return null;
        }
    }

    public static Date stringToDate(String dateString, String dateFormat) {

        try {
            return new SimpleDateFormat(dateFormat, Locale.ENGLISH).parse(dateString);
        } catch (Exception e) {
            if (e.getMessage() != null) {
                Log.e("Constants", e.getMessage());
            }
            return null;
        }
    }

    //MARK: - LiveData Ids

    public static String ATT_GAMES_COUNT_LIVE_DATA = "gamesCount";
    public static String ATT_PAGE_LIVE_DATA = "page";
    public static String ATT_QUERY_LIVE_DATA = "query";
    public static String ATT_SORT_KEY_LIVE_DATA = "sortKey";
    public static String ATT_SORT_ORDER_LIVE_DATA = "sortOrder";
    public static String ATT_SELECTED_PLATFORMS_LIVE_DATA = "platforms";
    public static String ATT_SELECTED_GENRES_LIVE_DATA = "genres";
    public static String ATT_POSITION_LIVE_DATA = "position";
    public static String ATT_REFRESHING_LIVE_DATA = "refreshing";
    public static String ATT_GAME_ID_LIVE_DATA = "gameId";
    public static String ATT_IMAGES_URL_LIVE_DATA = "imagesUrl";

    //MARK: - Ids

    public static String GAME_ID = "gameId";
    public static String IMAGES_URL = "imagesUrl";
    public static String IMAGE_URL = "imageUrl";
    public static String ERROR_POPUP_ID = "errorPopup";
    public static String VIDEO_POPUP_ID = "videoPopup";

    //MARK: - Game list

    public static int TOOLBAR_TITLE_PADDING_BOTTOM = 20;
    public static int PLATFORM_BUTTON_HEIGHT = 50;
    public static int INITIAL_POSITION_LIST = 0;
    public static int MARGIN_LIST = 50;
    public static int NO_MARGIN_LIST = 0;
    public static int IMAGE_CORNER = 40;

    public static void setToolbarTitleStyle(Context context, Toolbar toolbar) {

        for(int i = 0; i < toolbar.getChildCount(); i++) {

            View view = toolbar.getChildAt(i);
            if (view instanceof TextView) {

                TextView textView = (TextView) view;
                textView.setGravity(Gravity.CENTER);
                textView.setTextSize(context.getResources().getDimension(R.dimen.font_tiny));
                textView.setPadding(0, 0, 0, Constants.TOOLBAR_TITLE_PADDING_BOTTOM);
            }
        }
    }

    public static String listToString(List<String> list, String separator) {

        StringBuilder result = new StringBuilder();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {

                result.append(list.get(i));
                result.append(separator);
            }
        }
        return result.length() == 0 ? null : result.substring(0, result.length() - separator.length());
    }

    public static NumberPicker getPicker(Context context, String[] values) {

        NumberPicker picker = new NumberPicker(context);
        picker.setMinValue(0);
        picker.setMaxValue(values.length - 1);
        picker.setWrapSelectorWheel(true);
        picker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        picker.setDisplayedValues(values);
        return picker;
    }

    public static LinearLayout.LayoutParams getPickerParams() {

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.weight = 1f;
        return params;
    }

    public static int getValuePositionInArray(String value, String[] values) {

        int i = 0;
        while (i < values.length) {

            if (values[i].equals(value)) {
                break;
            }
            i++;
        }
        return i % values.length;
    }

    public static <T> MutableLiveData<List<T>> newMutableEmptyList() {

        MutableLiveData<List<T>> list = new MutableLiveData<>();
        list.setValue(new ArrayList<>());
        return list;
    }

    public static <T> List<T> addElementsToList(List<T> list,
                                                List<T> elementsToAdd,
                                                boolean removeLast) {

        if (list == null) {
            list = new ArrayList<>();
        } else if (list.size() > 0 && removeLast) {
            list.remove(list.size() - 1);
        }
        list.addAll(elementsToAdd);
        return list;
    }

    //MARK: - Game detail

    public static int MAX_LINES = Integer.MAX_VALUE;
    public static int STORE_BUTTON_SEPARATOR_WIDTH = 40;
    public static int STORE_BUTTON_HEIGHT = 50;
    public static String EMPTY_VALUE = "";
    public static String NO_VALUE = "-";
    public static String NEXT_VALUE_SEPARATOR = ", ";

    public static int getStoreImageId(String storeId) {

        switch (storeId) {
            case "steam":
                return R.drawable.steam;
            case "playstation-store":
                return R.drawable.playstation_store;
            case "xbox-store": case "xbox360":
                return R.drawable.xbox_store;
            case "apple-appstore":
                return R.drawable.apple_store;
            case "gog":
                return R.drawable.gog;
            case "nintendo":
                return R.drawable.nintendo_eshop;
            case "google-play":
                return R.drawable.google_play;
            case "itch":
                return R.drawable.itchio;
            case "epic-games":
                return R.drawable.epic_games;
            default:
                return 0;
        }
    }

    public static RoundedBitmapDrawable getRoundImageView(Drawable image, Context context, float radius) {

        Bitmap imageBitmap = ((BitmapDrawable) image).getBitmap();
        RoundedBitmapDrawable imageDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), imageBitmap);
        imageDrawable.setCircular(true);
        if (radius > 0) {
            imageDrawable.setCornerRadius(radius);
        } else {
            imageDrawable.setCornerRadius(Math.max(imageBitmap.getWidth(), imageBitmap.getHeight()) / 2.0f);
        }
        return imageDrawable;
    }

    //MARK: - Video

    public static String FRAME_VIDEO = "<html><body><br><iframe width=\"320\" height=\"200\" src=\"%s\" frameborder=\"0\" allowfullscreen></iframe></body></html>";
    public static String FRAME_VIDEO_MIME_TYPE = "text/html";
    public static String FRAME_VIDEO_ENCODING = "utf-8";
}
