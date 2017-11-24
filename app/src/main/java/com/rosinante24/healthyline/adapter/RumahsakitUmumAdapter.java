package com.rosinante24.healthyline.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rosinante24.healthyline.R;
import com.rosinante24.healthyline.activities.DetailRumahSakitKhusus;
import com.rosinante24.healthyline.activities.DetailRumahSakitUmum;
import com.rosinante24.healthyline.response.RumahSakitUmumResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rosinante24 on 23/11/17.
 */

public class RumahsakitUmumAdapter extends RecyclerView.Adapter<RumahsakitUmumAdapter.ViewHolder> {
    private List<RumahSakitUmumResponse.RumahSakitUmumData> data;
    private Context context;

    public RumahsakitUmumAdapter(Context context, List<RumahSakitUmumResponse.RumahSakitUmumData> data) {
        this.data = data;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_ambulance, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.textnama.setText(data.get(position).getNama_rsu());
        holder.textalamat.setText(data.get(position).getLocation().getAlamat());
        holder.textjenisrs.setText(data.get(position).getJenis_rsu());
        holder.imageback.setImageResource(R.drawable.hospital);

        String ubahformattelpon = data.get(position).getTelepon().toString();
        String ubahformattelpon2 = ubahformattelpon.substring(1, ubahformattelpon.length() - 1);
        final String formattelpon = ubahformattelpon2.replace(",", ", " + "021");

        String ubahformatfax = data.get(position).getFaximile().toString();
        String ubahformatfax2 = ubahformatfax.substring(1, ubahformatfax.length() - 1);
        final String formatfax = ubahformatfax2.replace(",", ", " + "021");

        holder.viewRecycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(), DetailRumahSakitUmum.class);
                intent.putExtra("nama", data.get(position).getNama_rsu());
                intent.putExtra("alamat", data.get(position).getLocation().getAlamat());
                intent.putExtra("jenis", data.get(position).getJenis_rsu());
                intent.putExtra("email", data.get(position).getEmail());
                intent.putExtra("website", data.get(position).getWebsite());
                intent.putExtra("kodepos", data.get(position).getKode_pos());
                intent.putExtra("latitude", data.get(position).getLocation().getLatitude());
                intent.putExtra("longitude", data.get(position).getLocation().getLongitude());
                intent.putExtra("notelp", formattelpon);
                intent.putExtra("gambar", R.drawable.hospital);
                intent.putExtra("faximile", formatfax);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
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
        @BindView(R.id.textjenisrs)
        TextView textjenisrs;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
