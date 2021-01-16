package com.frozenbits.lightswitchapp.Models

import com.google.gson.annotations.SerializedName


class LightSwitchResponse {
    @SerializedName("code")
    var code: Int = -99

    @SerializedName("code_details")
    var code_details: String? = null

    @SerializedName("message")
    var message: String? = null

    @SerializedName("status")
    var status: String? = null
}