package com.sintaxis.ParserRss.Model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root(name = "rss", strict = false)
@Namespace(reference="http://search.yahoo.com/mrss/")
public class Rss {

    @Attribute
    private String version;

    @Element
    private Channel channel;

    public Rss() {
    }

    public Rss(Channel channel) {
        this.channel = channel;
    }

    public Channel getChannel() {
        return channel;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}