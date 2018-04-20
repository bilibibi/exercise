package com.value.auto.pagecontrol;

import java.util.ArrayList;
import java.util.List;

public class CheckBox extends PageControl {
    public CheckBox(){
        this.type="radio";
    }
    private List<String> itemLabel = new ArrayList<String>();
    public List<String> getItemLabel() {
        return itemLabel;
    }

    public void setItemLabel(List<String> itemLabel) {
        this.itemLabel = itemLabel;
    }
    
}
