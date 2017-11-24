package com.rosinante24.healthyline.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.rosinante24.healthyline.R;
import com.rosinante24.healthyline.adapter.PuskesmasAdapter;
import com.rosinante24.healthyline.network.RetrofitConfig;
import com.rosinante24.healthyline.network.ServiceApi;
import com.rosinante24.healthyline.response.PuskesmasResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PuskesmasFragment extends Fragment {


    @BindView(R.id.recycler_puskesmas)
    RecyclerView recyclerPuskesmas;
    Unbinder unbinder;
    @BindView(R.id.progress_puskesmas)
    ProgressBar progressPuskesmas;

    public PuskesmasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_puskesmas, container, false);
        // Inflate the layout for this fragment
        getDataPuskesmas();
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    private void getDataPuskesmas() {
        ServiceApi api = RetrofitConfig.getInstance();
        Call<PuskesmasResponse> puskesmasResponseCall = api.getPuskesmas();
        puskesmasResponseCall.enqueue(new Callback<PuskesmasResponse>() {
            @Override
            public void onResponse(Call<PuskesmasResponse> call, Response<PuskesmasResponse> response) {
                progressPuskesmas.setVisibility(View.VISIBLE);
                if (response.isSuccessful()) {
                    progressPuskesmas.setVisibility(View.GONE);
                    recyclerPuskesmas.setAdapter(new PuskesmasAdapter(getActivity(), response.body().getData()));
                    Log.d("Data : ", response.body().getData().toString());
                }

            }

            @Override
            public void onFailure(Call<PuskesmasResponse> call, Throwable t) {
                progressPuskesmas.setVisibility(View.GONE);
                Log.d("Data: ", t.getMessage());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
