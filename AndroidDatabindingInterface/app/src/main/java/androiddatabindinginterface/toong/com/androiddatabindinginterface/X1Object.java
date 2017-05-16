package androiddatabindinginterface.toong.com.androiddatabindinginterface;

public class X1Object extends IXObject {
    private String name;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }
}