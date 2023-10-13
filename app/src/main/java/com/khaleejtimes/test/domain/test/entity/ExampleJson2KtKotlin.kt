package com.khaleejtimes.test.domain.test.entity

import com.google.gson.annotations.SerializedName


data class TestApiResponse (

  @SerializedName("count") var count   : Int? = null,
  @SerializedName("entries" ) var entries : ArrayList<Entries> = arrayListOf()

)