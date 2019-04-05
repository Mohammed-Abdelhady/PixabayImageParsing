package com.programming.congar.pixabayimageparsing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ImageAdapter mImageAdapter;
    private ArrayList<Image> mImages;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mImages = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);

        parseJson();
    }

    private void parseJson() {

        String url = "https://pixabay.com/api/?key=12103504-591b87ab2aa7e893ed53c8b38&q=cats&image_type=photo&pretty=true";
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray hitsArray = response.getJSONArray("hits");

                            for(int i = 0 ; i < hitsArray.length() ; i++){
                                JSONObject hit = hitsArray.getJSONObject(i);

                                String creatorName = hit.getString("user");
                                String imageUrl = hit.getString("webformatURL");
                                int likes = hit.getInt("likes");

                                mImages.add(new Image(imageUrl , creatorName , likes));
                            }
                            mImageAdapter = new ImageAdapter(MainActivity.this , mImages);
                            mRecyclerView.setAdapter(mImageAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mRequestQueue.add(request);
    }
}
