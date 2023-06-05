package com.senpro.ulamsae.data

import androidx.room.TypeConverter
import java.io.File

class FileConverter {
    @TypeConverter
    fun fileToString(file: File): String {
        return file.absolutePath
    }

    @TypeConverter
    fun stringToFile(path: String): File {
        return File(path)
    }
}