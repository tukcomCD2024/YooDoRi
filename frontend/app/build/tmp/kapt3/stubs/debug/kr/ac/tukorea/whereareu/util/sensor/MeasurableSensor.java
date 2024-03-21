package kr.ac.tukorea.whereareu.util.sensor;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u0007\n\u0002\u0010\u0002\n\u0002\b\u000b\b&\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J \u0010\u0014\u001a\u00020\r2\u0018\u0010\u0015\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0004\u0012\u00020\r0\nJ\b\u0010\u0016\u001a\u00020\rH&J\b\u0010\u0017\u001a\u00020\rH&R\u0012\u0010\u0005\u001a\u00020\u0006X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\bR.\u0010\t\u001a\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0004\u0012\u00020\r\u0018\u00010\nX\u0084\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013\u00a8\u0006\u0018"}, d2 = {"Lkr/ac/tukorea/whereareu/util/sensor/MeasurableSensor;", "", "sensorType", "", "(I)V", "doesSensorExist", "", "getDoesSensorExist", "()Z", "onSensorValueChanged", "Lkotlin/Function1;", "", "", "", "getOnSensorValueChanged", "()Lkotlin/jvm/functions/Function1;", "setOnSensorValueChanged", "(Lkotlin/jvm/functions/Function1;)V", "getSensorType", "()I", "setOnSensorValuesChangedListener", "listener", "startListening", "stopListening", "app_debug"})
public abstract class MeasurableSensor {
    private final int sensorType = 0;
    @org.jetbrains.annotations.Nullable()
    private kotlin.jvm.functions.Function1<? super java.util.List<java.lang.Float>, kotlin.Unit> onSensorValueChanged;
    
    public MeasurableSensor(int sensorType) {
        super();
    }
    
    protected final int getSensorType() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    protected final kotlin.jvm.functions.Function1<java.util.List<java.lang.Float>, kotlin.Unit> getOnSensorValueChanged() {
        return null;
    }
    
    protected final void setOnSensorValueChanged(@org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function1<? super java.util.List<java.lang.Float>, kotlin.Unit> p0) {
    }
    
    public abstract boolean getDoesSensorExist();
    
    public abstract void startListening();
    
    public abstract void stopListening();
    
    public final void setOnSensorValuesChangedListener(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.util.List<java.lang.Float>, kotlin.Unit> listener) {
    }
}