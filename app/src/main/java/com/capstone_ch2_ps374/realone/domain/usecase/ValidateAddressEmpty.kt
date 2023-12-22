package com.capstone_ch2_ps374.realone.domain.usecase

import com.capstone_ch2_ps374.realone.api.CityResponse
import com.capstone_ch2_ps374.realone.api.ProvinceResponseItem
import com.capstone_ch2_ps374.realone.util.Validators

class ValidateAddressEmpty {
    fun execute(selected: ProvinceResponseItem) : ValidationResult {
        if (selected.id == null) {
            return ValidationResult(
                successful = false,
                errorMessage = "Kolom Tidak Boleh Kosong"
            )
        }
        return ValidationResult(
            successful = true
        )
    }

    fun execute(selected: CityResponse): ValidationResult {
        if (selected.id == null) {
            return ValidationResult(
                successful = false,
                errorMessage = "Kolom Tidak Boleh Kosong"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}