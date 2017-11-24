package com.rosinante24.healthyline.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.rosinante24.healthyline.R;
import com.rosinante24.healthyline.adapter.RumahSakitKhususAdapter;
import com.rosinante24.healthyline.network.RetrofitConfig;
import com.rosinante24.healthyline.network.ServiceApi;
import com.rosinante24.healthyline.response.RumahSakitKhususResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RumahSakitKhususActivity extends AppCompatActivity {

    @BindView(R.id.recycler_rumahsakitkhusus)
    RecyclerView recyclerRumahsakitkhusus;
    @BindView(R.id.progress_rumahsakitkhusus)
    ProgressBar progressRumahsakitkhusus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rumah_sakit_khusus);
        ButterKnife.bind(this);
        setTitle("Daftar Rumah Sakit Khusus");
        ServiceApi api = RetrofitConfig.getInstance();
        Call<RumahSakitKhususResponse> responseCall = api.getRumahSakitKhusus();
        responseCall.enqueue(new Callback<RumahSakitKhususResponse>() {
            @Override
            public void onResponse(Call<RumahSakitKhususResponse> call, Response<RumahSakitKhususResponse> response) {
                progressRumahsakitkhusus.setVisibility(View.VISIBLE);
                if (response.isSuccessful()) {
                    progressRumahsakitkhusus.setVisibility(View.GONE);
                    recyclerRumahsakitkhusus.setAdapter(new RumahSakitKhususAdapter(getApplicationContext(), response.body().getData()));
                }
            }

            @Override
            public void onFailure(Call<RumahSakitKhususResponse> call, Throwable t) {

            }
        });
    }
}
