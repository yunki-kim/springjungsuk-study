// package com.fastcampus.ch3;
//
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Qualifier;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.ApplicationContext;
// import org.springframework.context.support.GenericXmlApplicationContext;
// import org.springframework.stereotype.Component;
//
// import javax.annotation.Resource;
// import java.util.Arrays;
//
// @Component("engine") class Engine {} // <bean id="engine" class="com.fastcampus.ch3.Engine"/>
// @Component class SuperEngine extends Engine {}
// @Component class TurboEngine extends Engine {}
// @Component class Door {}
// @Component
// class Car {
//     @Value("red") String color;
//     @Value("100") int oil;
//     @Autowired // by Type
//     @Qualifier("superEngine") // 후보가 여러개 일 때 Qualifier로 선택
//     // @Resource(name="superEngine") // 위에 두줄을 대체 가능하지만 다른 것, by Name
//     Engine engine; // 타입으로 먼저 검색, 여러개면 이름으로 검색. - engine, superEngine, turboEngine
//     @Autowired Door[] doors;
//
//     // 기본 생성자를 꼭 만들어주자
//     public Car() {
//     }
//
//     // xml <constructor-arg> - constructor 이용
//     public Car(String color, int oil, Engine engine, Door[] doors) {
//         this.color = color;
//         this.oil = oil;
//         this.engine = engine;
//         this.doors = doors;
//     }
//
//     // xml <property> - setter 이용
//     public void setColor(String color) {
//         this.color = color;
//     }
//
//     public void setOil(int oil) {
//         this.oil = oil;
//     }
//
//     public void setEngine(Engine engine) {
//         this.engine = engine;
//     }
//
//     public void setDoors(Door[] doors) {
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
// public class SpringDITest {
//     public static void main(String[] args) {
//         // config.xml scope 기본값 singleton, 매번 새로운 객체를 생성하기 원하면 prototype
//         ApplicationContext ac = new GenericXmlApplicationContext("config.xml");
//
//         // Car car = (Car) ac.getBean("car"); // 아래와 동일
//         Car car = ac.getBean("car", Car.class);
//
//         // Engine engine = ac.getBean(Engine.class); // by Type - 같은 타입이 3개라서 에러
//         // Engine engine = (Engine) ac.getBean("superEngine"); // by Name
//
//         // Door door = (Door) ac.getBean("door");
//
//         // car.setColor("red");
//         // car.setOil(100);
//         // car.setEngine(engine);
//         // car.setDoors(new Door[]{ac.getBean("door", Door.class), (Door) ac.getBean("door")});
//
//         System.out.println("car = " + car);
//     }
// }
