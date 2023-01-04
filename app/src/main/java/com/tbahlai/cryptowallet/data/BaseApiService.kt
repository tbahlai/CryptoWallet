package com.tbahlai.cryptowallet.data

import com.tbahlai.cryptowallet.data.models.ApiErrorDTO
import com.tbahlai.cryptowallet.data.models.NewDTO
import com.tbahlai.cryptowallet.data.models.RequestResult
import retrofit2.http.GET
import retrofit2.http.Query

interface BaseApiService {

    @GET("news/list")
    suspend fun getNewsList(
        @Query("performanceId") performanceId: String = "0P0000OQN8"
    ): RequestResult<List<NewDTO>, ApiErrorDTO>

}