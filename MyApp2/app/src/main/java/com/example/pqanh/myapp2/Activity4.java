package com.example.pqanh.myapp2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.pqanh.myapp2.custom.DownloadImageTask;
import com.example.pqanh.myapp2.custom.Item;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Activity4 extends AppCompatActivity {
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);

        imageView = (ImageView)this.findViewById(R.id.id_atv4_image);

        // Tạo một đối tượng làm nhiệm vụ download image.
        DownloadImageTask task = new DownloadImageTask(this.imageView, getApplicationContext(), new Item("http://1.bp.blogspot.com/-sufWqm9brog/Wfnt4cT2RUI/AAAAAAAAXgY/3t3IG2879KcYv7tWjNcKVc0vY-CFdq_BgCHMYCw/s0/bia_fb.jpg?imgmax=0","1"));


        // Thực thi nhiệm vụ (Truyền vào URL).
        task.execute();

    }
}
