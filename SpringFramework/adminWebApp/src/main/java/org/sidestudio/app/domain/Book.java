package org.sidestudio.app.domain;

/**
 * Book 도메인(model)
 *
 * @author logan
 * @since 2016-03-04
 */
public class Book {

    String name;
    String price;
    String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
