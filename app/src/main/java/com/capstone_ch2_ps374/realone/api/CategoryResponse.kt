package com.capstone_ch2_ps374.realone.api

import com.google.gson.annotations.SerializedName

data class CategoryResponse(

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("categoryId")
	val categoryId: Int? = null
)
