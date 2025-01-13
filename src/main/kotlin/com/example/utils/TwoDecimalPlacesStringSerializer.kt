package com.example.utils

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.math.BigDecimal
import java.math.RoundingMode

object TwoDecimalPlacesSerializer : KSerializer<BigDecimal> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("TwoDecimalPlaces", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: BigDecimal) {
        // Ensure value has two decimal places
        val formattedValue = value.setScale(2, RoundingMode.HALF_UP)
        encoder.encodeString(formattedValue.toString())
    }

    override fun deserialize(decoder: Decoder): BigDecimal {
        val value = decoder.decodeString()
        return BigDecimal(value).setScale(2, RoundingMode.HALF_UP)
    }
}