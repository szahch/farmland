package com.alex.web;

import java.io.IOException;

import org.junit.Test;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PostTest {

	@Test
	public void okHttpTest() {
		OkHttpClient client = new OkHttpClient();
		RequestBody body = new FormBody.Builder()  
                .add("useName", "abc")//Ìí¼Ó¼üÖµ¶Ô  
                .add("usePwd", "321")  
                .build();  
		Request request = new Request.Builder()  
                .url("http://192.168.2.144:8080/Farmland/AA")  
                .post(body)  
                .build();  
		client.newCall(request).enqueue(new Callback() {
			
			public void onResponse(Call arg0, Response arg1) throws IOException {
				System.out.println("onResponse");
			}
			
			public void onFailure(Call arg0, IOException arg1) {
				System.out.println("onFailure");
			}
		});
		
	}

}
