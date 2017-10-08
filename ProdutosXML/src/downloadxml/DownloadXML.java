/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadxml;

import br.com.BO.Produto;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author Icaro
 */
public class DownloadXML {

    private static File file = new File("C:\\Users\\Icaro\\Downloads\\ola.xml");
    static String destino = "C:\\Users\\Icaro\\Downloads\\ola.xml";

    public static void main(String[] args) throws FileNotFoundException, MalformedURLException, IOException, Exception {
    //As linhas abaixo convertem para um arquivo xml;
        /*  URL url = new URL("http://www.wddsport.com.br/xml/mnw.php");


        InputStream is = url.openStream();

        FileOutputStream fos = new FileOutputStream(destino);

        int bytes = 0;

        while ((bytes = is.read()) != -1) {
            fos.write(bytes);
        }

        is.close();

        fos.close();*/
       // List<Produto> tel = lerXml();
        //for (int i = 0; i < tel.size(); i++) {
          //  System.out.println("Nome Prod:"+ tel.get(i).getNome()+"   Codigo: " + tel.get(i).getCodBarras());

        //}
    }

    public static String getChildTagValue(Element elem, String tagName) throws Exception {
        NodeList children = elem.getElementsByTagName(tagName);
        String result = null;
        //children, a tag pai que estamos lendo,
        // por exemplo a tag 
        if (children == null) {
            return result;
        }
        //child, a tag que queremos recuperar o valor, por exemplo
        //a tag 
        Element child = (Element) children.item(0);

        if (child == null) {
            return result;
        }
        //recuperamos o texto contido na tagName   
        result = child.getTextContent();

        return result;
    }

    private static List<Produto> lerXml() throws Exception, SAXException, TransformerException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Collection<Produto> produtos = new ArrayList<>();
        //Informamos qual arquivo xml vamos ler
        Document doc = db.parse(new InputSource(file.toString()));
        //Criamos um objeto Element que vai receber as informacoes de doc
        Element raiz = doc.getDocumentElement();

        //Vamos criar um objeto Produto
        //Informamos qual tag vamos ler
        NodeList endList = raiz.getElementsByTagName("produtos");
        Element endElement;
        for (int i = 0; i < endList.getLength(); i++) {

            endElement = (Element) endList.item(i);
            Produto produto = new Produto();
            //Passamos para o metodo getChildTagValue a tag Pai que
            //iremos ler mais qual tag Filha ser? capturada
            produto.setCodBarras(getChildTagValue(endElement, "cod_bar"));
            produto.setNome(getChildTagValue(endElement, "prodname"));
            produto.setValor(getChildTagValue(endElement, "valor"));
            produto.setEstoque(Integer.parseInt(getChildTagValue(endElement, "stoque")));
            produto.setPesoGramas(Double.parseDouble(getChildTagValue(endElement, "pesoemgramas")));
            produto.setLargura(Double.parseDouble(getChildTagValue(endElement, "largura")));
            produto.setAltura(Double.parseDouble(getChildTagValue(endElement, "altura")));
            produto.setProfundidade(Double.parseDouble(getChildTagValue(endElement, "profundidade")));
            produtos.add(produto);
        }
        return (List<Produto>) produtos;
    }
}
