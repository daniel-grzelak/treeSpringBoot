package com.daniel.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kapelusznik on 05.07.2017.
 */
@Entity
public class Node {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int number;

    @OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL, mappedBy="node")
    private List<Node> children = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    private Node node;


    public Node(int number, List<Node> children, Node node) {
        this.number = number;
        this.children = children;
        this.node = node;
    }

    public Node() {
    }

    public Node(int number, Node node) {
        this.number = number;
        this.node = node;
    }

    public Node(int number) {
        this.number = number;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public String showChildren() {
        if(getChildren().isEmpty() || getChildren() == null) {

            return "";
        }
        else{
            StringBuilder sb = new StringBuilder();
                for(Node n : getChildren()) {
                    sb.append("Node " + n.getId() + " ");
                }
            return " Children: " + sb.toString();
        }
    }

    /**
     *
     * This boolean is used for checking if node is a leaf or not.
     */
    public Boolean isALeaf() {
        if(getChildren().isEmpty() || getChildren() == null) {

            return true;
        }else {
            return false;
        }
    }


    /**
     *
     * This method sums up all the numbers in the leaf's path. First it checks if the node is a leaf, then it uses recurrence to sum the numbers.
     */
    public Integer sum() {
            Integer add = 0;
            add += getNumber();
            if(node != (null)) {
                return add += node.sum();
            }
            else {
                return add;
            }

    }


    /**
     *
     * Such way of creating methods helps me to manage how the toString() is shown.
     */
    public String leafResult() {
        if(isALeaf() == true) {
            return " Leaf result: " + sum();
        }else {
            return "";
        }
    }

    public String showParent() {
        if(node == null) {

            return "";
        }
        else{

            return " Parent: " + node.getId();
        }
    }

    @Override
    public String toString() {
        return "Node " + id + " Number held: " + number + showParent() + showChildren() + leafResult();
    }
}
