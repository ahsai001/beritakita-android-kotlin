package com.ahsailabs.beritakita_kotlin.ui.login.models

data class LoginResponse(
	val data: LoginData? = null,
	val message: String? = null,
	val status: Int? = null
)

data class LoginData(
	var name: String? = null,
	var token: String? = null,
	var username: String? = null
)

