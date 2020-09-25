package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Step 1: add dependencies
        // step 2: First create a java todo object
        // step 3: Create API Client class
        // step 4: Create Api Interface
        // step 5: Add Internet Permissions in mainfest.xml file

        apiInterface=ApiClient.getClient().create(ApiInterface.class);

    }

    public void getTodos(View view){
        // Get all the data
        Call<List<Todo>> call=apiInterface.getTodos();
        call.enqueue(new Callback<List<Todo>>() {
            @Override
            public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {
             Log.e(TAG,"onResponse :"+response.body());
            }

            @Override
            public void onFailure(Call<List<Todo>> call, Throwable t) {
                Log.e(TAG,"onResponse :"+t.getLocalizedMessage());
            }
        });

    }
    // Get Data based on ID
    public void getTodosUsingRouteParameter(View view){
        Call<Todo> todoCall=apiInterface.getTodo(3);
        todoCall.enqueue(new Callback<Todo>() {
            @Override
            public void onResponse(Call<Todo> call, Response<Todo> response) {
                Log.e(TAG,"onResponse :"+response.body());
            }

            @Override
            public void onFailure(Call<Todo> call, Throwable t) {
                Log.e(TAG,"onResponse :"+t.getLocalizedMessage());
            }
        });
    }
    // Get data based on userdId and completed
    public void getTodosUsingQuery(View view){
       Call<List<Todo>> listCall=apiInterface.getTodosUsingQuery(2,false);
       listCall.enqueue(new Callback<List<Todo>>() {
           @Override
           public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {
               Log.e(TAG,"onResponse :"+response.body());
           }

           @Override
           public void onFailure(Call<List<Todo>> call, Throwable t) {
               Log.e(TAG,"onResponse :"+t.getLocalizedMessage());
           }
       });
    }
    // POST data to API
    public void postTodo(View view){
       Todo todo=new Todo(4,"Ashis Kumar Patel",false);
       Call<Todo> todoCall=apiInterface.postTodo(todo);
       todoCall.enqueue(new Callback<Todo>() {
           @Override
           public void onResponse(Call<Todo> call, Response<Todo> response) {
               Log.e(TAG,"onResponse :"+response.body());
           }

           @Override
           public void onFailure(Call<Todo> call, Throwable t) {
               Log.e(TAG,"onResponse :"+t.getLocalizedMessage());
           }
       });
    }
}

