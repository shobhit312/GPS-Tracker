package com.example.shobhit.demo;

import android.os.AsyncTask;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MyAsyncTask extends AsyncTask<String, Integer, Double> {

    @Override
    protected Double doInBackground(String... params) {
        String latitudetxt = params[0];
        String longitudetxt = params[1];
        String type = params[2];
        //String time = params[3];
        postData(latitudetxt,longitudetxt,type);
        return null;
    }

    protected void onPostExecute(Double result){
       // pb.setVisibility(View.GONE);
       // Toast.makeText(getApplicationContext(), "command sent", Toast.LENGTH_LONG).show();
    }

    protected void onProgressUpdate(Integer... progress){
        //pb.setProgress(progress[0]);
    }

    public void postData(String latitudetxt,String longitudetxt,String type) {
        HttpClient httpclient = new DefaultHttpClient();
        String str = "http://104.197.7.207/alerts/shobit/index.php/home?username=atalashutosh&latitude="+latitudetxt+"&longitude="+longitudetxt+"&concern="+type;
        HttpPost httppost = new HttpPost(str);
        try {

           HttpResponse response = httpclient.execute(httppost);

        } catch (ClientProtocolException e) {

        } catch (IOException e) {

        }
    }

}

