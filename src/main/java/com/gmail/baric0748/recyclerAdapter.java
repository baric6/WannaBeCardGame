package com.gmail.baric0748;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder>
{
    private ArrayList<cardObject> cd;

    private OnItemClickListener listener;

    private player p = player.getPlayerInstance();

    public interface OnItemClickListener
    {
        void onItemClicked(int pos);
        void onSetFavorite(int pos);
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        this.listener = listener;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        public  ImageView decImage;
        public Button addToDeck;
        public  TextView viewCardName;
        public  TextView viewCardAtk;
        public  TextView viewCardDef;

        public MyViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);

            decImage = itemView.findViewById(R.id.decImage);
            addToDeck = itemView.findViewById(R.id.addToDeck);
            viewCardName = itemView.findViewById((R.id.viewCardName));
            viewCardAtk = itemView.findViewById((R.id.viewCardAtk));
            viewCardDef = itemView.findViewById((R.id.viewCardDef));

            itemView.setOnClickListener((v)->{
                if(listener != null)
                {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION)
                    {
                        listener.onItemClicked(pos);
                    }
                }
            });

            addToDeck.setOnClickListener((v)->{
                if(listener != null)
                {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION)
                    {
                        //putting animation here make every card have the animation
                        YoYo.with(Techniques.RubberBand).playOn(itemView.findViewById(R.id.addToDeck));
                        listener.onSetFavorite(pos);
                    }
                }
            });
        }
    }

    public recyclerAdapter(ArrayList<cardObject> cd)
    {
        this.cd = cd;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_cards_recyler, viewGroup, false);
        MyViewHolder vh = new MyViewHolder(v, listener);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        cardObject current = cd.get(i);

        myViewHolder.decImage.setImageResource(current.getTexture());
        myViewHolder.viewCardName.setText("" + current.getTitle());
        myViewHolder.viewCardAtk.setText("Atk:" +current.getAttack());
        myViewHolder.viewCardDef.setText("Def:" + current.getDefence());
    }

    @Override
    public int getItemCount() {
        return cd.size();
    }

    //remove cards when the X is clicked on the card
    public void removeAt(int position) {
        cd.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, cd.size());
    }
}
