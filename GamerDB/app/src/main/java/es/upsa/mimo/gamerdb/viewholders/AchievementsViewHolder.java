/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 18/9/2020
 */

package es.upsa.mimo.gamerdb.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import es.upsa.mimo.gamerdb.R;
import es.upsa.mimo.gamerdb.customviews.ImageLoading;
import es.upsa.mimo.gamerdb.models.AchievementResponse;
import es.upsa.mimo.gamerdb.utils.Constants;

public class AchievementsViewHolder extends RecyclerView.ViewHolder {

    private View itemView;

    public AchievementsViewHolder(@NonNull View itemView) {

        super(itemView);
        this.itemView = itemView;
    }

    public void fillData(AchievementResponse achievement, Context context) {

        ImageView ivAchievement = itemView.findViewById(R.id.image_view_achievement);
        ImageLoading loading = itemView.findViewById(R.id.image_loading_achievement);
        TextView tvPercent = itemView.findViewById(R.id.text_view_percent);
        TextView tvName = itemView.findViewById(R.id.text_view_name);
        TextView tvDescription = itemView.findViewById(R.id.text_view_description);

        loading.setVisibility(View.VISIBLE);
        String achievementImage = achievement.getImage();
        if (achievementImage == null) {

            loading.setVisibility(View.GONE);
            ivAchievement.setImageResource(R.drawable.error_image_2);
        } else {

            Picasso
                    .get()
                    .load(achievementImage)
                    .fit()
                    .centerCrop()
                    .error(R.drawable.error_image)
                    .into(ivAchievement, new Callback() {
                        @Override
                        public void onSuccess() {

                            ivAchievement.setImageDrawable(Constants.getRoundImageView(
                                    ivAchievement.getDrawable(),
                                    context,
                                    context.getResources().getDimension(R.dimen.size_semi_huge))
                            );
                            loading.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError(Exception e) {

                            ivAchievement.setImageResource(R.drawable.error_image_2);
                            loading.setVisibility(View.GONE);
                        }
                    });
        }

        String percent = context.getResources().getString(R.string.achievement_percent, achievement.getPercent());
        tvPercent.setText(percent);

        tvName.setText(achievement.getName());

        tvDescription.setText(achievement.getDescription());
    }
}
