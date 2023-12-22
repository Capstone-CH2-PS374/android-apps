package com.capstone_ch2_ps374.realone.repositories

import com.capstone_ch2_ps374.realone.api.AddressApiService
import com.capstone_ch2_ps374.realone.api.CityResponse
import com.capstone_ch2_ps374.realone.api.ProvinceResponse
import com.capstone_ch2_ps374.realone.api.ProvinceResponseItem
import com.capstone_ch2_ps374.realone.api.UserResponse
import javax.inject.Inject

class AddressRepositories @Inject constructor(
    private val apiService: AddressApiService
) {

    suspend fun getProvince(): List<ProvinceResponseItem> {
        return apiService.getProvince()
    }

    suspend fun getCity(id: String): List<CityResponse> {
        return apiService.getRegencies(id)
    }
}