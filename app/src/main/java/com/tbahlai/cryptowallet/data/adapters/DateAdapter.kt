package com.tbahlai.cryptowallet.data.adapters

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.time.Instant

class DateAdapter : JsonDeserializer<Instant> {

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Instant {
        return Instant.parse(json.asString.removeSuffix("-06:00").plus("Z"))
    }
}
