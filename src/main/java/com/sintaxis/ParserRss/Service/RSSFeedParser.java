package com.sintaxis.ParserRss.Service;

import com.sintaxis.ParserRss.Model.Feed;
import com.sintaxis.ParserRss.Model.FeedMessage;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;


public class RSSFeedParser {

    static final String TITLE = "title";
    static final String DESCRIPTION = "description";
    static final String CHANNEL = "channel";
    static final String LANGUAGE = "language";
    static final String COPYRIGHT = "copyright";
    static final String LINK = "link";
    static final String AUTHOR = "author";
    static final String ITEM = "item";
    static final String PUB_DATE = "pubDate";
    static final String GUID = "guid";
    static final String RSS = "rss";
    static final String SALTO = "/n";


    final InputStream file;

    public RSSFeedParser(InputStream file) {
        this.file = file;

    }

    public Boolean readFeed() {
        Feed feed = null;
        Boolean error =false;
        try {
            boolean isFeedHeader = true;
            // Establecer valores de encabezado iniciales a la cadena vacía
            String description = "";
            String title = "";
            String link = "";
            String language = "";
            String copyright = "";
            String author = "";
            String pubdate = "";
            String guid = "";
            String chanel="";
            String rss="";

            // Primero cree una nueva XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            //Configurar un nuevo eventReader
            InputStream in = this.file;
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            // lee el documento XML
            int count=0;
            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                String evento= event.toString();
                Integer tipoevento=event.getEventType();
                boolean atributo= event.isAttribute();
                //StartElement startElement = event.asStartElement();
                if (tipoevento !=4 && count==0){
                     count= count + 1;
                     if (tipoevento !=7  ){
                         error = true;
                         return error;
                     }
                }
                if (event.isStartElement()) {
                    String localPart = event.asStartElement().getName()
                            .getLocalPart();
                    switch (localPart) {
                        case ITEM:
                            if (isFeedHeader) {
                                isFeedHeader = false;
                                feed = new Feed(title, link, description, language,
                                        copyright, pubdate);
                            }
                            event = eventReader.nextEvent();
                            break;
                        case CHANNEL:
                            chanel = getCharacterData(event, eventReader);
                            break;
                        case TITLE:
                            title = getCharacterData(event, eventReader);
                            break;
                        case DESCRIPTION:
                            description = getCharacterData(event, eventReader);
                            break;
                        case LINK:
                            link = getCharacterData(event, eventReader);
                            break;
                        case GUID:
                            guid = getCharacterData(event, eventReader);
                            break;
                        case LANGUAGE:
                            language = getCharacterData(event, eventReader);
                            break;
                        case AUTHOR:
                            author = getCharacterData(event, eventReader);
                            break;
                        case PUB_DATE:
                            pubdate = getCharacterData(event, eventReader);
                            break;
                        case COPYRIGHT:
                            copyright = getCharacterData(event, eventReader);
                            break;
                        case RSS:

                            break;
                        case SALTO:

                            break;
                        default :
                            error = true;
                            return error;
                    }
                } else if (event.isEndElement()) {
                    if (event.asEndElement().getName().getLocalPart() == (ITEM)) {
                        FeedMessage message = new FeedMessage();
                        message.setAuthor(author);
                        message.setDescription(description);
                        message.setGuid(guid);
                        message.setLink(link);
                        message.setTitle(title);
                        feed.getMessages().add(message);
                        event = eventReader.nextEvent();
                        continue;
                    }
                }
            }
        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }
        return error;
    }

    private String getCharacterData(XMLEvent event, XMLEventReader eventReader)
            throws XMLStreamException {
        String result = "";
        event = eventReader.nextEvent();
        if (event instanceof Characters) {
            result = event.asCharacters().getData();
        }
        return result;
    }


}