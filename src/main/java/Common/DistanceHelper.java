package Common;

/**
 * Created by jan on 30/08/16.
 */
public class DistanceHelper {
    private DistanceHelper(){}
    public static float from_geographic_to_km(float lat1, float lon1, float lat2, float lon2){
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
    private static float deg2rad(double deg) {
        return (float) (deg * Math.PI / 180.0);
    }
    private static float rad2deg(double rad) {
        return (float) (rad * 180 / Math.PI);
    }
}