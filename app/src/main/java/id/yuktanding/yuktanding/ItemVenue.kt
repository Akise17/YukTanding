package id.yuktanding.yuktanding

import android.os.Parcel
import android.os.Parcelable

class ItemVenue() : Parcelable {
    var namaVenue: String? = null
    var descVenue: String? = null
    var addInfo: String?=null
    var imgVenue: Int = 0

    constructor(venuename: String, desc: String,additionInf:String, img: Int) : this() {
        this.namaVenue = venuename
        this.descVenue = desc
        this.addInfo = additionInf
        this.imgVenue = img
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(this.namaVenue)
        parcel.writeString(this.descVenue)
        parcel.writeString(this.addInfo)
        parcel.writeInt(this.imgVenue)
    }

    protected constructor(`in`:Parcel) : this() {
        this.namaVenue = `in`.readString()
        this.descVenue = `in`.readString()
        this.addInfo = `in`.readString()
        this.imgVenue = `in`.readInt()
    }

    companion object {

        val CREATOR : Parcelable.Creator<ItemVenue> = object : Parcelable.Creator<ItemVenue> {
            override fun createFromParcel(parcel: Parcel): ItemVenue {
                return ItemVenue(parcel)
            }

            override fun newArray(size: Int): Array<ItemVenue?> {
                return arrayOfNulls(size)
            }
        }
    }
}