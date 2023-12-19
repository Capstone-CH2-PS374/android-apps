package com.capstone_ch2_ps374.realone.domain.usecase

interface IValidateRepeatedPassword {

    fun execute(passwordRepeated: String, password: String): ValidationResult
}