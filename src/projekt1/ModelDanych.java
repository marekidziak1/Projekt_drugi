/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt1;

import java.awt.Color;
import java.awt.Point;
import java.awt.Shape;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;
import java.util.Vector;
import javax.swing.DefaultListModel;

public class ModelDanych implements ListModel<Shape>{
    private Vector<Shape> shape;
    private Vector<Color> shapeColor;
    private Vector<Color> shapeStrokeColor;
    private Vector<Integer> shapeStrokeSize;
    private Vector<Tla> bitmapy;
    public ModelDanych(){
        shape=new Vector<Shape>();
        shapeColor=new Vector<Color>();
        shapeStrokeColor=new Vector<Color>();
        shapeStrokeSize=new Vector<Integer>();
        bitmapy=new Vector<Tla>();
    }
    //========================================
    public Tla getTla(int index){
        return bitmapy.get(index);
    }
    public void addTla(Tla bitmapa){
        bitmapy.add(bitmapa);
    }
    public void addTla(Tla bitmapa, Point start, Point end){
        Tla przej=bitmapa;
        przej.setStart(start);
        przej.setEnd(end);        
        bitmapy.add(przej);
    }
    public void removeTla(int index){
        bitmapy.remove(index);
    }
    public void clearTla(){
        bitmapy.removeAllElements();
    }
    public int getTlaSize(){
        return bitmapy.size();
    }
    //===============================
    public Shape getShape(int index){
        return shape.get(index);
    }
    public Color getShapeColor(int index){
        return shapeColor.get(index);
    }
    public void setShapeColor(Integer nrObjEdycji, Color kolorWypelnienia) {
        shapeColor.set(nrObjEdycji, kolorWypelnienia);
    }
    public Color getShapeStrokeColor(int index){
        return shapeStrokeColor.get(index);
    }
    public void setShapeStrokeColor(Integer nrObjEdycji, Color kolorSciezki) {
        shapeStrokeColor.set(nrObjEdycji, kolorSciezki);
    }
    public int getShapeStrokeSize(int index){
        return shapeStrokeSize.get(index);
    }
    public void setShapeStrokeSize(int index, Integer value){
        shapeStrokeSize.set(index, value); 
    }
    public void add(Shape s, Color c, Color sc, int strokeSize){
        shape.add(s);
        shapeColor.add(c);
        shapeStrokeColor.add(sc);
        shapeStrokeSize.add(strokeSize);
    }
    public void add(Tekst tekst, Color sc, int strokeSize){
        shape.add(tekst);
        shapeColor.add(null);
        shapeStrokeColor.add(sc);
        shapeStrokeSize.add(strokeSize);
    }
    public void add(Tla bitmapa){
        shape.add(bitmapa);
        shapeColor.add(null);
        shapeStrokeColor.add(null);
        shapeStrokeSize.add(null);
    }
    public void remove(int index){
        shape.remove(index);
        shapeColor.remove(index);
        shapeStrokeColor.remove(index);
        shapeStrokeSize.remove(index);
    }

    @Override
    public int getSize() {
        return shape.size();
    }
    @Override
    public Shape getElementAt(int index) {
        return shape.get(index);
    }
//==============================================================================
    @Override
    public void addListDataListener(ListDataListener l) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public void removeListDataListener(ListDataListener l) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    
}
