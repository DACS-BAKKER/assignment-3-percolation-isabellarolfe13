//Algs by Isabella Rolfe
//October 7th, 2019
public class algs {

    //QUICK FIND
    public void QuickFindUnion(int loc1, int loc2){
        if(!QuickFindFind(loc1, loc2)) {
            for (int x : Percolationedited.connected) {
                if (Percolationedited.connected[x] == Percolationedited.connected[loc2]) {
                    //connecting locations
                    Percolationedited.connected[x] = Percolationedited.connected[loc1];
                }
            }
        }
    }
    //check if connected
    public boolean QuickFindFind(int loc1, int loc2){
        boolean b=(Percolationedited.connected[loc1]==Percolationedited.connected[loc2]);
        return b;
    }

    //-----------------------------------------------------
    //QUICK UNION
    public void QuickUnionUnion(int loc1, int loc2){
        int rootloc1=QuickUnionFindRoot(loc1);
        int rootloc2=QuickUnionFindRoot(loc2);
        Percolationedited.connected[rootloc1]=rootloc2;
    }

    public int QuickUnionFindRoot(int loc1){
        while(loc1!=Percolationedited.connected[loc1]){
            loc1=Percolationedited.connected[loc1];
        }
        return loc1;
    }

    public boolean QuickUnionFind(int loc1, int loc2){
        return QuickUnionFindRoot(loc1)==QuickUnionFindRoot(loc2);
    }

    //-----------------------------------------------------
    //QUICKUNIONFIND
    public boolean QuickUnionFind_Find(int loc1, int loc2){
        return QuickUnionFind_FindRoot(loc1)==QuickUnionFind_FindRoot(loc2);
    }

    public void QuickUnionFind_Union(int loc1, int loc2){
        int rootloc1 = QuickUnionFind_FindRoot(loc1);
        int rootloc2 = QuickUnionFind_FindRoot(loc2);
        if (rootloc1 == rootloc2) {
            return;
        }
        //smaller will now connect to larger
        if (Percolationedited.weights[rootloc1] < Percolationedited.weights[rootloc2]) {
            Percolationedited.connected[loc1] = loc2;
            Percolationedited.weights[rootloc2]+=Percolationedited.weights[rootloc1];
        }
        else {
            Percolationedited.connected[loc2] = loc1;
            Percolationedited.weights[rootloc1]+=Percolationedited.weights[rootloc2];
        }
    }

    public int QuickUnionFind_FindRoot(int loc1){
        while (loc1!= Percolationedited.connected[loc1])
            loc1= Percolationedited.connected[loc1];
        return loc1;
    }
}