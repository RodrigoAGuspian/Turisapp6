package com.aprendiz.ragp.turisapp6.models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aprendiz.ragp.turisapp6.R;

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
        holder.connectData(lugaresList.get(position));
    }

    @Override
    public int getItemCount() {
        return lugaresList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView txtNombre;
        TextView txtUbicacion;
        TextView txtDescripcion;
        ImageView imagen = itemView.findViewById(R.id.imgItem);
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

        public void connectData(Lugares lugares){
            if (item == R.layout.item_list){
                txtNombre = itemView.findViewById(R.id.txtNombreItem);
                txtUbicacion = itemView.findViewById(R.id.txtUbicacionItem);
                txtDescripcion = itemView.findViewById(R.id.txtDescripcionItem);

                txtNombre.setText(lugares.getNombre());
                txtUbicacion.setText(lugares.getUbicacion());
                txtDescripcion.setText(lugares.getDescripcionc());

            }


            if (item == R.layout.item_grid){
                txtNombre = itemView.findViewById(R.id.txtNombreItem);
                txtUbicacion = itemView.findViewById(R.id.txtUbicacionItem);

                txtNombre.setText(lugares.getNombre());
                txtUbicacion.setText(lugares.getUbicacion());

            }


            if (item == R.layout.item_land){
                txtNombre = itemView.findViewById(R.id.txtNombreItem);

                txtNombre.setText(lugares.getNombre());

            }

            BitmapFactory.Options op = new BitmapFactory.Options();
            op.inSampleSize=6;
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),lugares.getImagen(),op);
            imagen.setImageBitmap(bitmap);
        }
    }
}
