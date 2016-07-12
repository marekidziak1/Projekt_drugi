
package projekt1;
import com.sun.javafx.cursor.CursorFrame;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javafx.scene.Cursor;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MainFrame extends JFrame {
    private JMenuBar menuBar;
    private JMenu Plik, Edycja, Wyjscie;
    private JMenuItem OtworzBitmapa, ZapiszBitmapa , OtworzSerializacja, 
                ZapiszSerializacja, Cofnij, Wytnij, Kopiuj, Wklej, WyjscieOstateczne;
    private JToolBar pasek;
    private JButton jbPunkt,jbLinia,jbWielokatPusty, jbTrojkatPusty, jbProstokatPusty, 
                jbKoloPuste, jbWielokatWypelniony, jbTrojkatWypelniony, 
                jbProstokatWypelniony, jbKoloWypelnione, jbTloRozciagniete, jbTloKafelkowe,
                jbWstawianieObrazow, jbTekst, jbZmianaKursora;
    private Wspol wspol;
    private JScrollPane jsp=new JScrollPane();
    private rysPanel jpRysowanie;
    private JPanel jpDodMenu;
    private JPanel panel;
    private JToolBar t1;
    private JButton jbKasowanie, jbObracanie, jbPrzesuwanie,jbZmianaGrubosci, jbZmianaKoloru,
                jbZmianaKoloruTla, jbZmianaKoloruWypelnienia, jbRozmiarCzcionki ;
    private DefaultListModel<Integer> lista;
    private JList kon;
    private Integer wybranaWartosc=null;
    private JScrollPane listaRozmiarow, listaCzcionek;
    private JTextField miejsceNaSlowa;
    private String slowo="";
    
    public MainFrame(){
        super("PROJEKT");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //setLayout(null);
        //nie rob k.rwa setLayout(null) bo nie uruchanmia się dobrze - nie wiem dlaczego?
                
        createMenu();
        addMenu();
        create2Menu();
        add2Menu();
        
        
        panel=new JPanel();
            createDodMenu();
            addDodMenu();
            addVisiblesDodMenu();
            addActionsDodMenu();
            //  createRysPanel();=======================
           
            
//==================================================================================
            jpRysowanie=new rysPanel(wspol,lista);
            jsp=new JScrollPane(jpRysowanie);
            panel.add(jsp);
//==================================================================================           
            
        //--------------------------------------------
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        add(panel);
        pack();
        setVisible(true);
    }
    //==========================================================================
    public void createJScroolPane(){
         
    }
    public void addActionsDodMenu(){
        jbKasowanie.addActionListener(new ActionListener() {
                   @Override
                   public void actionPerformed(ActionEvent e) {
                       if(wybranaWartosc!=null){
                        jpRysowanie.setNrFunctionEdycji(1);
                        jpRysowanie.setNrObjEdycji(wybranaWartosc);
                        jpRysowanie.repaint();
                        lista.setSize(lista.getSize()-1);
                        System.out.println(wybranaWartosc+"  "+"kasowanie"+" rozmiarlisty: "+lista.getSize());
                        if(lista.getSize()==0){
                            wybranaWartosc=null;
                        }
                       }
                    }
               });
        jbObracanie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jpRysowanie.setNrFunctionEdycji(2);
                jpRysowanie.setNrObjEdycji(wybranaWartosc);
                
            }
        });
        jbPrzesuwanie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jpRysowanie.setNrFunctionEdycji(3);
                jpRysowanie.setNrObjEdycji(wybranaWartosc);
            }
        });
        jbZmianaGrubosci.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jpRysowanie.setNrFunctionEdycji(4);
                jpRysowanie.setNrObjEdycji(wybranaWartosc);
                jpRysowanie.repaint();
            }
        });
        jbZmianaKoloru.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jpRysowanie.setNrFunctionEdycji(5);
                jpRysowanie.setNrObjEdycji(wybranaWartosc);
                Color kolor=JColorChooser.showDialog(null,  "Pick a Stroke", Color.BLACK);
                jpRysowanie.setKolorSciezki(kolor);
                jpRysowanie.repaint();
            }
        });
        jbZmianaKoloruTla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                jpRysowanie.setNrFunctionEdycji(6);
//                jpRysowanie.setNrObjEdycji(wybranaWartosc);
                Color kolor=JColorChooser.showDialog(null,  "Pick a Stroke", Color.BLACK);
                jpRysowanie.getListaDanych().clearTla();
                jpRysowanie.setKolorTla(kolor);
                jpRysowanie.repaint();
            }
        });
        jbZmianaKoloruWypelnienia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jpRysowanie.setNrFunctionEdycji(7);
                jpRysowanie.setNrObjEdycji(wybranaWartosc);
                Color kolor=JColorChooser.showDialog(null,  "Pick a Stroke", Color.BLACK);
                jpRysowanie.setKolorWypelnienia(kolor);
                jpRysowanie.repaint();
            }
        });
    }
    public void addVisiblesDodMenu(){
        jbPunkt.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                t1.setVisible(true);
                jpRysowanie.setNumerFunkcji(0);
            }
        });
        jbLinia.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                t1.setVisible(true);
                jpRysowanie.setNumerFunkcji(1);
            }
        });
        jbWielokatPusty.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                t1.setVisible(true);
                jpRysowanie.setNumerFunkcji(2);
            }
        });
        jbTrojkatPusty.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                t1.setVisible(true);
                jpRysowanie.setNumerFunkcji(3);
            }
        });
        jbProstokatPusty.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                t1.setVisible(true);
                jpRysowanie.setNumerFunkcji(4);
            }
        }); 
        jbKoloPuste.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                t1.setVisible(true);
                jpRysowanie.setNumerFunkcji(5);
            }
        });
        jbWielokatWypelniony.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                t1.setVisible(true);
                jpRysowanie.setNumerFunkcji(6);
            }
        });
        jbTrojkatWypelniony.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                t1.setVisible(true);
                jpRysowanie.setNumerFunkcji(7);
            }
        });
        jbProstokatWypelniony.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                t1.setVisible(true);
                jpRysowanie.setNumerFunkcji(8);
            }
        });
        jbKoloWypelnione.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                t1.setVisible(true);
                jpRysowanie.setNumerFunkcji(9);
            }
        });
        jbTloRozciagniete.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                t1.setVisible(true);
                jpRysowanie.setNumerFunkcji(10);
                //=============================================
                jpRysowanie.setPlik("rozmiarCzcionki.png");
                Image img=Toolkit.getDefaultToolkit().getImage("images2"+File.separatorChar+jpRysowanie.getPlik());
                Tla nowy=new Tla(img,FunkcjeBitmapy.tloRozciagnie);
                jpRysowanie.setTlo(nowy);
                jpRysowanie.repaint();
            }
        });
        jbTloKafelkowe.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                t1.setVisible(true);
                jpRysowanie.setNumerFunkcji(11);
                //=============================================
                jpRysowanie.setPlik("rozmiarCzcionki.png");
                Image img=Toolkit.getDefaultToolkit().getImage("images2"+File.separatorChar+jpRysowanie.getPlik());
                Tla nowy=new Tla(img,FunkcjeBitmapy.tloKafelkowe);
                jpRysowanie.setTlo(nowy);
                jpRysowanie.repaint();
            }
        });
        jbWstawianieObrazow.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                t1.setVisible(true);
                jpRysowanie.setNumerFunkcji(12);
                //============================================
                jpRysowanie.setPlik("rozmiarCzcionki.png");
                Image img=Toolkit.getDefaultToolkit().getImage("images2"+File.separatorChar+jpRysowanie.getPlik());
                Tla nowy=new Tla(img,FunkcjeBitmapy.bitmapa);
                jpRysowanie.setBitmapa(nowy);
                
            }
        });
        jbTekst.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                t1.setVisible(true);
                jpRysowanie.setNumerFunkcji(13);
            }
        });
        jbZmianaKursora.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                t1.setVisible(false);
                jpRysowanie.setNumerFunkcji(14);
            }
        });
    }
    public void addDodMenu(){
        t1.add(jbKasowanie);
        t1.add(jbPrzesuwanie);
        t1.add(jbObracanie);
        t1.add(jbZmianaGrubosci);
        t1.add(jbZmianaKoloru);
        t1.add(jbZmianaKoloruWypelnienia);
        t1.add(jbZmianaKoloruTla);
        //t1.add(jbRozmiarCzcionki);
        t1.add(listaRozmiarow);
        t1.add(listaCzcionek);
        t1.add(miejsceNaSlowa);
        t1.setVisible(false);
        
        
        
        jbKasowanie.setVisible(true);
        jbObracanie.setVisible(true);
        jbPrzesuwanie.setVisible(true);
        jbZmianaGrubosci.setVisible(true);
        jbZmianaKoloru.setVisible(true);
        jbZmianaKoloruTla.setVisible(true);
        jbZmianaKoloruWypelnienia.setVisible(true);
        
        jpDodMenu.add(t1);
        panel.add(jpDodMenu);
    }
    public void createDodMenu(){
        jpDodMenu=new JPanel();
        jpDodMenu.setLayout(new BoxLayout(jpDodMenu,BoxLayout.Y_AXIS));

        t1=new JToolBar();
        t1.setAlignmentX(0);
        
        ImageIcon imKasowanie=new ImageIcon("images2"+File.separatorChar+"kasowanie.png");
        jbKasowanie=new JButton(imKasowanie);
        ImageIcon imObracanie=new ImageIcon("images2"+File.separatorChar+"obracanie.png");
        jbObracanie=new JButton(imObracanie);
        ImageIcon imPrzesuwanie=new ImageIcon("images2"+File.separatorChar+"przesuwanie.png");
        jbPrzesuwanie=new JButton(imPrzesuwanie);
        ImageIcon imZmianaGrubosci= new ImageIcon("images2"+File.separatorChar+"zmianaGrubosci.png");
        jbZmianaGrubosci=new JButton(imZmianaGrubosci);
        ImageIcon imZmianaKoloru=new ImageIcon("images2"+File.separatorChar+"zmianaKoloru.png");
        jbZmianaKoloru=new JButton(imZmianaKoloru);
        ImageIcon imZmianaKoloruWypelnienia=new ImageIcon("images2"+File.separatorChar+"zmianaKoloruWypelnienia.png");
        jbZmianaKoloruWypelnienia=new JButton(imZmianaKoloruWypelnienia);                
        ImageIcon imZmianaKoloruTla=new ImageIcon("images2"+File.separatorChar+"zmianaKoloruTla.png");
        jbZmianaKoloruTla=new JButton(imZmianaKoloruTla);
//        ImageIcon imRozmiarCzcionki=new ImageIcon("images2"+File.separator+"rozmiarCzcionki.png");
//        jbRozmiarCzcionki=new JButton(imRozmiarCzcionki);
        String [] rozmiary = new String [100];
        for(int i=0; i<rozmiary.length; i++){
            rozmiary[i]=i+"";
        }
        JList list=new JList(rozmiary);
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                jpRysowanie.setRozmiarSciezki(e.getFirstIndex());
            }
        });
        listaRozmiarow=new JScrollPane(list);
        listaRozmiarow.setPreferredSize(new Dimension(18,18));
        listaRozmiarow.setMaximumSize(new Dimension(100,18));
        listaRozmiarow.setMinimumSize(new Dimension(50,18));
        String [] czcionki = {
            "PLAIN", "BOLD", "ITALIC",
        };
        JList lista2=new JList(czcionki);
        lista2.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int font=0;
                switch(e.getFirstIndex()){
                        case 0: font=Font.PLAIN; break;
                        case 1: font=Font.BOLD; break;
                        case 2: font=Font.ITALIC; break;
                        default: break;
                }
                jpRysowanie.setFonty(font);
            }
        });
        listaCzcionek=new JScrollPane(lista2);
        listaCzcionek.setPreferredSize(new Dimension(18,18));
        listaCzcionek.setMaximumSize(new Dimension(100,18));
        listaCzcionek.setMinimumSize(new Dimension(50,18));

        
        miejsceNaSlowa =new JTextField();
        miejsceNaSlowa.setPreferredSize(new Dimension(150,18));
        miejsceNaSlowa.setMaximumSize(new Dimension(200,18));
        miejsceNaSlowa.setMinimumSize(new Dimension(50,18));
        
        miejsceNaSlowa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                slowo="";
            }
        });
        miejsceNaSlowa.addKeyListener(new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent e) {
            slowo+=e.getKeyChar();
            jpRysowanie.setSlowa(slowo);
        }
        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
        });
        
    }
    public void add2Menu(){
        pasek.add(wspol);
        pasek.add(jbPunkt);
        pasek.add(jbLinia);
        pasek.add(jbWielokatPusty);
        pasek.add(jbTrojkatPusty);
        pasek.add(jbProstokatPusty);
        pasek.add(jbKoloPuste);
        pasek.add(jbWielokatWypelniony);
        pasek.add(jbTrojkatWypelniony);
        pasek.add(jbProstokatWypelniony);
        pasek.add(jbKoloWypelnione);
        pasek.add(jbTloRozciagniete);
        pasek.add(jbTloKafelkowe);
        pasek.add(jbWstawianieObrazow);
        pasek.add(jbTekst);
        pasek.add(jbZmianaKursora);
        JScrollPane scroll=new JScrollPane(kon);
        scroll.setPreferredSize(new Dimension(30,24));
        scroll.setMaximumSize(new Dimension(40,24));
        scroll.setMinimumSize(new Dimension(20,24));
        pasek.add(scroll);
        add(pasek, BorderLayout.NORTH);
        
    }
    public void create2Menu(){
        pasek=new JToolBar();
        ImageIcon imPunkt=new ImageIcon("images"+File.separatorChar+"punkty.png");
        jbPunkt=new JButton(imPunkt);
        ImageIcon imLinia=new ImageIcon("images"+File.separatorChar+"linia.png");
        jbLinia=new JButton(imLinia);
        ImageIcon imWielokatPusty=new ImageIcon("images"+File.separatorChar+"wielokatyPuste.png");
        jbWielokatPusty=new JButton(imWielokatPusty);
        ImageIcon imTrojkatPusty=new ImageIcon("images"+File.separatorChar+"trojkatPusty.png");
        jbTrojkatPusty=new JButton(imTrojkatPusty);
        ImageIcon imProstokatPusty=new ImageIcon("images"+File.separatorChar+"prostokatyPuste.png");
        jbProstokatPusty=new JButton(imProstokatPusty);
        ImageIcon imKoloPuste=new ImageIcon("images"+File.separatorChar+"koloPuste.png");
        jbKoloPuste=new JButton(imKoloPuste);
        
        ImageIcon imWielokatWypelniony=new ImageIcon("images"+File.separatorChar+"wielokatyWypelnione.png");
        jbWielokatWypelniony=new JButton(imWielokatWypelniony);
        ImageIcon imTrojkatWypelniony= new ImageIcon("images"+File.separatorChar+"trojkatWypelniony.png");
        jbTrojkatWypelniony=new JButton(imTrojkatWypelniony);
        ImageIcon imProstokatWypelniony=new ImageIcon("images"+File.separatorChar+"prostokatyWypelnione.png");
        jbProstokatWypelniony=new JButton(imProstokatWypelniony);
        ImageIcon imKoloWypelnione=new ImageIcon("images"+File.separatorChar+"koloWypelnione.png");
        jbKoloWypelnione=new JButton(imKoloWypelnione);
        
        ImageIcon imTloRozciagniete=new ImageIcon("images"+File.separatorChar+"tloRozciagniete.png");
        jbTloRozciagniete=new JButton(imTloRozciagniete);
        ImageIcon imTloKafelkowe=new ImageIcon("images"+File.separatorChar+"tloKafelkowe.png");
        jbTloKafelkowe=new JButton(imTloKafelkowe);
        ImageIcon imWstawianieObrazow=new ImageIcon("images"+File.separatorChar+"wstawianieObrazow.png");
        jbWstawianieObrazow=new JButton(imWstawianieObrazow);
        
        ImageIcon imTekst=new ImageIcon("images"+File.separatorChar+"tekst.png");
        jbTekst=new JButton(imTekst);
        ImageIcon imZmianaKursora=new ImageIcon("images"+File.separatorChar+"zmianaKursora.png");
        jbZmianaKursora=new JButton(imZmianaKursora);
        wspol =new Wspol("      X: 0000 Y: 0000");
        
        lista=new DefaultListModel<Integer>();
        kon=new JList<Integer>(lista);
        kon.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                
                Integer obj=e.getFirstIndex();
                wybranaWartosc=obj;
                System.out.println("wybrano wartosc"+wybranaWartosc);
            }
        });
        
    }
    
    public void addMenu(){
        Plik.add(OtworzBitmapa);
        Plik.add(ZapiszBitmapa);
        Plik.addSeparator();
        Plik.add(OtworzSerializacja);
        Plik.add(ZapiszSerializacja);
        Edycja.add(Cofnij);
        Edycja.add(Wytnij);
        Edycja.add(Kopiuj);
        Edycja.add(Wklej);
        
        Wyjscie.add(WyjscieOstateczne);
        Wyjscie.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
        
        
        menuBar.add(Plik);
        menuBar.add(Edycja);
        menuBar.add(Wyjscie);
        setJMenuBar(menuBar);
    }
    public void createMenu(){
        menuBar=new JMenuBar();
        Plik=new JMenu("Plik");
        Edycja=new JMenu("Edycja");
        Wyjscie=new JMenu("Wyjście");
        OtworzBitmapa=new JMenuItem("Otwórz Bitmapa");
        ZapiszBitmapa=new JMenuItem("ZapiszBitmapa");        
        OtworzSerializacja=new JMenuItem("OtworzSerializacja");
        ZapiszSerializacja=new JMenuItem("ZapiszSerializacja"); 
        Cofnij=new JMenuItem("Cofnij");
        Wytnij=new JMenuItem("Wytnij");
        Kopiuj=new JMenuItem("Kopiuj");
        Wklej=new JMenuItem("Wklej");
        WyjscieOstateczne=new JMenuItem("Wyjscie");
    }
}
