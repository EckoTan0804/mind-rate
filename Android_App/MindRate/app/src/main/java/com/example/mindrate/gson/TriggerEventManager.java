package com.example.mindrate.gson;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Renhan on 2017/1/25.
 */

public class TriggerEventManager {
    private List<TriggerEvent> triggerEventList;

    public TriggerEventManager(){
        this.triggerEventList = new ArrayList<TriggerEvent>() ;
    }

    public List<TriggerEvent> getTriggerEventList(){
        return this.triggerEventList;
    }

    public void setTriggerEventList(List<TriggerEvent> triggerEventList) {
        this.triggerEventList = triggerEventList;
    }
    public void addTriggerEvent(TriggerEvent tE){
        this.triggerEventList.add(tE);
    }

    public void removeTriggerEvent(TriggerEvent tE){
        Iterator iter = this.triggerEventList.iterator();
        //TODO：modified
        while(iter.hasNext()){
            if(tE.equals(iter.next())){
                   this.triggerEventList.remove(tE);
            }
        }
    }






}
