package com.example.pqanh.myapp2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.pqanh.myapp2.custom.CustomListAdapter;
import com.example.pqanh.myapp2.custom.Item;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class Activity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

//        // Set fullscreen
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//
//        // Loại bỏ tiêu đề.
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        List<Item> image_details = getListData();
        ListView listView = (ListView) findViewById(R.id.id_atv3_listView);
        listView.setAdapter(new CustomListAdapter(getApplicationContext(), image_details, getWindowManager().getDefaultDisplay().getWidth(), getWindowManager().getDefaultDisplay().getHeight()));

    }
    private  List<Item> getListData() {
        List<Item> list = new ArrayList<Item>();
        Item item1 = new Item("http://1.bp.blogspot.com/-sufWqm9brog/Wfnt4cT2RUI/AAAAAAAAXgY/3t3IG2879KcYv7tWjNcKVc0vY-CFdq_BgCHMYCw/s0/bia_fb.jpg?imgmax=0", "1");
        Item item2 = new Item("http://1.bp.blogspot.com/-4uYShhFi4mw/Wfnt6jYgBRI/AAAAAAAAXgk/PQKtKh9GdYo_5riT75M0SMVaers1BgewwCHMYCw/s0/000a.jpg?imgmax=0", "2");
        Item item3 = new Item("http://1.bp.blogspot.com/-lb5ZHvKga_o/Wfnt8YsGUBI/AAAAAAAAXgo/Sgb70W-nLEgwjGKz4JrvfO7gAV-ORcF_ACHMYCw/s0/IMG_1971.jpg?imgmax=0", "3");

//        Item item1 = new Item("mot", "1");
//        Item item2 = new Item("hai", "2");
//        Item item3 = new Item("ba", "3");

        list.add(item1);
        list.add(item2);
        list.add(item3);

        return list;
    }
}
