package com.rosinante24.healthyline.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.rosinante24.healthyline.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailAmbulanceActivity extends AppCompatActivity implements OnMapReadyCallback {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.imagebackdetail)
    ImageView imagebackdetail;
    @BindView(R.id.viewDetail)
    View viewDetail;
    @BindView(R.id.textnamadetail)
    TextView textnamadetail;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.textdeskripsi)
    TextView textdeskripsi;
    @BindView(R.id.textpemilik)
    TextView textdeskripsis;
    @BindView(R.id.textnotelp)
    TextView textnotelp;
    @BindView(R.id.buttonCall)
    Button buttonCall;
    @BindView(R.id.cardMap)
    CardView cardMap;

    private GoogleMap googleMaps;
    private int gambar;
    private String nama, alamat, latitude, longitude, tanggalakhir, platnomer, statusmesin, statuspemakaian, statusoperasional, pemilik, notelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_ambulance);
        ButterKnife.bind(this);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //getIntent()
        nama = getIntent().getStringExtra("nama");
        alamat = getIntent().getStringExtra("alamat");
        tanggalakhir = getIntent().getStringExtra("tanggal_expired");
        platnomer = getIntent().getStringExtra("platnomer");
        gambar = getIntent().getIntExtra("gambar", 0);
        latitude = getIntent().getStringExtra("latitude");
        longitude = getIntent().getStringExtra("longitude");
        statusmesin = getIntent().getStringExtra("status_mesin");
        statuspemakaian = getIntent().getStringExtra("status_pemakaian");
        statusoperasional = getIntent().getStringExtra("status_operasional");
        pemilik = getIntent().getStringExtra("pemilik");
        notelp = getIntent().getStringExtra("notelp");
        //set data from intent

        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
        Date date = new Date();
        try {
            date = formatter.parse(tanggalakhir);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String newFormat = formatter.format(date);
        textdeskripsi.setText("Plat nomor :  " + platnomer + "\n\n");
        textdeskripsi.append("Masa berakhir :  " + newFormat + "\n\n");
        textdeskripsi.append("Status mesin :  " + statusmesin + "\n\n");
        textdeskripsi.append("Status pemakaian :  " + statuspemakaian + "\n\n");
        textdeskripsi.append("Status operasional :  " + statusoperasional);
        textdeskripsis.setText("Pemilik :  " + pemilik);
        textnotelp.setText("Nomor Telpon :  " + notelp);
        buttonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent call = new Intent(Intent.ACTION_CALL);
                call.setData(Uri.parse("tel:" + notelp));
                if (ActivityCompat.checkSelfPermission(DetailAmbulanceActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(call);
            }
        });
        textnamadetail.setText(nama);
        imagebackdetail.setImageResource(gambar);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        googleMaps = googleMap;
        double latitudes, longitudes;
        try {
            latitudes = Double.parseDouble(latitude);
            longitudes = Double.parseDouble(longitude);
            LatLng sydney = new LatLng(latitudes, longitudes);
            googleMaps.addMarker(new MarkerOptions().position(sydney).title(alamat));
            googleMaps.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            googleMaps.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 16));
        } catch (NullPointerException e) {
            cardMap.setVisibility(View.GONE);
        }

        googleMaps.getUiSettings().setAllGesturesEnabled(true);
        googleMaps.getUiSettings().setZoomGesturesEnabled(true);
        googleMaps.setTrafficEnabled(true);
    }
}
