package com.spade.mazda.ui.profile.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.spade.mazda.CustomViews.CustomTextView;
import com.spade.mazda.R;
import com.spade.mazda.ui.profile.model.History;
import com.spade.mazda.utils.GlideApp;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 12/28/17.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private Context context;
    private List<History> histories;

    public HistoryAdapter(Context context, List<History> histories) {
        this.context = context;
        this.histories = histories;
    }

    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_history_layout, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HistoryViewHolder holder, int position) {
        History history = histories.get(position);
        holder.commentTextView.setText(history.getHistoryComment());

        if (history.getHistoryImage() != null) {
            holder.historyImage.setVisibility(View.VISIBLE);
            RequestOptions requestOptions = new RequestOptions();
            requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(16));
            GlideApp.with(context).applyDefaultRequestOptions(requestOptions).load(history.getHistoryImage())
                    .into(holder.historyImage);
        } else {
            holder.historyImage.setVisibility(View.GONE);
        }

        if (position == 0) {
            holder.separator1.setVisibility(View.INVISIBLE);
        } else if (position == histories.size() - 1) {
            holder.separator2.setVisibility(View.INVISIBLE);
        } else {
            holder.separator1.setVisibility(View.VISIBLE);
            holder.separator2.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return histories.size();
    }

    class HistoryViewHolder extends RecyclerView.ViewHolder {
        private CustomTextView dateTextView, commentTextView;
        private ImageView historyImage;
        private View separator1, separator2;

        public HistoryViewHolder(View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.history_date_text_view);
            commentTextView = itemView.findViewById(R.id.history_comment);
            historyImage = itemView.findViewById(R.id.history_image);
            separator1 = itemView.findViewById(R.id.separator_1);
            separator2 = itemView.findViewById(R.id.separator_2);
        }
    }
}
