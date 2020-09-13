/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 8/9/2020
 */

package es.upsa.mimo.gamerdb.viewholders;

import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import es.upsa.mimo.gamerdb.R;
import es.upsa.mimo.gamerdb.adapters.GamesAdapter;

public class LoadMoreItemsViewHolder extends RecyclerView.ViewHolder {

    public LoadMoreItemsViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void setItem(GamesAdapter.OnItemClickListener onItemClickListener) {

        Button btLoadMoreItems = itemView.findViewById(R.id.button_load_more_items);
        btLoadMoreItems.setOnClickListener(v -> onItemClickListener.onLoadMoreItemsClick());
    }
}
