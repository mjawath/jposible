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
    
     private static final String EQUAL_OP = " = ";
    private static final String QT_OP = " > ";
    private static final String QTE_OP  = " >= ";
    private static final String LT_OP  = " < ";
    private static final String LTE_OP  = " <= ";
    private static final String NOTE_EQ_OP  = " != ";
    private static final String LIKE_OP  = " LIKE ";
    private static final String LIKE_CONTAIN_OP  = " LIKE %";
    

    private String description;
    private String attribute;
    
    private Object value;     
    private String operator;

    private String value2;

    public SearchAttributeModel(Object value, String field) {
        this(field, value, LIKE_CONTAIN_OP);
    }
    
    

    public SearchAttributeModel( String field,Object value, String operator) {
        this.value = value;
        this.attribute = field;
        this.operator = operator;
    }

    
    
    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
   
    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

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
