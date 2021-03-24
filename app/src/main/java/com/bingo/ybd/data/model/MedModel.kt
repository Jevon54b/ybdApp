package com.bingo.ybd.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MedBrief(
    val id: Int,
    val name: String,
    val price: Float,
    val prescript: Int,
    val note: String,
    val pic: String,
    @SerializedName("salesnum") val salesNum: Int,
    @SerializedName("med_type") val medType: Int
)


data class MedDetail(
    val id: Int,
    val name: String,
    val price: Float,
    val prescript: Int,
    val note: String,
    val pic: String,
    @SerializedName("salesnum") val salesNum: Int,
    @SerializedName("med_type") val medType: Int,
    @SerializedName("type_name") val typeName: String,
    @SerializedName("normal_name") val normalName: String,
    @SerializedName("goods_name") val goodsName: String,
    val composition: String,
    val avoid: String,
    val function: String,
    val usage: String,
    val properties: String,
    @SerializedName("packing_size") val packingSize: String,
    @SerializedName("adverse_reaction") val adverseReaction: String,
    @SerializedName("store_condition") val storeCondition: String,
    @SerializedName("valid_time") val validTime: String,
    @SerializedName("attensions") val attentions: String,
    @SerializedName("register_number") val registerNumber: String,
    val manufacturer: String
)

data class MedInOrder(
    @SerializedName("med_id") val medId: Int,
    @SerializedName("om_id") val mOrderId: Int,
    val name: String,
    val pic: String,
    val prescript: Int,
    @SerializedName("packing_size") val packingSize: String,
    @SerializedName("med_num") val medNum: String,
    val price: Float,
    @SerializedName("total_sum") val totalSum: Float
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readFloat(),
        parcel.readFloat()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(medId)
        parcel.writeInt(mOrderId)
        parcel.writeString(name)
        parcel.writeString(pic)
        parcel.writeInt(prescript)
        parcel.writeString(packingSize)
        parcel.writeString(medNum)
        parcel.writeFloat(price)
        parcel.writeFloat(totalSum)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MedInOrder> {
        override fun createFromParcel(parcel: Parcel): MedInOrder {
            return MedInOrder(parcel)
        }

        override fun newArray(size: Int): Array<MedInOrder?> {
            return arrayOfNulls(size)
        }
    }
}

object UserOrderInfo {
    var name: String = ""
    var phone: String = ""
    var address: String = ""
    var speed: Int = -1
    var addressId: Int = 0
}

object MedTypeMaps {
    val typeMap: HashMap<Int, String> = HashMap()

    init {
        typeMap[30001] = "感冒发烧"
        typeMap[30002] = "两性健康"
        typeMap[30003] = "咳嗽用药"
        typeMap[30004] = "皮肤用药"
        typeMap[30005] = "肠胃用药"
        typeMap[30006] = "跌打扭伤"
        typeMap[30007] = "儿童用药"
        typeMap[30008] = "滋补调养"
        typeMap[30009] = "处方用药"
        typeMap[30010] = "家庭常备"
        typeMap[30011] = "眼科用药"
        typeMap[30012] = "维矿补益"
    }


}