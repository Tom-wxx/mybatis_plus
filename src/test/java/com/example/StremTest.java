package com.example;

import com.example.pojo.Person;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import java.sql.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
public class StremTest {

    @Test
    public void testMax(){
        List<Integer> list = Arrays.asList(7, 6, 9, 4, 11, 6);
        List<String> list1 = Arrays.asList("adnm", "admmt", "pot", "xbangd", "weoujgsd");
        List<String> list3 = Arrays.asList("m,k,l,a", "1,3,5,7");


        System.out.println("===============list====================");
        //自然排序
        Optional<Integer> max = list.stream().max(Integer::compareTo);
        System.out.println("最大值结果为："+max.get());
        //自定义排序
        Optional<Integer> max1 = list.stream().max(new Comparator<Integer>() {
            @Override
            public int compare(Integer integer, Integer t1) {
                return integer.compareTo(t1);
            }
        });
        System.out.println("自定义最大值结果为："+max1);
        long count = list.stream().filter(x -> x > 6).count();
        System.out.println("大于6的个数："+count);
        List<Integer> collect = list.stream().map(x -> x + 3).collect(Collectors.toList());
        System.out.println("每个元素加3"+collect);
        /*reduce方式*/
        //求和方式1,2,3
        Optional<Integer> sum = list.stream().reduce((x, y) -> x + y);
        Optional<Integer> sum1 = list.stream().reduce(Integer::sum);
        Integer sum2 = list.stream().reduce(0, Integer::sum);
        //求乘积
        Optional<Integer> product  = list.stream().reduce((x, y) -> x * y);
        //求最大值方式1,2,3
        Optional<Integer> max3 = list.stream().max(Integer::compareTo);
        Optional<Integer> max4 = list.stream().reduce((x, y) -> x > y ? x : y);
        Integer max5 = list.stream().reduce(1, Integer::max);

        System.out.println("list求和：" + sum.get() + "," + sum1.get() + "," + sum2);
        System.out.println("list求积：" + product .get());
        System.out.println("list求最大：" + max4.get() + "," + max5);

        System.out.println("==============list1=====================");
        Optional<String> max2 = list1.stream().max(Comparator.comparing(String::length));
        System.out.println("最长的字符串:"+max2.get());
        List<String> toUpperCase = list1.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println("每个元素大写："+toUpperCase);

        System.out.println("==============list3=====================");
        List<String> collect3 = list3.stream().flatMap(s -> {
            //将每个元素换成一个stream
            String[] split = s.split(",");
            Stream<String> stream = Arrays.stream(split);
            return stream;
        }).collect(Collectors.toList());
        System.out.println("处理前的集合：" + list3);
        System.out.println("处理后的集合：" + collect3);

        System.out.println("===============排序随机====================");
        Stream<Integer> stream = Stream.of(1,2,3,4,5,6);
        List<Integer> collect1 = Stream.iterate(0, (x) -> x + 3).limit(10).collect(Collectors.toList());
        System.out.println(collect1);
        //stream2.forEach(System.out::println);
        Stream<Double> stream3 = Stream.generate(Math::random).limit(6);
        stream3.forEach(System.out::println);
    }

    @Test
    public void testPerson(){
        ArrayList<Person> peopleList = new ArrayList<>();

        peopleList.add(new Person("Tom", 8900, 21,"male", "New York"));
        peopleList.add(new Person("Jack", 7000, 22,"male", "Washington"));
        peopleList.add(new Person("Lily", 7800, 23,"female", "Washington"));
        peopleList.add(new Person("Anni", 8200, 24,"female", "New York"));
        peopleList.add(new Person("Owen", 9500, 25,"male", "New York"));
        peopleList.add(new Person("Alisa", 7900, 26,"female", "New York"));

      /*  List<String> stringStream = peopleList.stream().filter(x -> x.getSalary() > 8000).map(Person::getName).collect(Collectors.toList());
        System.out.println("工资高于80000："+stringStream);
        Optional<Person> maxSalary = peopleList.stream().max(Comparator.comparingInt(Person::getSalary));
        System.out.println("工资最高: "+maxSalary.get()+"\n"+"工资数："+maxSalary.get().getSalary());
        // 不改变原来员工集合的方式
        List<Person> collect = peopleList.stream().map(person -> {
            Person personNew = new Person(person.getName(), 0, 0, null, null);
            personNew.setSalary(person.getSalary() + 1000);
            return personNew;
        }).collect(Collectors.toList());
        System.out.println("不改变原来员工集合的方式");
        System.out.println("一次改动前："+peopleList.get(1).getName()+"-->"+peopleList.get(1).getSalary());
        System.out.println("一次改动后："+collect.get(1).getName()+"-->"+collect.get(1).getSalary());
        // 改变原来员工集合的方式
        List<Person> collect1 = peopleList.stream().map(person -> {
            person.setSalary(person.getSalary() + 1000);
            return person;
        }).collect(Collectors.toList());
        System.out.println("改变原来员工集合的方式");
        System.out.println("二次改动前："+peopleList.get(0).getName()+"-->"+peopleList.get(0).getSalary());
        System.out.println("二次改动后："+collect1.get(0).getName()+"-->"+collect1.get(0).getSalary());*/

        Optional<Integer> sum = peopleList.stream().map(Person::getSalary).reduce(Integer::sum);
        Integer sumSalary2  = peopleList.stream().reduce(0, (x,y) -> x += y.getSalary(), (x, y) -> x + y);
        Integer sumSalary3  = peopleList.stream().reduce(0, (x,y) -> x += y.getSalary(), Integer::sum);
        System.out.println("工资之和："+sum.get()+","+sumSalary2+","+sumSalary3);
        Optional<Integer> maxSalary  = peopleList.stream().map(Person::getSalary).reduce(Integer::max);
        Integer maxSalary1 = peopleList.stream().reduce(0, (max, person) -> max > person.getSalary() ? max : person.getSalary(), Integer::max);
        Integer maxSalary2 = peopleList.stream().reduce(0, (max, person) -> max > person.getSalary() ? max : person.getSalary(), (x,y) -> x > y ? x : y);
        System.out.println("最高工资："+maxSalary.get()+","+maxSalary1+","+maxSalary2);
    }

}
