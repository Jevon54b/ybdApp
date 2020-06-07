package com.bingo.ybd.modules.main.model

import com.google.gson.annotations.SerializedName

data class TopMedListResponse(val status:String,
                              val msg:String,
                              @SerializedName("data")val medList:List<MedBriefModel>)

//药品概要信息
data class MedBriefModel(val id:String,
                         val name:String,
                         val price:String,
                         val prescript:String,
                         val note:String,
                         val pic:String,
                         @SerializedName("salesnum")val salesNum:String,
                         @SerializedName("packing_size")val packingSize:String,
                         @SerializedName("med_type")val medType:String,
                         @SerializedName("med_num")val medNum:String)
