package com.spacepal.internal.app.model.response

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

open class Order : RealmObject() {
    @SerializedName("orderId")
    var orderId: String? = null
    @SerializedName("isCompleted")
    var isCompleted: Boolean = false
    @SerializedName("priority")
    var priority: Int = 0
}
