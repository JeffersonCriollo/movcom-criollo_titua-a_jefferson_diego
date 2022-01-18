package com.example.examen1b

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi

class OConsola (

    var id: Int,
    var nombre: String?,
    var nueva: Boolean,
    var costo: Double,
    var idConsola: Int

): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readDouble(),
        parcel.readInt()
    ) {
    }

    override fun toString(): String {
        return "${id} ${nombre} ${nueva} ${costo} ${idConsola}"
    }

    override fun describeContents(): Int {
        return 0
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun writeToParcel(p0: Parcel?, p1: Int) {
        p0?.writeInt( id )
        p0?.writeString( nombre )
        p0?.writeBoolean( nueva )
        p0?.writeDouble( costo )
        p0?.writeInt( idConsola )
    }

    companion object CREATOR : Parcelable.Creator<OConsola> {
        override fun createFromParcel(parcel: Parcel): OConsola {
            return OConsola(parcel)
        }

        override fun newArray(size: Int): Array<OConsola?> {
            return arrayOfNulls(size)
        }
    }

}