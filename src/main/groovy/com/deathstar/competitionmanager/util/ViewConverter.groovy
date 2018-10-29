package com.deathstar.competitionmanager.util

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable

import java.lang.reflect.Field

abstract class ViewConverter<D, V> {
    List<V> convertToViews(List<D> domainEntities) {
        return domainEntities.collect { convertToView(it) }
    }

    Page<V> convertToViews(Page<D> entitiesPage, Pageable pageable) {
        def views = convertToViews(entitiesPage.content)
        return new PageImpl<>(views, pageable, entitiesPage.totalPages)
    }

    abstract V convertToView(D domainEntity)

    List<D> convertToDomainEntities(List<V> views) {
        return views.collect { convertToDomainEntity(it) }
    }

    abstract D convertToDomainEntity(V view);

    protected Object copyFields(Object source, Object target, Set<String> excludeFields = []) {
        source.class.declaredFields
                .findAll { field -> !field.synthetic && source[field.name] != null && !excludeFields.contains(field.name) }
                .each { field ->
            target[field.name] = source[field.name]
        }

        return target
    }
}
