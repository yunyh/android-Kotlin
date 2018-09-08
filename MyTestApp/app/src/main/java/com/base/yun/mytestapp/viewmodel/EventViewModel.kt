package com.base.yun.mytestapp.viewmodel

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import android.databinding.ObservableInt
import com.base.yun.mytestapp.model.CommitModel
import com.base.yun.mytestapp.model.GitHubEventsModel

class EventViewModel : ViewModel() {
    val repoName = ObservableField<String>()
    val eventType = ObservableField<String>()
    val actorData = ObservableField<GitHubEventsModel.ActorModel>()
    val commitData = ObservableArrayList<CommitModel>()
    val commitCount = ObservableInt()

    object Provider {
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
}