package com.rosinante24.healthyline.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.rosinante24.healthyline.R;
import com.rosinante24.healthyline.activities.RumahSakitKhususActivity;
import com.rosinante24.healthyline.activities.RumahSakitUmumActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class HospitalFragment extends Fragment {


    @BindView(R.id.imagerumahsakitumum)
    ImageView imagerumahsakitumum;
    @BindView(R.id.rumahsakitumum)
    LinearLayout rumahsakitumum;
    @BindView(R.id.imagerumahsakitkhusus)
    ImageView imagerumahsakitkhusus;
    @BindView(R.id.rumahsakitkhusus)
    LinearLayout rumahsakitkhusus;
    @BindView(R.id.lineargambartulisan)
    LinearLayout lineargambartulisan;
    Unbinder unbinder;

    public HospitalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hospital, container, false);
        // Inflate the layout for this fragment
        unbinder = ButterKnife.bind(this, view);
        imagerumahsakitumum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), RumahSakitUmumActivity.class));
            }
        });
        imagerumahsakitkhusus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), RumahSakitKhususActivity.class));
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
