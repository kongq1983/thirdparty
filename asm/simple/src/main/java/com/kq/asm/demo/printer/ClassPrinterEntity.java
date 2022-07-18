package com.kq.asm.demo.printer;

/**
 * @author kq
 * @date 2022-07-18 16:55
 * @since 2020-0630
 */
public class ClassPrinterEntity {

    private Long id;
    private String name;
    private int age;

    public ClassPrinterEntity(){

    }

    public ClassPrinterEntity(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
