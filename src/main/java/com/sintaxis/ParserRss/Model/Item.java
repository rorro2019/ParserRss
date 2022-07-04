package com.sintaxis.ParserRss.Model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;


import java.net.URL;

/**
 * Clase que representa la etiqueta <item> del feed
 */

@Root(name = "item", strict = false)
public class Item {

    @Element(name="title")
    private String title;

    @Element(name = "description")
    private String descripcion;

    @Element(name="link")
    private URL link;

    @Element(name="content",required=false )
    @Namespace(reference="http://search.yahoo.com/mrss/", prefix="media")
    private Content content;



    public Item() {
    }

    public Item(String title, String descripcion, URL link, Content content) {
        this.title = title;
        this.descripcion = descripcion;
        this.link=link;
        this.content = content;
    }

    public Item(String title, String descripcion, URL link) {
        this.title = title;
        this.descripcion = descripcion;
        this.link=link;

    }



    public String getTitle() {
        return title;
    }

    public String getDescripcion() {
        return descripcion;
    }


    public Content getContent() {
        return content;
    }

    public URL getLink() {
        return link;
    }

    public void setLink(URL link) {
        this.link = link;
    }
}