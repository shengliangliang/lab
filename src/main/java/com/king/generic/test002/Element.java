package com.king.generic.test002;



public class Element implements Comparable<Element>{

    private Integer id;

    private Long enQueueTime;

    private Integer priority;


    public Element(Integer id,Integer priority){
        this.id = id;
        this.priority = priority;
    }

    private Element(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getEnQueueTime() {
        return enQueueTime;
    }

    public void setEnQueueTime(Long enQueueTime) {
        this.enQueueTime = enQueueTime;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @Override
    public int compareTo(Element element) {
        return (this.priority - element.getPriority())>=0?1:-1;
    }

    @Override
    public String toString(){
        return "id:"+this.getId()+",time:"+this.getEnQueueTime()+",priority:"+this.getPriority();
    }
}
