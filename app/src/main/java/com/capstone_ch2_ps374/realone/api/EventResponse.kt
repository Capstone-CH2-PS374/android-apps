package com.capstone_ch2_ps374.realone.api

import com.google.gson.annotations.SerializedName

data class EventResponse(

	@field:SerializedName("organizationId")
	val organizationId: String? = null,

	@field:SerializedName("eventId")
	val eventId: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("start")
	val start: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("end")
	val end: String? = null,

	@field:SerializedName("location")
	val location: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("categoryId")
	val categoryId: Int? = null,

	@field:SerializedName("registerDate")
	val registerDate: String? = null
)
