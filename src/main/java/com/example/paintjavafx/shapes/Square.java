package com.example.paintjavafx.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Square extends Shape {

    public Square(Color color, double size, double x, double y) {
        super(color, size, x, y);
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(getColor());
        gc.setStroke(getColor());
        gc.beginPath();
        double cx = x;
        double cy = y;
        double r = size / Math.sqrt(2); // радиус окружности
        gc.moveTo(cx + r, cy + r);
        gc.lineTo(cx + r, cy - r);
        gc.lineTo(cx - r, cy - r);
        gc.lineTo(cx - r, cy + r);
        gc.closePath();
        gc.fill();
    }

    @Override
    public String descriptor() {
        return "Square";
    }
}