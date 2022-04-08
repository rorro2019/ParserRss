package com.sintaxis.ParserRss.Controller;

import com.apptastic.rssreader.Item;
import com.apptastic.rssreader.RssReader;
import com.sintaxis.ParserRss.Model.Rss;
import com.sintaxis.ParserRss.Model.Xml;
import com.sintaxis.ParserRss.Service.RSSFeedParser;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.*;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.apache.juli.FileHandler.DEFAULT_BUFFER_SIZE;

@RestController
@RequestMapping("/api/rss")
@CrossOrigin("*")
public class RssController {



    @PostMapping("/parser")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) throws Exception {
        try {
             InputStream inputStream = new ByteArrayInputStream(file.getBytes());
             RssReader reader = new RssReader();
         /**   Stream<Item> rssFeed = reader.read(inputStream);
               List<Item> articles = rssFeed.filter(i -> i.getTitle().equals(Optional.of("RSS")))
                      .collect(Collectors.toList());
                 Boolean parrs=this.romeLibraryExample(inputStream);
             //String result = convertInputStreamToString(inputStream);
            inputStream = new ByteArrayInputStream(file.getBytes());**/
             Scanner s = new Scanner(inputStream).useDelimiter("\\A");
             String result = s.hasNext() ? s.next() : "";
             String extension= this.obtenerExtension(file.getOriginalFilename()).trim().toLowerCase();
             if ( !extension.equals("rss")){
                  return ResponseEntity.badRequest()
                        .body("Archivo incorrecto, Error en la extension");
             }

            /**verifico la primer linea si es valida**/
            Boolean xmlval= this.validarXml(result);
            //control para etiquetas desconocidas
            inputStream = new ByteArrayInputStream(file.getBytes());
            RSSFeedParser parser = new RSSFeedParser(inputStream);
            Boolean feed = parser.readFeed();

            Serializer serializer = new Persister();
            Rss rss = serializer.read(Rss.class, result);

            if (feed==true || xmlval==true){
                return ResponseEntity.ok("Archivo con errores ");
            }
            return ResponseEntity.ok("Archivo correcto ");
        } catch (Exception e) {
            String exep= e.getMessage() ;
          //  Translate.setHttpReferrer("http://code.google.com/p/google-api-translate-java/");
           // String resultad= Translate.translate(e.getLocalizedMessage(), Language.ENGLISH , Language.SPANISH);
            return ResponseEntity.badRequest()
                    .body("Archivo incorrecto, descripcion: "+ exep);
        }
    }

    @PostMapping("/parserString")
    public ResponseEntity<String> uploadString(@RequestBody String result) throws Exception {
        try {


            /**verifico la primer linea si es valida**/
            Boolean xmlval= this.validarXml(result);
            //control para etiquetas desconocidas
            RSSFeedParser parser = new RSSFeedParser();
            Boolean feed = parser.readFeedString(result);

            Serializer serializer = new Persister();
            Rss rss = serializer.read(Rss.class, result);

            if (feed==true || xmlval==true){
                return ResponseEntity.ok("Archivo con errores ");
            }
            return ResponseEntity.ok("Archivo correcto ");
        } catch (Exception e) {
            String exep= e.getMessage() ;
            return ResponseEntity.badRequest()
                    .body("Archivo incorrecto, descripcion: "+ exep);
        }
    }


    public String obtenerExtension(String filename) {
        String fe = "";
        int i = filename.lastIndexOf('.');
        if (i > 0) {
            fe = filename.substring(i + 1);
        }
        return  fe;
    }


    // funcion para validar la primera linea del archivo xml, devuelve true si es correcta, false si tiene algun error
    private  Boolean validarXml(String result) throws IOException {
        Boolean var=  false;
        try {
            int primera = result.lastIndexOf("<?xml");
            int futlima = result.lastIndexOf("?>");

            if(futlima==0){
                var = true;
                return var;
            }

            String primerLinea = result.substring(primera, futlima + 2);

            Serializer serializer = new Persister();
            String parsear = primerLinea.replace("?","")+"</xml>";
            Xml xml = serializer.read(Xml.class, parsear);

            return var;
        } catch (Exception e) {
            e.printStackTrace();
            var = true;
            return var;
        }


    }
    /**   @PostMapping("/prueba")
    public Object uploadd(@RequestParam("file") MultipartFile file) {
    try{
    //  ANTLRStringStream in = new ANTLRStringStream("12*(5-6)");
    InputStream inputStream = new ByteArrayInputStream(file.getBytes());
    // String result = convertInputStreamToString(inputStream);
    Scanner s = new Scanner(inputStream).useDelimiter("\\A");
    String result = s.hasNext() ? s.next() : "";
    CharStream in = CharStreams.fromString(result);
    ExpLexer lexer = new ExpLexer(in);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    ExpParser parser = new ExpParser(tokens);
    ANTLRErrorStrategy error = parser.getErrorHandler();
    //ExpParser.EvalContext tree=parser.eval();
    ParseTree tree = parser.eval(); // begin parsing at init rule
    // System.out.println(tree.toStringTree(parser);
    // System.out.println(parser.eval()); // print the value


    Visitor visitor = new Visitor();
    System.out.println(visitor.visit(tree));
    return  tree.toStringTree(parser);
    }
    catch (IOException e) {
    return "Archivo incorrecto";
    }

    }
     **/
}
