package com.capstone_ch2_ps374.realone.domain.usecase

class ValidateRepeatedPassword:IValidateRepeatedPassword {

    override fun execute(passwordRepeated: String, password: String): ValidationResult {
        if (passwordRepeated.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Password tidak boleh kosong"
            )
        }
        if(password != passwordRepeated){
            return ValidationResult(
                successful = false,
                errorMessage = "Password tidak sama"
            )
        }
        return ValidationResult(
            true
        )
    }
}