package com.example.paintjavafx.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Triangle extends Shape {

    public Triangle(Color color, double size, double x, double y) {
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
        double cy = y + size / 3; // смещаем центр окружности ниже, чтобы треугольник был вписан
        double r = size / Math.sqrt(3); // радиус окружности
        double x1 = cx + r; // координаты вершин треугольника
        double y1 = cy;
        double x2 = cx - r / 2;
        double y2 = cy + r * Math.sqrt(3) / 2;
        double x3 = cx - r / 2;
        double y3 = cy - r * Math.sqrt(3) / 2;
        gc.moveTo(x1, y1);
        gc.lineTo(x2, y2);
        gc.lineTo(x3, y3);
        gc.closePath();
        gc.fill();
    }

    @Override
    public String descriptor() {
        return "Triangle";
    }
}