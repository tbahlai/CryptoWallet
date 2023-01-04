package com.tbahlai.cryptowallet.data.repositories

import com.tbahlai.cryptowallet.data.BaseApiService
import com.tbahlai.cryptowallet.data.models.ApiErrorDTO
import com.tbahlai.cryptowallet.data.models.RequestResult
import com.tbahlai.cryptowallet.domain.models.ApiError
import com.tbahlai.cryptowallet.domain.models.New
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(
    private val apiService: BaseApiService
) {

    val news: StateFlow<List<New>>
        get() = _news
    private val _news: MutableStateFlow<List<New>> = MutableStateFlow(emptyList())

    suspend fun getNewsList(needToRefresh: Boolean = false) : RequestResult<List<New>, ApiError> {
        if (_news.value.isNotEmpty() && !needToRefresh) return RequestResult.Success(_news.value)

        return apiService.getNewsList()
            .mapFailure(ApiErrorDTO::toApiError)
            .map { it.map { it.toNew() } }
            .onSuccess { _news.value = it }
    }
}