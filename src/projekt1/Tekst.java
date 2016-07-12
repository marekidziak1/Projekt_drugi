/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt1;

import java.awt.Point;
import java.awt.Polygon;

/**
 *
 * @author Marek
 */
public class Tekst extends Polygon{
    private String tekst;
    private double x;
    private double y;
    private int fontyTekst;
    public Tekst(String tekst){
        this.tekst=tekst;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
    
    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public int getFontyTekst() {
        return fontyTekst;
    }

    public void setFontyTekst(int fontyTekst) {
        this.fontyTekst = fontyTekst;
    }
    
    
}
