package com.example.clicker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static int questionNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BtnListener listener = new BtnListener();
        (findViewById(R.id.A)).setOnClickListener(listener);
        (findViewById(R.id.B)).setOnClickListener(listener);
        (findViewById(R.id.C)).setOnClickListener(listener);
        (findViewById(R.id.D)).setOnClickListener(listener);
    }

    private class BtnListener implements OnClickListener {
        // On-click event handler for all the buttons
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.A:
                case R.id.B:
                case R.id.C:
                case R.id.D:
                    questionNo++;
                    String choice = ((Button) view).getText().toString().toLowerCase();

                    String url = "http://10.27.149.206:9999/clicker/select?choice=" + choice +"&questionNo=" + questionNo;

                    Intent i = new Intent();
                    i.setClass(MainActivity.this, SelectPage.class);
                    i.putExtra("url", url);
                    startActivity(i);
            }

        }
    }
}
