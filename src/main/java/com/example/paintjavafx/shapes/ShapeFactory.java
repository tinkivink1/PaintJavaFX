package com.example.paintjavafx.shapes;

import javafx.scene.paint.Color;

public class ShapeFactory {
    public static Shape createShape(String figure, Color color, double size, double x, double y) {

        if (figure.equalsIgnoreCase("Triangle")) {
            return new Triangle(color, size, x, y);
        } else if (figure.equalsIgnoreCase("Square")) {
            return new Square(color, size, x, y);
        } else if (figure.equalsIgnoreCase("Pentagon")) {
            return new Pentagon(color, size, x, y);
        } else {
            // Handle unsupported number of sides
            return new Triangle(color, size, x, y);
        }
    }
}
