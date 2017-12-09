package com.spade.mazda.ui.authentication.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.spade.mazda.R;
import com.spade.mazda.utils.GlideApp;

import java.io.File;
import java.util.List;

/**
 * Created by Ayman Abouzeid on 11/16/17.
 */

public class NationalIDsAdapter extends RecyclerView.Adapter<NationalIDsAdapter.NationalViewHolder> {

    private Context context;
    private NationalImageView nationalImageView;
    private List<File> files;

    public NationalIDsAdapter(Context context, List<File> files) {
        this.context = context;
        this.files = files;
    }

//    public NationalIDsAdapter(Context context, List<Uri> uriList) {
//        this.context = context;
//        this.uriList = uriList;
//    }

    @Override
    public NationalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.image_item, parent, false);
        return new NationalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NationalViewHolder holder, int position) {
        Uri uri = Uri.fromFile(files.get(position));
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(16));
        GlideApp.with(context).applyDefaultRequestOptions(requestOptions).load(uri).into(holder.imageView);
        holder.deleteImageView.setOnClickListener(view -> nationalImageView.onClearClicked(files.get(position)));
    }

    @Override
    public int getItemCount() {
        return files.size();
    }

    public void setNationalImageView(NationalImageView nationalImageView) {
        this.nationalImageView = nationalImageView;
    }

    public interface NationalImageView {
        void onClearClicked(File file);
    }

    class NationalViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView, deleteImageView;

        public NationalViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.photo_image_view);
            deleteImageView = itemView.findViewById(R.id.delete_image_view);
        }
    }
}
