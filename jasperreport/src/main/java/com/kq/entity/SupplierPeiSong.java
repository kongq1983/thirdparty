package com.kq.entity;

/**
 * @author kq
 * @date 2022-01-22 14:03
 * @since 2020-0630
 */
public class SupplierPeiSong {

    private String id;
    private String name;
    private String person;
    private String approveDate;
    private String dtcolumn;

    private String[] dataArray;

    public String[] getDataArray() {
        return dataArray;
    }

    public void setDataArray(String[] dataArray) {
        this.dataArray = dataArray;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(String approveDate) {
        this.approveDate = approveDate;
    }

    public String getDtcolumn() {
        return dtcolumn;
    }

    public void setDtcolumn(String dtcolumn) {
        this.dtcolumn = dtcolumn;
    }

    @Override
    public String toString() {
        return "SupplierPeiSong{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", person='" + person + '\'' +
                ", approveDate='" + approveDate + '\'' +
                ", dtcolumn='" + dtcolumn + '\'' +
                '}';
    }
}
