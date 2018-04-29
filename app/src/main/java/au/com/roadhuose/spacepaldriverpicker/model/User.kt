package au.com.roadhuose.spacepaldriverpicker.model

import au.com.roadhuose.spacepaldriverpicker.model.response.Address
import com.google.gson.annotations.Expose
import io.realm.RealmList

class User { //: RealmObject()
    private var id: String? = null
    private var firstName: String? = null
    private var lastName: String? = null
    private var email: String? = null

    @Expose(serialize = false)
    private var password: String? = null

    @Expose(serialize = false)
    private var isSignIn: Boolean = false
    @Expose(serialize = false)
    private var token: String? = null
    private var isDisabled: Boolean = false
    private var roles: Array<String>? = null

    private var phoneNumber: String? = null
    private var isArchived: Boolean = false
    private var imageUrl: String? = null
    private var customerType: String? = null

    private var notes: List<String>? = null

    private var addresses: List<Address>? = null

    fun getPassword(): String? {
        return password
    }

    fun setPassword(password: String) {
        this.password = password
    }

    fun getToken(): String? {
        return token
    }

    fun setToken(token: String) {
        this.token = token
    }

    fun getId(): String? {
        return id
    }

    fun setId(id: String) {
        this.id = id
    }

    fun getFirstName(): String? {
        return firstName
    }

    fun setFirstName(firstName: String) {
        this.firstName = firstName
    }

    fun getLastName(): String? {
        return lastName
    }

    fun setLastName(lastName: String) {
        this.lastName = lastName
    }

    fun getEmail(): String? {
        return email
    }

    fun setEmail(email: String) {
        this.email = email
    }

    fun isDisabled(): Boolean {
        return isDisabled
    }

    fun setDisabled(disabled: Boolean) {
        isDisabled = disabled
    }

    fun getRoles(): ArrayList<String> {
        val mRoles = ArrayList<String>()
        for (role in roles!!) {
            mRoles.add(role)
        }
        return mRoles
    }

    fun setRoles(roles: Array<String>) {
        this.roles = roles
    }

    fun getPhoneNumber(): String? {
        return phoneNumber
    }

    fun setPhoneNumber(phoneNumber: String) {
        this.phoneNumber = phoneNumber
    }

    fun isArchived(): Boolean {
        return isArchived
    }

    fun setArchived(archived: Boolean) {
        isArchived = archived
    }

    fun getImageUrl(): String? {
        return imageUrl
    }

    fun setImageUrl(imageUrl: String) {
        this.imageUrl = imageUrl
    }

    fun getCustomerType(): String? {
        return customerType
    }

    fun setCustomerType(customerType: String) {
        this.customerType = customerType
    }

    fun getNotes(): List<String>? {
        return notes
    }

    fun setNotes(notes: RealmList<String>) {
        this.notes = notes
    }

    fun getAddresses(): List<Address>? {
        return addresses
    }

    fun setAddresses(addresses: RealmList<Address>) {
        this.addresses = addresses
    }

    fun isSignIn(): Boolean {
        return isSignIn
    }

    fun setSignIn(signIn: Boolean) {
        isSignIn = signIn
    }
}
