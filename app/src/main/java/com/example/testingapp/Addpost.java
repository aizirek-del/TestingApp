package com.example.testingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Addpost extends AppCompatActivity {

    private TextInputEditText mEditTitle;
    private TextInputEditText mEditBody;
    private TextInputEditText mEditId;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addpost);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Добавление");
        initView();

    }

    private void initView() {
        mEditTitle =findViewById(R.id.edit_title);
        mEditBody = findViewById(R.id.edit_body);
        mEditId = findViewById(R.id.edit_id);


    }

    public void sendBtn(View v) {

        String title = mEditTitle.getText().toString();
        String body = mEditBody.getText().toString();
        int sId = mEditId.getId();


        MyList mL =new MyList();
        mL.title = title;
        mL.body = body;
        mL.id =sId;

        Random  random = new Random();
        int val  = random.nextInt(50);
        mEditId.setText(Integer.toString(val));

        Log.e("check","works or  not!");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PostApi api = retrofit.create(PostApi.class);
        api.POST(mL).enqueue(new Callback<MyList>(){
            @Override
            public void onResponse(Call<MyList> call, Response<MyList> response) {

                Log.e("onResponse", "code: " + response.code());
                Log.e("onResponse", "message: " + response.message());
                if (response.code() == 200) {
                    Toast.makeText(Addpost.this, "Post добавлен", Toast.LENGTH_SHORT).show();
                    Addpost.this.finish();
                }

            }

            @Override
            public void onFailure(Call<MyList> call, Throwable t) {
                Log.e("onFailure", t.toString());

            }
        });



    }
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }



}