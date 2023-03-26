package me.bakuleva.coursework3.dto;

import me.bakuleva.coursework3.model.Color;
import me.bakuleva.coursework3.model.Size;

public class SockRequest {
    private final Color color;
    private final Size size;
    private final  int cottonPercentage;

    private int quantity;

    public SockRequest(Color color, Size size, int cottonPercentage, int quantity) {
        this.color = color;
        this.size = size;
        this.cottonPercentage = cottonPercentage;
        this.quantity = quantity;
    }

    public Color getColor() {
        return color;
    }

    public Size getSize() {
        return size;
    }

    public int getCottonPercentage() {
        return cottonPercentage;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}



