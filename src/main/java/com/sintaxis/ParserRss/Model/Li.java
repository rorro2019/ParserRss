package com.sintaxis.ParserRss.Model;



import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Li {



    @XmlElement
    private A a;

    @XmlElement
    private String p;

    public Li( A a, String p) {

        this.a = a;
        this.p = p;
    }

    public Li() {
    }


    public void setA(A a) {
        this.a = a;
    }

    public void setP(String p) {
        this.p = p;
    }
}
