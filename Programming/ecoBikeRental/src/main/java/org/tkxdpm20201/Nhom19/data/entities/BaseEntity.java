package org.tkxdpm20201.Nhom19.data.entities;

public class BaseEntity {
    private Integer id;

    public BaseEntity(){

    }

    public BaseEntity(Integer id){
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
