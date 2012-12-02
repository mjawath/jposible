/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.dao.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author nnjj
 */
public class Cache {
String key;
String specialKey;
int CurrentPage ;
Long count;
List list;
String query;

    Map<String,Cache> map;
    public Cache() {
          map = new HashMap();
    }



    public int getCurrentPage() {
        return CurrentPage;
    }

    public void setCurrentPage(int CurrentPage) {
        this.CurrentPage = CurrentPage;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public Map<String, Cache> getMap() {
        return map;
    }

    public void setMap(Map<String, Cache> map) {
        this.map = map;
    }

    public String getSpecialKey() {
        return specialKey;
    }

    public void setSpecialKey(String specialKey) {
        this.specialKey = specialKey;
    }
    
    public Cache getbyQueryName(String spclkey ){
         return getMap().get(spclkey);
    }
    
    public String getQuery(){
    return query;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public void setQuery(String query) {
        this.query = query;
    }
    
    
    public void createAndAddCache(String key,String query,int page, Long count,List ob){
      Cache ch = new Cache();
      ch.setCurrentPage(page);
      ch.count = count;
      ch.list = ob;
      ch.setQuery(query);
        map.put(key, ch);
    }
    
}
