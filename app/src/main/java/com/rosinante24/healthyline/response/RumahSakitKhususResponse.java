package com.rosinante24.healthyline.response;

import java.util.List;

import lombok.Data;

/**
 * Created by Rosinante24 on 22/11/17.
 */
@Data
public class RumahSakitKhususResponse {

    private List<RumahSakitKhususData> data;

    @Data
    public class RumahSakitKhususData {
        private String nama_rsk;
        private String jenis_rsk;
        private Location location;
        private int kode_pos;
        private List<String> telepon;
        private List<String> faximile;
        private String website;
        private String email;

        @Data
        public class Location {
            private String alamat;
            private double latitude;
            private double longitude;
        }
    }
}
