package com.silverpine.uu.test.instrumented.annotations

import java.lang.annotation.Inherited

/**
 * This annotation is used to mark tests that require user interaction with a test UI
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
annotation class UUInteractionRequired