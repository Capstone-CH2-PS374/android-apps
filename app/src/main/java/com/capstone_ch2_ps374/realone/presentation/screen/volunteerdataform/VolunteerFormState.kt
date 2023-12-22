package com.capstone_ch2_ps374.realone.presentation.screen.volunteerdataform

import com.capstone_ch2_ps374.realone.api.CityResponse
import com.capstone_ch2_ps374.realone.api.ProvinceResponseItem

data class VolunteerFormState(
    val userId: String = "",
    val name: String = "",
    val nameError:String? = null,
    val address: String = "",
    val addressError: String? = null,
    val birthDate: String = "",
    val birthDateError: String? = null,
    val job: String = "",
    val jobError: String? = null,
    val highestEdu: String = "",
    val highestEduError: String? = null,
    val interest: String = "",
    val interestError: String? = null,
    val phoneNumber: String = "",
    val phoneNumberError: String? = null,
    val isRegisterSuccessful: Boolean = false,
    val registerError: String? = null,
    val signInSuccess: Boolean? = null,
    val isLoading: Boolean = false,
    val provinceList: List<ProvinceResponseItem> = listOf(),
    val province: ProvinceResponseItem = ProvinceResponseItem(),
    val provinceError: String? = null,
    val citiesList: List<CityResponse> = listOf(),
    val city: CityResponse = CityResponse(),
    val cityError: String? = null,
    val onFirstPageSuccess: Boolean = false,
    val currentStep: Int = 1,
    val userDataSucces: Boolean = false,
)
