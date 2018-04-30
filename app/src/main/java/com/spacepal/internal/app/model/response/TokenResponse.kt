package com.spacepal.internal.app.model.response

import com.google.gson.annotations.SerializedName

class TokenResponse {
    @SerializedName("access_token")
    var accessToken: String? = null

    @SerializedName("refresh_token")
    var refreshToken: String? = null

    @SerializedName("token_type")
    var tokenType: String? = null

    @SerializedName("expires_in")
    var expiresIn: Int = 0

    override fun toString(): String {
        return "Response{" +
                "access_token = '" + accessToken + '\''.toString() +
                ",refresh_token = '" + refreshToken + '\''.toString() +
                ",token_type = '" + tokenType + '\''.toString() +
                ",expires_in = '" + expiresIn + '\''.toString() +
                "}"
    }

}
