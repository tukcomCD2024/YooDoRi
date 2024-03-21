package kr.ac.tukorea.whereareu.data.model.home;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.Date;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b(\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B\u0095\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\f\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\n0\f\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\n0\f\u0012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\n0\f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0013\u0012\u0006\u0010\u0015\u001a\u00020\u0011\u0012\u0006\u0010\u0016\u001a\u00020\n\u00a2\u0006\u0002\u0010\u0017J\t\u0010*\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010+\u001a\b\u0012\u0004\u0012\u00020\n0\fH\u00c6\u0003J\t\u0010,\u001a\u00020\u0011H\u00c6\u0003J\t\u0010-\u001a\u00020\u0013H\u00c6\u0003J\t\u0010.\u001a\u00020\u0013H\u00c6\u0003J\t\u0010/\u001a\u00020\u0011H\u00c6\u0003J\t\u00100\u001a\u00020\nH\u00c6\u0003J\t\u00101\u001a\u00020\u0005H\u00c6\u0003J\t\u00102\u001a\u00020\u0005H\u00c6\u0003J\t\u00103\u001a\u00020\u0003H\u00c6\u0003J\t\u00104\u001a\u00020\u0003H\u00c6\u0003J\t\u00105\u001a\u00020\nH\u00c6\u0003J\u000f\u00106\u001a\b\u0012\u0004\u0012\u00020\n0\fH\u00c6\u0003J\u000f\u00107\u001a\b\u0012\u0004\u0012\u00020\n0\fH\u00c6\u0003J\u000f\u00108\u001a\b\u0012\u0004\u0012\u00020\n0\fH\u00c6\u0003J\u00b7\u0001\u00109\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\f2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\n0\f2\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\n0\f2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\n0\f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00132\b\b\u0002\u0010\u0015\u001a\u00020\u00112\b\b\u0002\u0010\u0016\u001a\u00020\nH\u00c6\u0001J\u0013\u0010:\u001a\u00020\u00132\b\u0010;\u001a\u0004\u0018\u00010<H\u00d6\u0003J\t\u0010=\u001a\u00020\u0011H\u00d6\u0001J\t\u0010>\u001a\u00020\u0003H\u00d6\u0001R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0010\u001a\u00020\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0016\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001dR\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010 R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\n0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0019R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\n0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0019R\u0011\u0010\u0014\u001a\u00020\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010$R\u0011\u0010\u0012\u001a\u00020\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010$R\u0011\u0010\u0015\u001a\u00020\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u001bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\n0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010\u0019R\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010&R\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010 \u00a8\u0006?"}, d2 = {"Lkr/ac/tukorea/whereareu/data/model/home/LocationInfo;", "Ljava/io/Serializable;", "dementiaKey", "", "latitude", "", "longitude", "time", "date", "currentSpeed", "", "accelerationsensor", "", "gyrosensor", "directionsensor", "lightsensor", "battery", "", "isInternetOn", "", "isGpsOn", "isRingstoneOn", "bearing", "(Ljava/lang/String;DDLjava/lang/String;Ljava/lang/String;FLjava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;IZZIF)V", "getAccelerationsensor", "()Ljava/util/List;", "getBattery", "()I", "getBearing", "()F", "getCurrentSpeed", "getDate", "()Ljava/lang/String;", "getDementiaKey", "getDirectionsensor", "getGyrosensor", "()Z", "getLatitude", "()D", "getLightsensor", "getLongitude", "getTime", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "", "hashCode", "toString", "app_debug"})
public final class LocationInfo implements java.io.Serializable {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String dementiaKey = null;
    private final double latitude = 0.0;
    private final double longitude = 0.0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String time = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String date = null;
    private final float currentSpeed = 0.0F;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.Float> accelerationsensor = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.Float> gyrosensor = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.Float> directionsensor = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.Float> lightsensor = null;
    private final int battery = 0;
    private final boolean isInternetOn = false;
    private final boolean isGpsOn = false;
    private final int isRingstoneOn = 0;
    private final float bearing = 0.0F;
    
    public LocationInfo(@org.jetbrains.annotations.NotNull()
    java.lang.String dementiaKey, double latitude, double longitude, @org.jetbrains.annotations.NotNull()
    java.lang.String time, @org.jetbrains.annotations.NotNull()
    java.lang.String date, float currentSpeed, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Float> accelerationsensor, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Float> gyrosensor, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Float> directionsensor, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Float> lightsensor, int battery, boolean isInternetOn, boolean isGpsOn, int isRingstoneOn, float bearing) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDementiaKey() {
        return null;
    }
    
    public final double getLatitude() {
        return 0.0;
    }
    
    public final double getLongitude() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTime() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDate() {
        return null;
    }
    
    public final float getCurrentSpeed() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.Float> getAccelerationsensor() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.Float> getGyrosensor() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.Float> getDirectionsensor() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.Float> getLightsensor() {
        return null;
    }
    
    public final int getBattery() {
        return 0;
    }
    
    public final boolean isInternetOn() {
        return false;
    }
    
    public final boolean isGpsOn() {
        return false;
    }
    
    public final int isRingstoneOn() {
        return 0;
    }
    
    public final float getBearing() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.Float> component10() {
        return null;
    }
    
    public final int component11() {
        return 0;
    }
    
    public final boolean component12() {
        return false;
    }
    
    public final boolean component13() {
        return false;
    }
    
    public final int component14() {
        return 0;
    }
    
    public final float component15() {
        return 0.0F;
    }
    
    public final double component2() {
        return 0.0;
    }
    
    public final double component3() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component5() {
        return null;
    }
    
    public final float component6() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.Float> component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.Float> component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.Float> component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kr.ac.tukorea.whereareu.data.model.home.LocationInfo copy(@org.jetbrains.annotations.NotNull()
    java.lang.String dementiaKey, double latitude, double longitude, @org.jetbrains.annotations.NotNull()
    java.lang.String time, @org.jetbrains.annotations.NotNull()
    java.lang.String date, float currentSpeed, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Float> accelerationsensor, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Float> gyrosensor, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Float> directionsensor, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Float> lightsensor, int battery, boolean isInternetOn, boolean isGpsOn, int isRingstoneOn, float bearing) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}