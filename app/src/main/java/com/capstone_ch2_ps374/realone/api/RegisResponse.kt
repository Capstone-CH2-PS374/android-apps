package com.capstone_ch2_ps374.realone.api

import com.google.gson.annotations.SerializedName

data class RegisResponse(

	@field:SerializedName("role")
	val role: String? = null,

	@field:SerializedName("userId")
	val userId: String? = null
)
