package com.capstone_ch2_ps374.realone.domain.usecase

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)
