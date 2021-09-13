package com.example.testingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ListAdapter mAdapter;
    private List<MyList> mItems = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        setTitle("Список");
        mAdapter = new ListAdapter(this, mItems);
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

        initView();

        PostClickListener listener = new PostClickListener(){
            @Override
            public void OnClick(int position) {
                MyList item = mItems.get(position);

                Intent intent = new Intent(MainActivity.this, DetailedPost.class);
                intent.putExtra("post_id", item.id);
                startActivity(intent);

                Log.e("onClick", "position: " + position);

            }


        };

        getData();
mAdapter.setOnItemClickListener(listener);
    }



    @Override
    protected void onRestart() {
        getData();
        Log.e("works! ", "check");
        super.onRestart();
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    private void getData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PostApi api = retrofit.create(PostApi.class);
        api.getAllPosts().enqueue(new Callback<List<MyList>>() {
            @Override
            public void onResponse(Call<List<MyList>> call, Response<List<MyList>> response) {
                Log.e("onResponse", "code: " + response.code());
                Log.e("onResponse", "string: " + response.toString());
                initData(response.body());

            }

            @Override
            public void onFailure(Call<List<MyList>> call, Throwable t) {

            }
        });

    }

    private void initData(List<MyList> body) {
        mItems.clear();
        mItems.addAll(body);

        mAdapter.notifyDataSetChanged();


    }

    private void initView() {
        mRecyclerView = findViewById(R.id.recycler_view);
    }

    public void addPost(View view) {
        Intent intent = new Intent(this, Addpost.class);
        startActivity(intent);

    }
}