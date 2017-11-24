package com.rosinante24.healthyline.adapter;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rosinante24.healthyline.R;
import com.rosinante24.healthyline.activities.DetailPuskesmasActivity;
import com.rosinante24.healthyline.response.PuskesmasResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rosinante24 on 22/11/17.
 */

public class PuskesmasAdapter extends RecyclerView.Adapter<PuskesmasAdapter.ViewHolder> {
    private List<PuskesmasResponse.PuskesmasData> data;
    private FragmentActivity fragmentActivity;

    public PuskesmasAdapter(FragmentActivity activity, List<PuskesmasResponse.PuskesmasData> data) {
        this.data = data;
        this.fragmentActivity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(fragmentActivity).inflate(R.layout.item_puskesmas, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.textnamapuskesmas.setText(data.get(position).getNama_Puskesmas());
        holder.textalamatpuskesmas.setText(data.get(position).getLocation().getAlamat());

        String tangkap = data.get(position).getTelepon().toString();
        String abcd = tangkap.substring(1, tangkap.length() - 1);
        final String formattelpon = abcd.replace("", "");

        String tangkaps = data.get(position).getFaximile().toString();
        String abcde = tangkaps.substring(1, tangkap.length() - 1);
        final String formatfax = abcde.replace("", "");

        holder.cardpuskesmas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(fragmentActivity.getApplicationContext(), DetailPuskesmasActivity.class);
                intent.putExtra("nama", data.get(position).getNama_Puskesmas());
                intent.putExtra("gambar", R.drawable.puskesmaslogos);
                intent.putExtra("alamat", data.get(position).getLocation().getAlamat());
                intent.putExtra("latitude", data.get(position).getLocation().getLatitude());
                intent.putExtra("longitude", data.get(position).getLocation().getLongitude());
                intent.putExtra("notelp", formattelpon);
                intent.putExtra("faximile", formatfax);
                intent.putExtra("email", data.get(position).getEmail());
                intent.putExtra("namakepala", data.get(position).getKepala_puskesmas());
                fragmentActivity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textnamapuskesmas)
        TextView textnamapuskesmas;
        @BindView(R.id.textalamatpuskesmas)
        TextView textalamatpuskesmas;
        @BindView(R.id.cardpuskesmas)
        CardView cardpuskesmas;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
