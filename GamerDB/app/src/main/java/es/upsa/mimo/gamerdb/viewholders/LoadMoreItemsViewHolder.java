/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 8/9/2020
 */

package es.upsa.mimo.gamerdb.viewholders;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import es.upsa.mimo.gamerdb.R;
import es.upsa.mimo.gamerdb.adapters.OnItemClickListener;

public class LoadMoreItemsViewHolder extends RecyclerView.ViewHolder {

    public LoadMoreItemsViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void setItem(OnItemClickListener onItemClickListener, boolean multiImage) {

        ConstraintLayout clLoadMoreItems = itemView.findViewById(R.id.constraint_layout_load_more_items);
        int width = multiImage ? ViewGroup.LayoutParams.MATCH_PARENT : ViewGroup.LayoutParams.WRAP_CONTENT;
        int height = multiImage ? ViewGroup.LayoutParams.WRAP_CONTENT : ViewGroup.LayoutParams.MATCH_PARENT;
        clLoadMoreItems.setLayoutParams(new ViewGroup.LayoutParams(width, height));

        Button btLoadMoreItems = itemView.findViewById(R.id.button_load_more_items);
        btLoadMoreItems.setOnClickListener(onItemClickListener::onLoadMoreItemsClick);
    }
}
