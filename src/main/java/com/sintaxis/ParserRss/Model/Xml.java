package com.sintaxis.ParserRss.Model;

import org.simpleframework.xml.*;

/**
 * Creado por Hermosa Programación
 *
 * Clase que representa al elemento <rss> del feed
 */

@Root(name = "xml", strict = false)
//@Namespace(reference="http://search.yahoo.com/mrss/")
public class Xml {


    @Attribute
    private String version;

    @Attribute(required = false,name = "encoding")
    private String encoding;

    public Xml() {
    }

    public Xml(String version, String encoding) {
        this.version = version;
        this.encoding = encoding;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }
}