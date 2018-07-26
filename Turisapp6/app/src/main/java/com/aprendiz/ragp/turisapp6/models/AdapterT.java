package com.aprendiz.ragp.turisapp6.models;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class AdapterT extends RecyclerView.Adapter<AdapterT.Holder>{
    List<Lugares> lugaresList = new ArrayList<>();
    Context context;
    int item;
    private OnItemClickListener mlisterner;
    public interface OnItemClickListener{
        void itemClick(int position);
    }

    public AdapterT(List<Lugares> lugaresList, Context context, int item) {
        this.lugaresList = lugaresList;
        this.context = context;
        this.item = item;
    }

    public void setMlisterner(OnItemClickListener mlisterner) {
        this.mlisterner = mlisterner;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(item, parent,false);
        Holder holder = new Holder(view,mlisterner);

        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return lugaresList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        public Holder(View itemView, final OnItemClickListener listerner) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listerner!=null){
                        int position = getLayoutPosition();
                        if (position!=RecyclerView.NO_POSITION){
                            listerner.itemClick(position);
                        }
                    }
                }
            });
        }

        public void connectData(){

        }
    }
}
