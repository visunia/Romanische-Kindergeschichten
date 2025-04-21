package com.aluisderagisch.vocabulary.other_products

import com.google.gson.annotations.SerializedName
import kotlin.reflect.full.memberProperties

data class OtherProduct (
    val id: Long,
    val appid: String,
    val owner: String,
    val display: String,
    val sortorder: Long,
    val link: String,
    val internalname: String,
    val iconname: String,
    @SerializedName("en_title")
    val enTitle: String,
    @SerializedName("en_description")
    val enDescription: String,
    @SerializedName("da_title")
    val daTitle: String,
    @SerializedName("da_description")
    val daDescription: String,
    @SerializedName("fr_title")
    val frTitle: String,
    @SerializedName("fr_description")
    val frDescription: String,
    @SerializedName("de_title")
    val deTitle: String,
    @SerializedName("de_description")
    val deDescription: String,
    @SerializedName("is_title")
    val isTitle: String,
    @SerializedName("is_description")
    val isDescription: String,
    @SerializedName("it_title")
    val itTitle: String,
    @SerializedName("it_description")
    val itDescription: String,
    @SerializedName("ms_title")
    val msTitle: String,
    @SerializedName("ms_description")
    val msDescription: String,
    @SerializedName("no_title")
    val noTitle: String,
    @SerializedName("no_description")
    val noDescription: String,
    @SerializedName("pt_title")
    val ptTitle: String,
    @SerializedName("pt_description")
    val ptDescription: String,
    @SerializedName("es_title")
    val esTitle: String,
    @SerializedName("es_description")
    val esDescription: String,
    @SerializedName("sv_title")
    val svTitle: String,
    @SerializedName("sv_description")
    val svDescription: String,
    @SerializedName("tr_title")
    val trTitle: String,
    @SerializedName("tr_description")
    val trDescription: String,
){
    fun getTitle(locale:String):String
    {
        return try {
            val title = getField<String>("${locale}Title")
            if(title.isNullOrEmpty())
                enTitle
            else title
        }catch (ex:Exception){
            enTitle
        }
    }

    fun getDescription(locale:String):String
    {
        return try {
            val description = getField<String>("${locale}Description")
            if(description.isNullOrEmpty())
                enDescription
            else description
        }catch (ex:Exception){
            enDescription
        }
    }

    fun getIcon(): String {
        return iconname
    }

   fun  getPackageName(): String {
        return appid
    }


}
@Throws(IllegalAccessException::class, ClassCastException::class)
inline fun <reified T> Any.getField(fieldName: String): T? {
    this::class.memberProperties.forEach { kCallable ->
        if (fieldName == kCallable.name) {
            return kCallable.getter.call(this) as T?
        }
    }
    return null
}