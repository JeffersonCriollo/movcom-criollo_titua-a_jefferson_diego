package com.example.examen1b

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi

class OVideojuego (

    var id: Int,
    var nombre: String?,
    var Costo: Double,
    var Plataforma: String?

): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readString()
    ) {
    }

    override fun toString(): String {
        return "${id} ${nombre} ${Costo} ${Plataforma}"
    }

    override fun describeContents(): Int {
        return 0
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun writeToParcel(p0: Parcel?, p1: Int) {
        p0?.writeInt( id )
        p0?.writeString( nombre )
        p0?.writeDouble( Costo )
        p0?.writeString( Plataforma )
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