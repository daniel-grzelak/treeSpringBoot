package com.daniel.controllers;

import com.daniel.dao.NodeDao;
import com.daniel.domain.FormNode;
import com.daniel.domain.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Kapelusznik on 05.07.2017.
 */
@Controller
public class MyController {

    private NodeDao nodeDao;


    @Autowired
    public MyController(NodeDao nodeDao) {
        this.nodeDao = nodeDao;
    }




    @RequestMapping("/add/{number}")
    /**
     * This is a help method for creating parentless nodes.
     */
    public String addParentless(Model m, @PathVariable("number") Integer number) {
        Node n = new Node(number);
        nodeDao.save(n);
        List<Node> nodes = nodeDao.findAll();
        Node node = new Node();
        m.addAttribute("node", node);
        m.addAttribute("nodes", nodes);

        return "redirect:/";
    }


    @RequestMapping("/select")
    public String select() {
        List<Node> nodes = nodeDao.findAll();
        System.out.println(nodes);
        return "index";
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String formGet(Model m) {
        FormNode formNode = new FormNode();
        m.addAttribute("formNode", formNode);
        List<Node> nodes = nodeDao.findAll();
        m.addAttribute("nodes", nodes);
        return "index";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String formDelete(@ModelAttribute FormNode node1, BindingResult result, Model m) {
        if (nodeDao.findOne(node1.getSelectedNode()).getNode() != null) {
            Node parentNode = nodeDao.findOne(node1.getSelectedNode()).getNode();
            parentNode.getChildren().remove(nodeDao.findOne(node1.getSelectedNode()));
        }

        nodeDao.delete(node1.getSelectedNode());
        return "redirect:/";
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String formModify(@ModelAttribute FormNode node1, BindingResult result, Model m) {
        Node n = nodeDao.findOne(node1.getSelectedNode());
        n.setNumber(node1.getNumber());
        nodeDao.save(n);
        return "redirect:/";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String formAdd(@ModelAttribute FormNode node1, BindingResult result, Model m) {

        if (node1.getSelectedNode() == null) {
            nodeDao.save(new Node(node1.getNumber()));
        } else {
            Node nodeParent = nodeDao.findOne(node1.getSelectedNode());
            Node n = new Node(node1.getNumber(), nodeParent);
            nodeParent.getChildren().add(n);
            nodeDao.save(n);
        }


        return "redirect:/";
    }


}
