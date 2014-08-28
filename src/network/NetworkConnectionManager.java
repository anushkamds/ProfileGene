package network;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

/**
 * Created by Anushka on 2014-08-22.
 */
public class NetworkConnectionManager {

    public String DomainName(String urlString) throws MalformedURLException {
        URL url = new URL(urlString);
        String domain = url.getHost();
        String protocol = url.getProtocol();
        String domainName = domain.startsWith("www.") ? domain.substring(4) : domain;
        return protocol + "://" + domainName; //get the connecting site domain name
    }

    public Document URLgraber(String urlString) throws IOException {
        URL url = new URL(urlString);
        Document doc = null;
        System.out.println("connecting: " + urlString);
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(
                "cache.mrt.ac.lk", 3128));

//        HttpURLConnection uc = (HttpURLConnection) url.openConnection(proxy);
        HttpURLConnection uc = (HttpURLConnection) url.openConnection(); /*With out proxy */

        try {
            uc.connect();
            String line = null;
            StringBuffer tmp = new StringBuffer();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    uc.getInputStream()));
            while ((line = in.readLine()) != null) {
                tmp.append(line);
            }
            doc = Jsoup.parse(String.valueOf(tmp));
            return doc;
        } catch (Exception ex) {
            System.out.println(ex.getStackTrace());
        }
        return null;
    }
}
