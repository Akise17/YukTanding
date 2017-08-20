package id.yuktanding.yuktanding

import android.os.Parcel
import android.os.Parcelable

class ItemTim() : Parcelable{
    var namaTim: String? = null
    var statusTim: String? = null
    var imgTim: Int = 0

    constructor(namaTim: String, statusTim: String, imgTim: Int) : this() {
        this.namaTim = namaTim
        this.statusTim = statusTim
        this.imgTim = imgTim
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(namaTim)
        parcel.writeString(statusTim)
        parcel.writeInt(imgTim)
    }

    constructor(parcel: Parcel) : this() {
        this.namaTim = parcel.readString()
        this.statusTim = parcel.readString()
        this.imgTim = parcel.readInt()
    }

    companion object CREATOR : Parcelable.Creator<ItemTim> {
        override fun createFromParcel(parcel: Parcel): ItemTim {
            return ItemTim(parcel)
        }

        override fun newArray(size: Int): Array<ItemTim?> {
            return arrayOfNulls(size)
        }
    }

}
