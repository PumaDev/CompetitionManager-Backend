package com.deathstar.competitionmanager.util


import javax.persistence.AttributeConverter
import javax.persistence.Converter
import java.sql.Timestamp
import java.time.LocalDate

@Converter
class JpaLocalDateConverter implements AttributeConverter<LocalDate, Timestamp> {
    @Override
    LocalDate convertToEntityAttribute(Timestamp timestamp) {
        if (timestamp == null) {
            return null
        }

        return timestamp.toLocalDateTime().toLocalDate()
    }

    @Override
    Timestamp convertToDatabaseColumn(LocalDate localDate) {
        if (localDate == null) {
            return null
        }

        return Timestamp.valueOf(localDate.atStartOfDay())
    }
}
