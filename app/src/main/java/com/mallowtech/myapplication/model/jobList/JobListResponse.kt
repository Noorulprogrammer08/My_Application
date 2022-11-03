package com.mallowtech.myapplication.model.jobList

import com.google.gson.annotations.SerializedName

data class JobListResponse(
    @SerializedName("contact_number")
    val contactNumber: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("experience")
    val experience: Int,
    @SerializedName("location")
    val location: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("url")
    val url: String
)