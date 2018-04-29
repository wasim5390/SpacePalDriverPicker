package au.com.roadhuose.spacepaldriverpicker.model

import com.google.gson.annotations.SerializedName

class Role {

    @SerializedName("name")
    var name: String? = null

    @SerializedName("id")
    var id: String? = null

    @SerializedName("htmlDescription")
    var htmlDescription: String? = null

    override fun toString(): String {
        return "Role{" +
                "name = '" + name + '\''.toString() +
                ",id = '" + id + '\''.toString() +
                ",htmlDescription = '" + htmlDescription + '\''.toString() +
                "}"
    }
}