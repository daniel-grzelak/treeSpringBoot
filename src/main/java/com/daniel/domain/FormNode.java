package com.daniel.domain;

/**
 * Created by Kapelusznik on 07.07.2017.
 */

//I create this class to simplify the control of my Thymeleaf form.
public class FormNode {
    private Long selectedNode;
    private Node node;
    private Integer number;


    public FormNode(Long selectedNode, Node node, Integer number) {
        this.selectedNode = selectedNode;
        this.node = node;
        this.number = number;
    }

    public FormNode() {
    }

    public Long getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(Long selectedNode) {
        this.selectedNode = selectedNode;
    }

    public Node getNode() {
        return node;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setNode(Node node) {
        this.node = node;
    }
}
