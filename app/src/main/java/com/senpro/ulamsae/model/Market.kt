package com.senpro.ulamsae.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.senpro.ulamsae.data.FileConverter
import kotlinx.parcelize.Parcelize
import java.io.File

@Entity(tableName = "market")
@Parcelize
data class Market(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val fileName: String,
    val harga: String,
    @TypeConverters(FileConverter::class)
    val file: File
) : Parcelable {
    constructor(fileName: String, harga: String, file: File) : this(0, fileName, harga, file)
}
