package com.example.pqanh.myapp2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity1 extends AppCompatActivity {
    public static final String TAG ="Activity1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
        Log.i(TAG,"onCreate");

        Intent intent = getIntent();

        // Tham số trong Intent truyền sang từ MainActivity
        String value1 = intent.getStringExtra("text1");


        // Tham số trong Intent truyền sang từ MainActivity
        String value2 = intent.getStringExtra("text2");

        // Lấy ra đối tượng trên giao diện bởi ID của nó.
        final TextView text1 = (TextView)this.findViewById(R.id.id_atv_text_1);

        // Lấy ra đối tượng trên giao diện bởi ID của nó.
        final TextView text2 = (TextView)this.findViewById(R.id.id_atv_text_2);

        text1.setText(value1);
        text2.setText(value2);

        Button button = (Button)this.findViewById(R.id.id_atv_btn_1);

        // Sử lý sự kiện khi click vào Button.
        button.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                text1.setText("You click button");
            }
        });

        // Sử lý sự kiện khi click vào Button và giữ lâu.
        button.setOnLongClickListener(new Button.OnLongClickListener() {


            // return true if the callback consumed the long click, false otherwise.
            @Override
            public boolean onLongClick(View v) {
                text2.setText("You long click button");
                return true;
            }
        });
    }
}
