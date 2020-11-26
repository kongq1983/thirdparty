package com.kq.rxjava3.entity;

/**
 * InventoryDetail
 *
 * @author kq
 * @date 2019-12-02
 */
public class InventoryDetail {

    private Long id;
    private Long inventoryId;
    private String color;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("InventoryDetail{");
        sb.append("id=").append(id);
        sb.append(", inventoryId=").append(inventoryId);
        sb.append(", color='").append(color).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
