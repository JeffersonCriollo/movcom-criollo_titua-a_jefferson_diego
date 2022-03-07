package com.example.motos

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi

class BVideojuego(

    val id: Int,
    var nombre: String?,
    var disponible: Boolean,
    var costo: Double,
    val idConsola: Int
) : Parcelable {




    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readDouble(),
        parcel.readInt()
    ) {
    }

    override fun toString(): String {
        return "${id}: ${nombre}, ${disponible} ${costo} ${idConsola}"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nombre)
        parcel.writeByte(if (disponible) 1 else 0)
        parcel.writeDouble(costo)
        parcel.writeInt(idConsola)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BVideojuego> {
        override fun createFromParcel(parcel: Parcel): BVideojuego {
            return BVideojuego(parcel)
        }

        override fun newArray(size: Int): Array<BVideojuego?> {
            return arrayOfNulls(size)
        }
    }

}