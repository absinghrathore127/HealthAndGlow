package com.healthandglow;

import android.graphics.Bitmap;

public class FilesItem {
    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String getSkuPrice() {
        return skuPrice;
    }

    public void setSkuPrice(String skuPrice) {
        this.skuPrice = skuPrice;
    }

    public String getSkuOfferPrice() {
        return skuOfferPrice;
    }

    public void setSkuOfferPrice(String skuOfferPrice) {
        this.skuOfferPrice = skuOfferPrice;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    private String skuName;
    private String skuPrice;
    private String skuOfferPrice;
    private String photo;
    private Bitmap bitmap;

}
