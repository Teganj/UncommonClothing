package com.teganjennings.uncommonclothing.Activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.teganjennings.uncommonclothing.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class SearchActivity extends AppCompatActivity {

    Button searchBtn;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchBtn = findViewById(R.id.btnSearch);
        searchView = findViewById(R.id.searchView);

        // If search button is clicked, send the GET request
        searchBtn.setOnClickListener(view ->{
            // Retrieving the text from the search bar
            CharSequence query = searchView.getQuery();
            String queryStr = query.toString();

            // Instantiate the RequestQueue.
            RequestQueue queue = Volley.newRequestQueue(this);
            String url ="../../../res/drawable" + queryStr;

            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
                            // Toast.makeText(SearchActivity.this, "Response is: " +
                            //        response.substring(0,500), Toast.LENGTH_SHORT).show();
                            try {
                                // Parsing through the entire JSONObject of the card received for
                                // the 'images' attribute
                                JSONObject jsonObject = new JSONObject(response);
                                JSONObject dataObject = jsonObject.getJSONObject("data");
                                JSONObject imagesObject = dataObject.getJSONObject("images");

                                // Retrieve imageURL from the JSONObject
                                String imageURL = imagesObject.get("large").toString();
                                // Logging the imageURL in the logs.
                                Log.i("JSON Object", imageURL);

                                // Calling method to display the image URL.
                                displayImage(imageURL);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(SearchActivity.this, "Error, could not find Clothing.",
                            Toast.LENGTH_SHORT).show();
                }
            });

            // Add the request to the RequestQueue.
            queue.add(stringRequest);

        });
    }

    // Create a bitmap from the image URL retrieved and set it to the imageView
    private void displayImage(String imageURL){
        try {
            ImageView image = findViewById(R.id.imageResult);
            Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(imageURL).getContent());
            image.setImageBitmap(bitmap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}