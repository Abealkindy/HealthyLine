package com.rosinante24.healthyline.response;

import java.util.List;

import lombok.Data;

/**
 * Created by Rosinante24 on 22/11/17.
 */
@Data
public class PuskesmasResponse {

    private List<PuskesmasData> data;

    @Data
    public class PuskesmasData {

        private String nama_Puskesmas;
        private Location location;
        private List<String> telepon;
        private List<String> faximile;
        private String email;
        private String kepala_puskesmas;

        @Data
        public class Location {
            private String alamat;
            private double latitude;
            private double longitude;
        }


    }
}
