/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 12/8/2020
 */

package es.upsa.mimo.gamerdb.viewholders;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.viewpagerindicator.LinePageIndicator;
import java.util.ArrayList;
import java.util.List;
import es.upsa.mimo.gamerdb.R;
import es.upsa.mimo.gamerdb.adapters.OnItemClickListener;
import es.upsa.mimo.gamerdb.adapters.ImageSliderAdapter;
import es.upsa.mimo.gamerdb.models.GameResponse;
import es.upsa.mimo.gamerdb.models.ScreenshotResponse;
import es.upsa.mimo.gamerdb.utils.Constants;

public class GamesViewHolder extends RecyclerView.ViewHolder {

    private View itemView;

    public GamesViewHolder(@NonNull View itemView) {

        super(itemView);
        this.itemView = itemView;
    }

    public void fillData(GameResponse game,
                         int position,
                         Context context,
                         OnItemClickListener onItemClickListener,
                         boolean multiImage) {

        ViewPager vpImages = itemView.findViewById(R.id.view_pager_images);
        TextView tvName = itemView.findViewById(R.id.text_view_name);
        TextView tvRating = itemView.findViewById(R.id.text_view_rating);
        ConstraintLayout clIndicator = itemView.findViewById(R.id.constraint_layout_indicator);
        LinePageIndicator indicator = itemView.findViewById(R.id.view_pager_indicator);

        List<ScreenshotResponse> screenshots = game.getShortScreenshots();
        List<String> images = new ArrayList<>();
        if (screenshots != null) {
            for (int i=0; i< screenshots.size(); i++) {

                ScreenshotResponse screenshot = screenshots.get(i);
                images.add(screenshot.getImage());
            }
        }
        if (images.isEmpty()) {
            images.add(Constants.EMPTY_VALUE);
        } else if (!multiImage) {
            
            String firstImageUrl = images.get(0);
            images.clear();
            images.add(firstImageUrl);
        }

        clIndicator.setVisibility(images.size() > 1 ? View.VISIBLE : View.GONE);

        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) vpImages.getLayoutParams();
        lp.topMargin = (position == 0 && multiImage) ? Constants.MARGIN_LIST : Constants.NO_MARGIN_LIST;

        vpImages.setAdapter(
                new ImageSliderAdapter(
                        game.getId(),
                        images,
                        context,
                        onItemClickListener
                )
        );
        tvName.setText(game.getName());
        tvRating.setText(String.valueOf(game.getRating()));
        indicator.setViewPager(vpImages);
        indicator.setCurrentItem(0);
        indicator.setVisibility(
                images.size() > 1 ? View.VISIBLE : View.GONE
        );
    }
}