package com.capstone_ch2_ps374.realone.presentation.screen.volunteerdataform

import com.capstone_ch2_ps374.realone.api.CityResponse
import com.capstone_ch2_ps374.realone.api.ProvinceResponseItem

sealed class VolunteerFormEvent {
    data class NameChanged(val name: String) : VolunteerFormEvent()
    data class AddressChanged(val address: String) : VolunteerFormEvent()
    data class BirthDateChanged(val birthDate: String) : VolunteerFormEvent()
    data class JobChanged(val job: String) : VolunteerFormEvent()
    data class HighestEduChanged(val highestEdu: String) : VolunteerFormEvent()
    data class InterestChanged(val interest: String) : VolunteerFormEvent()
    data class PhoneNumberChanged(val phoneNumber: String) : VolunteerFormEvent()
    data class ProvinceChanged(val province: ProvinceResponseItem) : VolunteerFormEvent()
    data class CityChanged(val city: CityResponse) : VolunteerFormEvent()
    object NextPage: VolunteerFormEvent()
    object PrevPage: VolunteerFormEvent()

    object Submit: VolunteerFormEvent()
}