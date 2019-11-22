package com.example.news;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.List;

public class SearchNews extends AppCompatActivity {

    private RecyclerView recyclerview;
    private String token="zpbyjki48l39tqnxksgiffygnoxwsqhihzyuvjjk";
    private NewsAdapter adapter;
    private EditText etTickers;
    // GET_API_KEY

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        recyclerview = findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NewsAdapter(this);
        recyclerview.setAdapter(adapter);
        etTickers = findViewById(R.id.et_tickers);
        findViewById(R.id.search_iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ticker = etTickers.getText().toString();
                if (TextUtils.isEmpty(ticker)){
                    return;
                }
                query(ticker);
            }
        });
    }


    private void query(String tickers){
        String url = "https://stocknewsapi.com/api/v1?tickers="+tickers+"&items=50&token="+token;
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            } };
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("news",response);
                Gson gson = new Gson();
                NewsData newsData = gson.fromJson(response, NewsData.class);
                List<News> news = newsData.getData();
                adapter.setList(news);

            } };

        StringRequest stringRequest =
                new StringRequest(Request.Method.GET, url, responseListener, errorListener);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}