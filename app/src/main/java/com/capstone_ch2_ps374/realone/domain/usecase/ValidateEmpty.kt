package com.capstone_ch2_ps374.realone.domain.usecase

import com.capstone_ch2_ps374.realone.util.Validators

class ValidateEmpty : IValidateEmail {

    override fun execute(string: String) : ValidationResult {
        if (string.isBlank()) {
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