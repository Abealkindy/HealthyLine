package com.rosinante24.healthyline.network;

import com.rosinante24.healthyline.response.AmbulanceResponse;
import com.rosinante24.healthyline.response.PuskesmasResponse;
import com.rosinante24.healthyline.response.RumahSakitKhususResponse;
import com.rosinante24.healthyline.response.RumahSakitUmumResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by Rosinante24 on 22/11/17.
 */

public interface ServiceApi {
    @Headers({"Authorization: " + "wfc2X6K0P9OVUTJkRE3DIYdrqMRSPCeCtFn8JnhpXbapVgL0bnyupIct23Ii6hw5"})
    @GET("puskesmas")
    Call<PuskesmasResponse> getPuskesmas();

    @Headers({"Authorization: " + "wfc2X6K0P9OVUTJkRE3DIYdrqMRSPCeCtFn8JnhpXbapVgL0bnyupIct23Ii6hw5"})
    @GET("emergency/ambulance")
    Call<AmbulanceResponse> getAmbulance();

    @Headers({"Authorization: " + "wfc2X6K0P9OVUTJkRE3DIYdrqMRSPCeCtFn8JnhpXbapVgL0bnyupIct23Ii6hw5"})
    @GET("rumahsakitkhusus")
    Call<RumahSakitKhususResponse> getRumahSakitKhusus();

    @Headers({"Authorization: " + "wfc2X6K0P9OVUTJkRE3DIYdrqMRSPCeCtFn8JnhpXbapVgL0bnyupIct23Ii6hw5"})
    @GET("rumahsakitumum")
    Call<RumahSakitUmumResponse> getRumahSakitUmum();
}
