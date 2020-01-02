package com.yu.utils;

import com.yu.model.MapCoordinate;

public class CoordinateConvertUtil {

    private static double pi = 3.1415926535897932384626;
    private static double a = 6378245.0;
    private static double ee = 0.00669342162296594323;/*判断是否在国内，不在国内则不做偏移*/
    
    public static MapCoordinate szbj54_To_Bd09(double lat, double lon) {
        MapCoordinate gps84 = bj54_To_Gps84(lat, lon);
        MapCoordinate gcj02 = gps84_To_Gcj02(gps84.getLatgitude(), gps84.getLongitude());
        return gcj02_To_Bd09(gcj02.getLatgitude(), gcj02.getLongitude());
    }
    
    /**
     * bj54坐标系转wgs84坐标系 bd09_To_Gps84
     * @param lat
     * @param lon
     * @return
     */
    public static MapCoordinate bj54_To_Gps84(double lat, double lon) {
        double _a = 6378245.0;
        double _f = 1.0 / 298.3;
        double north2 = -3421129;
        double centerX = 120.5833333333333;
        double east = 50805;
        
        double longitude1, latitude1, longitude0, xval, yval;
        double e1, e2, ee, NN, T, C, M, D, R, u, fai, iPI;
        iPI = 0.0174532925199433; // 3.1415926535898/180.0;
        longitude0 = centerX * iPI;
        xval = lon - east;
        yval = lat - north2;
        e2 = 2 * _f - _f * _f;
        e1 = (1.0 - Math.sqrt(1 - e2)) / (1.0 + Math.sqrt(1 - e2));
        ee = e2 / (1 - e2);
        M = yval;
        u = M / (_a * (1 - e2 / 4 - 3 * e2 * e2 / 64 - 5 * e2 * e2 * e2 / 256));
        fai = u + (3 * e1 / 2 - 27 * e1 * e1 * e1 / 32) * Math.sin(2 * u) + (21 * e1 * e1 / 16 - 55 * e1 * e1 * e1 * e1 / 32) * Math.sin(4 * u) + (151 * e1 * e1 * e1 / 96) * Math.sin(6 * u) + (1097 * e1 * e1 * e1 * e1 / 512) * Math.sin(8 * u);
        C = ee * Math.cos(fai) * Math.cos(fai);
        T = Math.tan(fai) * Math.tan(fai);
        NN = _a / Math.sqrt(1.0 - e2 * Math.sin(fai) * Math.sin(fai));
        R = _a * (1 - e2) / Math.sqrt((1 - e2 * Math.sin(fai) * Math.sin(fai)) * (1 - e2 * Math.sin(fai) * Math.sin(fai)) * (1 - e2 * Math.sin(fai) * Math.sin(fai)));
        D = xval / NN;
        // 计算经度(Longitude) 纬度(Latitude)
        longitude1 = longitude0 + (D - (1 + 2 * T + C) * D * D * D / 6 + (5 - 2 * C + 28 * T - 3 * C * C + 8 * ee + 24 * T * T) * D * D * D * D * D / 120) / Math.cos(fai);
        latitude1 = fai - (NN * Math.tan(fai) / R) * (D * D / 2 - (5 + 3 * T + 10 * C - 4 * C * C - 9 * ee) * D * D * D * D / 24 + (61 + 90 * T + 298 * C + 45 * T * T - 256 * ee - 3 * C * C) * D * D * D * D * D * D / 720);
        // 转换为度 DD
        double longitude = Math.round((longitude1 / iPI) * 10000000) / 10000000.0 + 0.0009;// 0.0009是经验值
        double latitude = Math.round((latitude1 / iPI) * 1000000) / 1000000.0 + 0.0003; // 0.0003是经验值

        return new MapCoordinate(latitude, longitude);
    }
    
    /**
     * gps84 to 火星坐标(GCJ-02)
     * 
     * @param lat
     * @param lon
     * @return
     */
    public static MapCoordinate gps84_To_Gcj02(double lat, double lon) {
        if (outOfChina(lat, lon)) {
            return null;
        }
        double dLat = transformLat(lon - 105.0, lat - 35.0);
        double dLon = transformLon(lon - 105.0, lat - 35.0);
        double radLat = lat / 180.0 * pi;
        double magic = Math.sin(radLat);
        magic = 1 - ee * magic * magic;
        double sqrtMagic = Math.sqrt(magic);
        dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);
        dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);
        double mgLat = lat + dLat;
        double mgLon = lon + dLon;
        return new MapCoordinate(mgLat, mgLon);
    }
    
    /**
     * 火星坐标(GCJ-02) to 百度坐标系 (BD-09)
     * @param gg_lat
     * @param gg_lon
     * @return
     */
    public static MapCoordinate gcj02_To_Bd09(double gg_lat, double gg_lon) {
        double x = gg_lon, y = gg_lat;
        double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * pi);
        double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * pi);
        double bd_lon = z * Math.cos(theta) + 0.0065;
        double bd_lat = z * Math.sin(theta) + 0.006;
        return new MapCoordinate(bd_lat, bd_lon);
    }
    
    public static boolean outOfChina(double lat, double lon) {
        if (lon < 72.004 || lon > 137.8347)
            return true;
        if (lat < 0.8293 || lat > 55.8271)
            return true;
        return false;
    }
    
    public static double transformLat(double x, double y) {
        double ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y + 0.2 * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(y * pi) + 40.0 * Math.sin(y / 3.0 * pi)) * 2.0 / 3.0;
        ret += (160.0 * Math.sin(y / 12.0 * pi) + 320 * Math.sin(y * pi / 30.0)) * 2.0 / 3.0;
        return ret;
    }

    public static double transformLon(double x, double y) {
        double ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1 * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(x * pi) + 40.0 * Math.sin(x / 3.0 * pi)) * 2.0 / 3.0;
        ret += (150.0 * Math.sin(x / 12.0 * pi) + 300.0 * Math.sin(x / 30.0 * pi)) * 2.0 / 3.0;
        return ret;
    }

}
