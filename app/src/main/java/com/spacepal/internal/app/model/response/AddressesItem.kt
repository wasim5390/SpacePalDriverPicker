package com.spacepal.internal.app.model.response


import com.google.gson.annotations.SerializedName

data class AddressesItem(

        @field:SerializedName("googleAddress")
        val googleAddress: String? = null,

        @field:SerializedName("lng")
        val lng: String? = null,

        @field:SerializedName("isPrimary")
        val isPrimary: Boolean? = null,

        @field:SerializedName("lat")
        val lat: String? = null
)