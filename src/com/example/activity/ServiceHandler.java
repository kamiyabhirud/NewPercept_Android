package com.example.activity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class ServiceHandler {

   static String response = null;
   public final static int GET = 1;
   public final static int POST = 2;
 
   public ServiceHandler() {

   }

   /**
    * Making service call
    * @url - url to make request
    * @method - http request method
    * */
   public String makeServiceCall(String url, int method) {
       return this.makeServiceCall(url, method, null);
   }

   /**
    * Making service call
    * @url - url to make request
    * @method - http request method
    * @params - http request params
    * */
   public String makeServiceCall(String url, int method,
           List<NameValuePair> params) {
       try { System.out.println("1");
           // http client
           DefaultHttpClient httpClient = new DefaultHttpClient();
           System.out.println("2");
           HttpEntity httpEntity = null;
           System.out.println("3");
           HttpResponse httpResponse = null;
           System.out.println("4");
           // Checking http request method type
           if (method == POST) {
               HttpPost httpPost = new HttpPost(url);
               // adding post params
               if (params != null) {
                   httpPost.setEntity(new UrlEncodedFormEntity(params));
                   System.out.println("5");
               }

               httpResponse = httpClient.execute(httpPost);

           } else if (method == GET) {
               // appending params to url
               if (params != null) {
            	   
            	   System.out.println("10");
                   String paramString = URLEncodedUtils .format(params, "UTF-8");
                   url += "?" + paramString;
                   System.out.println("6 param string" +paramString);
              }
               System.out.println("11");
               HttpGet httpGet = new HttpGet(url);
               System.out.println("7");
               httpResponse = httpClient.execute(httpGet);
               System.out.println("8");
           }
           httpEntity = httpResponse.getEntity();
        //   System.out.println("httpEntity============== : "+ httpEntity );
           response = EntityUtils.toString(httpEntity);
          // System.out.println("response===============>>>>>>>>" +response);
       } catch (UnsupportedEncodingException e) {
           e.printStackTrace();
       } catch (ClientProtocolException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       }
        
       return response;

   }
}