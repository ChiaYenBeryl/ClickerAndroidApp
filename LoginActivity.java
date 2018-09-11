package com.example.clicker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BtnListener listener = new BtnListener();
        ((Button) findViewById(R.id.loginButton)).setOnClickListener(listener);
    }

    private class BtnListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            EditText roomNum = (EditText) findViewById(R.id.roomNumBox);
            String roomNumStr = roomNum.getText().toString();
            EditText pw = (EditText) findViewById(R.id.pwBox);
            String pwStr = pw.getText().toString();


            //intent.putExtra("roomNum", roomNumStr);
            //intent.putExtra("pw", pwStr);
            if(roomNumStr.equals("3215") && pwStr.equals("password101")) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }else {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setMessage("Wrong room number or password")
                        .setNeutralButton("Retry", null);

                AlertDialog alert = builder.create();
                alert.show();

            }
        }
    }

}
