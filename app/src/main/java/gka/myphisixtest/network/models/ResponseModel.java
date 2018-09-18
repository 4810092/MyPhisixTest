package gka.myphisixtest.network.models;

import java.util.ArrayList;

public class ResponseModel {

    private ArrayList<ItemModel> stock;
    private String as_of;


    public ArrayList<ItemModel> getStock() {
        return stock;
    }

    public void setStock(ArrayList<ItemModel> stock) {
        this.stock = stock;
    }

    public String getAs_of() {
        return as_of;
    }

    public void setAs_of(String as_of) {
        this.as_of = as_of;
    }
}
