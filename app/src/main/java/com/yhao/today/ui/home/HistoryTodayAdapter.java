package com.yhao.today.ui.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yhao.today.R;
import com.yhao.today.pojo.HistoryToday;
import com.yhao.today.ui.OnItemClickListener;

import java.util.List;

/**
 * Created by yhao on 2017/11/25.
 * https://github.com/yhaolpz
 */

public class HistoryTodayAdapter extends RecyclerView.Adapter<HistoryTodayAdapter.HistoryTodayViewHolder> {

    private List<HistoryToday> mData;

    public HistoryTodayAdapter(List<HistoryToday> data) {
        mData = data;
    }

    @Override
    public HistoryTodayAdapter.HistoryTodayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HistoryTodayViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_history_today, parent, false));
    }

    @Override
    public void onBindViewHolder(HistoryTodayAdapter.HistoryTodayViewHolder holder, int position) {
        HistoryToday historyToday = mData.get(position);
        holder.dateTv.setText(historyToday.getYear() + "." + historyToday.getMonth() + "." + historyToday.getDay());
        holder.infoTv.setText(historyToday.getTitle());
        Glide.with(holder.itemView.getContext()).load(historyToday.getImg())
                .placeholder(R.drawable.history_today_place_holder).into(holder.historyTodayIv);
        holder.historyTodayItemFloatView.setOnClickListener(v -> {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    class HistoryTodayViewHolder extends RecyclerView.ViewHolder {

        ImageView historyTodayIv;
        TextView dateTv;
        TextView infoTv;
        View historyTodayItemFloatView;

        public HistoryTodayViewHolder(View itemView) {
            super(itemView);
            historyTodayIv = itemView.findViewById(R.id.historyTodayIv);
            dateTv = itemView.findViewById(R.id.dateTv);
            infoTv = itemView.findViewById(R.id.infoTv);
            historyTodayItemFloatView = itemView.findViewById(R.id.historyTodayItemFloatView);
        }
    }

}
