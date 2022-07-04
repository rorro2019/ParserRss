package com.sintaxis.ParserRss.Model;


import javax.xml.bind.annotation.XmlElement;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


@XmlRootElement
public class Body {


    @XmlElement
    private String h1;

    @XmlElement
    private String h3;


    @XmlElement
    private List<Li> ul;

    public Body() {
    }



    public void setH1(String h1) {
        this.h1 = h1;
    }



    public void setH3(String h3) {
        this.h3 = h3;
    }



    public void setUl(List<Li> ul) {
        this.ul = ul;
    }
}
