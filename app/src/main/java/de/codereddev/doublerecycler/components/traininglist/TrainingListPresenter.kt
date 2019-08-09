package de.codereddev.doublerecycler.components.traininglist

import java.lang.ref.WeakReference

class TrainingListPresenter {

    interface TrainingListView {

    }

    private var view: WeakReference<TrainingListView>? = null

    fun setView(view: TrainingListView) {
        this.view = WeakReference(view)
    }

    fun addTrainingSet() {

    }
}