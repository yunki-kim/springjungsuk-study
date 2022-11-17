package com.fastcampus.ch3.diCopy4;

import com.google.common.reflect.ClassPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component class Car {
    @Resource Engine engine;
    @Resource Door door;

    @Override
    public String toString() {
        return "Car{" +
                "engine=" + engine +
                ", door=" + door +
                '}';
    }
}
@Component class SportsCar extends Car {}
@Component class Truck extends Car {}
@Component class Engine {}
@Component class Door {}

class AppContext {
    Map map;

    AppContext() {
        map = new HashMap();
        doComponentScan();
        doAutowired();
        doResource();
    }

    private void doComponentScan() {
        // 1. 패키지 내의 클래스 목록을 가져온다.
        try {
            ClassLoader classLoader = AppContext.class.getClassLoader();
            ClassPath classPath = ClassPath.from(classLoader);

            Set<ClassPath.ClassInfo> set = classPath.getTopLevelClasses("com.fastcampus.ch3.diCopy4");
            // 2. 반복문으로 클래스를 하나씩 읽어와서 @Component이 붙어있는지 확인
            for (ClassPath.ClassInfo classInfo : set) {
                Class aClass = classInfo.load();
                Component component = (Component) aClass.getAnnotation(Component.class);
                if (component != null) {
                    // 3. @Component가 붙어있으면 객체를 생성해서 map에 저장
                    String key = StringUtils.uncapitalize(classInfo.getSimpleName());
                    map.put(key, aClass.newInstance());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doAutowired() {
        // map에 저장된 객체의 iv중에 @Autowired가 붙어있으면
        // map에서 iv의 타입에 맞는 객체를 찾아서 연결(객체의 주소를 iv저장)
        try {
            for (Object bean : map.values()) {
                for (Field fld : bean.getClass().getDeclaredFields()) {
                    if (fld.getAnnotation(Autowired.class) != null) { // by Type
                        fld.set(bean, getBean(fld.getType())); // car.engine = obj;
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void doResource() {
        // map에 저장된 객체의 iv중에 @Resource가 붙어있으면
        // map에서 iv의 타입에 맞는 객체를 찾아서 연결(객체의 주소를 iv저장)
        try {
            for (Object bean : map.values()) {
                for (Field fld : bean.getClass().getDeclaredFields()) {
                    if (fld.getAnnotation(Resource.class) != null) { // by Name
                        fld.set(bean, getBean(fld.getName())); // car.engine = obj;
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    Object getBean(String key) { // by Name
        return map.get(key);
    }

    Object getBean(Class aClass) { // by Type
        for (Object object : map.values()) {
            if (aClass.isInstance(object)) {
                return object;
            }
        }
        return null;
    }
}

public class Main4 {
    public static void main(String[] args) throws Exception {

        AppContext ac = new AppContext();

        Car car = (Car) ac.getBean("car");
        Engine engine = (Engine) ac.getBean("engine");
        Door door = (Door) ac.getBean("door");

        // 수동으로 객체를 연결
        // car.engine = engine;
        // car.door = door;

        System.out.println("car = " + car);
        System.out.println("engine = " + engine);
        System.out.println("door = " + door);
    }
}
