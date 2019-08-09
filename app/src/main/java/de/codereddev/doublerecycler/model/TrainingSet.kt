package de.codereddev.doublerecycler.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.UUID

open class TrainingSet : RealmObject() {
    @PrimaryKey
    var uuid: String = UUID.randomUUID().toString()

    var text: String? = null
}