package au.com.roadhuose.spacepaldriverpicker.model.realm

import android.app.Activity
import android.app.Application
import android.support.v4.app.Fragment
import au.com.roadhuose.spacepaldriverpicker.model.Role
import au.com.roadhuose.spacepaldriverpicker.model.User
import au.com.roadhuose.spacepaldriverpicker.model.response.Order
import io.realm.Realm
import io.realm.RealmObject

class RealmController(application: Application) {
//    val realm: Realm
//
//    val orders: List<Order>
//        get() = realm.copyFromRealm<E>(realm.where(Order::class.java).findAll())
//
//    val roles: List<Role>
//        get() = realm.copyFromRealm<E>(realm.where(Role::class.java!!).findAll())
//
//    init {
//        realm = Realm.getDefaultInstance()
//    }
//
//    /*********** Saving object must be RealmObject  */
//    fun save(mLocation: Any) {
//        realm.beginTransaction()
//        realm.copyToRealm(mLocation as RealmObject)
//        realm.commitTransaction()
//    }
//
//
//    fun saveRoles(roleList: List<Role>) {
//        realm.executeTransaction { realm ->
//            realm.where(Role::class.java!!).findAll().deleteAllFromRealm()
//            realm.insert(roleList)
//        }
//
//    }
//
//    fun saveOrders(orderList: List<Order>) {
//        realm.executeTransaction { realm ->
//            realm.where(Order::class.java!!).findAll().deleteAllFromRealm()
//            realm.copyToRealm(orderList)
//        }
//
//    }
//
//
//    //Refresh the realm istance
//    fun refresh() {
//
//        realm.refresh()
//    }
//
//    companion object {
//
//        var instance: RealmController? = null
//            private set
//
//        fun with(fragment: Fragment): RealmController {
//
//            if (instance == null) {
//                instance = RealmController(fragment.activity!!.application)
//            }
//            return instance
//        }
//
//        fun with(activity: Activity): RealmController {
//
//            if (instance == null) {
//                instance = RealmController(activity.application)
//            }
//            return instance
//        }
//
//        fun with(application: Application): RealmController {
//
//            if (instance == null) {
//                instance = RealmController(application)
//            }
//            return instance
//        }
//    }
//

}