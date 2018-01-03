package com.spade.mazda.ui.general.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spade.mazda.CustomViews.CustomTextView;
import com.spade.mazda.R;
import com.spade.mazda.ui.general.view.interfaces.InterestRatesInterface;
import com.spade.mazda.ui.services.model.InterestRates;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 12/21/17.
 */

public class YearsNumberAdapter extends RecyclerView.Adapter<YearsNumberAdapter.RatesViewHolder> {

    private List<InterestRates> interestRatesList;
    private Context context;
    private InterestRatesInterface interestRatesInterface;

    public YearsNumberAdapter(List<InterestRates> interestRatesList, Context context) {
        this.interestRatesList = interestRatesList;
        this.context = context;
    }

    @Override
    public RatesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.spinner_item, parent, false);
        return new RatesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RatesViewHolder holder, int position) {
        InterestRates rates = interestRatesList.get(position);
        holder.itemTitle.setText(String.valueOf(rates.getYearNumber()));
        holder.itemView.setOnClickListener(view -> interestRatesInterface.onRateSelected(rates));
    }

    @Override
    public int getItemCount() {
        return interestRatesList.size();
    }

    public void setRatesActions(InterestRatesInterface interestRatesInterface) {
        this.interestRatesInterface = interestRatesInterface;
    }

    class RatesViewHolder extends RecyclerView.ViewHolder {
        CustomTextView itemTitle;

        public RatesViewHolder(View itemView) {
            super(itemView);
            itemTitle = itemView.findViewById(R.id.item_title);
        }
    }
}
