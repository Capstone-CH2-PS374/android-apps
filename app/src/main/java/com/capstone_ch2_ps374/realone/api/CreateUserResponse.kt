package com.capstone_ch2_ps374.realone.api

import com.google.gson.annotations.SerializedName

data class CreateUserResponse(

	@field:SerializedName("data")
	val data: UserResponse? = null,

	@field:SerializedName("message")
	val message: String? = null
)
