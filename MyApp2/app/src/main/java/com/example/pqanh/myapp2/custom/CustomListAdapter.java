package com.example.pqanh.myapp2.custom;

import android.app.Activity;
import android.app.LauncherActivity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.util.Log;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.pqanh.myapp2.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pqanh on 15-03-18.
 */

public class CustomListAdapter extends BaseAdapter {
    public static final String TAG ="CustomListAdapter";

    private List<Item> listData = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private Context context;
    private int maxWidth;
    private int maxHeight;


    public CustomListAdapter(Context context,  List<Item> listData, int maxWidth, int maxHeight) {
        this.context = context;
        this.listData = listData;
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {

        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item_layout, parent, false);
            viewHolder = new ViewHolder(convertView, this.maxWidth, this.maxHeight);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Item item = this.listData.get(position);
        DownloadImageTask task = new DownloadImageTask(viewHolder.imageView, context, item);

        // Thực thi nhiệm vụ (Truyền vào URL).
        task.execute();

//        int imageId = this.getMipmapResIdByName(item.getUrlImg());
//
//        viewHolder.imageView.setImageResource(imageId);
        return convertView;



//        ImageView imgView;
//        if(convertView == null){
//            convertView = layoutInflater.inflate(R.layout.list_item_layout, parent, false);
//            imgView = new ImageView(context);
//            //can chỉnh lại hình cho đẹp
//            LinearLayout linearLayout = (LinearLayout) context.findViewById(R.id.id_atv3_layout);
//            imgView.setLayoutParams(new GridView.LayoutParams(200, 300));
//            imgView.setScaleType(ImageView.ScaleType.FIT_XY);
//            imgView.setPadding(8, 8, 8, 8);
//            convertView.setTag(imgView);
//        }else{
//            imgView = (ImageView) convertView.getTag();
//        }
//        Item item = this.listData.get(position);
//        DownloadImageTask task = new DownloadImageTask(imgView, context, item);
//
//        // Thực thi nhiệm vụ (Truyền vào URL).
//        task.execute();
//
//        return convertView;

    }
    // Tìm ID của Image ứng với tên của ảnh (Trong thư mục mipmap).
    public int getMipmapResIdByName(String resName)  {
        String pkgName = this.context.getPackageName();

        // Trả về 0 nếu không tìm thấy.
        int resID = this.context.getResources().getIdentifier(resName , "mipmap", pkgName);
        Log.i("CustomListView", "Res Name: "+ resName+"==> Res ID = "+ resID);
        return resID;
    }
    static class ViewHolder {
        ImageView imageView;
        public ViewHolder(View view, int maxWidth, int maxHeight) {
            imageView = (ImageView) view.findViewById(R.id.id_atv3_img);
            imageView.getLayoutParams().width = maxWidth - 16;
            imageView.getLayoutParams().height = maxHeight - 16;
//            imageView.setLayoutParams(new GridView.LayoutParams(300, 400));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        }
    }
}
