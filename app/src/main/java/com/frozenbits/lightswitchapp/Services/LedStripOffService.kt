package com.frozenbits.lightswitchapp.Services

import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import com.frozenbits.lightswitchapp.Helper.LightSwitchHelper.Companion.service
import com.frozenbits.lightswitchapp.Helper.LightSwitchHelper.Companion.enqueueBuilder

class LedStripOffService: TileService() {
    override fun onClick() {
        super.onClick()
        val call = service.getLedStripTurnOffResult()
        enqueueBuilder(call)
    }

    override fun onStartListening() {
        super.onStartListening()
        val tile = qsTile
        tile.label = "LED OFF"
        tile.state = Tile.STATE_INACTIVE
        tile.updateTile()
    }
}