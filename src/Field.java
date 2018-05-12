public class Field{
    private int x;
    private int y;

    Field(){}

    Field(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Field) {
            Field f = (Field) obj;
            return (f.getX() == getX() && f.getY() == getY());
        }
        return false;
    }

    public int getX(){
        return x;
    }

    public int getY() {
        return y;
    }

    public String toString(){
        return "x: "+ getX()+", y: "+getY();
    }

}
