/**
 * Created by IntelliJ IDEA.
 * User: Mike
 * Date: Apr 23, 2003
 * Time: 10:28:45 PM
 * To change this template use Options | File Templates.
 */
package persistence;

import org.dom4j.*;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.dom4j.io.OutputFormat;

import java.util.Properties;
import java.util.List;
import java.util.Iterator;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.net.URL;
import java.net.MalformedURLException;

import util.GeneralProperties;


public class XMLPersister {
    public static final String ROOT = "cloudData";
    public static final String DATA = "data";
    public static final String STDEV = "stdev";
    public static final String MOONPHASE = "moonphase";
    public static final String TWILIGHT = "twilight";
    public static final String EVAL = "eval";

    private Properties props;
    private Document doc;
    private File file;

    public XMLPersister() {
        props = new Properties();
        try {

            InputStream input = this.getClass().getResourceAsStream("/general.properties");
            props.load(input);
        } catch (IOException e) {
            System.out.println("properties file not found");
            e.printStackTrace();
        }

        String txtFileName = props.getProperty(GeneralProperties.DATA_FILE);
        file = new File(txtFileName);
        if(file.exists()) {
            doc = parseFile(file);
        } else {
            doc = createDocument();
            System.out.println("doc = " + doc);
        }
    }

    public Document parseFile(File file) {
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(file);
        } catch (DocumentException e) {
            System.out.println("can't read xml");
            e.printStackTrace();
        } catch (MalformedURLException e) {
            System.out.println("filename incorrect");
            e.printStackTrace();  //To change body of catch statement use Options | File Templates.
        }
        System.out.println("document is " + document);
        return document;
    }


    protected Document createDocument() {
        doc = DocumentHelper.createDocument();
        doc.addElement( ROOT );
        return doc;
    }

    public void addRecord(double stdev, double moonphase, double twilight, String eval) {
        System.out.println("doc = " + doc);
        Element root = doc.getRootElement();
        root.addElement( DATA )
                .addAttribute( STDEV, String.valueOf(stdev))
                .addAttribute( MOONPHASE, String.valueOf(moonphase))
                .addAttribute( TWILIGHT, String.valueOf(twilight))
                .addAttribute( EVAL, String.valueOf(eval));
    }

    public List getData() {
              List list = doc.selectNodes( "//data" );

        for (Iterator iter = list.iterator(); iter.hasNext(); ) {
            Attribute attribute = (Attribute) iter.next();
            String url = attribute.getValue();
        }
        return null;
    }

    /**
     *      Writing a document to a file
     */
    public void writeFile() {


        try {
            // Compact format : format = OutputFormat.createCompactFormat();
            OutputFormat format = OutputFormat.createPrettyPrint();
            // lets write to a file
            XMLWriter writer = new XMLWriter(
                new FileWriter( file )
            );
            writer.write( doc );
            writer.close();
        } catch (IOException e) {
            System.out.println("error writing file");
            e.printStackTrace();
        }
    }

}




