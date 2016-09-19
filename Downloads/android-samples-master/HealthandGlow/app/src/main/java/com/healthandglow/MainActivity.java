package com.healthandglow;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.healthandglow.utils.AppConstants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private ProgressDialog progressDialog;
    public GridAdapter mAdapter;
    public static SwipeRefreshLayout swipeLayout;
    public static RecyclerView recyclerView;
    public static GridLayoutManager gridLayoutManager;
    public static ArrayList<FilesItem> filesArrayList;
    public static RelativeLayout rl_no_files;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        filesArrayList = new ArrayList<FilesItem>();

        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);

        gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        gridLayoutManager.setSmoothScrollbarEnabled(true);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        rl_no_files = (RelativeLayout) findViewById(R.id.rl_no_files);

        try {
            if (filesArrayList.size() == 0) {
                rl_no_files.setVisibility(View.VISIBLE);
                swipeLayout.setVisibility(View.GONE);

                makeSampleHttpRequest();
            } else {
                mAdapter = new GridAdapter(getApplicationContext(), filesArrayList);
                recyclerView.setAdapter(mAdapter);
                rl_no_files.setVisibility(View.GONE);
                swipeLayout.setVisibility(View.VISIBLE);
                mAdapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
            rl_no_files.setVisibility(View.VISIBLE);
            swipeLayout.setVisibility(View.GONE);
            makeSampleHttpRequest();

        }


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.loading___));
        progressDialog.setCancelable(false);


    }


    private void makeSampleHttpRequest() {
        swipeLayout.setRefreshing(true);
        String url = AppConstants.SERVER_URL;


        JSONObject data = new JSONObject();

        try {
            data.put("appType", "ANDROID");
            data.put("appVersion", "2.0.0.1");
            data.put("batchSize", "20");
            data.put("categoryId", "NAILPOLISH");
            data.put("lastItemCount", "0");
            data.put("pinNumber", "560103");
        } catch (JSONException e) {
            e.printStackTrace();
        }


        JSONObject filter = new JSONObject();
        JSONArray selectedBrand = new JSONArray();
        JSONArray selectedCategory = new JSONArray();
        JSONArray selectedPrice = new JSONArray();
        try {
            filter.put("selectedBrand", selectedBrand);
            filter.put("selectedCategory", selectedCategory);
            filter.put("selectedPrice", selectedPrice);
            data.put("filter", filter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject sort = new JSONObject();
        try {
            sort.put("sortBy", "");
            sort.put("sortOrder", "");
            data.put("sort", sort);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, data, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                //TODO: handle success
                try {
                    if (response.getString("status").equals("Success")) {
                        if (response.has("data")) {
                            // For getting datalist
                            JSONObject dataArray = response.getJSONObject("data");
                            JSONArray filesArray = dataArray.getJSONArray("skuItems");

                            if (!filesArray.toString().equals("[]")) {
                                for (int o = 0; o < filesArray.length(); o++) {
                                    FilesItem file = new FilesItem();
                                    file.setSkuName(filesArray.getJSONObject(o).getString("skuName"));
                                    file.setSkuPrice(filesArray.getJSONObject(o).getString("skuPrice"));
                                    file.setSkuOfferPrice(filesArray.getJSONObject(o).getString("skuOfferPrice"));
                                    Log.d("files are", "" + file.getSkuName());
                                    JSONArray imagesArray = filesArray.getJSONObject(o).getJSONArray("skuImageUrls");
                                    file.setPhoto(imagesArray.getString(0));
                                    Log.d("skuImageUrls are", "" + file.getPhoto());
                                    filesArrayList.add(file);
                                }
                                mAdapter = new GridAdapter(getApplicationContext(), filesArrayList);
                                recyclerView.setAdapter(mAdapter);
                                mAdapter.notifyDataSetChanged();
                                rl_no_files.setVisibility(View.GONE);
                                swipeLayout.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                swipeLayout.setRefreshing(false);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                //TODO: handle failure
            }

        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");

                return headers;
            }
        };


        Volley.newRequestQueue(this).add(jsonRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       /* if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRefresh() {
        makeSampleHttpRequest();
    }
}
