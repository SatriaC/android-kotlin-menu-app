package com.sayyes.easyfood.pojo


import com.google.gson.annotations.SerializedName

data class CategoryList(
    @SerializedName("categories")
    val categories: List<Category>?
)