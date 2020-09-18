/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 18/9/2020
 */

package es.upsa.mimo.gamerdb.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import es.upsa.mimo.gamerdb.R;
import es.upsa.mimo.gamerdb.models.AchievementResponse;
import es.upsa.mimo.gamerdb.viewholders.AchievementsViewHolder;
import es.upsa.mimo.gamerdb.viewholders.LoadMoreItemsViewHolder;

public class AchievementsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //MARK: - Private properties

    private List<AchievementResponse> achievements;
    private OnItemClickListener onItemClickListener;
    private Context context;

    //MARK: - Lifecycle methods

    public AchievementsAdapter(List<AchievementResponse> achievements,
                               OnItemClickListener onItemClickListener) {

        this.achievements = achievements;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemViewType(int position) {

        if (achievements.get(position).getId() > 0) {
            return R.layout.achievement_item;
        } else {
            return R.layout.load_more_items_item;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        this.context = parent.getContext();
        View itemView = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        RecyclerView.ViewHolder holder;
        if (viewType == R.layout.achievement_item) {
            holder = new AchievementsViewHolder(itemView);
        } else {
            holder = new LoadMoreItemsViewHolder(itemView);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof AchievementsViewHolder) {

            AchievementsViewHolder achievementsViewHolder = (AchievementsViewHolder) holder;
            AchievementResponse achievement = achievements.get(position);
            achievementsViewHolder.fillData(achievement, context);
        } else {

            LoadMoreItemsViewHolder loadMoreItemsViewHolder = (LoadMoreItemsViewHolder) holder;
            loadMoreItemsViewHolder.setItem(onItemClickListener, false);
        }
    }

    @Override
    public int getItemCount() {

        if (achievements == null) {
            return 0;
        } else {
            return achievements.size();
        }
    }

    //MARK: - Public methods

    public void setAchievements(List<AchievementResponse> newAchievements) {

        int position = this.achievements.size();
        this.achievements = newAchievements;
        notifyItemInserted(position);
    }

    public void resetList() {

        this.achievements = new ArrayList<>();
        notifyDataSetChanged();
    }
}
