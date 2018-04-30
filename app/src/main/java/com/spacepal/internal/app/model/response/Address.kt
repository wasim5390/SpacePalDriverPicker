package com.spacepal.internal.app.model.response

import io.realm.RealmObject

open class Address : RealmObject {
    var isPrimary: Boolean = false
    var googleAddress: String? = null
    var lat: String? = null
    var lng: String? = null

    constructor() {}

    constructor(isPrimary: Boolean, googleAddress: String, lat: String, lng: String) {
        this.isPrimary = isPrimary
        this.googleAddress = googleAddress
        this.lat = lat
        this.lng = lng
    }


}
