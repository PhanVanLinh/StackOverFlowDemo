package example.toong.parsespannablestringviaintent;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView) findViewById(R.id.textView);
        final SpannableString spannableText = new SpannableString("Hello stackOverflow");
        spannableText.setSpan(new RelativeSizeSpan(1.5f), spannableText.length() - "stackOverflow".length(),
                spannableText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableText.setSpan(new ForegroundColorSpan(Color.RED), 3, spannableText.length() - 3,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spannableText);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("KEY", spannableText);
                startActivity(intent);
            }
        });
    }
}
