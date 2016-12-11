/* Problen Statement : Need to create 1000 xml files from SAP-server dump. XML files are used to perform performance testing on new server. 
CSV data is converteed to XML files which is inturn fed to jMeter to perform load and performance testing. 



*/

package com.csv_xml.dynamic;

import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

class CountFrequencies 
{
   
    public static void main(String[] args) 
    {
    	try {
    		String filepath = "c:\\tmp\\es_main.xml";
        
    		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
    		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
    		Document doc = docBuilder.parse(filepath);
        
    		Scanner sc = new Scanner(new File("C:\\tmp\\serial.txt"));
    		ArrayList<String> lines = new ArrayList<String>();
    		while (sc.hasNextLine()) {
    		  lines.add(sc.nextLine());
    		}
    		String[] serial = lines.toArray(new String[0]);
        
    		Scanner sc1 = new Scanner(new File("C:\\tmp\\product.txt"));
    		ArrayList<String> lines1 = new ArrayList<String>();
    		while (sc1.hasNextLine()) {
    		  lines1.add(sc1.nextLine());
    		}        
    		String[] product = lines1.toArray(new String[0]);
    		
        Node company = doc.getFirstChild();
        Node main = doc.getElementsByTagName("EntitlementBySnRequest").item(0);
        NodeList list = main.getChildNodes();
       
             for (int i = 0; i < product.length; i++) {

              Node SerialNumber = list.item(45);
              SerialNumber.setTextContent(serial[i]);
              Node Product = list.item(47);
              Product.setTextContent(product[i]);
              TransformerFactory transformerFactory = TransformerFactory.newInstance();
              Transformer transformer = transformerFactory.newTransformer();
              DOMSource source = new DOMSource(doc);
              StreamResult result = new StreamResult(new File("C:\\tmp\\output\\"+i+".xml"));
              transformer.transform(source, result);

            }
            System.out.println("Done");

    	  }
        catch (ParserConfigurationException PaException) {
    		    PaException.printStackTrace();
    	   } 
         catch (TransformerException TsException) {
    		    TsException.printStackTrace();
    	   }
         catch (IOException IOException) {
    		    IOExceptione.printStackTrace();
    	   }
         catch (SAXException SAException) {
    	    	SAException.printStackTrace();
    	   }
    	}
    }
