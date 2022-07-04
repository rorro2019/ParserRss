package com.sintaxis.ParserRss.Model;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class A {

    @XmlAttribute
    private String href;


    @XmlElement
    private String h4;

    public A(String href, String h4) {
        this.href = href;
        this.h4 = h4;
    }

    public A() {
    }

    public void setHref(String href) {
        this.href = href;
    }


    public void setH4(String h4) {
        this.h4 = h4;
    }
}
