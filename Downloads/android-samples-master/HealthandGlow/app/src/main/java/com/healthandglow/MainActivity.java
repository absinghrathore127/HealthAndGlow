package com.healthandglow;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.healthandglow.utils.AppConstants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;
    private Button button;
    JsonObjectRequest jsonObjRequest;
    private RequestQueue mVolleyQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //createVendor();
                makeSampleHttpRequest();
            }
        });
        /* GridView gridview = (GridView) findViewById(R.id.gridview);
         gridview.setAdapter(new DataAdapter(this));*/

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.loading___));
        progressDialog.setCancelable(false);

    }

    /*public void getServicesTask() {
       progressDialog.show();

        StringRequest sr = new StringRequest(Request.Method.POST, AppConstants.SERVER_URL + "categoryNew?", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject response_obj = new JSONObject(response);
                    if (response_obj.getString("status").equals("Success")) {
                        if (response_obj.has("data")) {
                            JSONObject result_obj = response_obj.getJSONObject("data");
                            Log.d("servicesArray", "" + result_obj);
                        }

                    } else
                        Toast.makeText(getApplicationContext(), R.string.error_loading, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                progressDialog.dismiss();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        try {

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        progressDialog.dismiss();
                        // for no internet error
                        if (error instanceof NoConnectionError)
                            Toast.makeText(getApplicationContext(), R.string.network_error, Toast.LENGTH_SHORT).show();
                        else // for server error
                            Toast.makeText(getApplicationContext(), R.string.server_error, Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("appType", "ANDROID");
                params.put("appVersion", "2.0.0.1");
                params.put("batchSize", "20");
                params.put("categoryId", "NAILPOLISH");
                params.put("lastItemCount", "0");
                params.put("pinNumber", "560103");

                JSONObject filter = new JSONObject();
                JSONObject selectedBrand = new JSONObject();
                JSONObject selectedCategory = new JSONObject();
                JSONObject selectedPrice = new JSONObject();
                try {
                    filter.put("selectedBrand", selectedBrand);
                    filter.put("selectedCategory", selectedCategory);
                    filter.put("selectedPrice", selectedPrice);
                    params.put("filter", filter.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JSONObject sort = new JSONObject();
                try {
                    sort.put("sortBy", "");
                    sort.put("sortOrder", "");
                    params.put("sort", sort.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return params;
            }
        };

        // Adding request to request queue
        HealthAndGlowApplication.getInstance().addToRequestQueue(sr, "categoryNew");
    }*/

    // For calling Apply Coupon API
    public void createVendor() {

        progressDialog.show();

        StringRequest sr = new StringRequest(Request.Method.POST, AppConstants.SERVER_URL + "categoryNew?", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                } catch (Exception e) {
                    e.printStackTrace();
                }

                progressDialog.dismiss();
                try {
                    JSONObject response_obj = new JSONObject(response);
                    if (response_obj.getString("status").equals("Success")) {
                        if (response_obj.has("data")) {
                            JSONObject result_obj = response_obj.getJSONObject("data");
                            Log.d("servicesArray", "" + result_obj);
                        }

                    } else
                        Toast.makeText(getApplicationContext(), response_obj.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), R.string.server_error, Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        try {

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        progressDialog.dismiss();
                        // for no internet error
                        if (error instanceof NoConnectionError)
                            Toast.makeText(getApplicationContext(), R.string.network_error, Toast.LENGTH_SHORT).show();
                        else // for server error
                            Toast.makeText(getApplicationContext(), R.string.server_error, Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("appType", "ANDROID");
                params.put("appVersion", "2.0.0.1");
                params.put("batchSize", "20");
                params.put("categoryId", "NAILPOLISH");
                params.put("lastItemCount", "0");
                params.put("pinNumber", "560103");

                JSONObject filter = new JSONObject();
                JSONArray selectedBrand = new JSONArray();
                JSONArray selectedCategory = new JSONArray();
                JSONArray selectedPrice = new JSONArray();
                try {
                    filter.put("selectedBrand", selectedBrand);
                    filter.put("selectedCategory", selectedCategory);
                    filter.put("selectedPrice", selectedPrice);
                    params.put("filter", filter.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JSONObject sort = new JSONObject();
                try {
                    sort.put("sortBy", "");
                    sort.put("sortOrder", "");
                    params.put("sort", sort.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return params;
            }


            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                // do not add anything here
                return headers;
            }
        };

    // Adding request to request queue
    HealthAndGlowApplication.getInstance().addToRequestQueue(sr, "categoryNew");
}
    private void makeSampleHttpRequest() {

        String url = "http://119.81.82.197:9090/hngeCommerceWebservice/rest/product/categoryNew";

        Map<String, String> params = new HashMap();
        params.put("appType", "ANDROID");
        params.put("appVersion", "2.0.0.1");
        params.put("batchSize", "20");
        params.put("categoryId", "NAILPOLISH");
        params.put("lastItemCount", "0");
        params.put("pinNumber", "560103");

        JSONObject filter = new JSONObject();
        JSONArray selectedBrand = new JSONArray();
        JSONArray selectedCategory = new JSONArray();
        JSONArray selectedPrice = new JSONArray();
        try {
            filter.put("selectedBrand", selectedBrand);
            filter.put("selectedCategory", selectedCategory);
            filter.put("selectedPrice", selectedPrice);
            params.put("filter", filter.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject sort = new JSONObject();
        try {
            sort.put("sortBy", "");
            sort.put("sortOrder", "");
            params.put("sort", sort.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject parameters = new JSONObject(params);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                //TODO: handle success
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                //TODO: handle failure
            }

        }){

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");

                return headers;
            }
            };


        Volley.newRequestQueue(this).add(jsonRequest);
    }

}
