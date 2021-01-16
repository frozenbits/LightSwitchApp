package com.frozenbits.lightswitchapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.frozenbits.lightswitchapp.Interfaces.NestService
import com.frozenbits.lightswitchapp.Models.NestResponse
import com.frozenbits.lightswitchapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.frozenbits.lightswitchapp.Helper.Token


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var retrofit: Retrofit
    private lateinit var service: NestService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        binding.btnLedStripToggle.setOnClickListener(this)
        binding.btnAuthtest.setOnClickListener(this)
        binding.btnGetPowerStatus.setOnClickListener(this)

        retrofit = Retrofit.Builder()
                .baseUrl(Token.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        service = retrofit.create(NestService::class.java)

        setContentView(view)
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.btn_ledStrip_toggle -> {
                val call = service.getYeelightTogglePowerResult()
                //val call_rgb = service.getLedStripSetRGBResult(255, 255, 255)
                enqueueBuilder(call)
            }

            R.id.btn_authtest -> {
                val call = service.getAuthTestResult("Bearer ${Token.TOKEN_KEY}")
                binding.tvStatus.text = "Authorizing..."
                enqueueBuilder(call)
            }

            R.id.btn_getPowerStatus -> {
                val call = service.getYeelightPowerStatusResult()
                enqueueBuilder(call)
            }
        }
    }

    fun enqueueBuilder(call: Call<NestResponse>) {
        call.enqueue(object: Callback<NestResponse> {
            override fun onResponse(call: Call<NestResponse>, response: Response<NestResponse>) {
                if (response.code() == 200) {
                    val nestResponse = response.body()
                    binding.tvCode.text = nestResponse?.code.toString()
                    binding.tvCodeDetails.text = nestResponse?.code_details
                    binding.tvMessage.text = nestResponse?.message
                    binding.tvStatus.text = nestResponse?.status
                }
            }
            override fun onFailure(call: Call<NestResponse>, t: Throwable) {
                binding.tvStatus.text = t.message
            }
        })
    }
}