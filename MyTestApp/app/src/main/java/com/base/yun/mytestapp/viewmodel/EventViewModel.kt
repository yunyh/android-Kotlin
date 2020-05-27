package com.base.yun.mytestapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.base.yun.mytestapp.model.CommitModel
import com.base.yun.mytestapp.model.GitHubEventsModel

class EventViewModel : ViewModel() {

    companion object {
        fun make(event: GitHubEventsModel): EventViewModel =
                EventViewModel().apply {
                    repoName.set(event.repo?.name)
                    eventType.set(event.type)
                    actorData.set(event.actor)

                    event.payload?.let {
                        commitCount.set(it.size)
                        it.commits?.run {
                            commitData.addAll(this@run)
                        }
                    }
                }
    }

    val repoName = ObservableField<String>()
    val eventType = ObservableField<String>()
    val actorData = ObservableField<GitHubEventsModel.ActorModel>()
    val commitData = ObservableArrayList<CommitModel>()
    val commitCount = ObservableInt()
}