package org.cwitmer34.invasion.framework;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Command {
	String[] names();
	boolean hidden() default false;
	String permission() default "";
	String description() default "";
	int cooldown() default 0;
}
