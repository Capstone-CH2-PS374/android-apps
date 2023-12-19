package com.capstone_ch2_ps374.realone.domain.usecase

import com.capstone_ch2_ps374.realone.util.Validators

class ValidateEmail : IValidateEmail {

    override fun execute(email: String) : ValidationResult {
        if (email.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Email Tidak Boleh Kosong"
            )
        }
        if (Validators.isNotValidEmail(email)){
            return ValidationResult(
                successful = false,
                errorMessage = "Email Tidak Sesuai Format"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}