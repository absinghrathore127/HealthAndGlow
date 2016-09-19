package com.healthandglow;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;



public class GridAdapter extends RecyclerView.Adapter<GridAdapter.VaultViewHolder> {
     boolean isPressed = false;
    ArrayList<FilesItem> filesItemList;
    Context con;

    public static class VaultViewHolder extends RecyclerView.ViewHolder {

        private ImageView img_thumbnail;
        private TextView product_name, offer_price, product_price;
        private ImageButton btn_favourite;


        public VaultViewHolder(final View itemView) {
            super(itemView);

            product_name = (TextView) itemView.findViewById(R.id.product_name);
            offer_price = (TextView) itemView.findViewById(R.id.offer_price);
            product_price = (TextView) itemView.findViewById(R.id.product_price);
            btn_favourite = (ImageButton) itemView.findViewById(R.id.btn_favourite);
            img_thumbnail = (ImageView) itemView.findViewById(R.id.img_thumbnail);
        }

    }

    GridAdapter(Context Con, ArrayList<FilesItem> filesitemList) {
        this.con = Con;
        filesItemList = filesitemList;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public VaultViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.file_card_item, viewGroup, false);
        return new VaultViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final VaultViewHolder vaultViewHolder, final int position) {

        final FilesItem homeItem = filesItemList.get(position);


        // vaultViewHolder.img_thumbnail
        vaultViewHolder.product_name.setText(homeItem.getSkuName());
        vaultViewHolder.offer_price.setText(homeItem.getSkuOfferPrice());
        vaultViewHolder.product_price.setText(homeItem.getSkuPrice());
        vaultViewHolder.product_price.setPaintFlags(vaultViewHolder.product_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        vaultViewHolder.btn_favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isPressed) {
                    vaultViewHolder.btn_favourite.setImageResource(R.drawable.dislike);
                    isPressed = false;

                } else {
                    vaultViewHolder.btn_favourite.setImageResource(R.drawable.like);
                    isPressed = true;
                }


            }
        });


        Picasso.with(con)
                .load("http://image3.mouthshut.com/images/imagesp/925664665s.png") // homeItem.getPhoto()
                .into(vaultViewHolder.img_thumbnail);
    }

    @Override
    public int getItemCount() {
        return filesItemList == null ? 0 : filesItemList.size();
    }
}