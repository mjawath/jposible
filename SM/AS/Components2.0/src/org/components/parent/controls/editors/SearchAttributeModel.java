/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.components.parent.controls.editors;

/**
 *
 * @author jawa
*/
public class SearchAttributeModel {

    private String description;
    private String attribute;

    public SearchAttributeModel(String description, String attribute) {
        this.description = description;
        this.attribute = attribute;
    }

    @Override
    public String toString() {
        return "SearchAttributeModel{" + "description=" + description + ", attribute=" + attribute + '}';
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    
}
