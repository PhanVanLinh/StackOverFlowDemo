package example.toong.parsespannablestringviaintent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        SpannableString a = (SpannableString) getIntent().getCharSequenceExtra("KEY");

        TextView textView = (TextView) findViewById(R.id.textView);

        textView.setText(a);
    }
}
