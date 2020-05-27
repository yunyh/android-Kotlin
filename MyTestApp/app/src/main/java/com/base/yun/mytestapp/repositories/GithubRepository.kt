package com.base.yun.mytestapp.repositories

import com.base.yun.mytestapp.model.GitHubEventsModel
import com.base.yun.mytestapp.model.RepositoryModel
import com.base.yun.mytestapp.module.retrofit.services.BaseServices
import com.base.yun.mytestapp.module.retrofit.services.GithubUsersServices
import com.base.yun.mytestapp.module.retrofit.wrapper.ResponseResult
import com.base.yun.mytestapp.module.retrofit.wrapper.mapResultResult

class GithubRepository {

    suspend fun getService(): ResponseResult<RepositoryModel> =
            BaseServices.INSTANCE.getRepos("yunyh", "android-ui").mapResultResult()

    suspend fun getReceivedEvents(userName: String): ResponseResult<List<GitHubEventsModel>> =
            GithubUsersServices.instance.getUsersEventsAsync(userName).mapResultResult()
}