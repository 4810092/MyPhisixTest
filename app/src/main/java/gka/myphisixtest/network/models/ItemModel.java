package gka.myphisixtest.network.models;

import java.util.Locale;

public class ItemModel {

    private String name;
    private PriceModel price;
    private double percent_change;
    private int volume;
    private String symbol;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PriceModel getPrice() {
        return price;
    }

    public void setPrice(PriceModel price) {
        this.price = price;
    }

    public double getPercent_change() {
        return percent_change;
    }

    public void setPercent_change(double percent_change) {
        this.percent_change = percent_change;
    }

    public String getVolume() {
        return String.valueOf(volume);
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getPriceAmount() {
        if (price == null)
            return "";

        return String.format(Locale.getDefault(), "%.2f", price.getAmount());

    }
}
