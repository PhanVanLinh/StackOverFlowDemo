package example.toong.circulartextview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class CircularTextView extends AppCompatTextView {
    private ShapeDrawable backgroundDrawable;
    private OvalShape ovalShape;

    private int backgroundColor;

    public CircularTextView(Context context) {
        super(context);
        backgroundColor =  Color.BLUE;
        allocateShapes();
    }

    public CircularTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        backgroundColor = Color.YELLOW;
        allocateShapes();
    }

    public CircularTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        backgroundColor = Color.RED;
        allocateShapes();
    }

    //Source https://stackoverflow.com/questions/25203501/android-creating-a-circular-textview/34685568#34685568
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int h = this.getMeasuredHeight();
        int w = this.getMeasuredWidth();
        int r = Math.max(w, h);

        setMeasuredDimension(r, r);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        backgroundDrawable.setShape(ovalShape);
        backgroundDrawable.getPaint().setColor(backgroundColor);

        setBackground(backgroundDrawable);
    }

    private void allocateShapes(){
        backgroundDrawable = new ShapeDrawable();
        ovalShape = new OvalShape();
    }

    public void setBackgroundColor(int color){
        backgroundColor = color;
        invalidate();
    }
}