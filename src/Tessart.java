import java.awt.*;
import java.io.*;

import net.sourceforge.tess4j.*;
import network.NetworkConnectionManager;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.PixelGrabber;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Created by Anushka on 2014-08-26.
 */
public class Tessart {
    public static void main(String ss[]) throws IOException {
        NetworkConnectionManager nt = new NetworkConnectionManager();
        Document mainPage = nt.URLgraber("http://wso2.com/about/leadership/sanjiva_weerawarana/");
        Elements img = mainPage.select(".basic .employee li.content img");
        for (Element el : img) {
            String src = el.attr("src");
            System.out.println("Image Found!");
            System.out.println("src attribute is : " + src);
            getImages("http://wso2.com"+src);
        }
//        Image image = new ImageIcon("images/sanjiva_email.gif").getImage();

//        if (image instanceof BufferedImage) {
        BufferedImage colorImage = ImageIO.read(new File("images/sanjiva_email.gif"));
        BufferedImage image1 = new BufferedImage(colorImage.getWidth(), colorImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        Graphics g = image1.getGraphics();
        g.drawImage(colorImage, 0, 0, null);
        File outputfile = new File("saved.png");
        ImageIO.write(image1, "png", outputfile);
//            g.dispose();
//        System.out.println(image1.getColorModel().hasAlpha());
//        }

//        PixelGrabber pg = new PixelGrabber(image, 0, 0, 1, 1, false);
//        try {
//            pg.grabPixels();
//        } catch (InterruptedException e) {
//        }
//
//        ColorModel cm = pg.getColorModel();
//        System.out.println(cm.hasAlpha());
        File imageFile = new File("saved.png");
        Tesseract instance = Tesseract.getInstance();  // JNA Interface Mapping

        try {
            String result = instance.doOCR(imageFile);
            System.out.println(result);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void getImages(String src) throws IOException {

        //Exctract the name of the image from the src attribute
        int indexname = src.lastIndexOf("/");

        if (indexname == src.length()) {
            src = src.substring(1, indexname);
        }

        indexname = src.lastIndexOf("/");
        String name = src.substring(indexname, src.length());

        System.out.println(src);

        //Open a URL Stream
        URL url = new URL(src);
        InputStream in = url.openStream();

        OutputStream out = new BufferedOutputStream(new FileOutputStream("images"+name));

        for (int b; (b = in.read()) != -1; ) {
            out.write(b);
        }
        out.close();
        in.close();

    }
}
