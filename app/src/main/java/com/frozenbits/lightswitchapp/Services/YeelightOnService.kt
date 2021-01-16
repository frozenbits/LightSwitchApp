package com.frozenbits.lightswitchapp.Services

import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import com.frozenbits.lightswitchapp.Helper.Token
import com.frozenbits.lightswitchapp.Interfaces.NestService
import com.frozenbits.lightswitchapp.MainActivity
import com.frozenbits.lightswitchapp.Models.NestResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class YeelightOnService : TileService() {

    private lateinit var retrofit: Retrofit
    private lateinit var service: NestService

    override fun onClick() {
        super.onClick()
        val call = service.getYeelightTurnOnResult()
        enqueueBuilder(call)
    }

    override fun onTileRemoved() {
        super.onTileRemoved()

    }

    override fun onTileAdded() {
        super.onTileAdded()
        val tile = qsTile
        tile.label = "Yeelight ON"
        tile.state = Tile.STATE_ACTIVE
        tile.updateTile()
    }

    override fun onStartListening() {
        super.onStartListening()
        retrofit = Retrofit.Builder()
            .baseUrl(Token.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(NestService::class.java)
    }

    fun enqueueBuilder(call: Call<NestResponse>) {
        call.enqueue(object : Callback<NestResponse> {
            override fun onResponse(call: Call<NestResponse>, response: Response<NestResponse>) {
            }

            override fun onFailure(call: Call<NestResponse>, t: Throwable) {
            }
        })
    }
}