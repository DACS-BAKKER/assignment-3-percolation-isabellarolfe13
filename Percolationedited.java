import java.util.Random;
import java.util.Scanner;
//Percolation by Isabella Rolfe
//October 7th, 2019
public class Percolationedited {
    //array stores whether each open, full etc (status)
    public int[] status;
    //array corresponds depending on quick find/unions which site is connected to which
    public static int[] connected;
    public static int length;
    public static int numTiles;
    //array for quickunionfind
    public static int[] weights;
    algs alg = new algs();

    // create N-by-N grid, with all sites blocked
    public Percolationedited(int N) {
        numTiles = length * length;
        status = new int[numTiles];
        connected = new int[numTiles];
        weights=new int[numTiles];
        //0 is blocked
        for (int i = 0; i < status.length; i++) {
            status[i] = 0;
            connected[i] = i;
            weights[i]=1;
        }
    }

    // open site (row i, column j) if it is not open already
    //1 = open
    public void open(int i, int j) {
        int index = convert(i, j);
        if (status[index] == 0) {
            status[index] = 1;
            //check if it goes off board
            if (i != length-1) {
                //checking below
                connectIfopen(index, i + 1, j);
            }
            if (i != 0) {
                //checking above
                connectIfopen(index, i - 1, j);
            }
            if (j != 0) {
                //checking left
                connectIfopen(index, i, j - 1);
            }
            if (j != length-1) {
                //checking right
                connectIfopen(index, i, j + 1);
            }
        }
    }

    public void connectIfopen(int index, int i, int j){
        int neighbor=convert(i, j);
        if(isOpen(i,j)){
            //sets index equal to neighbor
            alg.QuickFindUnion(neighbor,index);
        }
    }

    //converts to 1D coordinates
    public int convert(int i, int j){
        return i * length + j;
    }

    // is site (row i, column j) open?
    public boolean isOpen(int i, int j){
        return (status[convert(i,j)] == 1);
    }

    // is site (row i, column j) full?
    public boolean isFull(int i, int j){
        for(int x=0;x<length;x++){
            if((alg.QuickFindFind(convert(i,j),convert(0,x)))){
                return true;
            }
        }
        return false;
    }

    // does the system percolate?
    public boolean Percolates(){
        for(int x=0;x<length;x++){
            for(int y=0;y<length;y++){
                //check to see if bottom and top are connected
                if(alg.QuickFindFind(convert(length-1,x), convert(0, y))){
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        System.out.println("Type in number for the percolation threshold!");
        length=scan.nextInt();
        Percolationedited perc=new Percolationedited(length);
        int openTiles=0;
        Random ran=new Random();

        //"shoots" off randomly to array
        for(int i=0;i<numTiles;i++){
            int x = ran.nextInt(length);
            int y= ran.nextInt(length);
            if(!perc.isOpen(x,y)) {
                openTiles++;
            }
            perc.open(x,y);
            if(perc.Percolates()){
                //if percolates break out of for loop
                break;
            }
        }
        double percolationThreshold = (double) openTiles/ numTiles;
        System.out.println("open tiles: " + openTiles);
        System.out.println("number of tiles: " + numTiles);
        System.out.println("Percolation Threshold: " + percolationThreshold);
    }
}
