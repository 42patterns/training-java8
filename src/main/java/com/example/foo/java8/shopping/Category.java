package com.example.foo.java8.shopping;

public class Category {

    private int id;
    private String name;
    private int parent;
    private int position;
    private boolean isProductCatalogueEnabled;

    public Category(int id, String name, int parent, int position, boolean isProductCatalogueEnabled) {
        this.id = id;
        this.name = name;
        this.parent = parent;
        this.position = position;
        this.isProductCatalogueEnabled = isProductCatalogueEnabled;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isProductCatalogueEnabled() {
        return isProductCatalogueEnabled;
    }

    public void setProductCatalogueEnabled(boolean productCatalogueEnabled) {
        isProductCatalogueEnabled = productCatalogueEnabled;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parent=" + parent +
                ", position=" + position +
                ", isProductCatalogueEnabled=" + isProductCatalogueEnabled +
                '}';
    }
}
