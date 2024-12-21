package com.example.demo1;

import javafx.scene.Node;

public class DraggableMaker {

    private double mouseAnchorY;
    private double mouseAnchorX;
    private double initialX;
    private double initialY;
    public void makeDraggable(Node node, int index) {
        node.setOnMousePressed(mouseEvent -> {
            initialX = node.getLayoutX();
            initialY = node.getLayoutY();
            mouseAnchorX = mouseEvent.getX();
            mouseAnchorY = mouseEvent.getY();
            System.out.println(index);
        });

        node.setOnMouseDragged(mouseEvent -> {
            node.setLayoutX(mouseEvent.getSceneX() - mouseAnchorX);
            node.setLayoutY(mouseEvent.getSceneY() - mouseAnchorY);
        });

        node.setOnMouseReleased(mouseEvent -> {
            node.setLayoutY(initialY);
            node.setLayoutX(initialX);
        });
    }
}
