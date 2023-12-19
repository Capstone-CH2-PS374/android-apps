package com.capstone_ch2_ps374.realone.domain.usecase

class ValidatePassword: IValidatePassword {

    override fun execute(password: String): ValidationResult {
        if(password.length < 8) {
            return ValidationResult(
                successful = false,
                errorMessage = "Password Minimal Memiliki 8 Karakter"
            )
        }
        val containsLettersAndDigits = password.any { it.isDigit() } &&
                password.any { it.isLetter() }
        if(!containsLettersAndDigits) {
            return ValidationResult(
                successful = false,
                errorMessage = "Password Minimal Memiliki Satu Digit Angka dan Huruf"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}