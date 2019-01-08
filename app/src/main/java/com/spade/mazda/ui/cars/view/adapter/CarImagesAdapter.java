package com.spade.mazda.ui.cars.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.spade.mazda.R;
import com.spade.mazda.ui.cars.model.Gallery;
import com.spade.mazda.utils.GlideApp;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 12/5/17.
 */

public class CarImagesAdapter extends RecyclerView.Adapter<CarImagesAdapter.GalleryViewHolder> {

    private Context context;
    private List<Gallery> galleryList;

    public CarImagesAdapter(Context context, List<Gallery> galleryList) {
        this.context = context;
        this.galleryList = galleryList;
    }

    @Override
    public GalleryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.gallery_item, parent, false);
        return new GalleryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GalleryViewHolder holder, int position) {
        Gallery gallery = galleryList.get(position);
        String imageUrl = gallery.getName();
        GlideApp.with(context).load(imageUrl).into(holder.galleryImage);
    }

    @Override
    public int getItemCount() {
        return galleryList.size();
    }

    class GalleryViewHolder extends RecyclerView.ViewHolder {
        private ImageView galleryImage;

        public GalleryViewHolder(View itemView) {
            super(itemView);
            galleryImage = itemView.findViewById(R.id.gallery_image);
        }
    }
}
