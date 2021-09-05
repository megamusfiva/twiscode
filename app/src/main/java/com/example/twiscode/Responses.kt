package com.example.twiscode

import com.google.gson.annotations.SerializedName

data class Responses(

    @field:SerializedName("is_halal")
    val isHalal: Int? = null,

    @field:SerializedName("is_available")
    val isAvailable: Int? = null,

    @field:SerializedName("weight")
    val weight: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("location_name")
    val locationName: String? = null,

    @field:SerializedName("price")
    val price: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("user")
    val user: User? = null
)

data class User(

    @field:SerializedName("user_id")
    val userId: String? = null,

    @field:SerializedName("city")
    val city: String? = null,

    @field:SerializedName("user_name")
    val userName: String? = null
)