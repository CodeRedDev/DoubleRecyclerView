package de.codereddev.doublerecycler.model

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.UUID

open class Training : RealmObject() {
    @PrimaryKey
    var uuid: String = UUID.randomUUID().toString()

    var sets: RealmList<TrainingSet> = RealmList()
}