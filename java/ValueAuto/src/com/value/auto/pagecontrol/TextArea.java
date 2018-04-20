package com.value.auto.pagecontrol;

import java.util.ArrayList;
import java.util.List;

public class TextArea extends PageControl {
    public TextArea(){
        this.type="textArea";
    }
    private int width;
    private int height;
    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    
}
