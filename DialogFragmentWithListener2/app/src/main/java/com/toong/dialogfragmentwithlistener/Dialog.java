package com.toong.dialogfragmentwithlistener;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by phanvanlinh on 16/06/2017.
 * Email: phanvanlinh.94vn@gmail.com
 */

public class Dialog extends DialogFragment {
    private final String TAG = "TAG";
    DialogListener listener;

    public static Dialog newInstance(String title, DialogListener listener) {
        Bundle args = new Bundle();
        args.putString("title", title);
        Dialog fragment = new Dialog();
        fragment.setArguments(args);
        fragment.setListener(listener);
        return fragment;
    }

    public void setListener(DialogListener listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            Bundle savedInstanceState) {
        Log.i(TAG, "DialogFragment onCreateView");
        View rootView = inflater.inflate(R.layout.dialog_view, container);

        Button btnA = (Button) rootView.findViewById(R.id.btnA);

        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick();
                dismiss();
            }
        });

        TextView textView = (TextView) rootView.findViewById(R.id.tvTitle);
        textView.setText(getArguments().getString("title"));

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i(TAG, "DialogFragment onAttach");
    }

    interface DialogListener {
        void onClick();
    }

}
