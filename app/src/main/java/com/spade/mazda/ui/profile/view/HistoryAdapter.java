package com.spade.mazda.ui.profile.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.spade.mazda.CustomViews.CustomTextView;
import com.spade.mazda.R;
import com.spade.mazda.ui.profile.model.History;

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

//        if (history.getHistoryImage() != null) {
//            holder.historyImage.setVisibility(View.VISIBLE);
//            RequestOptions requestOptions = new RequestOptions();
//            requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(16));
//            GlideApp.with(context).applyDefaultRequestOptions(requestOptions).load(history.getHistoryImage())
//                    .into(holder.historyImage);
//        } else {
//            holder.historyImage.setVisibility(View.GONE);
//        }


        holder.serviceAdvisor.setText(history.getServiceAdvisor().trim());
        holder.taskStatus.setText(history.getTaskStatus());
        holder.netAmount.setText(context.getResources().getString(R.string.net_amount)+history.getNetAmount());
        holder.netKm.setText(context.getResources().getString(R.string.km)+history.getKm());


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
        private CustomTextView serviceAdvisor, taskStatus,netKm,netAmount;
        private ImageView historyImage;
        private View separator1, separator2;

        public HistoryViewHolder(View itemView) {
            super(itemView);
            serviceAdvisor = itemView.findViewById(R.id.service_adviser);
            taskStatus = itemView.findViewById(R.id.task_status);
            netKm = itemView.findViewById(R.id.km);
            netAmount = itemView.findViewById(R.id.net_amount);

//            historyImage = itemView.findViewById(R.id.history_image);
            separator1 = itemView.findViewById(R.id.separator_1);
            separator2 = itemView.findViewById(R.id.separator_2);
        }
    }
}
