package com.base.yun.mytestapp.model

import com.fasterxml.jackson.annotation.JsonProperty

data class GitHubEventsModel(
        @JsonProperty("id") var id: String,
        @JsonProperty("type") var type: String,
        @JsonProperty("repo") var repo: RepoModel?,
        @JsonProperty("actor") var actor: ActorModel?,
        @JsonProperty("payload") var payload: PayloadModel?) : BaseVO() {

    data class RepoModel(@JsonProperty("name") var name: String)

    data class PayloadModel(@JsonProperty("name") var name: String?,
                            @JsonProperty("commits") var commits: List<CommitModel>?,
                            @JsonProperty("size") val size: Int)

    data class ActorModel(@JsonProperty("display_login") var displayLogin: String,
                          @JsonProperty("avatar_url") val avatarUrl: String)
}