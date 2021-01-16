package com.frozenbits.lightswitchapp.Helper

import com.frozenbits.lightswitchapp.Interfaces.LightSwitchService
import com.frozenbits.lightswitchapp.Models.LightSwitchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LightSwitchHelper {
    companion object {
        val retrofit: Retrofit  = Retrofit.Builder()
            .baseUrl(Token.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service: LightSwitchService = retrofit.create(LightSwitchService::class.java)

        // Only for use outside of layouts
        fun enqueueBuilder(call: Call<LightSwitchResponse>) {
            call.enqueue(object : Callback<LightSwitchResponse> {
                override fun onResponse(call: Call<LightSwitchResponse>, response: Response<LightSwitchResponse>) {
                }
                override fun onFailure(call: Call<LightSwitchResponse>, t: Throwable) {
                }
            })
        }
    }
}