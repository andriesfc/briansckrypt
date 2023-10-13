package com.andriesfc.briansckrypt

import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable

@Introspected
@Serdeable
data class Contract(
    @JsonProperty("name") val name: String,
    @JsonProperty("color") val color: String,
    @JsonProperty("units") val units: ULong,
)