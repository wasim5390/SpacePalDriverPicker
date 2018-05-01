package com.spacepal.internal.app.model


import com.google.gson.annotations.SerializedName


data class ChangePassword(

        @field:SerializedName("oldPassword")
        val oldPassword: String? = null,
        @field:SerializedName("newPassword")
        val newPassword: String? = null,
        @field:SerializedName("newPasswordConfirm")
        val newPasswordConfirm: String? = null


)