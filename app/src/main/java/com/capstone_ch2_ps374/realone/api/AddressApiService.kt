package com.capstone_ch2_ps374.realone.api

import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AddressApiService {
    @GET("provinces.json")
    suspend fun getProvince():List<ProvinceResponseItem>

    @GET("regencies/{provinceId}.json")
    suspend fun getRegencies(
        @Path("provinceId") provinceId: String
    ):List<CityResponse>
}

class AdressApiConfig {
    companion object {
        fun getApiService(): AddressApiService {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://www.emsifa.com/api-wilayah-indonesia/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(AddressApiService::class.java)
        }
    }
}