package com.ahsailabs.beritakita_kotlin.ui.home.models

import com.google.gson.annotations.SerializedName

data class NewsListResponse(
		@field:SerializedName("data")
		val data: List<News>? = null,

		@field:SerializedName("message")
		val message: String? = null,

		@field:SerializedName("status")
		val status: Int? = null
)

data class News(
		@field:SerializedName("summary")
		val summary: String? = null,

		@field:SerializedName("photo")
		val photo: String? = null,

		@field:SerializedName("created_at")
		val createdAt: String? = null,

		@field:SerializedName("id")
		val id: String? = null,

		@field:SerializedName("title")
		val title: String? = null,

		@field:SerializedName("created_by")
		val createdBy: String? = null
)
