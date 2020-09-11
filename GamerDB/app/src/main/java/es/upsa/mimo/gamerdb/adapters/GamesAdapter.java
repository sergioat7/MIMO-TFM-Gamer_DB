/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 12/8/2020
 */

package es.upsa.mimo.gamerdb.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import java.util.ArrayList;
import java.util.List;
import es.upsa.mimo.gamerdb.R;
import es.upsa.mimo.gamerdb.models.GameResponse;
import es.upsa.mimo.gamerdb.utils.Constants;
import es.upsa.mimo.gamerdb.viewholders.GamesViewHolder;
import es.upsa.mimo.gamerdb.viewholders.LoadMoreItemsViewHolder;

public class GamesAdapter extends RecyclerView.Adapter<ViewHolder> {

    //MARK: - Private properties

    private List<GameResponse> games;
    private OnItemClickListener onItemClickListener;
    private Context context;
    private boolean showLoadMoreItems;

    //MARK: - Lifecycle methods

    public GamesAdapter(List<GameResponse> games, OnItemClickListener onItemClickListener) {

        this.games = games;
        this.onItemClickListener = onItemClickListener;
        this.showLoadMoreItems = true;
    }

    @Override
    public int getItemViewType(int position) {

        if (position < games.size()) {
            return R.layout.game_item;
        } else {
            return R.layout.load_more_items_item;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        this.context = parent.getContext();
        View itemView = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        ViewHolder holder;
        if (viewType == R.layout.game_item) {
            holder = new GamesViewHolder(itemView);
        } else {
            holder = new LoadMoreItemsViewHolder(itemView);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if (holder instanceof GamesViewHolder) {

            GameResponse game = games.get(position);
            ((GamesViewHolder) holder).fillData(game, position, context, onItemClickListener);
        } else {
            ((LoadMoreItemsViewHolder) holder).setItem(onItemClickListener);
        }
    }

    @Override
    public int getItemCount() {

        if (games.size() == 0) {
            return 0;
        } else if (showLoadMoreItems) {
            return games.size() + 1;
        } else {
            return games.size();
        }
    }

    //MARK: - Interface methods

    public interface OnItemClickListener {
        void onItemClick(int gameId);
        void onLoadMoreItemsClick();
    }

    //MARK: - Public methods

    public void setGames(List<GameResponse> newGames) {

        int position = this.games.size();
        this.games = newGames;
        showLoadMoreItems = (newGames.size() % Constants.PAGE_SIZE) == 0;
        notifyItemInserted(position);
    }

    public void resetList() {

        this.games = new ArrayList<>();
        this.showLoadMoreItems = true;
        notifyDataSetChanged();
    }
}