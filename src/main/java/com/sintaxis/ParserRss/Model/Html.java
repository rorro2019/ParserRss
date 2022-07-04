package com.sintaxis.ParserRss.Model;



import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Html {


    @XmlElement
    private Head head;


    @XmlElement
    private Body body;

    public Html() {
    }



    public void setHead(Head head) {
        this.head = head;
    }


    public void setBody(Body body) {
        this.body = body;
    }
}
