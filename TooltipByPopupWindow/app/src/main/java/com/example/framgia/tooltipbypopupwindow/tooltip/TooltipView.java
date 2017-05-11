package com.example.framgia.tooltipbypopupwindow.tooltip;

import android.app.ActionBar;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.framgia.tooltipbypopupwindow.R;

public class TooltipView {
    private Context mContext;
    private View mAnchor;
    private PopupWindow mPopupWindow;
    private View mContentView;
    private TextView tvMessage;
    private ImageView ivArrow;
    private View mRootView;

    public TooltipView(Context context) {
        mContext = context;
        mPopupWindow = new PopupWindow(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mContentView = inflater.inflate(R.layout.layout_tooltip, null);

        initLayout();
        configPopupWindow();
    }

    private void initLayout() {
        mRootView = mContentView.findViewById(R.id.linear_root);
        tvMessage = (TextView) mContentView.findViewById(R.id.text_message);
        ivArrow = (ImageView) mContentView.findViewById(R.id.image_arrow);
    }

    private void configPopupWindow() {
        mPopupWindow.setHeight(ActionBar.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setWidth(ActionBar.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setContentView(mContentView);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
    }

    /**
     * We prefer display error tooltip ABOVE and CENTER to anchor view
     * But in some case (anchor view close to left, top, right) we should align flexible
     */
    private void measureSizeThenUpdatePopupWindowPosition(
            final OnUpdateSuccessListener onUpdateSuccessListener) {
        ViewTreeObserver viewTreeObserver = mRootView.getViewTreeObserver();
        if (!viewTreeObserver.isAlive()) {
            return;
        }
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mRootView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                Size rootViewSize = new Size(mRootView.getWidth(), mRootView.getHeight());
                Rect anchorRect = getRectFromView(mAnchor);
                // calculate x,y position for display popupwindow above and center anchor view
                int popupWinDownPositionX =
                        (int) (anchorRect.centerX() - (rootViewSize.getWidth() / 2));
                int popupWinDownPositionY = (int) (anchorRect.top - rootViewSize.getHeight());

                // anchor view close to left
                if (popupWinDownPositionX < 0) {
                    popupWinDownPositionX = 0;
                }
                // anchor view close to top
                if (popupWinDownPositionY < 0) {
                    popupWinDownPositionY = 0;
                }
                // anchor view close to right
                if (popupMayExceedsTheScreen(popupWinDownPositionX,
                        (int) rootViewSize.getWidth())) {
                    popupWinDownPositionX =
                            (int) (getScreenWidth() - rootViewSize.getWidth());
                }

                // update popupwindow position and dimens
                mPopupWindow.update(popupWinDownPositionX, popupWinDownPositionY,
                        (int) rootViewSize.getWidth(), (int) rootViewSize.getHeight());
                if (onUpdateSuccessListener != null) {
                    onUpdateSuccessListener.onSuccess(popupWinDownPositionX);
                }
            }
        });
    }

    /**
     * @param popupWinDownPositionX This use for make arrow image always CENTER-ABOVE anchor view
     */
    private void updateImageArrowMargin(final int popupWinDownPositionX) {
        final Rect anchorRect = getRectFromView(this.mAnchor);
        ViewTreeObserver viewTreeObserver = ivArrow.getViewTreeObserver();
        if (!viewTreeObserver.isAlive()) {
            return;
        }
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ivArrow.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                Size arrowImageSize = new Size(ivArrow.getWidth(), ivArrow.getHeight());
                LinearLayout.LayoutParams params =
                        (LinearLayout.LayoutParams) ivArrow.getLayoutParams();
                params.setMarginStart((int) (anchorRect.centerX()
                        - popupWinDownPositionX
                        - (arrowImageSize.getWidth()
                        / 2))); // margin here is margin to PopupWindow not margin to screen
                ivArrow.setLayoutParams(params);
            }
        });
    }

    private int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public void showToolTipAbove(View anchor, String message) {
        mAnchor = anchor;
        // set value
        tvMessage.setText(message);
        // first, show tooltip
        mPopupWindow.showAtLocation(anchor, Gravity.NO_GRAVITY, 0, 0);

        // measure root capacity and update
        measureSizeThenUpdatePopupWindowPosition(new OnUpdateSuccessListener() {
            @Override
            public void onSuccess(int popupWinDownPositionX) {
                updateImageArrowMargin(popupWinDownPositionX);
            }
        });
    }

    private boolean popupMayExceedsTheScreen(int popupWinDownPositionX, int rootViewWidth) {
        return popupWinDownPositionX + rootViewWidth > getScreenWidth();
    }

    private Rect getRectFromView(View view) {
        // Get location of anchor view on screen
        int[] screenPos = new int[2];
        view.getLocationOnScreen(screenPos);
        return new Rect(screenPos[0], screenPos[1], screenPos[0] + view.getWidth(),
                screenPos[1] + view.getHeight());
    }

    public boolean isTooltipShown() {
        return mPopupWindow != null && mPopupWindow.isShowing();
    }

    public void dismissTooltip() {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
    }

    public void setTooltipFocusable(boolean isFocusable) {
        mPopupWindow.setFocusable(isFocusable);
    }

    protected void addOnDismissListener(PopupWindow.OnDismissListener mOnDismissListener) {
        if (mOnDismissListener != null) {
            mPopupWindow.setOnDismissListener(mOnDismissListener);
        }
    }

    interface OnUpdateSuccessListener {
        void onSuccess(int popupWinDownPositionX);


    }

    private class Size {
        private float width;
        private float height;

        Size(float width, float height) {
            this.width = width;
            this.height = height;
        }

        float getWidth() {
            return width;
        }

        float getHeight() {
            return height;
        }
    }
}
