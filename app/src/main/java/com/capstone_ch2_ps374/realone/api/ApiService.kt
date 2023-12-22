package com.capstone_ch2_ps374.realone.api

import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {


    @GET("users/{id}")
    suspend fun getUserById(
        @Path("id") id : String
    ):UserResponse

    @GET("categories")
    suspend fun getCategories(
    ):List<CategoryResponse>

    @GET("events/{id}")
    suspend fun getEventDetail(
        @Path("id") id: String
    ):EventDetailResponse

    @GET("events")
    suspend fun getAllEvent(
    ):List<EventResponse>

    @Multipart
    @POST("register")
    suspend fun registerUserVolunteer(
        @Part() id : MultipartBody.Part,
        @Part() role: MultipartBody.Part = MultipartBody.Part.createFormData("role", "User")
    ):RegisResponse

    @Multipart
    @POST("users")
    suspend fun createUserData(
        @Part userId: MultipartBody.Part,
        @Part name: MultipartBody.Part,
        @Part address: MultipartBody.Part,
        @Part birthDate: MultipartBody.Part,
        @Part jobs: MultipartBody.Part,
        @Part highestEdu: MultipartBody.Part,
        @Part type_organization: MultipartBody.Part,
        @Part interest: MultipartBody.Part,
        @Part phone: MultipartBody.Part,
    ):CreateUserResponse

    @Multipart
    @POST("events")
    suspend fun createEvent(
        @Part name: MultipartBody.Part,
        @Part start: MultipartBody.Part,
        @Part end: MultipartBody.Part,
        @Part location: MultipartBody.Part,
        @Part type: MultipartBody.Part,
        @Part description: MultipartBody.Part,
        @Part categoryId: MultipartBody.Part,
        @Part registerDate: MultipartBody.Part,
        @Part OrganizationId: MultipartBody.Part,
    ):CreateUserResponse
}



class ApiConfig {
    companion object {
        fun getApiService(): ApiService {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://backendimpacter-dot-capstone-ch2-ps374.et.r.appspot.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}