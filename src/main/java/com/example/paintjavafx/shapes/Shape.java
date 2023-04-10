package com.example.paintjavafx.shapes;

import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Shape extends Node {
    protected Color color;
    protected double size;
    protected double x, y;
    public Shape(Color color, double size, double x, double y) {
        this.color = color;
        this.size = size;
        this.x = x;
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public abstract void draw(GraphicsContext gc);

    public abstract String descriptor();

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setSize(double size) {
        this.size = size;
    }

    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }
}