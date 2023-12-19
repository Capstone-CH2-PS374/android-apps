package com.capstone_ch2_ps374.realone.domain.usecase

interface IValidatePassword {

    fun execute(password: String) : ValidationResult
}