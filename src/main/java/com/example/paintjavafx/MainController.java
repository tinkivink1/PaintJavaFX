package com.example.paintjavafx;

import com.example.paintjavafx.shapes.Shape;
import com.example.paintjavafx.shapes.ShapeFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainController {

    private double lastX, lastY;
    private double startX, startY;

    private Color currentColor = Color.BLACK;
    private double currentPenSize = 5;


    @FXML
    private TextField penSizeTextField;

    @FXML
    private Canvas canvas;

    @FXML
    private Button penSizeButton;

    @FXML
    private Button colorButton;

    @FXML
    private Button drawModeButton;

    @FXML
    private Button clearButton;

    @FXML
    private CheckBox freeDrawCheckBox;

    @FXML
    private ComboBox<String> shapeComboBox;

    private boolean freeDraw = false;
    private String selectedShape;

    @FXML
    public void initialize() {
        penSizeTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                double size = Double.parseDouble(newValue);
                if (size >= 0 && size <= 100) {
                    currentPenSize = size;
                }
            } catch (NumberFormatException e) {
                // Ignore invalid input
                System.out.println("Size parsing troubles");
            }
        });
    }

    @FXML
    public void handleFreeDrawCheckBox() {
        freeDraw = freeDrawCheckBox.isSelected();
    }

    @FXML
    public void handleShapeComboBox() {
        selectedShape = shapeComboBox.getSelectionModel().getSelectedItem();
    }


    @FXML
    public void handleCanvasMouseReleased(MouseEvent event) {
        if (!freeDraw) {
            double endX = event.getX();
            double endY = event.getY();
            double size = Math.sqrt(Math.pow(endX - startX, 2) + Math.pow(endY - startY, 2));
            double centerX = (startX + endX) / 2;
            double centerY = (startY + endY) / 2;
            Shape shape = ShapeFactory.createShape(selectedShape, currentColor, size, centerX, centerY);
            shape.draw(canvas.getGraphicsContext2D());
            System.out.println("mouse released");
            System.out.println("selectedShape: " + selectedShape);
            System.out.println("size: " + size);
            System.out.println("centerX: " + centerX);
            System.out.println("centerY: " + centerY);
            System.out.println(shape);
        }
    }



    @FXML
    private void handleCanvasMouseDragged(MouseEvent event) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        if (freeDraw) {
            if(selectedShape.equalsIgnoreCase("Default")){

                gc.setStroke(currentColor);
                gc.setLineWidth(currentPenSize);

                double x1 = lastX;
                double y1 = lastY;
                double x2 = (lastX + event.getX()) / 2;
                double y2 = (lastY + event.getY()) / 2;
                double x3 = event.getX();
                double y3 = event.getY();

                gc.beginPath();
                gc.moveTo(x1, y1);
                gc.bezierCurveTo(x2, y2, x2, y2, x3, y3);
                gc.stroke();

                lastX = event.getX();
                lastY = event.getY();
            }
            else{
                Shape shape = ShapeFactory.createShape(selectedShape, currentColor, currentPenSize, event.getX(), event.getY());
                shape.draw(gc.getCanvas().getGraphicsContext2D());
            }
        }
    }

    @FXML
    private void handleCanvasMousePressed(MouseEvent event) {
        if (freeDraw) {
            lastX = event.getX();
            lastY = event.getY();

            System.out.println("size: " + currentPenSize);
            System.out.println("color: " + currentColor);
        }
        else {
            startX = event.getX();
            startY = event.getY();
        }
    }

    @FXML
    private void handleClearButtonAction(ActionEvent event)  {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
    }

    public void handleColorRectangleClicked(MouseEvent event) {
        Rectangle colorRectangle = (Rectangle) event.getSource();
        Color selectedColor = (Color) colorRectangle.getFill();

        currentColor = selectedColor;
        System.out.println("Colored rectangle clicked!");
    }

}