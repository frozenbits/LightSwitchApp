package com.frozenbits.lightswitchapp.Services

import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import com.frozenbits.lightswitchapp.Helper.LightSwitchHelper.Companion.service
import com.frozenbits.lightswitchapp.Helper.LightSwitchHelper.Companion.enqueueBuilder

class LedStripOnService : TileService() {
    override fun onClick() {
        super.onClick()
        val call = service.getLedStripTurnOnResult()
        enqueueBuilder(call)
    }

    override fun onStartListening() {
        super.onStartListening()
        val tile = qsTile
        tile.label = "LED ON"
        tile.state = Tile.STATE_ACTIVE
        tile.updateTile()
    }
}