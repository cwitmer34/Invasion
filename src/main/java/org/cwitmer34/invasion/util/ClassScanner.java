package org.cwitmer34.invasion.util;

import org.cwitmer34.invasion.commands.admin.StartInvasion;
import org.cwitmer34.invasion.commands.misc.Debug;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class ClassScanner {

  public static Set<Class<?>> findClassesWithAnnotation(final Class<? extends Annotation> annotation) {
    final Set<Class<?>> classes = new HashSet<>();

    classes.add(Debug.class);
    classes.add(StartInvasion.class);

    final Set<Class<?>> annotatedClasses = new HashSet<>();
    for (final Class<?> cls : classes) {
      for (final Method method : cls.getDeclaredMethods()) {
        if (method.isAnnotationPresent(annotation)) {
          annotatedClasses.add(cls);
        }
      }
    }

    return annotatedClasses;
  }
}
