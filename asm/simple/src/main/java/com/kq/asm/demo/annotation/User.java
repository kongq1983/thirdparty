package com.kq.asm.demo.annotation;

/**
 * @author kq
 * @date 2022-07-23 11:05
 * @since 2020-0630
 */
@Table(name="BASE_USER")
@Encode("utf-8")
public class User {
    @Column(name = "name")
    private String name = "loowj";
    @Column(name = "id")
    private String id = "001";

    /**
     * 构造函数1
     */
    public User(String id,String name) {
    }
    public User(String name){
    }
    private User(){}

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void getUser(){
        System.out.println(this.id + "   " +this.name);
    }

}
