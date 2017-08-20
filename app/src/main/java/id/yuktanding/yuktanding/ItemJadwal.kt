package id.yuktanding.yuktanding

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Akise on 20/08/2017.
 */

class ItemJadwal : Parcelable {

    //Getter and Setter
    var namaLapangan: String? = null
    var jadwalLapangan: String? = null
    var imgLapangan: Int = 0

    constructor() {}

    //Constructor

    constructor(namaLapangan: String, jadwalLapangan: String, imgLapangan: Int) {
        this.namaLapangan = namaLapangan
        this.jadwalLapangan = jadwalLapangan
        this.imgLapangan = imgLapangan
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.namaLapangan)
        dest.writeString(this.jadwalLapangan)
        dest.writeInt(this.imgLapangan)
    }

    protected constructor(`in`: Parcel) {
        this.namaLapangan = `in`.readString()
        this.jadwalLapangan = `in`.readString()
        this.imgLapangan = `in`.readInt()
    }

    companion object {

        val CREATOR: Parcelable.Creator<ItemJadwal> = object : Parcelable.Creator<ItemJadwal> {
            override fun createFromParcel(source: Parcel): ItemJadwal {
                return ItemJadwal(source)
            }

            override fun newArray(size: Int): Array<ItemJadwal?> {
                return arrayOfNulls(size)
            }
        }
    }
}
