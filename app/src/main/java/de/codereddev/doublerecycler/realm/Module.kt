package de.codereddev.doublerecycler.realm

import de.codereddev.doublerecycler.model.Training
import de.codereddev.doublerecycler.model.TrainingSet
import io.realm.annotations.RealmModule

@RealmModule(classes = [Training::class, TrainingSet::class])
class Module