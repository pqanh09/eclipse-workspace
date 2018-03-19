package com.example.pqanh.myapp2;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String TAG ="MainActivity";
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG,"onCreate");

        // Lấy ra button theo ID
        Button button1 = (Button) this.findViewById(R.id.id_btn_1);

        // Sét đặt sự kiện Click vào Button1.
        button1.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Tạo một Intent:
                // (Mang nội dung sẽ gửi tới Example1Activity).
                Intent myIntent = new Intent(MainActivity.this, Activity1.class);

                // Các tham số gắn trên Intent (Không bắt buộc).
                myIntent.putExtra("text1", "This is text");
                myIntent.putExtra("text2", "This is long text");

                // Yêu cầu chạy Example1Activity.
                MainActivity.this.startActivity(myIntent);
            }
        });

        // Lấy ra button theo ID
        Button button2 = (Button) this.findViewById(R.id.id_btn_2);

        // Sét đặt sự kiện Click vào Button2.
        button2.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Tạo một Intent:
                // (Mang nội dung sẽ gửi tới Example2Activity).
                Intent myIntent = new Intent(MainActivity.this, Activity2.class);

                // Yêu cầu chạy Example2Activity.
                MainActivity.this.startActivity(myIntent);
            }
        });

        // Lấy ra button theo ID
        Button button3 = (Button) this.findViewById(R.id.id_btn_3);

        // Sét đặt sự kiện Click vào Button2.
        button3.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean networkOK = checkInternetConnection();
                if (!networkOK) {
                    return;
                }
                // Tạo một Intent:
                // (Mang nội dung sẽ gửi tới Activity3).
                Intent myIntent = new Intent(MainActivity.this, Activity3.class);

                // Yêu cầu chạy Example2Activity.
                MainActivity.this.startActivity(myIntent);
            }
        });
        // Lấy ra button theo ID
        Button button4 = (Button) this.findViewById(R.id.id_btn_4);
        // Sét đặt sự kiện Click vào Button2.
        button4.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean networkOK = checkInternetConnection();
                if (!networkOK) {
                    return;
                }
                // Tạo một Intent:
                // (Mang nội dung sẽ gửi tới Activity3).
                Intent myIntent = new Intent(MainActivity.this, Activity4.class);

                // Yêu cầu chạy Example2Activity.
                MainActivity.this.startActivity(myIntent);
            }
        });
        // Lấy ra button theo ID
        Button button5 = (Button) this.findViewById(R.id.id_btn_5);
        this.textView = (TextView)this.findViewById(R.id.id_text_connection);
        // Sét đặt sự kiện Click vào Button2.
        button5.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInternetConnection()){
                    textView.setText("Ok.");
                } else {
                    textView.setText("Fail.");
                }
            }
        });
    }

    private boolean checkInternetConnection() {
        ConnectivityManager connManager =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
        if (networkInfo == null) {
            Toast.makeText(this, "No default network is currently active", Toast.LENGTH_LONG).show();
            return false;
        }
        if (!networkInfo.isConnected()) {
            Toast.makeText(this, "Network is not connected", Toast.LENGTH_LONG).show();
            return false;
        }
        if (!networkInfo.isAvailable()) {
            Toast.makeText(this, "Network not available", Toast.LENGTH_LONG).show();
            return false;
        }
        Toast.makeText(this, "Network OK", Toast.LENGTH_LONG).show();
        return true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Print Log
        Log.i(TAG,"onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Print Log
        Log.i(TAG,"onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Print Log
        Log.i(TAG,"onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Print Log
        Log.i(TAG,"onResume");      }

    @Override
    protected void onStart() {
        super.onStart();
        // Print Log
        Log.i(TAG,"onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        // Print Log
        Log.i(TAG,"onRestart");
    }
}
