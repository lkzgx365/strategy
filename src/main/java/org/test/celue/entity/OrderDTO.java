package org.test.celue.entity;

/**
 * @Description:
 * @Author:
 * @Date:
 **/
public class OrderDTO {

    private String type;

    private String code;

    private String price;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "type='" + type + '\'' +
                ", code='" + code + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
