/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 17/9/2020
 */

package es.upsa.mimo.gamerdb.viewholders;

import android.content.Context;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import es.upsa.mimo.gamerdb.R;
import es.upsa.mimo.gamerdb.customviews.ImageLoading;
import es.upsa.mimo.gamerdb.models.DeveloperResponse;
import es.upsa.mimo.gamerdb.models.PositionResponse;
import es.upsa.mimo.gamerdb.utils.Constants;

public class DevelopersViewHolder extends RecyclerView.ViewHolder {

    private View itemView;

    public DevelopersViewHolder(@NonNull View itemView) {

        super(itemView);
        this.itemView = itemView;
    }

    public void fillData(DeveloperResponse developer, Context context) {

        ImageView ivBackground = itemView.findViewById(R.id.image_view_background);
        ImageView ivDeveloper = itemView.findViewById(R.id.image_view_developer);
        ImageLoading loading = itemView.findViewById(R.id.image_loading_developer);
        TextView tvName = itemView.findViewById(R.id.text_view_name);
        TextView tvPositions = itemView.findViewById(R.id.text_view_positions);
        TextView tvGamesDeveloped = itemView.findViewById(R.id.text_view_games_developed);

        Picasso
                .get()
                .load(developer.getImageBackground())
                .fit()
                .centerCrop()
                .into(ivBackground, new Callback() {
                    @Override
                    public void onSuccess() {
                        ivBackground.setImageDrawable(Constants.getRoundImageView(
                                ivBackground.getDrawable(),
                                context,
                                Constants.IMAGE_CORNER)
                        );
                    }

                    @Override
                    public void onError(Exception e) {}
                });

        loading.setVisibility(View.VISIBLE);
        String developerImage = developer.getImage();
        if (developerImage == null) {

            loading.setVisibility(View.GONE);
            ivDeveloper.setImageResource(R.drawable.error_image_developer);
        } else {

            Picasso
                    .get()
                    .load(developerImage)
                    .fit()
                    .centerCrop()
                    .error(R.drawable.error_image)
                    .into(ivDeveloper, new Callback() {
                        @Override
                        public void onSuccess() {

                            ivDeveloper.setImageDrawable(Constants.getRoundImageView(
                                    ivDeveloper.getDrawable(),
                                    context,
                                    context.getResources().getDimension(R.dimen.size_semi_huge))
                            );
                            loading.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError(Exception e) {

                            ivDeveloper.setImageResource(R.drawable.error_image_developer);
                            loading.setVisibility(View.GONE);
                        }
                    });
        }

        SpannableString name = new SpannableString(developer.getName());
        name.setSpan(new UnderlineSpan(), 0, name.length(), 0);
        tvName.setText(name);

        List<String> positions = new ArrayList<>();
        if (developer.getPositions() != null) {

            for (PositionResponse position : developer.getPositions()) {
                positions.add(position.getName());
            }
        }
        tvPositions.setText(Constants.listToString(positions, Constants.NEXT_VALUE_SEPARATOR));

        tvGamesDeveloped.setText(String.valueOf(developer.getGamesCount()));
    }
}
