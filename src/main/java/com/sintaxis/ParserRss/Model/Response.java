package com.sintaxis.ParserRss.Model;

public class Response {

    private String respuesta;

    private String html;

    public Response() {
    }

    public Response(String respuesta, String html) {
        this.respuesta = respuesta;
        this.html = html;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }
}
