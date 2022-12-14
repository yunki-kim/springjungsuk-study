// package com.fastcampus.ch3;
//
// import org.springframework.beans.factory.annotation.*;
// import org.springframework.context.*;
// import org.springframework.context.annotation.PropertySource;
// import org.springframework.context.annotation.Scope;
// import org.springframework.context.support.*;
// import org.springframework.stereotype.*;
//
// import java.util.*;
//
// @Component
// @Scope("prototype")
// class Door {}
// @Component class Engine {}
// @Component class TurboEngine extends Engine {}
// @Component class SuperEngine extends Engine {}
//
// @Component
// class Car {
//     String color;
//     int oil;
//     Engine engine;
//     Door[] doors;
//
//     @Autowired
//     public Car(@Value("red") String color, @Value("100") int oil, Engine engine, Door[] doors) {
//         this.color = color;
//         this.oil = oil;
//         this.engine = engine;
//         this.doors = doors;
//     }
//
//     @Override
//     public String toString() {
//         return "Car{" +
//                 "color='" + color + '\'' +
//                 ", oil=" + oil +
//                 ", engine=" + engine +
//                 ", doors=" + Arrays.toString(doors) +
//                 '}';
//     }
// }
//
// @Component
// @PropertySource("setting.properties")
// class SysInfo {
//     @Value("#{systemProperties['user.timezone']}")
//     String timeZone;
//
//     @Value("#{systemEnvironment['PWD']}")
//     String currDir;
//
//     @Value("${autosaveDir}")
//     String autosaveDir;
//
//     @Value("${autosaveInterval}")
//     int autosaveInterval;
//
//     @Value("${autosave}")
//     boolean autosave;
//
//     @Override
//     public String toString() {
//         return "SysInfo{" +
//                 "timeZone='" + timeZone + '\'' +
//                 ", currDir='" + currDir + '\'' +
//                 ", autosaveDir='" + autosaveDir + '\'' +
//                 ", autosaveInterval=" + autosaveInterval +
//                 ", autosave=" + autosave +
//                 '}';
//     }
// }
//
// public class ApplicationContextTest {
//     public static void main(String[] args) {
//         ApplicationContext ac = new GenericXmlApplicationContext("config.xml");
// //      Car car = ac.getBean("car", Car.class); // ????????? ???????????? ????????? ????????????. ????????? ????????? ??????
//         Car car  = (Car) ac.getBean("car"); // ???????????? ??? ??????
//         System.out.println("car = " + car);
//
//         System.out.println("ac.getBean(SysInfo.class) = " + ac.getBean(SysInfo.class));
//     }
// }
