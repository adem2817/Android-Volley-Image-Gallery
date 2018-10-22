package com.androiddemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.androiddemo.adapter.ImageAdapter;
import com.androiddemo.com.androiddemo.listener.ItemClickListener;
import com.androiddemo.model.Resim;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ImageListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    public static final String EXTRA_URL = "imageUrl";
    private RequestQueue mQueue;
    private Context context = this;
    private ImageAdapter adapter;
    private ArrayList<Resim> resimList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_list);
        recyclerView = findViewById(R.id.recyclerView);
        mQueue = Volley.newRequestQueue(getApplicationContext());

        getImageList();
    }

    public void getImageList(){
        String url = "https://pixabay.com/api/?key=10466460-c02a7b278f618f22645a6a2a5&q=yellow+flowers&image_type=photo&pretty=true";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                resimList = new ArrayList<>();
                try {
                    JSONArray jsonArray = response.getJSONArray("hits");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject hit =  jsonArray.getJSONObject(i);
                        String imageUrl = hit.getString("webformatURL");
                        resimList.add(new Resim(imageUrl));
                    }

                    adapter = new ImageAdapter(getApplicationContext(), resimList, new ItemClickListener() {
                        @Override
                        public void onDetail(View view, int position) {
                            Resim resim = resimList.get(position);
                            Intent i = new Intent(ImageListActivity.this,Detail.class);
                            i.putExtra(EXTRA_URL,resim.getUrl());
                            startActivity(i);
                        }
                    });
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ImageListActivity.this);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(context, e+"", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "hata mesajı", Toast.LENGTH_SHORT).show();
            }

        });
        mQueue.add(request);
    }
}
