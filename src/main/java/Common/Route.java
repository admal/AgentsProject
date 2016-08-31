package Common;

import Common.Abstract.IPosition;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jan on 31/08/16.
 */
public class Route implements Serializable{
    private List<IPosition> points;
    private long distance;
    private long time;
    public Route(List<IPosition> points, long distance, long time){
        this.points = points;
        this.distance = distance;
        this.time = time;
    }

    public long getDistance() {
        return distance;
    }

    public List<IPosition> getPoints() {
        return points;
    }

    public long getTime() {
        return time;
    }
}
