
package pl.tom.weatherappspring.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rain {

    @SerializedName("1h")
    @Expose
    private Double _1h;
    @SerializedName("2h")
    @Expose
    private Double _2h;
    @SerializedName("3h")
    @Expose
    private Double _3h;
    @SerializedName("4h")
    @Expose
    private Double _4h;
    @SerializedName("5h")
    @Expose
    private Double _5h;

    public Double get1h() {
        return _1h;
    }

    public void set1h(Double _1h) {
        this._1h = _1h;
    }

    public Double get2h() {
        return _2h;
    }

    public void set2h(Double _2h) {
        this._2h = _2h;
    }

    public Double get3h() {
        return _3h;
    }

    public void set3h(Double _3h) {
        this._3h = _3h;
    }

    public Double get4h() {
        return _4h;
    }

    public void set4h(Double _4h) {
        this._4h = _4h;
    }

    public Double get5h() {
        return _5h;
    }

    public void set5h(Double _5h) {
        this._5h = _5h;
    }

}
