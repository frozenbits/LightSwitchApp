package com.frozenbits.lightswitchapp.Interfaces

import com.frozenbits.lightswitchapp.Models.NestResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NestService {
    @GET("authtest")
    fun getAuthTestResult(@Header("Authorization") auth: String): Call<NestResponse>

    @GET("control/yeelight/turn_on")
    fun getYeelightTurnOnResult(): Call<NestResponse>

    @GET("control/yeelight/turn_off")
    fun getYeelightTurnOffResult(): Call<NestResponse>

    @GET("control/yeelight/toggle_power")
    fun getYeelightTogglePowerResult(): Call<NestResponse>

    @GET("control/yeelight/set_brightness?")
    fun getYeelightSetBrightnessResult(@Query("brightness_value") brightness_value: Int): Call<NestResponse>

    @GET("control/yeelight/set_rgb?")
    fun getYeelightSetRGBResult(@Query("r_value") r_value: Int, @Query("g_value") g_value: Int, @Query("b_value") b_value: Int): Call<NestResponse>

    @GET("control/yeelight/set_hsv?")
    fun getYeelightSetHSVResult(@Query("h_value") h_value: Int, @Query("s_value") s_value: Int): Call<NestResponse>

    @GET("control/yeelight/set_color_temp?")
    fun getYeelightSetColorTempResult(@Query("temp_value") temp_value: Int): Call<NestResponse>

    @GET("control/yeelight/change_target_ip?")
    fun getYeelightChangeTargetIPResult(@Query("target_ip") target_ip: String): Call<NestResponse>

    @GET("control/yeelight/get_power_status")
    fun getYeelightPowerStatusResult(): Call<NestResponse>

    @GET("control/led_strip/turn_on")
    fun getLedStripTurnOnResult(): Call<NestResponse>

    @GET("control/led_strip/turn_off")
    fun getLedStripTurnOffResult(): Call<NestResponse>

    @GET("control/led_strip/toggle_power")
    fun getLedStripTogglePowerResult(): Call<NestResponse>

    @GET("control/led_strip/set_rgb?")
    fun getLedStripSetRGBResult(@Query("r_value") r_value: Int, @Query("g_value") g_value: Int, @Query("b_value") b_value: Int): Call<NestResponse>

    @GET("control/led_strip/get_power_status")
    fun getLedStripPowerStatusResult(): Call<NestResponse>
}