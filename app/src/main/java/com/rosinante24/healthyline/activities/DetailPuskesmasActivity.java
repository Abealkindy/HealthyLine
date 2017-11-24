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
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.rosinante24.healthyline.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailPuskesmasActivity extends AppCompatActivity implements OnMapReadyCallback {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.imagebackdetailpuskesmas)
    ImageView imagebackdetailpuskesmas;
    @BindView(R.id.viewDetailPuskesmas)
    View viewDetailPuskesmas;
    @BindView(R.id.textnamadetailpuskesmas)
    TextView textnamadetailpuskesmas;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.textkepala)
    TextView textkepala;
    @BindView(R.id.textnotelppuskesmas)
    TextView textnotelppuskesmas;
    @BindView(R.id.buttonCallpuskesmas)
    Button buttonCallpuskesmas;
    @BindView(R.id.textemail)
    TextView textemail;
    @BindView(R.id.buttonsendemail)
    Button buttonsendemail;
    @BindView(R.id.textfax)
    TextView textfax;

    private GoogleMap googleMaps;
    private double latitude, longitude;
    private String alamat, nama, kepala, notelp, nofax, email;
    private int gambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_puskesmas);
        ButterKnife.bind(this);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        latitude = getIntent().getDoubleExtra("latitude", 0);
        longitude = getIntent().getDoubleExtra("longitude", 0);
        gambar = getIntent().getIntExtra("gambar", 0);
        alamat = getIntent().getStringExtra("alamat");
        nama = getIntent().getStringExtra("nama");
        kepala = getIntent().getStringExtra("namakepala");
        email = getIntent().getStringExtra("email");
        notelp = getIntent().getStringExtra("notelp");
        nofax = getIntent().getStringExtra("faximile");

        textnamadetailpuskesmas.setText(nama);
        imagebackdetailpuskesmas.setImageResource(gambar);
        textemail.setText(email);

        if (getIntent().getStringExtra("notelp").equals("")) {
            textnotelppuskesmas.setText("-");
        } else {
            textnotelppuskesmas.setText("021" + notelp);
        }

        if (getIntent().getStringExtra("faximile").equals("")) {
            textfax.setText("-");
        } else {
            textfax.setText("Fax : 021" + nofax);
        }

        textkepala.setText("Kepala : " + kepala);

        buttonCallpuskesmas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callPuskesmas = new Intent(Intent.ACTION_CALL);
                callPuskesmas.setData(Uri.parse("tel:" + "021" + notelp));
                if (ActivityCompat.checkSelfPermission(DetailPuskesmasActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(callPuskesmas);
            }
        });
        buttonsendemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] TO = {email};
                Intent sendEmail = new Intent(Intent.ACTION_SEND);
                sendEmail.setData(Uri.parse("mailto:"))
                        .setType("text/plain")
                        .putExtra(Intent.EXTRA_EMAIL, TO);
                startActivity(Intent.createChooser(sendEmail, "Send mail..."));
                try {
                    startActivity(Intent.createChooser(sendEmail, "send mail..."));

                    Log.d("Email sent...", "");
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(DetailPuskesmasActivity.this, "gagal bro", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        googleMaps = googleMap;
        LatLng sydney = new LatLng(latitude, longitude);
        googleMaps.addMarker(new MarkerOptions().position(sydney).title(alamat));
        googleMaps.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        googleMaps.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 16));
        googleMaps.getUiSettings().setAllGesturesEnabled(true);
        googleMaps.getUiSettings().setZoomGesturesEnabled(true);
        googleMaps.setTrafficEnabled(true);
    }
}
