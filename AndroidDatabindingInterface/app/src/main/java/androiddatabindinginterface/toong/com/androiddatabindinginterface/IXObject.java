package androiddatabindinginterface.toong.com.androiddatabindinginterface;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

public abstract class IXObject extends BaseObservable {
    public abstract void setName(String name);

    @Bindable
    public abstract String getName();
}