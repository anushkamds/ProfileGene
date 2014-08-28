import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import network.NetworkConnectionManager;

/**
 * Created by Anushka on 2014-08-21.
 */
public class WSO2ProfileCreator {
    private static String DOMAINNAME = null;
    private static NetworkConnectionManager nt=new NetworkConnectionManager();

    public static void main(String arg[]) throws IOException {
        String urlString = "http://wso2.com/about/team/";
        DOMAINNAME = nt.DomainName(urlString);
        Document mainPage = nt.URLgraber(urlString);
        teamPageReader(mainPage);

    }

    private static void teamPageReader(Document document) throws IOException {
        System.out.println("analyzing document.....");
        Elements exa = document.select(".team-container #tag-filter .main li a");
        for (Element link : exa) {
            String url = link.attr("href");
            membersFinding(nt.URLgraber(DOMAINNAME + url));
        }
    }

    private static void membersFinding(Document membersPage) throws IOException {
        Elements link = membersPage.select(".team-container #rightArea li .infoBubble div");
        System.out.println(link.select("h4").text());
        System.out.println(link.select("h5 em").text());
    }
}


