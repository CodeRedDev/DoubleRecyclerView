package de.codereddev.doublerecycler.realm

import io.realm.Realm
import io.realm.RealmModel

/**
 * Executes a block inside of a [Realm] transaction. The instance
 * is opened and closed automatically.
 */
internal inline fun databaseTransaction(crossinline block: Realm.() -> Unit) {
    DoubleRecyclerRealm.newRealmInstance().use { realm ->
        realm.refresh()
        realm.executeTransaction {
            block(it)
        }
    }
}

/**
 * Execute a [Realm] query and returns the copied
 * result of [Realm.copyFromRealm]. The [Realm]
 * instance is opened and closed automatically.
 */
internal inline fun <T : RealmModel> copyListFromDatabase(query: Realm.() -> List<T>): List<T> {
    DoubleRecyclerRealm.newRealmInstance().use {
        it.refresh()
        val list: List<T> = query(it)
        return if (list.isEmpty()) emptyList() else it.copyFromRealm(query(it))
    }
}