/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt1;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;

public class rysPanel extends JPanel implements Cloneable{
    private int numerFunkcji=-1;
    private Color kolorWypelnienia=Color.BLACK;
    private Color kolorSciezki=Color.BLACK;
    private Color kolorTla=Color.WHITE;
    private int rozmiarSciezki=1;
   
    private Shape przejsc;
    private String plik=null;//taka sciezka do obrazka;
    private Tla bitmapa=null;
    private Tla tlo=null;
    private Tekst tekst;
    private Point drawStart, drawEnd;
    private ModelDanych listaDanych=new ModelDanych();
    private Wspol wspolrz;
    private DefaultListModel<Integer> lista;
    private Integer nrObjEdycji=null;
    private Integer nrFunctionEdycji=null;
    private int fonty = Font.PLAIN;
    private String slowa="to są słowa";

    public Integer getNrFunctionEdycji() {
        return nrFunctionEdycji;
    }
    public void setNrFunctionEdycji(Integer nrFunctionEdycji) {
        this.nrFunctionEdycji = nrFunctionEdycji;
    }
    public Integer getNrObjEdycji() {
        return nrObjEdycji;
    }
    public void setNrObjEdycji(Integer nrObjEdycji) {
        this.nrObjEdycji = nrObjEdycji;
    }
    public String getPlik(){
        return plik;
    }
    public void setPlik(String plik){
        this.plik=plik;
    }
    public void setTlo(Tla obrazek){
        this.tlo=obrazek;
    }
    public void setBitmapa(Tla obrazek){
        this.bitmapa=obrazek;
    }

    public void setKolorWypelnienia(Color kolorWypelnienia) {
        this.kolorWypelnienia = kolorWypelnienia;
    }

    public void setKolorSciezki(Color kolorSciezki) {
        this.kolorSciezki = kolorSciezki;
    }

    public Color getKolorWypelnienia() {
        return kolorWypelnienia;
    }

    public Color getKolorSciezki() {
        return kolorSciezki;
    }

    public void setKolorTla(Color kolorTla) {
        this.kolorTla = kolorTla;
    }

    public ModelDanych getListaDanych() {
        return listaDanych;
    }

    public void setListaDanych(ModelDanych listaDanych) {
        this.listaDanych = listaDanych;
    }

    public int getRozmiarSciezki() {
        return rozmiarSciezki;
    }

    public void setRozmiarSciezki(int rozmiarSciezki) {
        this.rozmiarSciezki = rozmiarSciezki;
    }

    public int getFonty() {
        return fonty;
    }

    public void setFonty(int fonty) {
        this.fonty = fonty;
    }
    
    public String getSlowa() {
        return slowa;
    }

    public void setSlowa(String slowa) {
        this.slowa = slowa;
    }
    
    
    public rysPanel(Wspol wspolrz, DefaultListModel<Integer> lista){
        this.wspolrz=wspolrz;
        this.lista=lista;
        setPreferredSize(new Dimension(600,500));
        addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent ex) {
                //przy kolorze wypelnienia specjalnie robię kolorSciezki - poniewaz Punkt nie powinien mieć innego koloru wewnątrz niż na zewnątrz;
                if(numerFunkcji==0){
                    listaDanych.add(new Ellipse2D.Float(ex.getX()-(rozmiarSciezki), ex.getY()-(rozmiarSciezki), 2*rozmiarSciezki,2*rozmiarSciezki), kolorSciezki, kolorSciezki, rozmiarSciezki/2);
                    lista.addElement(listaDanych.getSize()-1);
                }
                if(numerFunkcji==13){
                    tekst=new Tekst(slowa);
                    tekst.setX(ex.getX());
                    tekst.setY(ex.getY());
                    tekst.setFontyTekst(fonty);
                    listaDanych.add(tekst, kolorSciezki, rozmiarSciezki);
                    lista.addElement(listaDanych.getSize()-1);
                }
                if(nrObjEdycji!=null && nrFunctionEdycji !=null){
                    if(nrFunctionEdycji==3){
                       // listaDanych.getShape(nrObjEdycji).
                       //zeby zrobić te funkcję to trzeba pododawać w każdym shapie poczatek rysowania i koniec rysowania 
                    }
                }
                repaint();
            }
            @Override
            public void mousePressed(MouseEvent e) {
                drawStart=new Point(e.getX(),e.getY());
                drawEnd=drawStart;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(numerFunkcji>=1 && numerFunkcji<=9){
                    if(numerFunkcji>=6 &&numerFunkcji<=9){
                        listaDanych.add(przejsc, kolorWypelnienia, kolorSciezki, rozmiarSciezki);
                    }
                    else{
                        listaDanych.add(przejsc, null, kolorSciezki, rozmiarSciezki);
                    }
                    lista.addElement(listaDanych.getSize()-1);
                }   
                if(numerFunkcji==12){
//                    Tla bitmap=new Tla(bitmapa, FunkcjeBitmapy.bitmapa, drawStart, drawEnd);
                    bitmapa.setStart(drawStart);
                    bitmapa.setEnd(drawEnd);
                    listaDanych.add(bitmapa);
                    Image img=Toolkit.getDefaultToolkit().getImage("images2"+File.separatorChar+plik);
                    Tla nowy=new Tla(img,FunkcjeBitmapy.bitmapa);
                    bitmapa=nowy;
                    lista.addElement(listaDanych.getSize()-1);
                }
                
                drawStart=null;
                drawEnd=null;
                repaint();
            }
//
//            @Override
//            public void mouseEntered(MouseEvent e) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//            
        });
        this.addMouseMotionListener(new MouseMotionAdapter(){
            @Override
            public void mouseDragged(MouseEvent e) {
                drawEnd=new Point(e.getX(),e.getY());
                repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                String xy;
                String yy;
                int x=e.getX();
                int y=e.getY();
               
                if(x<1000){
                    if(x<100){
                        if(x<10){
                            xy="000"+x;
                        }else{
                            xy="00"+x;
                        }
                    }else{
                        xy="0"+x;
                    }
                }else{
                    xy=""+x;
                }
                if(y<1000){
                    if(y<100){
                        if(y<10){
                            yy="000"+y;
                        }else{
                            yy="00"+y;
                        }
                    }else{
                        yy="0"+y;
                    }
                }else{
                    yy=""+y;
                }
                   
                wspolrz.setText("     X: "+xy+" Y: "+yy);                
                //System.out.println(e.getX()+"  "+e.getY());
            }
            
        });
    }
    @Override
    public void paintComponent(Graphics gd){
        Graphics2D gd2=(Graphics2D)gd;
        gd2.setColor(kolorTla);
        gd2.fillRect(0, 0, getWidth(), getHeight());
        
        if(tlo!=null){
            listaDanych.addTla(tlo);            
            tlo=null;
        }
        for(int i=0; i<listaDanych.getTlaSize();i++){
            if(listaDanych.getTla(i).getFunkcje().equals(FunkcjeBitmapy.tloRozciagnie)){
               Image img=listaDanych.getTla(i).getImg();
               gd2.drawImage(img,0, 0, getWidth(), getHeight(), this);
               gd2.finalize();
            }
            else if(listaDanych.getTla(i).getFunkcje().equals(FunkcjeBitmapy.tloKafelkowe)){
               Image img=listaDanych.getTla(i).getImg();
                for(int j=0; j<getWidth()/img.getWidth(this)+1;j++){
                    for(int k=0; k<getHeight()/img.getHeight(this)+1;k++){
                        gd2.drawImage(img,j*img.getWidth(this),k*img.getHeight(this),this);
                    }
                }
                gd2.finalize();
            }
        } 
        if(nrObjEdycji!=null && nrFunctionEdycji !=null){
            //problem z tą funkcją - samo lista.removeElementAt() - nie działa - nie wiem wię c
            if(nrFunctionEdycji==1){ 
                if(listaDanych.getSize()>0){
                    listaDanych.remove(nrObjEdycji);
                    nrFunctionEdycji=null;
                    nrObjEdycji=null;
                }
            }
            if(nrFunctionEdycji==4){
                if(listaDanych.getSize()>0){
                    Shape p=listaDanych.getShape(nrObjEdycji);
                    if((p instanceof Tla)==false){
                        listaDanych.setShapeStrokeSize(nrObjEdycji, rozmiarSciezki);
                    }
                }
            }
            if(nrFunctionEdycji==5){
                if(listaDanych.getSize()>0){
                    Shape p=listaDanych.getShape(nrObjEdycji);
                    if((p instanceof Tla)==false){
                        listaDanych.setShapeStrokeColor(nrObjEdycji, kolorSciezki);
                    }
                }
            }
            if(nrFunctionEdycji==7){
                if(listaDanych.getSize()>0){
                    Shape p=listaDanych.getShape(nrObjEdycji);
                    if((p instanceof Tla)==false ){
                        listaDanych.setShapeColor(nrObjEdycji, kolorWypelnienia);
                    }
                }
            }
        }
        for(int i=0; i<listaDanych.getSize(); i++){
            if(listaDanych.getShape(i) instanceof Tekst){
                Tekst tekscior=(Tekst)(listaDanych.getShape(i));
                gd2.setPaint(listaDanych.getShapeStrokeColor(i));
                Font font=new Font("Serif", ((Tekst)(listaDanych.getShape(i))).getFontyTekst() ,listaDanych.getShapeStrokeSize(i)*10);
                gd2.setFont(font);
                gd2.drawString(tekscior.getTekst(),(int)tekscior.getX(), (int)tekscior.getY());//(int)tekscior.getStart().getX(), (int)tekscior.getStart().getY());
            }
            if(listaDanych.getShape(i) instanceof Tla){
               Tla m=(Tla)(listaDanych.getShape(i));
               gd2.drawImage(m.getImg(), (int) ((Tla)(listaDanych.getShape(i))).getStart().getX(), (int) ((Tla)(listaDanych.getShape(i))).getStart().getY(),
                       (int)(((Tla)(listaDanych.getShape(i))).getEnd().getX()-(int)((Tla)(listaDanych.getShape(i))).getStart().getX()),
                       (int)(((Tla)(listaDanych.getShape(i))).getEnd().getY()-(int)((Tla)(listaDanych.getShape(i))).getStart().getY()), this);
               gd2.finalize();
            }  
            else{
                gd2.setStroke(new BasicStroke(listaDanych.getShapeStrokeSize(i)));
                if(listaDanych.getShapeStrokeColor(i)!=null){
                    gd2.setPaint(listaDanych.getShapeStrokeColor(i));
                }
                gd2.draw(listaDanych.getShape(i));
                
                if(listaDanych.getShapeColor(i)!=null){
                    gd2.setPaint(listaDanych.getShapeColor(i));
                  gd2.fill(listaDanych.getShape(i));
                }
            }
        }
        if(drawStart!=null && drawEnd!=null){
            if(numerFunkcji==1){
                gd2.setPaint(kolorSciezki);
                gd2.drawLine((int)drawStart.getX(), (int) drawStart.getY(),(int)drawEnd.getX(), (int)drawEnd.getY());
                int [] tabX={
                    (int)drawStart.getX(),
                    (int)drawEnd.getX()
                };
                int [] tabY={
                    (int) drawStart.getY(),
                    (int)drawEnd.getY()
                };
                Polygon linia=new Polygon(tabX,tabY,2);
                przejsc=linia;
            }
            if(numerFunkcji==2){
                gd2.setPaint(kolorSciezki);
                int []tabX={
                    (int)drawStart.getX(),
                    (int)(drawStart.getX()+(drawEnd.getX()-drawStart.getX())/4),//(((drawStart.getY()+drawEnd.getY())/2)*(Math.sqrt(3)))/3),
                    (int)(drawEnd.getX()-(drawEnd.getX()-drawStart.getX())/4),//-(((drawStart.getY()+drawEnd.getY())/2)*(Math.sqrt(3)))/3),
                    (int)drawEnd.getX(),
                    (int)(drawEnd.getX()-(drawEnd.getX()-drawStart.getX())/4),//-(((drawStart.getY()+drawEnd.getY())/2)*(Math.sqrt(3)))/3),
                    (int)(drawStart.getX()+(drawEnd.getX()-drawStart.getX())/4),//(((drawStart.getY()+drawEnd.getY())/2)*(Math.sqrt(3)))/3),
                };
                int[]tabY={
                    (int)(drawStart.getY()+drawEnd.getY())/2,
                    (int)drawStart.getY(),
                    (int)drawStart.getY(),
                    (int)(drawStart.getY()+drawEnd.getY())/2,
                    (int)drawEnd.getY(),
                    (int)drawEnd.getY()
                };
                Polygon polygon=new Polygon(tabX, tabY, 6);
                gd2.drawPolygon(polygon);
                przejsc=polygon;
            }
            if(numerFunkcji==3){
                gd2.setPaint(kolorSciezki);
                int []tabX={
                    (int)drawStart.getX(),
                    (int)((drawStart.getX()+drawEnd.getX())/2),
                    (int)drawEnd.getX()
                };
                int[]tabY={
                    (int)drawEnd.getY(),
                    (int)drawStart.getY(),
                    (int)drawEnd.getY()
                };
                Polygon trojkat=new Polygon(tabX, tabY, 3);
                gd2.drawPolygon(trojkat);
                przejsc=trojkat;
            }
            if(numerFunkcji==4){
               gd2.setPaint(kolorSciezki);
                double startX=drawStart.getX();
                double roznicaX=(drawEnd.getX()-drawStart.getX());
                    if(roznicaX<0){
                        roznicaX=Math.abs(roznicaX);
                        startX=drawEnd.getX();
                    }
                double startY=drawStart.getY();
                double roznicaY=(drawEnd.getY()-drawStart.getY());
                    if(roznicaY<0){
                        roznicaY=Math.abs(roznicaY);
                        startY=drawEnd.getY();
                    }
                Rectangle2D.Double prostokat=new Rectangle2D.Double(startX, startY, roznicaX, roznicaY);
               gd2.draw(prostokat);
               przejsc=prostokat;
            }
            if(numerFunkcji==5){
               gd2.setPaint(kolorSciezki);
                double startX=drawStart.getX();
                double roznicaX=(drawEnd.getX()-drawStart.getX());
                    if(roznicaX<0){
                        roznicaX=Math.abs(roznicaX);
                        startX=drawEnd.getX();
                    }
                double startY=drawStart.getY();
                double roznicaY=(drawEnd.getY()-drawStart.getY());
                    if(roznicaY<0){
                        roznicaY=Math.abs(roznicaY);
                        startY=drawEnd.getY();
                    }
                Ellipse2D.Double elipsa=new Ellipse2D.Double(startX, startY, roznicaX, roznicaY);
               gd2.draw(elipsa);
               przejsc=elipsa;
            }
            if(numerFunkcji==6){
                gd2.setPaint(kolorSciezki);
                int []tabX={
                    (int)drawStart.getX(),
                    (int)(drawStart.getX()+(drawEnd.getX()-drawStart.getX())/4),//(((drawStart.getY()+drawEnd.getY())/2)*(Math.sqrt(3)))/3),
                    (int)(drawEnd.getX()-(drawEnd.getX()-drawStart.getX())/4),//-(((drawStart.getY()+drawEnd.getY())/2)*(Math.sqrt(3)))/3),
                    (int)drawEnd.getX(),
                    (int)(drawEnd.getX()-(drawEnd.getX()-drawStart.getX())/4),//-(((drawStart.getY()+drawEnd.getY())/2)*(Math.sqrt(3)))/3),
                    (int)(drawStart.getX()+(drawEnd.getX()-drawStart.getX())/4),//(((drawStart.getY()+drawEnd.getY())/2)*(Math.sqrt(3)))/3),
                };
                int[]tabY={
                    (int)(drawStart.getY()+drawEnd.getY())/2,
                    (int)drawStart.getY(),
                    (int)drawStart.getY(),
                    (int)(drawStart.getY()+drawEnd.getY())/2,
                    (int)drawEnd.getY(),
                    (int)drawEnd.getY()
                };
                Polygon polygon=new Polygon(tabX, tabY, 6);
                gd2.drawPolygon(polygon);
                gd2.setPaint(kolorWypelnienia);
                gd2.fillPolygon(polygon);
                przejsc=polygon;
            }
            if(numerFunkcji==7){
                gd2.setPaint(kolorSciezki);
                int []tabX={
                    (int)drawStart.getX(),
                    (int)((drawStart.getX()+drawEnd.getX())/2),
                    (int)drawEnd.getX()
                };
                int[]tabY={
                    (int)drawEnd.getY(),
                    (int)drawStart.getY(),
                    (int)drawEnd.getY()
                };
                Polygon trojkat=new Polygon(tabX, tabY, 3);
                gd2.drawPolygon(trojkat);
                gd2.setPaint(kolorWypelnienia);
                gd2.fillPolygon(trojkat);
                przejsc=trojkat;
            }
            if(numerFunkcji==8){
               gd2.setPaint(kolorSciezki);
                double startX=drawStart.getX();
                double roznicaX=(drawEnd.getX()-drawStart.getX());
                    if(roznicaX<0){
                        roznicaX=Math.abs(roznicaX);
                        startX=drawEnd.getX();
                    }
                double startY=drawStart.getY();
                double roznicaY=(drawEnd.getY()-drawStart.getY());
                    if(roznicaY<0){
                        roznicaY=Math.abs(roznicaY);
                        startY=drawEnd.getY();
                    }
                Rectangle2D.Double prostokat=new Rectangle2D.Double(startX, startY, roznicaX, roznicaY);
               gd2.draw(prostokat);
               gd2.setPaint(kolorWypelnienia);
               gd2.fill(prostokat);
               przejsc=prostokat;
            }
            if(numerFunkcji==9){
               gd2.setPaint(kolorSciezki);
                double startX=drawStart.getX();
                double roznicaX=(drawEnd.getX()-drawStart.getX());
                    if(roznicaX<0){
                        roznicaX=Math.abs(roznicaX);
                        startX=drawEnd.getX();
                    }
                double startY=drawStart.getY();
                double roznicaY=(drawEnd.getY()-drawStart.getY());
                    if(roznicaY<0){
                        roznicaY=Math.abs(roznicaY);
                        startY=drawEnd.getY();
                    }
                Ellipse2D.Double elipsa=new Ellipse2D.Double(startX, startY, roznicaX, roznicaY);
               gd2.draw(elipsa);
               gd2.setPaint(kolorWypelnienia);
               gd2.fill(elipsa);
               przejsc=elipsa;
            }
            if(numerFunkcji==12){
                Image img=bitmapa.getImg();
                Image img2=img;
                    gd2.drawImage(img2, (int)drawStart.getX(), (int)drawStart.getY(),
                                (int)(drawEnd.getX()-drawStart.getX()),(int)(drawEnd.getY()-drawStart.getY()), this);
                    gd2.finalize();
                
//                Tla kopiaBitmapy=null;
//                try {
//                    kopiaBitmapy=(Tla)bitmapa.clone();
//                    Image img=kopiaBitmapy.getImg();                            
//                    gd2.drawImage(img, (int)drawStart.getX(), (int)drawStart.getY(),
//                                (int)(drawEnd.getX()-drawStart.getX()),(int)(drawEnd.getY()-drawStart.getY()), this);
//                } catch (CloneNotSupportedException ex) {
//                    ex.printStackTrace();
//                }
            }
        }
    }
    @Override
    protected Object clone()throws CloneNotSupportedException{
        return super.clone();
    }
    public void setNumerFunkcji(int numerFunkcji){
        this.numerFunkcji=numerFunkcji;
    }
}
