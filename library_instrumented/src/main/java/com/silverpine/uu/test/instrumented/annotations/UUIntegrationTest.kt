package com.silverpine.uu.test.instrumented.annotations

import java.lang.annotation.Inherited

/**
 * This annotation is used to mark tests that require one or more 'live' services
 *
 * The annotation can be applied to test classes, and to individual
 * tests.
 */
@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@Inherited
annotation class UUIntegrationTest