package com.capstone_ch2_ps374.realone.presentation.screen.volunteerdataform

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone_ch2_ps374.realone.api.ProvinceResponseItem
import com.capstone_ch2_ps374.realone.domain.usecase.ValidateAddressEmpty
import com.capstone_ch2_ps374.realone.domain.usecase.ValidateEmpty
import com.capstone_ch2_ps374.realone.presentation.screen.volunteerregister.RegisterFormEvent
import com.capstone_ch2_ps374.realone.repositories.AddressRepositories
import com.capstone_ch2_ps374.realone.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VolunteerFormViewModel @Inject constructor(
    private val validateEmpty: ValidateEmpty,
    private val validateAddressEmpty: ValidateAddressEmpty,
    private val addressRepositories: AddressRepositories,
    private val userRepositories: UserRepository
) : ViewModel() {
    private val _state = MutableStateFlow(VolunteerFormState())
    val state = _state.asStateFlow()

    fun setUserId(id: String) {
        _state.update {
            it.copy(
                userId = id
            )
        }
    }

    fun fetchProvince() {
        viewModelScope.launch {
            try {
                val response = addressRepositories.getProvince()
                if (response != null) {
                    _state.update {
                        it.copy(
                            provinceList = response
                        )
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun fetchCity() {
        viewModelScope.launch {
            try {
                val response = addressRepositories.getCity(state.value.province.id!!)
                if (response != null) {
                    _state.update {
                        it.copy(
                            citiesList = response
                        )
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    fun onEvent(event: VolunteerFormEvent) {
        when (event) {
            is VolunteerFormEvent.NameChanged -> {
                _state.update {
                    it.copy(
                        name = event.name
                    )
                }
            }

            is VolunteerFormEvent.AddressChanged -> {
                _state.update {
                    it.copy(
                        address = event.address
                    )
                }
            }

            is VolunteerFormEvent.BirthDateChanged -> {
                _state.update {
                    it.copy(
                        birthDate = event.birthDate
                    )
                }
            }

            is VolunteerFormEvent.JobChanged -> {
                _state.update {
                    it.copy(
                        job = event.job
                    )
                }
            }

            is VolunteerFormEvent.HighestEduChanged -> {
                _state.update {
                    it.copy(
                        highestEdu = event.highestEdu
                    )
                }
            }


            is VolunteerFormEvent.InterestChanged -> {
                _state.update {
                    it.copy(
                        interest = event.interest
                    )
                }
            }

            is VolunteerFormEvent.PhoneNumberChanged -> {
                _state.update {
                    it.copy(
                        phoneNumber = event.phoneNumber
                    )
                }
            }

            is VolunteerFormEvent.ProvinceChanged -> {
                _state.update {
                    it.copy(
                        province = event.province
                    )
                }
            }

            is VolunteerFormEvent.CityChanged -> {
                _state.update {
                    it.copy(
                        city = event.city
                    )
                }
            }

            is VolunteerFormEvent.NextPage -> {
                nextPage()
            }

            is VolunteerFormEvent.PrevPage -> {
                prevPage()
            }

            is VolunteerFormEvent.Submit -> {
                submit()
            }

            else -> {}
        }
    }

    private fun submit() {
        val provinceResult = validateAddressEmpty.execute(state.value.province)
        val cityResult = validateAddressEmpty.execute(state.value.city)


        val hasError = listOf(
            provinceResult,
            cityResult,
        ).any { !it.successful }

        if (hasError) {
            _state.update {
                it.copy(
                    provinceError = provinceResult.errorMessage,
                    cityError = cityResult.errorMessage,
                )
            }
        } else {
            viewModelScope.launch {
                try {
                    _state.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                    userRepositories.createUserData(
                        id = state.value.userId,
                        name = state.value.name,
                        address = state.value.city.name!!,
                        birthDate = state.value.birthDate,
                        job = state.value.job,
                        highestEdu = state.value.highestEdu,
                        typeOrganization = "",
                        interest = state.value.interest,
                        phone = state.value.phoneNumber
                    )
                    _state.update {
                        it.copy(
                            isLoading = false,
                            userDataSucces = true,
                        )
                    }
                } catch (e: Exception) {

                }
            }
        }
    }

    private fun prevPage() {
        _state.update {
            it.copy(
                currentStep = it.currentStep - 1
            )
        }
    }

    private fun nextPage() {
        val nameResult = validateEmpty.execute(state.value.name)
        val jobResult = validateEmpty.execute(state.value.job)
        val highestEduResult = validateEmpty.execute(state.value.highestEdu)
        val interestResult = validateEmpty.execute(state.value.interest)
        val birthDateResult = validateEmpty.execute(state.value.birthDate)
        val phoneNumberResult = validateEmpty.execute(state.value.phoneNumber)


        val hasError = listOf(
            nameResult,
            jobResult,
            highestEduResult,
            interestResult,
            birthDateResult,
            phoneNumberResult
        ).any { !it.successful }

        if (hasError) {
            _state.update {
                it.copy(
                    nameError = nameResult.errorMessage,
                    jobError = jobResult.errorMessage,
                    highestEduError = highestEduResult.errorMessage,
                    interestError = interestResult.errorMessage,
                    phoneNumberError = phoneNumberResult.errorMessage,
                    birthDateError = birthDateResult.errorMessage
                )
            }
        } else {
            nextStep()
        }
    }

    fun nextStep() {
        _state.update {
            it.copy(
                currentStep = it.currentStep + 1
            )
        }
    }

}