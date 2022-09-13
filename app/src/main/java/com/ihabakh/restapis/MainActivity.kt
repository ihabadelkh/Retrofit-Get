package com.ihabakh.restapis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ihabakh.restapis.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit = Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiInterface = retrofit.create(ApiInterface::class.java)
        val result = apiInterface.getPost()
        result.enqueue(object: Callback<PostModel> {
            override fun onResponse(call: Call<PostModel>, response: Response<PostModel>) {
                binding.tvBody.text = response.body()?.body.toString()
            }

            override fun onFailure(call: Call<PostModel>, t: Throwable) {
                binding.tvBody.text = t.message!!
            }

        })
    }
}