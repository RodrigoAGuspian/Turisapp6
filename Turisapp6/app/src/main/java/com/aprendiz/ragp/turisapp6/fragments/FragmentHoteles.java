package com.aprendiz.ragp.turisapp6.fragments;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aprendiz.ragp.turisapp6.R;
import com.aprendiz.ragp.turisapp6.controllers.Detalle;
import com.aprendiz.ragp.turisapp6.controllers.MenuT;
import com.aprendiz.ragp.turisapp6.models.AdapterT;
import com.aprendiz.ragp.turisapp6.models.GestorDB;
import com.aprendiz.ragp.turisapp6.models.Lugares;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentHoteles#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentHoteles extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView recyclerView;

    int position;
    public static Lugares lugares = new Lugares();
    int valor;

    public FragmentHoteles() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentHoteles.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentHoteles newInstance(String param1, String param2) {
        FragmentHoteles fragment = new FragmentHoteles();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_hoteles, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        inputAdapter(view);

        return view;
    }

    private void inputAdapter(View view) {
        GestorDB gestorDB = new GestorDB(getContext());
        final List<Lugares> lugaresList = gestorDB.hotelesList();
        position= getActivity().getWindowManager().getDefaultDisplay().getRotation();
        if (position== Surface.ROTATION_0 || position==Surface.ROTATION_180) {
            if (valor==0) {
                AdapterT adapterT = new AdapterT(lugaresList, getContext(), R.layout.item_list);
                recyclerView.setAdapter(adapterT);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                recyclerView.setHasFixedSize(true);
                adapterT.setMlisterner(new AdapterT.OnItemClickListener() {
                    @Override
                    public void itemClick(int position) {
                        lugares = lugaresList.get(position);
                        MenuT.lugares =lugares;
                        Intent intent = new Intent(getActivity(), Detalle.class);
                        startActivity(intent);
                    }
                });
            }else {

                AdapterT adapterT = new AdapterT(lugaresList, getContext(), R.layout.item_list);
                recyclerView.setAdapter(adapterT);
                recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2,GridLayoutManager.VERTICAL, false));
                recyclerView.setHasFixedSize(true);
                adapterT.setMlisterner(new AdapterT.OnItemClickListener() {
                    @Override
                    public void itemClick(int position) {
                        lugares = lugaresList.get(position);
                        MenuT.lugares =lugares;
                        Intent intent = new Intent(getActivity(), Detalle.class);
                        startActivity(intent);
                    }
                });
            }
        }

        if (position== Surface.ROTATION_270 || position==Surface.ROTATION_90) {
            final ImageView imagenL = view.findViewById(R.id.imagenLand);
            final TextView txtDescripcion = view.findViewById(R.id.txtDescripcionLand);

            AdapterT adapterT = new AdapterT(lugaresList, getContext(), R.layout.item_land);
            recyclerView.setAdapter(adapterT);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            recyclerView.setHasFixedSize(true);
            adapterT.setMlisterner(new AdapterT.OnItemClickListener() {
                @Override
                public void itemClick(int position) {
                    lugares = lugaresList.get(position);
                    BitmapFactory.Options op = new BitmapFactory.Options();
                    op.inSampleSize=3;
                    Bitmap bitmap = BitmapFactory.decodeResource(getActivity().getResources(),lugares.getImagen(),op);
                    imagenL.setImageBitmap(bitmap);
                    txtDescripcion.setText(lugares.getDescripcion());
                }
            });
        }

    }

}
