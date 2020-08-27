package org.itourshare.test;

import com.sun.org.apache.xml.internal.security.Init;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName : TestJDK8
 * @Description :
 * @Author : its
 * @Date: 2020-08-27 09:07
 */
public class TestJDK8 {

    public static List<Picture> pictures = new ArrayList<>();

    public static void init() {
        pictures = new ArrayList<>();
        pictures.add(new Picture("AA", 11.11));
        pictures.add(new Picture("BB", 22.22));
        pictures.add(new Picture("CC", 33.33));
    }

    public static void main(String[] args) {
        Picture dd = new Picture("DD", 44.44);
        dd = null;
        Picture ee = Optional.ofNullable(dd).orElse(new Picture("EE", 55.55));
        System.out.println(ee);

        System.out.println("===================================");

        init();
        List<Picture> collect = pictures.stream().map(picture -> {
            picture.setPrice(998D);
            return picture;
        }).collect(Collectors.toList());
        collect.stream().forEach(System.out::println);

        System.out.println("===================================");

        init();
        List<Picture> collect1 = pictures.stream().sorted(Comparator.comparing(Picture::getPrice)).collect(Collectors.toList());
        collect1.stream().forEach(System.out::println);

        init();
        List<Picture> collect2 = pictures.stream().sorted(Comparator.comparing(Picture::getPrice).reversed()).collect(Collectors.toList());
        collect2.stream().forEach(System.out::println);

        System.out.println("===================================");

        init();
        List<Picture> collect3 = pictures.stream().filter(picture -> {
            return "BB".equals(picture.getName());
        }).collect(Collectors.toList());
        collect3.stream().forEach(System.out::println);

        System.out.println("===================================");

        init();
        boolean b = pictures.stream().anyMatch(picture -> picture.getPrice() > 30);
        System.out.println(b);

        System.out.println("===================================");

    }
}

class Picture {
    private String name;

    private Double price;

    public Picture(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
