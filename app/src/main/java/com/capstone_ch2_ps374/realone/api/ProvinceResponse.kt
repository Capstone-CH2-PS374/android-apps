package com.capstone_ch2_ps374.realone.api

import com.google.gson.annotations.SerializedName

data class ProvinceResponse(

	@field:SerializedName("ProvinceResponse")
	val provinceResponse: List<ProvinceResponseItem?>? = null
)

data class ProvinceResponseItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String? = null
)
