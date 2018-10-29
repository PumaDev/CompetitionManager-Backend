package com.deathstar.competitionmanager.util

import javax.persistence.AttributeConverter
import javax.persistence.Converter
import java.sql.Timestamp
import java.time.Instant

@Converter
class JpaInstantConverter implements AttributeConverter<Instant, Timestamp> {
    @Override
    Timestamp convertToDatabaseColumn(Instant attribute) {
        Timestamp timestamp = null
        if (attribute) {
            timestamp = Timestamp.from(attribute)
        }
        return timestamp
    }

    @Override
    Instant convertToEntityAttribute(Timestamp dbData) {
        Instant instant = null
        if (dbData) {
            instant = dbData.toInstant()
        }
        return instant
    }
}
