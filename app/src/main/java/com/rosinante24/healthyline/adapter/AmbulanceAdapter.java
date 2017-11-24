package com.rosinante24.healthyline.adapter;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rosinante24.healthyline.R;
import com.rosinante24.healthyline.activities.DetailAmbulanceActivity;
import com.rosinante24.healthyline.response.AmbulanceResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rosinante24 on 22/11/17.
 */

public class AmbulanceAdapter extends RecyclerView.Adapter<AmbulanceAdapter.ViewHolder> {
    private List<AmbulanceResponse.AmbulanceDatas.AmbulanceData> data;
    private FragmentActivity fragmentActivity;

    public AmbulanceAdapter(FragmentActivity activity, List<AmbulanceResponse.AmbulanceDatas.AmbulanceData> data) {
        this.data = data;
        this.fragmentActivity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(fragmentActivity).inflate(R.layout.item_ambulance, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.textnama.setText(data.get(position).getVEHICLE_TYPE());
        holder.textalamat.setText(data.get(position).getADDRESS());
        if (data.get(position).getVEHICLE_TYPE().equals("Truck")) {
            holder.imageback.setImageResource(R.drawable.trukambulance);
        } else {
            holder.imageback.setImageResource(R.drawable.agddinkes);
        }
        holder.viewRecycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(fragmentActivity.getApplicationContext(), DetailAmbulanceActivity.class);
                intent.putExtra("nama", data.get(position).getVEHICLE_TYPE());
                intent.putExtra("alamat", data.get(position).getADDRESS());
                if (data.get(position).getVEHICLE_TYPE().equals("Truck")) {
                    intent.putExtra("gambar", R.drawable.trukambulance);
                } else {
                    intent.putExtra("gambar", R.drawable.agddinkes);
                }
                intent.putExtra("latitude", data.get(position).getLATITUDE());
                intent.putExtra("longitude", data.get(position).getLONGITUDE());
                intent.putExtra("platnomer", data.get(position).getLICENSE());
                intent.putExtra("tanggal_expired", data.get(position).getEXP_DATE());
                intent.putExtra("status_mesin", data.get(position).getSTATUS_ENGINE());
                intent.putExtra("status_pemakaian", data.get(position).getVEHICLE_STATE());
                intent.putExtra("status_operasional", data.get(position).getSTATUS_EXP());
                intent.putExtra("pemilik", data.get(position).getOWNERNAME());
                intent.putExtra("notelp", data.get(position).getGSM());
                fragmentActivity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageback)
        ImageView imageback;
        @BindView(R.id.viewRecycler)
        View viewRecycler;
        @BindView(R.id.textnama)
        TextView textnama;
        @BindView(R.id.textalamat)
        TextView textalamat;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
