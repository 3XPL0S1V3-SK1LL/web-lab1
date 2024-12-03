package back;

import com.fastcgi.FCGIInterface;

import java.nio.charset.StandardCharsets;

public class RequestGetter {
    public String get() {
        return new String(FCGIInterface.request.inStream.buff, StandardCharsets.UTF_8);
    }
}
