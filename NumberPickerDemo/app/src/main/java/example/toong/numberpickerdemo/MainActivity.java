package example.toong.numberpickerdemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.NumberPicker;

public class MainActivity extends AppCompatActivity {
    private static final String PREF_NUMBER_PICKER_LAST_SELECTED_POSITION =
            "PREF_NUMBER_PICKER_LAST_SELECTED_POSITION";
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        final String[] power = { "0.00", "0.25", "0.50", "0.75s", "1.00s" };
        NumberPicker numberPicker = (NumberPicker) findViewById(R.id.numberPicker);
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(power.length - 1);
        numberPicker.setDisplayedValues(power);
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                Log.i("TAG", "picker change " + oldVal + "-" + newVal);
                saveIntToSharedPreferences(mContext, PREF_NUMBER_PICKER_LAST_SELECTED_POSITION,
                        newVal);
            }
        });
        numberPicker.setValue(
                getIntFromSharedPreferences(mContext, PREF_NUMBER_PICKER_LAST_SELECTED_POSITION));
    }

    private void saveIntToSharedPreferences(Context context, String key, int value) {
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    private int getIntFromSharedPreferences(Context context, String key) {
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getInt(key, 0);
    }
}
