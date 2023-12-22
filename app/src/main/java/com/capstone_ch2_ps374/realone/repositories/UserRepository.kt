package com.capstone_ch2_ps374.realone.repositories

import com.capstone_ch2_ps374.realone.api.ApiService
import com.capstone_ch2_ps374.realone.api.CategoryResponse
import com.capstone_ch2_ps374.realone.api.CreateUserResponse
import com.capstone_ch2_ps374.realone.api.EventDetailResponse
import com.capstone_ch2_ps374.realone.api.EventResponse
import com.capstone_ch2_ps374.realone.api.RegisResponse
import com.capstone_ch2_ps374.realone.api.UserResponse
import okhttp3.MultipartBody
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getUserById(id: String):UserResponse{
        return apiService.getUserById(id)
    }

    suspend fun getEvents():List<EventResponse>{
        return apiService.getAllEvent()
    }

    suspend fun getEventDetail(id: String):EventDetailResponse{
        return apiService.getEventDetail(id)
    }

    suspend fun register(id: String): RegisResponse {
        return apiService.registerUserVolunteer(MultipartBody.Part.createFormData("userId", id))
    }

    suspend fun createUserData(
        id: String,
        name: String,
        address: String,
        birthDate: String,
        job: String,
        highestEdu: String,
        typeOrganization: String,
        interest: String,
        phone: String,
    ): CreateUserResponse {
        return apiService.createUserData(
            MultipartBody.Part.createFormData("userId", id),
            MultipartBody.Part.createFormData("name", name),
            MultipartBody.Part.createFormData("address", address),
            MultipartBody.Part.createFormData("birthDate", birthDate),
            MultipartBody.Part.createFormData("jobs", job),
            MultipartBody.Part.createFormData("highest_edu", highestEdu),
            MultipartBody.Part.createFormData("type_organization", typeOrganization),
            MultipartBody.Part.createFormData("skills", interest),
            MultipartBody.Part.createFormData("phone", phone)
        )
    }

//    suspend fun createEvent(
//        name: String,
//        start: String,
//        end: String,
//        location: String,
//        type: String,
//        description: String,
//        categoryId: String,
//        registerDate: String,
//        organizationId: String,
//    ):CreateUserResponse {
//        return apiService.createUserData(
//            MultipartBody.Part.createFormData("userId", id),
//            MultipartBody.Part.createFormData("name", name),
//            MultipartBody.Part.createFormData("address", address),
//            MultipartBody.Part.createFormData("birthDate", birthDate),
//            MultipartBody.Part.createFormData("jobs", job),
//            MultipartBody.Part.createFormData("highest_edu", highestEdu),
//            MultipartBody.Part.createFormData("type_organization", typeOrganization),
//            MultipartBody.Part.createFormData("interest", interest),
//            MultipartBody.Part.createFormData("phone", phone)
//        )
//    }



    suspend fun getCategories():List<CategoryResponse>{
        return apiService.getCategories()
    }
}