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

    suspend fun getNewsList() : RequestResult<List<New>, ApiError> {
        return apiService.getNewsList()
            .mapFailure(ApiErrorDTO::toApiError)
            .map { it.map { it.toNew() } }
            .onSuccess {
                val list = _news.value.toMutableList()
                list.addAll(0, it)
                _news.value = list
            }
    }
}