package com.example.testingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailedPost extends AppCompatActivity {

    private TextView mTextId;
    private TextView mTextTitle;
    private TextView mTextBody;

    private Retrofit mRetrofit;
    private int mPostId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_post);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Подробнее");
        initView();

        mRetrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        mPostId = extras.getInt("userId");

        getData();

    }

    public void getData() {
        PostApi api = mRetrofit.create(PostApi.class);
        api.GET(mPostId).enqueue(new Callback<MyList>(){

            @Override
            public void onResponse(Call<MyList> call, Response<MyList> response) {
                Log.e("onResponse", "code: " + response.code());
                Log.e("onResponse", "message: " + response.message());
                initPost(response.body());
            }

            @Override
            public void onFailure(Call<MyList> call, Throwable throwable) {
                Log.e("onFailure", throwable.toString());
            }
        });
    }

    private void initPost(MyList body) {
//        mTextId.setText(String.valueOf(body.id));
        mTextTitle.setText(body.title);
        mTextBody.setText(body.body);
    }
    private void initView() {
//       mTextId = findViewById(R.id.text_id);
        mTextTitle = findViewById(R.id.text_title);
        mTextBody = findViewById(R.id.text_body);

    }


}
