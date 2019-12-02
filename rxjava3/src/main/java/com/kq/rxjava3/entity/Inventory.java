package com.kq.rxjava3.entity;

/**
 * Inventory
 *
 * @author kq
 * @date 2019-12-02
 */
public class Inventory {

    private Long id;
    private String name;
    private String code;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Inventory{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", code='").append(code).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
