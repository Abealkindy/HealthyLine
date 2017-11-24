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
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
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

public class DetailRumahSakitUmum extends AppCompatActivity implements OnMapReadyCallback {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.imagebackdetailrumahsakitumum)
    ImageView imagebackdetailrumahsakitumum;
    @BindView(R.id.viewDetailrumahsakitumum)
    View viewDetailrumahsakitumum;
    @BindView(R.id.textnamadetailrumahsakitumum)
    TextView textnamadetailrumahsakitumum;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.texttypeumum)
    TextView texttypeumum;
    @BindView(R.id.textkodepos)
    TextView textkodepos;
    @BindView(R.id.textnotelprumahsakitumum)
    TextView textnotelprumahsakitumum;
    @BindView(R.id.buttonCallrumahsakitumum)
    Button buttonCallrumahsakitumum;
    @BindView(R.id.textemail)
    TextView textemail;
    @BindView(R.id.buttonsendemail)
    Button buttonsendemail;
    @BindView(R.id.textfaxrumahsakitumum)
    TextView textfaxrumahsakitumum;
    @BindView(R.id.textwebsiteumum)
    TextView textwebsiteumum;

    private GoogleMap googleMaps;
    private double latitude, longitude;
    private String alamat, nama, jenis, notelp, nofax, email, website;
    private int gambar, kodepos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_rumah_sakit_umum);
        ButterKnife.bind(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        alamat = getIntent().getStringExtra("alamat");
        nama = getIntent().getStringExtra("nama");
        jenis = getIntent().getStringExtra("jenis");
        email = getIntent().getStringExtra("email");
        website = getIntent().getStringExtra("website");
        notelp = getIntent().getStringExtra("notelp");
        nofax = getIntent().getStringExtra("faximile");
        gambar = getIntent().getIntExtra("gambar", 0);
        kodepos = getIntent().getIntExtra("kodepos", 0);
        latitude = getIntent().getDoubleExtra("latitude", 0);
        longitude = getIntent().getDoubleExtra("longitude", 0);

        textnamadetailrumahsakitumum.setText(nama);
        texttypeumum.setText(jenis);
        textemail.setText(email);
        textkodepos.setText("" + kodepos);
        imagebackdetailrumahsakitumum.setImageResource(gambar);
        textnotelprumahsakitumum.setText("021 " + notelp);
        textfaxrumahsakitumum.setText("021 " + nofax);
        if (website == null) {
            textwebsiteumum.setText("-");
        } else {
            SpannableString spannableString = new SpannableString("http://" + website);
            spannableString.setSpan(new UnderlineSpan(), 0, spannableString.length(), 0);
            textwebsiteumum.setText(spannableString);

            textwebsiteumum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + website));
                    startActivity(intent);
                }
            });
        }
        buttonCallrumahsakitumum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + "021" + notelp));
                if (ActivityCompat.checkSelfPermission(DetailRumahSakitUmum.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(intent);
            }
        });
        buttonsendemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] TO = {email};
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setData(Uri.parse("mailto:"));
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL, TO);
                startActivity(Intent.createChooser(intent, "Send mail..."));
                try {
                    startActivity(Intent.createChooser(intent, "send mail..."));
                    Log.d("Email sent...", "");
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(DetailRumahSakitUmum.this, "gagal bro", Toast.LENGTH_SHORT).show();
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
