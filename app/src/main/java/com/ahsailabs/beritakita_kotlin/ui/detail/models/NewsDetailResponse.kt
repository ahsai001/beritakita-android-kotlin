package com.ahsailabs.beritakita_kotlin.ui.detail.models

import com.google.gson.annotations.SerializedName

data class NewsDetailResponse(
	@field:SerializedName("data")
	val data: NewsDetail? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
)

data class NewsDetail(
	@field:SerializedName("summary")
	val summary: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: Any? = null,

	@field:SerializedName("group_id")
	val groupId: String? = null,

	@field:SerializedName("updated_by")
	val updatedBy: Any? = null,

	@field:SerializedName("photo")
	val photo: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("body")
	val body: String? = null,

	@field:SerializedName("created_by")
	val createdBy: String? = null
)
