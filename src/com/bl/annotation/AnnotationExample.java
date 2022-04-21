package com.bl.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AnnotationExample {

	@Override
	@MethodInfo(author = "Darpan", date = "20/04/2022", comments = "new toString() method", revision = 1)
	public String toString() {
		System.out.println("NEW TOSTRING METHOD");
		return "This is new implementation of toString()";
	}

	@Deprecated
	@MethodInfo(comments = "Deprecated method", date = "20/04/2022")
	public void oldMethod() {
		System.out.println("old method, don't use it");
	}

	public static void main(String[] args) {
		AnnotationExample annotation = new AnnotationExample();

		Method[] methods = annotation.getClass()
				.getMethods();

		for (Method method : methods) {
			if (method.isAnnotationPresent(
					MethodInfo.class)) {
				/*
				 * try { method.invoke(annotation, args); } catch (Exception e) { // TODO
				 * Auto-generated catch block e.printStackTrace(); }
				 */
				for (Annotation anno : method
						.getDeclaredAnnotations()) {
					System.out.println("Annotation "
							+ anno.annotationType()
							+ " is used in method "
							+ method.getName());
				}
				MethodInfo info = method
						.getAnnotation(MethodInfo.class);
				if (info.revision() == 1) {
					System.out.println(
							"Method " + method.getName());
					System.out.println(info.author());
					System.out.println(info.date());
					System.out.println(info.comments());
				}
			}
		}
	}
}