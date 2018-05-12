import java.io.File;
import java.util.Vector;

public class KnightMove {

    private Vector<Vector<Field>> matrix = new Vector<>();
    private MoveSequence<Field> moveSequence = new MoveSequence<>();
    private Field lastField = new Field(0,0);
    private int size = 4;

    private void setIndexes(int w, int h){ //initializes the field
        for(int x=0;x<w;x++){
            Vector<Field> r = new Vector<>();
            for(int y=0;y<h;y++){
                Field f=new Field(x,y);
                r.add(f);
            }
            matrix.add(r);
        }
    }

    private void printMatrix(int w, int h){
        for(int i=0;i<w;i++){
            Vector<Field> r=matrix.get(i);
            for(int j=0;j<h;j++){
                System.out.println("x: "+r.get(j).getX()+", y: "+r.get(j).getY()+"\n");
            }
        }
    }

    private boolean checkBounds(int num, int add){
        return (num+add>-1&&num+add<size);
    }

    private int[][] moves = {{-1,-2},{-2,-1}, {1,2},{2,1}, {1,-2},{2,-1}, {-1,2},{-2,-1}};

    private Field nextField(Field f, int check){
        int x = lastField.getX();
        int y = lastField.getY();
        Field next;
        int nX = moves[check][0];
        int nY = moves[check][1];

        if(!moveSequence.contains(f)){
            lastField = f;
            return f;
        }

        else if(checkBounds(x,nX)&&checkBounds(y,nY)) {
            next = new Field(x + nX, y + nY);
            return nextField(next, check + 1);
        }
        else return nextField(lastField,check+1);
    }

    private void makeMove() {
        Field moveTo = nextField(lastField,0);
        moveSequence.add(moveTo);
    }

    public static void main(String[] args){
        KnightMove k = new KnightMove();
        k.setIndexes(k.size,k.size);
        for(int i=0;i<13;i++){ //takes the same path every time so only maxes out at 13 moves
            k.makeMove();
        }
        System.out.println(k.moveSequence.toString());
    }
}
