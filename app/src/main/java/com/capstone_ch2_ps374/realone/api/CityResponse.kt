package com.capstone_ch2_ps374.realone.api

import com.google.gson.annotations.SerializedName

data class CityResponse(

	@field:SerializedName("province_id")
	val provinceId: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String? = null
)
