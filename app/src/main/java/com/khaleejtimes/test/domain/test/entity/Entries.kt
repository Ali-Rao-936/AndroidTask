package com.khaleejtimes.test.domain.test.entity

import com.google.gson.annotations.SerializedName


data class Entries (

  @SerializedName("API"         ) var API         : String?  = null,
  @SerializedName("Description" ) var Description : String?  = null,
  @SerializedName("Auth"        ) var Auth        : String?  = null,
  @SerializedName("HTTPS"       ) var HTTPS       : Boolean? = null,
  @SerializedName("Cors"        ) var Cors        : String?  = null,
  @SerializedName("Link"        ) var Link        : String?  = null,
  @SerializedName("Category"    ) var Category    : String?  = null

)