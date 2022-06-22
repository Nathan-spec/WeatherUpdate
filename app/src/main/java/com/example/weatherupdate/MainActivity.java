package com.example.weatherupdate;

import static java.lang.System.err;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.CharArrayWriter;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    EditText etCity, etCountry;
    TextView Weatherdetails;
    //Specific url for the weather website
    private final String url = "https://api.openweathermap.org/data/2.5/weather";
    //App id
    private final String appid = "3ff35ad392d69104b19cbbecf129b7cd";
    DecimalFormat df = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etCity = findViewById(R.id.etCity);
        etCountry = findViewById(R.id.etCountry);
        Weatherdetails = findViewById(R.id.Weatherdetails);
    }

    public void getWeatherdetails(View view) {
        String tempUrl = "";
        String city = etCity.getText().toString().trim();
        String country = etCountry.getText().toString().trim();
        if (city.equals(""))
        {
            Weatherdetails.setText("Field cannot be empty");
        }
        else
        {
            if (country.equals(""))
            {
                tempUrl = url + "?q=" + city + "," + country + "&appid=" + appid;
            }
            else
            {
                tempUrl = url + "?q=" + city + "&appid=" + appid;
            }
            StringRequest stringRequest = new StringRequest(Request.Method.POST, tempUrl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("response", response);

                },  Response.ErrorListener()
                {

                    @Override
                            public void onErrorResponse(VolleyError err)
                    {
                        Toast.makeText(makeText(getApplicationContext()), err.toString().trim(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);

        }
    }
}