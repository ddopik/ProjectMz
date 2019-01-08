
package com.spade.mazda.ui.cars.model;

import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CarDetailsData implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("gallery")
    @Expose
    private List<Gallery> gallery = null;
    @SerializedName("video")
    @Expose
    private String video;
    @SerializedName("categories")
    @Expose
    private List<Category> categories = null;
    public final static Creator<CarDetailsData> CREATOR = new Creator<CarDetailsData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CarDetailsData createFromParcel(Parcel in) {
            return new CarDetailsData(in);
        }

        public CarDetailsData[] newArray(int size) {
            return (new CarDetailsData[size]);
        }

    };

    protected CarDetailsData(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
        this.image = ((String) in.readValue((String.class.getClassLoader())));
        this.price = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.gallery, (com.spade.mazda.ui.cars.model.Gallery.class.getClassLoader()));
        this.video = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.categories, (Category.class.getClassLoader()));
    }

    public CarDetailsData() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<Gallery> getGallery() {
        return gallery;
    }

    public void setGallery(List<Gallery> gallery) {
        this.gallery = gallery;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(description);
        dest.writeValue(image);
        dest.writeValue(price);
        dest.writeList(gallery);
        dest.writeValue(video);
        dest.writeList(categories);
    }

    public int describeContents() {
        return 0;
    }

}
