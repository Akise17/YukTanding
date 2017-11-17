package id.yuktanding.yuktanding

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by ekkyh on 11/7/2017.
 */
class ItemMatch() : Parcelable {

    var tim_biru_nama: String? = null
    var tim_biru_desc: String? = null
    var tim_biru_img: String?=null
    var tim_merah_nama: String? = null
    var tim_merah_desc: String?=null
    var tim_merah_img: String?=null
    var txt3: String?=null
    var txt4: String?=null
    var txt5: String?=null
    var txt6: String?=null

    constructor(biru_nama:String,
                biru_desc:String,
                biru_img:String,
                merah_nama:String,
                merah_desc:String,
                merah_img:String,
                txt3_in:String,
                txt4_in:String,
                txt5_in:String,
                txt6_in:String) : this() {
        this.tim_biru_nama = biru_nama
        this.tim_biru_desc = biru_desc
        this.tim_biru_img = biru_img
        this.tim_merah_nama = merah_nama
        this.tim_merah_desc = merah_desc
        this.tim_merah_img = merah_img
        this.txt3 = txt3_in
        this.txt4 = txt4_in
        this.txt5 = txt5_in
        this.txt6 = txt6_in
    }


    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(this.tim_biru_nama)
        parcel.writeString(this.tim_biru_desc)
        parcel.writeString(this.tim_biru_img)
        parcel.writeString(this.tim_merah_nama)
        parcel.writeString(this.tim_merah_desc)
        parcel.writeString(this.tim_merah_img)
        parcel.writeString(this.txt3)
        parcel.writeString(this.txt4)
        parcel.writeString(this.txt5)
        parcel.writeString(this.txt6)
    }

    protected constructor(`in`:Parcel) : this() {
        this.tim_biru_nama = `in`.readString()
        this.tim_biru_desc = `in`.readString()
        this.tim_biru_img = `in`.readString()
        this.tim_merah_nama = `in`.readString()
        this.tim_merah_desc = `in`.readString()
        this.tim_merah_img = `in`.readString()
        this.txt3 = `in`.readString()
        this.txt4 = `in`.readString()
        this.txt5 = `in`.readString()
        this.txt6 = `in`.readString()
    }


    companion object {

        val CREATOR : Parcelable.Creator<ItemMatch> = object : Parcelable.Creator<ItemMatch> {
            override fun createFromParcel(parcel: Parcel): ItemMatch {
                return ItemMatch(parcel)
            }

            override fun newArray(size: Int): Array<ItemMatch?> {
                return arrayOfNulls(size)
            }
        }
    }

}