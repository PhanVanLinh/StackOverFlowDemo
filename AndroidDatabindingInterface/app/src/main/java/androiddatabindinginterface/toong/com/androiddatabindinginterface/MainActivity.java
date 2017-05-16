package androiddatabindinginterface.toong.com.androiddatabindinginterface;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import androiddatabindinginterface.toong.com.androiddatabindinginterface.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

//
        final IXObject xobj = new X1Object();
        xobj.setName("nguyen");
        binding.setXObject(xobj);

//
//        final X1Object xobj = new X1Object();
//        xobj.setName("djakldjklas");
//        binding.setXObject(xobj);


        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xobj.setName("dasd" + ++count);
            }
        });
    }


}
