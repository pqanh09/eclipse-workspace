package com.example.pqanh.myapp2.custom;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by pqanh on 16-03-18.
 */

public class DownloadImageTask extends AsyncTask<Void, Void, Bitmap> {
    private Context context;
    private Item item;
    private ImageView imageView;

    public DownloadImageTask(ImageView imageView, Context context, Item item) {
        this.imageView = imageView;
        this.context = context;
        this.item = item;
    }

    @Override
    protected Bitmap doInBackground(Void... params) {
        if(item == null  || context == null){
            return null;
        }
        String imageUrl = item.getUrlImg();

        InputStream in = null;
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();

            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.connect();
            int resCode = httpConn.getResponseCode();
            Log.i("DownloadImageTask", "resCode: " + resCode);
            if (resCode == HttpURLConnection.HTTP_OK) {
                in = httpConn.getInputStream();
            } else {
                Log.i("DownloadImageTask", "Download img successfully!!!");
                return null;
            }
            Bitmap bitmap = BitmapFactory.decodeStream(in);
            Log.i("DownloadImageTask", "Download img "+ item.getPage() +"successfully!!!");
            //SystemClock.sleep(4000);
            int width = Integer.valueOf(this.imageView.getWidth());
            int height = Integer.valueOf(this.imageView.getHeight());
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@: " + width + ":"+ height);
            return bitmap;
        } catch (Exception e) {
            Toast.makeText(context, "Error. See log",
                    Toast.LENGTH_LONG).show();
            Log.e("DownloadImageTask", "Failed to fetch data!", e);
        } finally {
            IOUtils.closeQuietly(in);
        }
        return null;
    }


    // Khi nhiệm vụ hoàn thành, phương thức này sẽ được gọi.
    // Download thành công, update kết quả lên giao diện.
    @Override
    protected void onPostExecute(Bitmap result) {
        if (result != null) {
            this.imageView.setImageBitmap(result);
        } else {
            Toast.makeText(context, "Failed to fetch data!",
                    Toast.LENGTH_LONG).show();
            Log.e("DownloadImageTask", "Failed to fetch data!");
        }
    }
}
