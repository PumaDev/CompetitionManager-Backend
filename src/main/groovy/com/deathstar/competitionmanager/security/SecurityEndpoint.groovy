package com.deathstar.competitionmanager.security

import com.deathstar.competitionmanager.domain.user.UserRole

import java.lang.annotation.*

import static com.deathstar.competitionmanager.domain.user.UserRole.*

@Retention(RetentionPolicy.RUNTIME)
@Target([ElementType.METHOD])
@Inherited
@interface SecurityEndpoint {

    UserRole[] rolesHasAccess() default [COACH, ADMIN, DEVELOPER]
}