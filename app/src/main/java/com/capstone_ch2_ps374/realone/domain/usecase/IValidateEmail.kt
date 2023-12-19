package com.capstone_ch2_ps374.realone.domain.usecase

interface IValidateEmail {

    fun execute(email: String): ValidationResult
}