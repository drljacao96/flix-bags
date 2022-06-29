package com.drbrosdev.flix_bags.network

import com.drbrosdev.flix_bags.network.model.LoginRequest
import com.drbrosdev.flix_bags.network.model.LoginResponse
import com.drbrosdev.flix_bags.network.model.TicketWithBaggageRequest
import com.drbrosdev.flix_bags.network.model.TicketWithBaggageResponse
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiInterface {

    @POST("companyUser/singin")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

    @POST("ticket/createNewTicketWithBag")
    suspend fun sendTicketWithBaggageQRCode(
        @Header("Authorization") token: String,
        @Body sendTicketWithBaggageRequest: TicketWithBaggageRequest
    ): TicketWithBaggageResponse
}

private const val BASE_URL = "https://askme5.it/qr/api/"

private val gson = GsonBuilder().setLenient().create()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create(gson))
    .baseUrl(BASE_URL)
    .build()

object TicketApi {
    val ticketApiService: ApiInterface by lazy {
        retrofit.create(ApiInterface::class.java)
    }
}