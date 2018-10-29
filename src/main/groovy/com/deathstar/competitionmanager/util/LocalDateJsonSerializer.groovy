package com.deathstar.competitionmanager.util

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class LocalDateJsonSerializer extends JsonSerializer<LocalDate> {

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    @Override
    void serialize(LocalDate value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        gen.writeString(value.format(dateTimeFormatter))
    }
}