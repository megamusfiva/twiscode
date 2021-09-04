package com.example.twiscode

import com.google.gson.annotations.SerializedName

data class Responses(

    @field:SerializedName("is_halal")
    val isHalal: Int? = null,

    @field:SerializedName("is_available")
    val isAvailable: Int? = null,

    @field:SerializedName("address")
    val address: String? = null,

    @field:SerializedName("sub_category")
    val subCategory: SubCategory? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("weight")
    val weight: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("location_name")
    val locationName: String? = null,

    @field:SerializedName("price")
    val price: String? = null,

    @field:SerializedName("cat_id")
    val catId: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("default_photo")
    val defaultPhoto: DefaultPhoto? = null,

    @field:SerializedName("category")
    val category: Category? = null,

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

data class SubCategory(

    @field:SerializedName("cat_id")
    val catId: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: String? = null
)

data class Category(

    @field:SerializedName("cat_name")
    val catName: String? = null,

    @field:SerializedName("cat_id")
    val catId: String? = null
)

data class DefaultPhoto(

    @field:SerializedName("img_type")
    val imgType: String? = null,

    @field:SerializedName("img_desc")
    val imgDesc: String? = null,

    @field:SerializedName("img_width")
    val imgWidth: String? = null,

    @field:SerializedName("img_path")
    val imgPath: String? = null,

    @field:SerializedName("img_height")
    val imgHeight: String? = null,

    @field:SerializedName("img_parent_id")
    val imgParentId: String? = null,

    @field:SerializedName("img_id")
    val imgId: String? = null
)
