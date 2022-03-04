package com.example.btl;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import  java.nio.file.Files;
import java.io.File;

public class HelloController {
    @FXML
    private ImageView imageView;
    @FXML
    private TreeView<File> treeView;

    public void InitTreeView(){
        File tmp = new File("/");
        TreeItem<File> root = new TreeItem<File>(tmp);
        File[] files = File.listRoots();
        for(File file:files){
           TreeItem<File> node = new TreeItem<File>(file);
           root.getChildren().add(node);
        }
        treeView.setRoot(root);
        treeView.setShowRoot(false);
    }
    public void Select(){
        try{
            TreeItem<File> item = treeView.getSelectionModel().getSelectedItem();
            File tmp = item.getValue();
            if(item.isLeaf()){
                if(!tmp.isFile()){
                    File[] files = tmp.listFiles();
                    for(File file:files){
                        TreeItem<File> node = new TreeItem<File>(file);
                        item.getChildren().add(node);
                    }
                }
            }
            if(tmp.isFile()){
                String name = tmp.toString();
                if(name.endsWith(".png")||
                    name.endsWith(".jpeg")||
                    name.endsWith(".jpg" )){
                    String path =
                    tmp.toURI().toURL().toString();
                    System.out.println(path);
                    Image image = new Image(path);
                    imageView.setImage(image);
                }
            }
            else{
                if(item.isExpanded()){
                    item.setExpanded(false);
                }
                else{
                    item.setExpanded(true);
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }

    }


    @FXML
    private Label welcomeText;

    public void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}