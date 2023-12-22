package com.capstone_ch2_ps374.realone.api

import com.google.gson.annotations.SerializedName

data class UserResponse(

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("type_organization")
	val typeOrganization: String? = null,

	@field:SerializedName("interest")
	val interest: String? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("jobs")
	val jobs: String? = null,

	@field:SerializedName("highest_edu")
	val highestEdu: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("userId")
	val userId: String? = null,

	@field:SerializedName("birthDate")
	val birthDate: String? = null
)
