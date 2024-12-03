package back;

import com.fastcgi.FCGIInterface;

import java.io.IOException;


public class Server {
    public static void main(String[] args) throws IOException {
        FCGIInterface fcgi = new FCGIInterface();
        RequestGetter requestGetter = new RequestGetter();
        while (fcgi.FCGIaccept() >= 0) {
            if ("POST".equals(FCGIInterface.request.params.getProperty("REQUEST_METHOD"))) {
                long start = System.currentTimeMillis();
                String req =  requestGetter.get();
                FieldExtractor extractor = new FieldExtractor(req.substring(req.indexOf("{\"x\""), req.indexOf("}") + 1));
                try {
                    System.out.printf("""
                           Content-Type: application/json; charset=utf-8


                           {"x": "%d", "y": "%s", "r": "%d", "hit": "%b", "executionTime": "%d", "error": "%s"}
                           """, extractor.getX(), extractor.getY(), extractor.getR(), extractor.isHit(), System.currentTimeMillis() - start, extractor.getErr());
                } catch (Exception e) {
                    System.out.printf("""
                           Content-Type: application/json; charset=utf-8


                           {"error": "%s"}
                           """, e);
                }
            }
        }
    }
}