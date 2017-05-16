package com.example.framgia.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RotateDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DistanceTextMarkerView extends LinearLayout {
    private TextView tvMessage;
    private Button btnArrow;
    private ImageView imgSquare;

    public DistanceTextMarkerView(ItemMarkerViewBuilder builder) {
        this(builder.mContext);
        setMessage(builder.message);
        setColor(builder.color);
    }

    public DistanceTextMarkerView(Context context) {
        this(context, null);
    }

    public DistanceTextMarkerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DistanceTextMarkerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.bg_tooltip, this, true);
        tvMessage = (TextView) findViewById(R.id.text_message);
        btnArrow = (Button) findViewById(R.id.button_arrow);
        imgSquare = (ImageView) findViewById(R.id.image_square);
    }

    public Bitmap toBitmap() {
        return getBitmapFromView(this);
    }

    public static Bitmap getBitmapFromView(View view) {
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.draw(canvas);
        return bitmap;
    }

    public void setMessage(String message) {
        this.tvMessage.setText(message);
    }

    private void setColor(int color) {
        changeArrowColor(color);
        changeMessageBackgroundColor(color);
        changeSquareColor(color);
    }

    private void changeArrowColor(int color) {
        LayerDrawable arrowIconLayerDrawable = (LayerDrawable) btnArrow.getBackground();
        RotateDrawable arrowIconRotateDrawable = (RotateDrawable) arrowIconLayerDrawable
                .findDrawableByLayerId(R.id.gradientDrawable);
        GradientDrawable arrowIconGradientDrawable = (GradientDrawable) arrowIconRotateDrawable.getDrawable();
        arrowIconGradientDrawable.setColor(color);
    }

    private void changeMessageBackgroundColor(int color) {
        GradientDrawable messageGradient = (GradientDrawable) tvMessage.getBackground();
        messageGradient.setColor(color);
    }

    private void changeSquareColor(int color) {
        imgSquare.setBackgroundColor(color);
    }

    public static class ItemMarkerViewBuilder {
        private final Context mContext;
        private String message;
        private int color;

        public ItemMarkerViewBuilder(Context context) {
            mContext = context;
        }

        public ItemMarkerViewBuilder setMessage(String message) {
            this.message = message;
            return this;
        }

        public ItemMarkerViewBuilder setColor(int color) {
            this.color = color;
            return this;
        }

        public DistanceTextMarkerView build() {
            return new DistanceTextMarkerView(this);
        }
    }
}