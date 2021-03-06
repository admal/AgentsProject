package Common;

import Common.Abstract.IPosition;

import java.io.Serializable;

/**
 * Created by janbaraniewski on 03/05/16.
 */
public class Position implements IPosition, Serializable {
    private float x;
    private float y;
    public Position(float x,float y){
        this.x = x;
        this.y = y;
    }
    public float GetX() {
        return x;
    }

    public float GetY() {
        return y;
    }

    @Override
    public String toString() {
        return x+"," + y ;
    }
}
