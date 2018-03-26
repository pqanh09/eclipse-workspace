package com.example.pqanh.myapp2;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.util.SortedList;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.pqanh.myapp2.resttest.ApiClient;
import com.example.pqanh.myapp2.resttest.ApiInterface;
import com.example.pqanh.myapp2.resttest.Chapter;
import com.example.pqanh.myapp2.resttest.ChapterResponseObject;
import com.example.pqanh.myapp2.resttest.Products;
import com.example.pqanh.myapp2.resttest.Team;
import com.example.pqanh.myapp2.resttest.TeamResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Activity2 extends AppCompatActivity {
    public static final String TAG ="TestGesture";

    private TextView textEvt1;
    private TextView textEvt2;
    private TabLayout tabLayout;
    // Bộ dò cử chỉ của người dùng.
    private GestureDetector gestureDetector;
    private Animation animationSlideUp;
    private Animation animationSlideDown;
    private boolean showTtbLayout = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        Log.i(TAG,"onCreate");

        this.textEvt1 = (TextView)this.findViewById(R.id.id_atv2_text1);
        this.textEvt2 = (TextView)this.findViewById(R.id.id_atv2_text2);
        this.tabLayout = (TabLayout)this.findViewById(R.id.id_atv2_tablayout);
        //tabLayout.setVisibility(View.INVISIBLE);
        animationSlideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up);
        animationSlideDown = AnimationUtils.loadAnimation(this, R.anim.slide_down);
        showTtbLayout = false;
        tabLayout.startAnimation(animationSlideUp);


        GestureDetector.OnGestureListener gestureListener = new MyOnGestureListener();
        GestureDetector.OnDoubleTapListener doubleTapListener = new MyOnDoubleTapListener();


        // GestureDetectorCompat(Context context, OnGestureListener listener)
        this.gestureDetector= new GestureDetector(this, gestureListener);

        this.gestureDetector.setOnDoubleTapListener(doubleTapListener);


        // Lấy ra đối tượng View gốc (Toàn bộ màn hình điện thoại).
        View rootView = this.findViewById(android.R.id.content).getRootView();

        // Sét bộ lắng nghe cho các sự kiện chạm vào bề mặt điện thoại.
        rootView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent me) {
                return gestureDetector.onTouchEvent(me);
            }
        });

        Log.e(TAG,"Running...");
        String URL_GET_PRODUCT = "http://35.226.84.34:8080/ComicService/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_GET_PRODUCT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface apiService = retrofit.create(ApiInterface.class);
        Call<ChapterResponseObject> call = apiService.getChapter();
        call.enqueue(new Callback<ChapterResponseObject>() {
            @Override
            public void onResponse(Call<ChapterResponseObject> call, Response<ChapterResponseObject> response) {
                Log.d("@@@@@@@@@@@ R: ", response.body().toString());
                ChapterResponseObject responseObj = response.body();
                Log.d("@@@@@@@@@@@ C: ", responseObj.toString());
//                List<Chapter> productsList = response.body();
//                for (int i = 0; i<productsList.size() ; i++) {
//                    productsList.add(productsList.get(i));
//                    Log.d("@@@@@@@@@@@", productsList.get(i).toString());
//                }
            }

            @Override
            public void onFailure(Call<ChapterResponseObject> call, Throwable t) {
                t.printStackTrace();
                Log.e(TAG, "@@@@@@@@@@@WWWWWWWWWW: " + t.getMessage());
            }
        });






//        Log.e(TAG,"Running...");
//        String URL_GET_PRODUCT = "http://dev.androidcoban.com/blog/";
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(URL_GET_PRODUCT)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        ApiInterface apiService = retrofit.create(ApiInterface.class);
//        Call<List<Products>> call = apiService.getAllProduct();
//        call.enqueue(new Callback<List<Products>>() {
//            @Override
//            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
//                List<Products> productsList = response.body();
//                for (int i = 0; i<productsList.size() ; i++) {
//                    productsList.add(productsList.get(i));
//                    Log.d("@@@@@@@@@@@", productsList.get(i).toString());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Products>> call, Throwable t) {
//                t.printStackTrace();
//                Log.e(TAG, "@@@@@@@@@@@WWWWWWWWWW: " + t.getMessage());
//            }
//        });



//        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
//
//        Call<TeamResponse> call = apiService.getAll();
//        call.enqueue(new Callback<TeamResponse>() {
//            @Override
//            public void onResponse(Response<TeamResponse> response, Retrofit retrofit) {
//                List<Team> movies = response.body().getResults();
//                for (Team t: movies) {
//                    Log.e("@@@@@@@@@@@", t.toString());
//                }
//            }
//            @Override
//            public void onFailure(Throwable t) {
//                t.printStackTrace();
//                Log.e("@@@@@@@@@@@ERRPOROROROROE: ", t.toString());
//            }
//        });



    }

    class MyOnGestureListener implements GestureDetector.OnGestureListener  {

        @Override
        public boolean onDown(MotionEvent e) {
            textEvt1.setText("onDown");
            textEvt2.setText(e.getX()+":"+ e.getY());
            Log.e(TAG, "onDown");
            return true;
        }

        @Override
        public void onShowPress(MotionEvent e) {
            textEvt1.setText("onShowPress");
            textEvt2.setText(e.getX()+":"+ e.getY());
            Log.e(TAG, "onShowPress");
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            textEvt1.setText("onSingleTapUp");
            textEvt2.setText(e.getX()+":"+ e.getY());
            Log.e(TAG, "onSingleTapUp");
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            textEvt1.setText("Scroll");
            textEvt2.setText(e1.getX()+":"+ e1.getY() +"  "+ e2.getX()+":"+ e2.getY());
            Log.e(TAG, "onScroll");
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            textEvt1.setText("onLongPress");
            textEvt2.setText(e.getX()+":"+ e.getY());
            Log.e(TAG, "onLongPress");
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            textEvt1.setText("onFling");
            textEvt2.setText(e1.getX() + ":" + e1.getY() + "  " + e2.getX() + ":" + e2.getY());
            Log.e(TAG, "onFling");
            return true;
        }


    }

    class MyOnDoubleTapListener implements GestureDetector.OnDoubleTapListener {

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            textEvt1.setText("onSingleTapConfirmed");
            textEvt2.setText(e.getX()+":"+ e.getY());
            Log.e(TAG, "onSingleTapConfirmed");
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            textEvt1.setText("onDoubleTap");
            textEvt2.setText(e.getX()+":"+ e.getY());
            Log.e(TAG, "onDoubleTap");
            if(showTtbLayout){
                tabLayout.startAnimation(animationSlideUp);
            } else {
                tabLayout.startAnimation(animationSlideDown);
            }
            showTtbLayout = !showTtbLayout;
//            if(tabLayout.getVisibility() == View.INVISIBLE) {
//                   Log.e("@@@@@@@@@@@", "VISIBLE");
////                tabLayout.setVisibility(View.VISIBLE);
//                tabLayout.startAnimation(animationSlideDown);
//            } else {
////                tabLayout.setVisibility(View.INVISIBLE);
//                Log.e("@@@@@@@@@@@", "INVISIBLE");
//                tabLayout.startAnimation(animationSlideUp);
//            }
            return true;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            textEvt1.setText("onDoubleTapEvent");
            textEvt2.setText(e.getX() + ":" + e.getY());
            Log.e(TAG, "onDoubleTapEvent");
            return true;
        }
    }
}
