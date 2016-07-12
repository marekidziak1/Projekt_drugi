
package projekt1;

import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;

public class Tla extends Polygon implements Cloneable{
    private Image img;
    private FunkcjeBitmapy funkcje;
    private Point start;
    private Point end;
    public Tla(Image img, FunkcjeBitmapy e){
        this.img=img;
        this.funkcje=e;
    }
    public Tla(Image img, FunkcjeBitmapy e, Point start, Point end){
        this.img=img;
        this.funkcje=e;
        this.start=start;
        this.end=end;
    }
    @Override
    public Object clone()throws CloneNotSupportedException{
       
        return(Tla)super.clone();
    }
    public Point getStart(){
        return start;
    }

    public void setStart(int x, int y) {
        Point p=new Point(x,y);
        this.start = p;
    }
    public void setStart(Point start){
        this.start=start;
    }

    public Point getEnd() {
        return end;
    }

    public void setEnd(int x, int y) {
        Point p=new Point(x,y);
        this.start = p;
    }
    public void setEnd(Point end){
        this.end=end;
    }
    
    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public FunkcjeBitmapy getFunkcje() {
        return funkcje;
    }

    public void setFunkcje(FunkcjeBitmapy funkcje) {
        this.funkcje = funkcje;
    }
    
}
