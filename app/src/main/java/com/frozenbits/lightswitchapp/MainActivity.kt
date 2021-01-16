package com.frozenbits.lightswitchapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.frozenbits.lightswitchapp.databinding.ActivityMainBinding
import com.frozenbits.lightswitchapp.Helper.Token
import com.frozenbits.lightswitchapp.Helper.LightSwitchHelper.Companion.service
import com.frozenbits.lightswitchapp.Models.LightSwitchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        binding.btnLedStripToggle.setOnClickListener(this)
        binding.btnAuthtest.setOnClickListener(this)
        binding.btnGetPowerStatus.setOnClickListener(this)

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

    fun enqueueBuilder(call: Call<LightSwitchResponse>) {
        call.enqueue(object: Callback<LightSwitchResponse> {
            override fun onResponse(call: Call<LightSwitchResponse>, response: Response<LightSwitchResponse>) {
                if (response.code() == 200) {
                    val nestResponse = response.body()
                    binding.tvCode.text = nestResponse?.code.toString()
                    binding.tvCodeDetails.text = nestResponse?.code_details
                    binding.tvMessage.text = nestResponse?.message
                    binding.tvStatus.text = nestResponse?.status
                }
            }
            override fun onFailure(call: Call<LightSwitchResponse>, t: Throwable) {
                binding.tvStatus.text = t.message
            }
        })
    }
}