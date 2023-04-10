package com.example.paintjavafx.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Pentagon extends Shape {

    public Pentagon(Color color, double size, double x, double y) {
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
        double theta = 2 * Math.PI / 5; // угол между вершинами пятиугольника
        double cx = x + size * Math.cos(Math.PI / 2 - theta / 2); // координата центра окружности по x
        double cy = y - size * Math.sin(Math.PI / 2 - theta / 2); // координата центра окружности по y
        double r = size * Math.sin(theta / 2); // радиус окружности
        gc.moveTo(cx + r, cy);
        for (int i = 1; i < 5; i++) {
            double x = cx + r * Math.cos(theta * i);
            double y = cy - r * Math.sin(theta * i);
            gc.lineTo(x, y);
        }
        gc.closePath();
        gc.fill();
    }

    @Override
    public String descriptor() {
        return "Pentagon";
    }
}