package com.example.examen1b

import android.os.Parcel
import android.os.Parcelable

class BConsola(
    val id: Int,
    var nombre: String?,
    var disponibilidad: Boolean,
    var Costo: Double

) :  Parcelable{
    override fun toString(): String {
        return "${id}: ${nombre} ${disponibilidad} ${Costo}"
    }

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readDouble()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nombre)
        parcel.writeByte(if (disponibilidad) 1 else 0)
        parcel.writeDouble(Costo)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BConsola> {
        override fun createFromParcel(parcel: Parcel): BConsola {
            return BConsola(parcel)
        }

        override fun newArray(size: Int): Array<BConsola?> {
            return arrayOfNulls(size)
        }
    }
}