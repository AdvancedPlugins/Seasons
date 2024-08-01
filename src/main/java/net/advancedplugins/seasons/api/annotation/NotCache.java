package net.advancedplugins.seasons.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Classes marked with this annotation are volatiles. Don't save their references at any time if you
 * don't want memory leaks.
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface NotCache {}
