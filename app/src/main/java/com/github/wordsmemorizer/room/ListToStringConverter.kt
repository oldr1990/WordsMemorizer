package com.github.wordsmemorizer.room


import androidx.room.TypeConverter

class ListToStringConverter {
    private val divider = "&"
    @TypeConverter
    fun fromListToString(list : List<String>) :String {
        var result = ""
        list.forEach {
            if(result.isNotEmpty()){
                result += divider
            }
            result += it
        }
        return result
    }

    @TypeConverter
    fun fromStringToList(string: String): List<String> = string.split(divider)

}