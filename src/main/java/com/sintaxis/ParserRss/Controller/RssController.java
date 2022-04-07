package com.sintaxis.ParserRss.Controller;

import com.apptastic.rssreader.Item;
import com.apptastic.rssreader.RssReader;
import com.google.api.translate.Language;
import com.google.api.translate.Translate;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import com.sintaxis.ParserRss.Antlr.ExpLexer;
import com.sintaxis.ParserRss.Antlr.ExpParser;
import com.sintaxis.ParserRss.Antlr.Visitor;
import com.sintaxis.ParserRss.Model.Feed;
import com.sintaxis.ParserRss.Model.Rss;
import com.sintaxis.ParserRss.Model.Xml;
import com.sintaxis.ParserRss.Service.RSSFeedParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

 /**   @PostMapping("/prueba2")
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

    public boolean romeLibraryExample(InputStream address) {
        boolean ok = false;
        try{

            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(address));
            ok = true;
        } catch (Exception exc){
            exc.printStackTrace();
        }
        return ok;
    }

    public String obtenerExtension(String filename) {
        String fe = "";
        int i = filename.lastIndexOf('.');
        if (i > 0) {
            fe = filename.substring(i + 1);
        }
        return  fe;
    }
    /**  @GetMapping("/obtener")
    public void read (){
    Optional<Role> userRole= roleService.findByName(ERole.ROLE_USER);
        if(!userRole.isPresent()){
            Role roluser = new Role(ERole.ROLE_USER);
            roleService.save(roluser);
        }
        userRole= roleService.findByName(ERole.ROLE_MODERATOR);
        if(!userRole.isPresent()){
            Role rolModerator = new Role(ERole.ROLE_MODERATOR);
            roleService.save(rolModerator);
        }
        userRole= roleService.findByName(ERole.ROLE_ADMIN);
        if(!userRole.isPresent()){
            Role rolAdministrador = new Role(ERole.ROLE_ADMIN);
            roleService.save(rolAdministrador);
        }


        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance ().getExternalContext ().getResponse ();
        response.setContentType ("application/rss+xml");

        Reader r = new StringReader(content);
        SeamTextLexer lexer = new SeamTextLexer(r);
        SeamTextParser parser = new SeamTextParser(lexer);
        try {
            parser.startRule();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        StringBuilder formattedContentBuilder = new StringBuilder();
        formattedContentBuilder.append("<![CDATA[");
        formattedContentBuilder.append(parser.toString());
        formattedContentBuilder.append("]]>");
        return formattedContentBuilder.toString();

        RSSFeedParser parser = new RSSFeedParser(
                "https://www.vogella.com/article.rss");
        Feed feed = parser.readFeed();
        System.out.println(feed);
        for (FeedMessage message : feed.getMessages()) {
            System.out.println(message);
    }
    }**/


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
}
