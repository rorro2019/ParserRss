package com.sintaxis.ParserRss.Model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
//import org.hibernate.validator.constraints.URL;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * Creado por Hermosa Programación.
 *
 * Clase que representa la etiqueta <channel> del feed
 */

@Root(name = "channel", strict = false)
public class Channel {

    @Element(name="title")
    private String title;

    @Element(name = "description")
    private String descripcion;

    @Element(name="link")
    private URL link;

    @ElementList(inline = true)
    private List<Item> items;

    public Channel() {
    }

    public Channel(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }


    public String getTitle() {
        return title;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public URL getLink() {
        return link;
    }

    public Channel(String title, String descripcion, URL link, List<Item> items) {
        this.title = title;
        this.descripcion = descripcion;
        this.link=link;
        this.items = items;
    }

    /**public void setLink(String link) {
        try {
            URL url = new URL(link);
            URLConnection conn = url.openConnection();
            conn.connect();
            this.link = link;
        } catch (MalformedURLException e) {
            // the URL is not in a valid form
        } catch (IOException e) {
            // the connection couldn't be established
        }

    }**/

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public void setItems(List<Item> items) {
        this.items = items;
    }


       /** public  boolean urlValidator(String url)
        {

            try {
                new URL(url).toURI();
                return true;
            }
            catch (URISyntaxException exception) {
                return false;
            }

            catch (MalformedURLException exception) {
                return false;
            }}**/

}