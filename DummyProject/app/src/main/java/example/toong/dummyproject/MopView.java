package example.toong.dummyproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMapOptions;

/**
 * Created by phanvanlinh on 27/04/2017.
 * Email: phanvanlinh.94vn@gmail.com
 */

public class MopView extends MapView{
    public MopView(@NonNull Context context) {
        super(context);
    }

    public MopView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MopView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MopView(@NonNull Context context, @Nullable MapboxMapOptions options) {
        super(context, options);
    }
}
