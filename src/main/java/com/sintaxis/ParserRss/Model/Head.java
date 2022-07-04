package com.sintaxis.ParserRss.Model;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Head {

    @XmlElement
    private String title;

    public Head(String title) {
        this.title = title;
    }
    public Head() {

    }

    public void setTitle(String title) {
        this.title = title;
    }
}
