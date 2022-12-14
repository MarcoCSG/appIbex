package com.example.ibex.network.response

import com.example.ibex.modelo.User

data class LoginResponse(

    val result:Boolean,
    val desc: String,
    val link: String,
    val data: User,

    )