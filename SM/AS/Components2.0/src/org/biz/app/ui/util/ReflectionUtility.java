package org.biz.app.ui.util;

import java.awt.Component;
import java.lang.reflect.*;
import java.util.*;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import org.apache.commons.jexl2.JexlEngine;
import org.components.parent.controls.PxTable;



public class ReflectionUtility {

  public static final int ATTRIBUTE_FIRST = 11;
  public static final int METHOD_FIRST = 22;

  /**
   * Returns a dynamic instance of a class, using the default constructor
   * @param fullClassName - Fully qualified name of the Class as a String
   * @return Object
   */
  public static Object getDynamicInstance(String fullClassName) {
    try {
      Class dynamicClass = Class.forName(fullClassName.trim());
      Object dynamicObject = dynamicClass.newInstance();
      return dynamicObject;
    }
    catch (Exception ex) {
      return null;
    }
  }

  /**
   * Returns a dynamic instance of a Class
   * @param fullClassName - Fully qualified name of the Class as a String
   * @param args - Array of Args to the Constructor
   * @return Object
   */
  public static Object getDynamicInstance(String fullClassName, Object[] args) {
    try {
      Class dynamicClass = Class.forName(fullClassName.trim());
      Class[] constructorArgumentTypes = new Class[args.length];
      for (int i = 0; i < args.length; i++) {
        constructorArgumentTypes[i] = args[i].getClass();
      }
      Constructor classConstructor = dynamicClass.getConstructor(constructorArgumentTypes);
      Object dynamicObject = classConstructor.newInstance(args);
      return dynamicObject;
    }
    catch (Exception ex) {
      return null;
    }
  }

  /**
   * Returns a dynamic instance of a Class
   * @param fullClassName - Fully qualified name of the Class as a String
   * @param args - Array of Args to the Constructor
   * @param argTypes Class[]
   * @return Object
   */
  public static Object getDynamicInstance(String fullClassName, Object[] args, Class[] argTypes) {
    try {
      Class dynamicClass = Class.forName(fullClassName.trim());
      Class[] constructorArgumentTypes = argTypes;
      Constructor classConstructor = dynamicClass.getConstructor(constructorArgumentTypes);
      Object dynamicObject = classConstructor.newInstance(args);
      return dynamicObject;
    }
    catch (Exception ex) {
      return null;
    }
  }


  /**
   * Invokes the Dynamic method on the Object
   *
   * @param obj - Object
   * @param methodName - Method Name
   * @param argValues - Array of arguments to the Method
   * @throws Exception
   * @return Object US:2863 - Refactor, to invoke private dynamic method also
   */
  public static Object invokeDynamicMethod(Object obj, String methodName, Object[] argValues) throws Exception {
    if (obj != null && !StringUtility.isEmptyString(methodName)) {
      Method classMethod = ReflectionUtility.getMethodMember(obj.getClass(), methodName, argValues);
      return classMethod.invoke(obj, argValues);
    }
    else {
      return null;
    }
  }

  public static Object invokeDynamicMethod(Object obj, String methodName, Object[] argValues, Class[] argTypes) throws Exception {
    if (obj != null && !StringUtility.isEmptyString(methodName)) {
      Method classMethod = ReflectionUtility.getMethodMember(obj.getClass(), methodName, argTypes);
      return classMethod.invoke(obj, argValues);
    }
    else {
      return null;
    }
  }

  /**
   * Invokes a static method
   *
   * @param fullClassName String
   * @param methodName String
   * @param args arguments to the method, can be null
   * @return Object US:2863 - Refactor, to invoke private static method also
   */
  public static Object invokeStaticMethod(String fullClassName, String methodName, Object[] args) throws Exception {
    if (!StringUtility.isEmptyString(fullClassName) && !StringUtility.isEmptyString(methodName)) {
      Class className = Class.forName(fullClassName.trim());
      Method classMethod = ReflectionUtility.getMethodMember(className, methodName, args);
      return classMethod.invoke(null, args);
    }
    return null;
  }

  /**
   * Returns the Class[] corresponding to the given object array.
   * @param args Object[]
   * @return Class[]
   */
  private static Class[] getClassesForObjects(Object[] args) {
    Class[] argTypes = null;
    if (args != null) {
      int argLength = args.length;
      argTypes = new Class[argLength];
      for (int i = 0; i < argLength; i++) {
        argTypes[i] = args[i].getClass();
      }
    }
    return argTypes;
  }

  /**
   * This method will set a value for a given object's field
   * @param obj the objects that must be populated
   * @param fieldName name of the field
   * @param value the value to be set US:2863 - Refactor, to set private field value also
   */
  public static void setDynamicFieldValue(Object obj, String fieldName, Object value) {
    if (obj != null) { // safty trap
      Class cls = obj.getClass();
      try {
        Field classfield = ReflectionUtility.getFieldMember(cls, fieldName);
        classfield.set(obj, value);
      }
      catch (Exception ex) {
      ex.printStackTrace();
      }
    }
  }

  /**
   * Reads the given attribute in the given object using reflection Returns the same object
   * @param obj - Object to Search
   * @param attrName - attribute to retrive
   * @return Object US:2863 - Refactor, to get private field value also
   */
  public static Object getDynamicValue(Object obj, String attrName) {
    try {
      Class classObj = obj.getClass();
      Field classfield = ReflectionUtility.getFieldMember(classObj, attrName);
      return classfield.get(obj);
    }
    catch (Exception e) {
      return new String("0");
    }
  }

  /**
   * Gets the value from an attribute or by invoking a method. The mode defines which to check first.
   *
   * ATTRIBUTE_FIRST means that the code will attempt to read the value from an attribute with the given <code>fieldOrMethodName</code> name, and failing that, it will attempt to read the value by invoking a method with the same name.
   *
   * METHOD_FIRST performs the above action in reverse order.
   *
   * @param obj Object
   * @param fieldOrMethodName name of attribute or method name
   * @param mode ATTRIBUTE_FIRST or METHOD_FIRST
   * @return Object US:2863 - Refactor, to get private field value or private method also
   */
  public static Object getDynamicValue(Object obj, String fieldOrMethodName, int mode) {
    Class className = obj.getClass();
    try {
      if (mode == ATTRIBUTE_FIRST) {
        try {
          Field classfield = ReflectionUtility.getFieldMember(className, fieldOrMethodName);
          return classfield.get(obj);
        }
        catch (NoSuchFieldException exception) {
          Method classMethod = ReflectionUtility.getMethodMember(className, fieldOrMethodName, null);
          return classMethod.invoke(obj, null);
        }
      }
      else if (mode == METHOD_FIRST) {
        try {
          Method classMethod = ReflectionUtility.getMethodMember(className, fieldOrMethodName, null);
          return classMethod.invoke(obj, null);
        }
        catch (NoSuchMethodException exception) {
          Field classfield = ReflectionUtility.getFieldMember(className, fieldOrMethodName);
          return classfield.get(obj);
        }
      }
      else
        return null; // undefined mode
    }
    catch (Exception ex) {
      return null;
    }
  }

  /**
   * Given fields' values of given obj1 will be set to given obj2.
   * @param obj1
   * @param obj2
   * @param fields
   * @see getValue
   * @see setValue
   */
  public static void setValues(Object obj1, Object obj2, Field[] fields) {
    if (fields != null) {
      for (Field field : fields) {
        // get the field value from obj1
        Object value = ReflectionUtility.getValue(obj1, field.getName());
        // set the value to obj2
        ReflectionUtility.setValue(obj2, field.getName(), value);
      }
    }
  }


  /**
   * Returns array which contains the public fields of given object.
   * @param obj
   * @return Field[]
   */
  public static Field[] getPublicFields(Object obj) {
    if (obj == null) {
      return null;
    }
    Class classObj = obj.getClass();
    return classObj.getFields();
  }


  /**
   * Sets given value to given field of given object.
   * Incase there is no field is defined with given name then it will be
   * checked for the setter method for given field, if the method exist then
   * it will be invoked and set the value
   * @param obj
   * @param field
   * @param value
   */
  public static void setValue(Object obj, String field, Object value) {

    try {
      if (obj == null || StringUtility.isEmptyString(field)) {
        return;
      }
      Class className = obj.getClass();
      try {
        Field classfield = ReflectionUtility.getFieldMember(className, field);
        classfield.set(obj, value);
      }
      catch (NoSuchFieldException exception) {
        invokeDynamicMethod(obj, getSetterNameFromFieldName(field), new Object[]{value});
      }
    }
    catch (Exception e) {
    }
  }

  /**
   * Returns the value which belongs to the given field of given object.
   * Incase there is no field is defined with given name then it will be
   * checked for the getter method for given field, if the method exist then
   * it will be invoked and return the value
   * @param obj
   * @param field
   * @return Object
   */
  public static Object getValue(Object obj, String field) {
    try {
      if (obj == null || StringUtility.isEmptyString(field)) {
        return null;
      }
      Class className = obj.getClass();
      try {
        Field classfield = ReflectionUtility.getFieldMember(className, field);
        return classfield.get(obj);
      }
      catch (NoSuchFieldException exception) {
        return invokeDynamicMethod(obj, getGetterNameFromFieldName(field), null);
      }
    }
    catch (Exception e) {
      return null;
    }
  }

  /**
   * Retrieves the value of a static member of a class.
   * @param fullClassName String
   * @param attribName String
   * @return value of the attribute, null if any exception occurs.
   */
  public static Object getStaticValue(String fullClassName, String attribName) {
    if (!StringUtility.isEmptyString(fullClassName) && !StringUtility.isEmptyString(attribName)) {
      try {
        Class classObj = Class.forName(fullClassName.trim());
        Field classfield = getFieldMember(classObj, attribName);
        return classfield.get(null);
      }
      catch (Exception ex) {
        return null;
      }
    }
    else
      return null;
  }

  /**
   * Gets the full qualified ClassName for the given data Class The following formats are supported : <Prefix>_<class> : based on the prefix the correct package is choosen (B, STI, OR, M, MAS, etc) <class> : no _ nor . then the default package com.aexis.server.engine is assumed <package i>.<package i+1>.<class> : then a fully qualified class name is expected
   *
   * This method is optimized due to the new package structure of the client and server. Earlier this supports only the com.aexis.server.engine and com.aexis.data packages Now it supports com.aexis.client.data and com.aexis.server.data packages as well.
   *
   * @param nameOfClass String
   * @throws NewMLineException
   * @return String
   */
  public static String getFullClassPath(String nameOfClass) throws BizException {
    nameOfClass = nameOfClass.trim();
    String prefix = "";
    String dataPackage = "";
    String packageTail = "";
    String classTail = "";
    String className = "";
    int position = 0;

    if (nameOfClass.indexOf(".") > 0) // full class name
      return nameOfClass;
    else {
      position = nameOfClass.indexOf("_");
      if (position < 0)
        dataPackage = "com.aexis.server.engine."; // default engine package and classname is taken as given
      else if (position > 0) {
        prefix = nameOfClass.substring(0, position);
        nameOfClass = nameOfClass.substring(position + 1);
        packageTail = "";
        dataPackage = "com.aexis.data.";
        if (prefix.equalsIgnoreCase("BT")) // BTM Object
          packageTail = "btm.";
        else if (prefix.equalsIgnoreCase("B")) // Basic Object
          packageTail = "general.";
        else if (prefix.equalsIgnoreCase("STI")) // Sterili Object
          packageTail = "sti.";
        else if (prefix.equalsIgnoreCase("M")) // MLINE Object
          packageTail = "mline.";
        else if (prefix.equalsIgnoreCase("MAS")) // MAS Object
          packageTail = "system.";
        else if (prefix.equalsIgnoreCase("OR")) // ORLINE Object
          packageTail = "orline.";
        else if (prefix.equalsIgnoreCase("ER")) // ERLINE Object
          packageTail = "er.";
        else
          throw new BizException(); // Undefined package

        nameOfClass = nameOfClass.substring(0, 1).toUpperCase() + nameOfClass.substring(1).toLowerCase(); // class name is formatted
      }
      else
        throw new BizException(); // unknown format

      className = dataPackage + packageTail + prefix.toUpperCase() + "_" + nameOfClass + classTail;
      // if the className does not belong to the "com.aexis.server.engine." or "com.aexis.data." or "com.aexis.data.btm."
      try {
        Class.forName(className);
      }
      catch (ClassNotFoundException ex) {
        
      StringBuilder buf = new StringBuilder();
      buf.append(dataPackage);
      buf.append(packageTail);
      if (prefix.length() > 0) {
        buf.append(prefix.toUpperCase());
        buf.append("_");
      }
      buf.append(nameOfClass);
      buf.append(classTail);
      return buf.toString();

    }
    }
    return null;
  }
  

  /**
   * Get attribute/method values of given object
   * Fileds can be restricted based on allowedfieldNames array
   * allowed field names can specify property of
   * a child object (1 level) inside main object using format mainAttrib.childAttrib
   * e.g "{id, orcode, pat.code, pat.getLocation}"
   * Curerntly fileds names must be specified.
   * @TODO make allowed field names optionl. If not specified get all attributes of main class.
   * @TODO If specifeid with "-" at start get all attributes except thoses in cluding child attributes
   * @TODO Improve to get value of more than one level of sub attribute
   * @param object
   * @param allowedfieldNames
   * @return ArrayList
   */
  public static ArrayList getFieldValues(Object object, String[] fieldNamesAllowed) {
    if (object == null || fieldNamesAllowed == null || fieldNamesAllowed.length < 1) {
      return null;
    }
    //Keep a list ot store results
    ArrayList attributeValues = new ArrayList();

    //Assuming getting only specified attributes
    for (int i = 0; i < fieldNamesAllowed.length; i++) {

      //Check filed name validity
      if (StringUtility.isEmptyString(fieldNamesAllowed[i])) {
//        ProgrammerAlert.notify("Element " + i + " of allowed field names empty ");
        continue;
      }

      String mainAttributeName = fieldNamesAllowed[i];
      if (fieldNamesAllowed[i].contains(".")) {
        //attrib of child object, extract main attrib name
        mainAttributeName = fieldNamesAllowed[i].substring(0, fieldNamesAllowed[i].indexOf("."));
      }

      //Load main attribute      
      Object mainFieldValue = ReflectionUtility.getDynamicValue(object, mainAttributeName, ReflectionUtility.ATTRIBUTE_FIRST);
      //No child values.. add main attrib value. If  null simply add it
      if (mainFieldValue == null || !fieldNamesAllowed[i].contains(".")) {
        attributeValues.add(mainFieldValue);
        continue;
      }

      //Get child attrib value
      String childAttributeName = fieldNamesAllowed[i].substring(fieldNamesAllowed[i].indexOf(".") + 1);
      if (StringUtility.isEmptyString(childAttributeName) ) {
        attributeValues.add(null);
//        ProgrammerAlert.notify("Unable to extract value of sub attribute " + fieldNamesAllowed[i]);
        continue;
      }

      //Add child attrib value
      Object childAttribValue = ReflectionUtility.getDynamicValue(mainFieldValue, childAttributeName, ReflectionUtility.ATTRIBUTE_FIRST);
      attributeValues.add(childAttribValue);
    }
    return attributeValues;

  }


  /**
   * Returns the class name of the given object
   * <p>
   * <b>Examples:</b> <br>
   * getFullClassName(new String("d")) returns "java.lang.String"
   *
   * @param obj Object
   * @return String
   */
  public static String getFullClassName(Object obj) {
    return obj.getClass().getName();
  }

  /**
   * Gets a method from the given class with the given name, whose signature matches the classes of the given object list.
   * @param cls Class
   * @param methodName String
   * @param args Object[]
   * @throws NoSuchMethodException
   * @return Method
   */
  private static Method getMethodMember(Class cls, String methodName, Object[] args) throws NoSuchMethodException {
    Class[] argTypes = getClassesForObjects(args); // if args is null, then null is returned
    return getMethodMember(cls, methodName, argTypes);
  }

  /**
   * Gets a method from the given class with the given name, whose signature matches the given Class list.
   * @param cls Class
   * @param methodName String
   * @param argTypes Class[]
   * @throws NoSuchMethodException
   * @return Method
   */
  private static Method getMethodMember(Class cls, String methodName, Class[] argTypes) throws NoSuchMethodException {
    Method classMethod = null;
    try {
      classMethod = cls.getMethod(methodName.trim(), argTypes);// Get declared method, no private access
    }
    catch (NoSuchMethodException e) {
      classMethod = cls.getDeclaredMethod(methodName.trim(), argTypes);// Get declared method with private access
      classMethod.setAccessible(true);// Set accessibility of the private method
    }
    return classMethod;
  }

  /**
   * Returns a Field object as that accessibility or calling from subclass object
   * @param cls - Class object
   * @param attrName - String attribute name
   * @return Field object
   * @throws NoSuchFieldException
   */
  private static Field getFieldMember(Class cls, String attrName) throws NoSuchFieldException {
    Field classfield = null;
    try {
      classfield = cls.getField(attrName.trim());// Get declared field, but no private access
    }
    catch (NoSuchFieldException e) {
      classfield = cls.getDeclaredField(attrName.trim());// Get declared field with private access
      classfield.setAccessible(true);// Set accessibility of the private field
    }
    return classfield;
  }

    public static Field getFieldMemberx(Class cls, String attrName) {
        Field classfield = null;
        while (cls != null) {

            try {
                classfield = cls.getField(attrName.trim());// Get declared field, but no private access
            } catch (NoSuchFieldException e) {
                try {
                    classfield = cls.getDeclaredField(attrName.trim());// Get declared field with private access
                    classfield.setAccessible(true);// Set accessibility of the private field

                } catch (NoSuchFieldException ex) {
                }
            }
            if (classfield == null) {
                cls = cls.getSuperclass();                
            }else{
            return classfield;
                    
            }
        }
        return classfield;
    }
  
    public static Class getFieldType(Class cls, String attrName) {
        Field field = getFieldMemberx(cls, attrName);
        Class type = field.getType();//GenericType();
        return type;
    }
  
  public static Class[] getFieldTypesForAttributes(Class objcls, String[] attrName ){  
      Class[] cls=new Class[attrName.length];
      int x=0;      
        for(String name: attrName){            
           Class clsa= getFieldType(objcls, name);
           cls[x]=clsa;
           x++;
        }
        return cls;
  }
  
  public static Class[] getFieldTypesForAttributesForTable(Class obj, String[] attrName){
    Class [] cls= getFieldTypesForAttributes(obj, attrName);
    Class[] cls2=  new Class[cls.length+1];
    cls2[0]=Object.class;
      for (int i = 0; i < cls.length; i++) {
          Class class1 = cls[i];
          cls2[i+1]=class1;
      }
    return cls2; 
  }

  /**
   * Returns getter method name based on given field name by following
   * java naming convension.
   * <p/>
   * EXPECTATION: Given fieldName is following the naming convension
   * "fullName" --> "getFullName"
   * @param fieldName
   * @return String
   */
  private static String getGetterNameFromFieldName(String fieldName) {
    if (StringUtility.isEmptyString(fieldName)) {
      return "";
    }
    else {
      return "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());
    }
  }

  /**
   * Returns setter method name based on given field name by following
   * java naming convension.
   * <p/>
   * EXPECTATION: Given fieldName is following the naming convension
   * "fullName" --> "setFullName"
   * @param fieldName
   * @return String
   */
  private static String getSetterNameFromFieldName(String fieldName) {
    if (StringUtility.isEmptyString(fieldName)) {
      return "";
    }
    else {
      return "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());
    }
  }

  /**
   * This method takes an int argument where a value of 0 (zero)
   * returns sun.reflect.Reflection, 1 returns the class of the currently executing
   * method and every increment looks up the call stack
   * @param level int
   * @return String
   */
  public static String getCallerClassName(int level) {
    return StringUtility.getTail(sun.reflect.Reflection.getCallerClass(level+1).getName(),".");
  }

  /**
   * Returns a list of all fields declared in the given class (inclusive of private fields), and it's superclasses
   * @param type
   * @return
   */
  public static List<Field> getAllFields(Class type) {
    List<Field> result = new ArrayList<Field>();

    Class i = type;
    while (i != null && i != Object.class) {
      for (Field field : i.getDeclaredFields()) {
        result.add(field);
      }
      i = i.getSuperclass();
    }

    return result;
  }

  public static  void   executeRetOnSW(final Component container ,final String method,final String retMethod){  
      new SwingWorker(){            
            protected Object doInBackground() throws Exception {
            Object ob= invokeDynamicMethod(container,method,null);
                return ob;
            }       
            protected void done() {
                try {
                    Object [] ob=(Object []) get();
                    invokeDynamicMethod(container,retMethod,ob);
                } catch (Exception ex) {
                  ex.printStackTrace();  
                } 
                
            }
  
  }.execute();
      
      
  }
    
  public static  void   executeOnSW(final Component container ,final String method,final String retMethod){  
      
      
      new SwingWorker(){            
            protected Object [] doInBackground() throws Exception {
            Object ob= invokeDynamicMethod(container,method,null);
                return new Object[]{ob};
            }       
            protected void done() {
                try {
                  Object xx = (Object)get();
                    Object [] ob=(Object [])xx ;
                    invokeDynamicMethod(container,retMethod,ob);
                } catch (Exception ex) {
                  ex.printStackTrace();  
                } 
                
            }
  
  }.execute();
      
      
  }

  
  //EL Launguage usage
    public  static  JexlEngine creatELEngine() {
   
        if (ex == null) {
            ex = new JexlEngine();
        }    //        JexlEngine ex = new JexlEngine();
        ex.setCache(512);
        
        ex.setLenient(false);
        ex.setSilent(false);
        return ex;
    }
        static JexlEngine ex = new JexlEngine();

   public static Object getProperty(Object object,String property){
       return ex.getProperty(object, property);
       
   }

   public static void setProperty(Object object, String property, Object value) {
        try {
            ex.setProperty(object, property, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   public static <T> T findByID(List<T> list,Object idTofind){
        if(list==null || list.isEmpty() ) return null;
       for (T obj : list) {

           Object id=  getProperty(obj, "id");
           
           if((idTofind==null && id ==null) || (id!=null && id.equals(idTofind))) return obj;
       }
       return null;
   }
}
