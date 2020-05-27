package com.base.yun.mytestapp.model

import com.fasterxml.jackson.annotation.JsonProperty

data class CommitModel(
        @JsonProperty("sha") val sha: String,
        @JsonProperty("author") val author: AuthorModel,
        @JsonProperty("message") val message: String,
        @JsonProperty("distinct") val distinct: Boolean,
        @JsonProperty("url") val url: String) {

    data class AuthorModel(@JsonProperty("email") val email: String, @JsonProperty("name") val name: String)
}

