package Common;

/**
 * Created by jan on 30/08/16.
 */
public class DistanceHelper {
    private DistanceHelper(){}
    public static float fromGeographicToKm(float lat1, float lon1, float lat2, float lon2){
        double theta = lon1 - lon2;
        float distance = (float) (Math.sin(deg2rad(lat1))
                * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta)));
        distance = (float) Math.acos(distance);
        distance = rad2deg(distance);
        distance = (float) (distance * 60 * 1.1515);
        distance = (float) (distance * 1.609344);
        return distance;
    }
    public static float fromGeographicToM(float lat1, float lon1, float lat2, float lon2){
        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lon2-lon1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng/2) * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        return (float) (earthRadius * c);
    }
    private static float deg2rad(double deg) {
        return (float) (deg * Math.PI / 180.0);
    }
    private static float rad2deg(double rad) {
        return (float) (rad * 180 / Math.PI);
    }
}
