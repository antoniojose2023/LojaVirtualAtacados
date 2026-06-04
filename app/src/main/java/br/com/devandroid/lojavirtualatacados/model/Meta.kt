package br.com.devandroid.lojavirtualatacados.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Meta(
    val barcode: String,
    val createdAt: String,
    val qrCode: String,
    val updatedAt: String
): Parcelable