package com.fastcampus.ch3.aop;

import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AopMain {
    public static void main(String[] args) throws Exception {
        MyAdvice myAdvice = new MyAdvice();

        Class myClass = Class.forName("com.fastcampus.ch3.aop.MyClass");
        Object object = myClass.newInstance();

        for (Method method : myClass.getDeclaredMethods()) {
            myAdvice.invoke(method, object, null);
        }
    }
}

class MyAdvice {
    Pattern pattern = Pattern.compile("a.*");

    boolean matches(Method method) {
        Matcher matcher = pattern.matcher(method.getName());
        return matcher.matches();
    }
    void invoke(Method method, Object object, Object... args) throws Exception {
        if (method.getAnnotation(Transactional.class) != null) {
            System.out.println("[before] {");
        }
        method.invoke(object, args);
        if (method.getAnnotation(Transactional.class) != null) {
            System.out.println("} [after]");
        }
    }
}

class MyClass {
    @Transactional
    void aaa() {
        System.out.println("aaa() is called");
    }

    void bbb() {
        System.out.println("bbb() is called");
    }

    void ccc() {
        System.out.println("ccc() is called");
    }
}
