package sg.games.fighter.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLHelper {

    String readQNAME = "";
    boolean readadd = false;
    ConfigPair readtempPair;
    
    public static ArrayList<ConfigPair> readp1;
    public static ArrayList<ConfigPair> readp2;
    
    int readcounter = 0;

    public void write(ConfigPair[] data1, ConfigPair[] data2)
            throws XMLStreamException, IOException {
        XMLOutputFactory xof = XMLOutputFactory.newInstance();
        XMLStreamWriter xtw = null;
        xtw = xof.createXMLStreamWriter(new FileWriter(System.getProperty("user.dir") + "/Control.xml"));

        xtw.writeStartElement("Control");
        xtw.writeCharacters("\n");
        xtw.writeCharacters("\t");
        xtw.writeStartElement("Player1");
        xtw.writeCharacters("\n");

        for (int i = 0; i < data1.length; i++) {
            xtw.writeCharacters("\t");
            xtw.writeCharacters("\t");
            xtw.writeStartElement(data1[i].getFirst());
            xtw.writeCharacters(Integer.toString(data1[i].getSecond()));
            xtw.writeEndElement();
            xtw.writeCharacters("\n");
        }

        xtw.writeCharacters("\t");
        xtw.writeEndElement();
        xtw.writeCharacters("\n");
        xtw.writeCharacters("\t");
        xtw.writeStartElement("Player2");
        xtw.writeCharacters("\n");
        for (int i = 0; i < data2.length; i++) {
            xtw.writeCharacters("\t");
            xtw.writeCharacters("\t");
            xtw.writeStartElement(data2[i].getFirst());
            xtw.writeCharacters(Integer.toString(data2[i].getSecond()));
            xtw.writeEndElement();
            xtw.writeCharacters("\n");
        }

        xtw.writeCharacters("\t");
        xtw.writeEndElement();
        xtw.writeCharacters("\n");
        xtw.writeEndElement();
        xtw.flush();
        xtw.close();
    }

    public ConfigPair[] writedefault1() {
        ConfigPair[] data1 = new ConfigPair[10];
        data1[0] = new ConfigPair("Left", 203);
        data1[1] = new ConfigPair("Right", 205);
        data1[2] = new ConfigPair("Up", 200);
        data1[3] = new ConfigPair("Down", 208);
        data1[4] = new ConfigPair("Left-Punch", 9);
        data1[5] = new ConfigPair("Right-Punch", 10);
        data1[6] = new ConfigPair("Left-Kick", 23);
        data1[7] = new ConfigPair("Right-Kick", 24);
        data1[8] = new ConfigPair("Side-step", 8);
        data1[9] = new ConfigPair("Defence", 22);
        return data1;
    }

    public ConfigPair[] writedefault2() {
        ConfigPair[] data2 = new ConfigPair[10];
        data2[0] = new ConfigPair("Left", 30);
        data2[1] = new ConfigPair("Right", 32);
        data2[2] = new ConfigPair("Up", 17);
        data2[3] = new ConfigPair("Down", 31);
        data2[4] = new ConfigPair("Left-Punch", 34);
        data2[5] = new ConfigPair("Right-Punch", 35);
        data2[6] = new ConfigPair("Left-Kick", 48);
        data2[7] = new ConfigPair("Right-Kick", 49);
        data2[8] = new ConfigPair("Side-step", 33);
        data2[9] = new ConfigPair("Defence", 47);
        return data2;
    }

    public void read() {
        this.readcounter = 0;

        SAXParserFactory spf = SAXParserFactory.newInstance();
        try {
            SAXParser sp = spf.newSAXParser();
            DefaultHandler handler = new DefaultHandler() {
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    if (qName.equalsIgnoreCase("Player1")) {
                        XMLHelper.readp1 = new ArrayList(10);
                        XMLHelper.this.readadd = false;
                    } else if (qName.equalsIgnoreCase("Player2")) {
                        XMLHelper.readp2 = new ArrayList(10);
                        XMLHelper.this.readadd = false;
                    } else if (qName.equalsIgnoreCase("Control")) {
                        XMLHelper.this.readadd = false;
                    } else {
                        XMLHelper.this.readadd = true;
                        XMLHelper.this.readQNAME = qName;
                    }
                }

                public void characters(char[] ch, int start, int length) throws SAXException {
                    String abc = new String(ch, start, length).trim();
                    if ((abc.length() > 0)
                            && (XMLHelper.this.readadd)) {
                        int a = Integer.parseInt(abc);
                        XMLHelper.this.readtempPair = new ConfigPair(XMLHelper.this.readQNAME, a);
                        if (XMLHelper.this.readcounter < 10) {
                            XMLHelper.readp1.add(XMLHelper.this.readtempPair);
                            XMLHelper.this.readcounter += 1;
                        } else {
                            XMLHelper.readp2.add(XMLHelper.this.readtempPair);
                            XMLHelper.this.readcounter += 1;
                        }
                    }
                }
            };
            sp.parse(System.getProperty("user.dir") + "/Control.xml", handler);
        } catch (SAXException se) {
            se.printStackTrace();
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (IOException ie) {
            ie.printStackTrace();
            try {
                write(writedefault1(), writedefault2());
                read();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    public void readprint() {
        for (int i = 0; i < readp1.size(); i++) {
            System.err.println(((ConfigPair) readp1.get(i)).getFirst() + " " + ((ConfigPair) readp1.get(i)).getSecond());
        }
        for (int i = 0; i < readp2.size(); i++) {
            System.err.println(((ConfigPair) readp2.get(i)).getFirst() + " " + ((ConfigPair) readp2.get(i)).getSecond());
        }
    }


}