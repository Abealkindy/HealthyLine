package com.rosinante24.healthyline.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.rosinante24.healthyline.R;
import com.rosinante24.healthyline.adapter.AmbulanceAdapter;
import com.rosinante24.healthyline.network.RetrofitConfig;
import com.rosinante24.healthyline.network.ServiceApi;
import com.rosinante24.healthyline.response.AmbulanceResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class AmbulanceFragment extends Fragment {


    @BindView(R.id.recycler_ambulance)
    RecyclerView recyclerAmbulance;
    Unbinder unbinder;
    @BindView(R.id.progress_ambulance)
    ProgressBar progressAmbulance;
    @BindView(R.id.buttonrefresh)
    Button buttonrefresh;
    @BindView(R.id.linearerror)
    LinearLayout linearerror;
    @BindView(R.id.linearnormal)
    LinearLayout linearnormal;

    public AmbulanceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ambulance, container, false);
        // Inflate the layout for this fragment
        getDataAmbulance();
        unbinder = ButterKnife.bind(this, view);
        buttonrefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDataAmbulance();
            }
        });
        return view;
    }

    private void getDataAmbulance() {
        ServiceApi api = RetrofitConfig.getInstance();
        Call<AmbulanceResponse> ambulanceResponseCall = api.getAmbulance();
        ambulanceResponseCall.enqueue(new Callback<AmbulanceResponse>() {
            @Override
            public void onResponse(Call<AmbulanceResponse> call, Response<AmbulanceResponse> response) {
                progressAmbulance.setVisibility(View.VISIBLE);
                if (response.isSuccessful()) {
                    progressAmbulance.setVisibility(View.GONE);
                    linearnormal.setVisibility(View.VISIBLE);
                    recyclerAmbulance.setAdapter(new AmbulanceAdapter(getActivity(), response.body().getVEHICLE().getDATA()));
                    Log.d("Data: ", response.body().getVEHICLE().getDATA().toString());
                } else {
                    linearerror.setVisibility(View.VISIBLE);
                    linearnormal.setVisibility(View.GONE);
                    progressAmbulance.setVisibility(View.GONE);
                    Toast.makeText(getActivity(), "Najaaay", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AmbulanceResponse> call, Throwable t) {
                try {
                    progressAmbulance.setVisibility(View.GONE);
                    linearerror.setVisibility(View.VISIBLE);
                    linearnormal.setVisibility(View.GONE);
                } catch (NullPointerException e) {
                    progressAmbulance.setVisibility(View.GONE);
                    linearerror.setVisibility(View.VISIBLE);
                    linearnormal.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
