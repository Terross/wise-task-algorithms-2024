package ru.leti;

import com.mathsystem.api.graph.model.Edge;

import java.util.*;

import static java.lang.Math.min;

class Block{
    int l;
    int r;
}
class AdjacentVert{
    int priority = 0;
    boolean frond = false;
    boolean arc = false;
    PalmVertex v;

    AdjacentVert(PalmVertex v){
        this.v = v;
    }
}


class SortByPrior implements Comparator<AdjacentVert> {
    public int compare(AdjacentVert v1, AdjacentVert v2){
        if (v1.priority==v2.priority) return 0;
        if (v1.priority>v2.priority) return 1;
        else return -1;
    }
}

class PalmVertex{
    int n = 0;
    int LPT1 = n;
    int LPT2 = n;
    ArrayList<AdjacentVert> adjacent;

    PalmVertex(int n){
        this.n = n;
    }
}

class PalmTree{
    int V = 0;
    int E = 0;
    int mode = 0; // 0 - test planarity, 1 - test triang
    ArrayList<PalmVertex> vertices;

    public void Builder(List<Edge> edges){
        int n = 0;

        Map<UUID,PalmVertex> verts = new HashMap<>();
        this.vertices = new ArrayList<PalmVertex>();

        for(Edge e: edges){
            PalmVertex v;
            if(!verts.containsKey(e.getToV())){
                v = new PalmVertex(n);
                verts.put(e.getToV(), v);
                V++;
                vertices.add(v);
                v.adjacent = new ArrayList<AdjacentVert>();

            }
            if(!verts.containsKey(e.getFromV())){
                v = new PalmVertex(n);
                verts.put(e.getFromV(),v);
                V++;
                vertices.add(v);
                v.adjacent = new ArrayList<AdjacentVert>();
            }
            v = verts.get(e.getFromV());
            v.adjacent.add(new AdjacentVert(verts.get(e.getToV())));
            v = verts.get(e.getToV());
            v.adjacent.add(new AdjacentVert(verts.get(e.getFromV())));
        }

    }
    int n = 1; //counter for vertices in 1-connected graph

    //firstDFS
    void labelTree(PalmVertex s, AdjacentVert w){ //s is ancestor of w
        if(s == null){
            n = 1;
            w.v.n = n;//first vertex - root
            V++;
        }else{
            n = n + 1;
            w.v.n = n; //children numbers are bigger than parent`s
            V++;
        }
        w.v.LPT1 = w.v.n; //default low point value is vertex num
        w.v.LPT2 = w.v.n;
        for(AdjacentVert u: w.v.adjacent){


            if(u.v.n ==0){//not processed yet
                u.arc = true; //tree edge
                E = E + 1; //counting edges in tree
                labelTree(w.v,u);
                if(u.v.LPT1 < w.v.LPT1){ //calculating LPT1,2 and priority for second DFS
                    w.v.LPT2 = min(u.v.LPT2,w.v.LPT1);
                    w.v.LPT1 = u.v.LPT1;

                } else if(u.v.LPT1 == w.v.LPT1) {
                    w.v.LPT2 = min(u.v.LPT2,w.v.LPT2);
                }else{
                    w.v.LPT2 = min(u.v.LPT1,w.v.LPT2);
                }
                if(u.v.LPT2>=w.v.n){
                    switch(mode){
                        case 0: u.priority = 2*u.v.LPT1;
                            break;
                        case 1: u.priority = 2*u.v.LPT1-1;
                            break;
                        default:
                            break;
                    }
                }
                if(u.v.LPT2<w.v.n){
                    switch(mode){
                        case 0: u.priority = 2*u.v.LPT1+1;
                            break;
                        case 1: u.priority = 2*u.v.LPT1;
                            break;
                        default:
                            break;
                    }
                }

            }

            if((u.v.n < w.v.n) && (u.v.n !=0)){
                //delete reverse edge of frond
                if(w.v.n>1){
                    if (u.v.n == s.n) {//if frond leads back to s, like s->w, w->s
                        continue;
                    }
                }
                E = E + 1;
                u.frond = true;
                if (u.v.n < w.v.LPT1){
                    w.v.LPT2 = w.v.LPT1;
                    w.v.LPT1 = u.v.n;
                }else if(u.v.n > w.v.LPT1){
                    w.v.LPT2 = min(w.v.LPT2, u.v.n);
                }
                u.priority = 2*u.v.n;
            }

        }
    }


    void sortAdjLists(){

        for(PalmVertex v: this.vertices){
            v.adjacent.removeIf(w -> w.priority == 0);
            v.adjacent.sort(new SortByPrior()); ///???
        }
    }

    int[] stack; // array for entries on L and R
    int[] next; //saving indexes of next entries
    //stack[i] returns entry on L(R), next[E] - start of R, next[0] - start of L
    LinkedList<Block> blocks;
    int[] path;
    int[] f;
    int free = 1;
    int p = 0;
    int s = 0;


    void showStack(){
        System.out.println("B:");

        int i = blocks.size()-1;
        while(i!=-1){
            System.out.printf("(%d;%d)\n",stack[blocks.get(i).l],stack[blocks.get(i).r]);
            i--;
        }
        int l = next[0];
        int r = next[E];
        System.out.println("L");
        do{
            System.out.println(stack[l]);
            l = next[l];
        }while(l!=0);
        do{
            System.out.println(stack[r]);
            r = next[r];
        }while(r!=0);

    }

    //secondDFS
    boolean PlanarEmbedding(PalmVertex v) {
        int save;
        for (AdjacentVert w : v.adjacent) {
            if (w.arc) {
                if (s == 0) {
                    s = v.n;
                    p = p + 1;
                }
                path[w.v.n] = p;

                boolean res = PlanarEmbedding(w.v);
                if (!res) return false;
                //delete stack entries and blocks corresponding to vertices no smaller than v
                var i = blocks.size();
                while ((blocks.get(i - 1).l >= v.n) || (blocks.get(i - 1).r >= v.n)) {
                    var x = blocks.get(i - 1).l;
                    var y = blocks.get(i - 1).r;
                    if ((stack[x] >= v.n) && (stack[y] <= v.n)) {
                        next[0] = next[x];
                        //!!!!
                        blocks.get(i - 1).l = 0;
                    }
                    if ((stack[y] >= v.n) && (stack[x] <= v.n)) {
                        next[E] = next[y];
                        //!!!!
                        blocks.get(i - 1).r = 0;
                    }
                    if ((blocks.get(i - 1).l == 0) && (blocks.get(i - 1).r == 0)) blocks.removeLast();
                    i = i - 1;
                    if (i == 0) break;
                }
                while (stack[next[E]] >= v.n) {
                    next[E] = next[next[E]];

                }
                while (stack[next[0]] >= v.n) {
                    next[0] = next[next[0]];
                }
                //all segments with first edge (v, w) has been embedded
                // New blocks must be moved from right to left

                if ((path[v.n] != path[w.v.n]) && (!blocks.isEmpty())) {
                    var x = blocks.getLast().l;
                    var y = blocks.getLast().r;


                    while ((stack[x] >= f[path[w.v.n]]) || (stack[y] >= f[path[w.v.n]])) {
                        if (stack[x] >= f[path[w.v.n]]) {
                            if (stack[y] >= f[path[w.v.n]]) return false;
                        } else if ((stack[y] >= f[path[w.v.n]]) && (stack[next[E]] != 0)) {
                            save = next[0];
                            next[0] = next[E];
                            next[E] = next[y];
                            next[y] = save;
                        }
                        blocks.removeLast();
                        x = blocks.getLast().l;
                        y = blocks.getLast().r;
                    }

                    //delete end-of-stack marker on right stack
                    next[E] = next[next[E]];

                }
            }else if (w.frond) {
                if (s == 0) {
                    s = v.n;
                    p = p + 1;
                }
                f[p] = w.v.n;
                //switch blocks of entries from left to right so that p may be embedded on left
                var L = 0;
                var R = E;
                while ((stack[next[L]] > w.v.n) || (stack[next[R]] > w.v.n)) {
                    var x = blocks.getLast().l;
                    var y = blocks.getLast().r;
                    if ((x != 0) && (y != 0)) {

                        if (stack[next[L]] > w.v.n) {
                            if (stack[next[R]] > w.v.n) {
                                return false;
                            }
                            save = next[R];
                            next[R] = next[L];
                            next[L] = save;
                            save = next[y];
                            next[y] = next[x];
                            next[x] = save;
                            L = y; //
                            R = x;//
                        } else if (stack[next[R]] > w.v.n) {
                            L = x;
                            R = y;
                        }
                    } else if ((x != 0) && (stack[next[L]] > w.v.n)) {
                        save = next[R];
                        next[R] = next[L];
                        next[L] = next[x];
                        next[x] = save;
                        R = x;
                    } else if (y != 0) {
                        R = y;
                    }
                    blocks.removeLast();
                }
                //add P to left stack if p is normal
                if (w.v.n > f[path[s]]) {
                    if (L == 0) L = free;
                    save = next[0];
                    stack[L] = w.v.n;
                    next[0] = L;
                    next[L] = save;
                    free = free + 1;

                }

                //add new block corresponding to combined old blocks.
                // New block may be empty if segment containing current path is not a single frond;
                if (R == E) R = 0;
                if ((L != 0) || (R != 0) || (s != v.n)) {
                    var newblock = new Block();
                    newblock.l = L;
                    newblock.r = R;
                    blocks.add(newblock);

                }

                //if segment containing current path is not a single frond,
                //add an end-of-stack marker to right stack;
                if (s != v.n) {

                    stack[free] = 0;
                    next[free] = next[E];
                    next[E] = free;
                    free = free + 1;

                }

                s = 0;
            }
        }
        return true;
    }


    boolean TriangEmbedding(PalmVertex v) {
        int save;

        for (AdjacentVert w : v.adjacent) {
            if (w.arc) {
                if (s == 0) {
                    s = v.n;
                    p = p + 1;
                }
                path[w.v.n] = p;
                boolean res = TriangEmbedding(w.v);
                if (!res) return false;
                //delete stack entries corresponding to vertices no smaller than v

                while (stack[next[E]] >= v.n) {
                    next[E] = next[next[E]];

                }
                while (stack[next[0]] >= v.n) {
                    next[0] = next[next[0]];
                }

            } else if (w.frond) {
                if (s == 0) {
                    s = v.n;
                    p = p + 1;
                }
                f[p] = w.v.n;
                //check if there is blocking fronds inside cycle

                if ((stack[next[0]] > w.v.n)) {
                    return false;
                }

                //add frond
                if (w.v.n >= f[path[s]]) {
                    int L = free;
                    stack[L] = w.v.n;
                    next[0] = L;
                    free = free + 1;
                }
                s = 0;
            }
        }
        return true;
    }


    boolean testPlanar(){
        var start = new PalmVertex(0);
        start.adjacent = new ArrayList<>(); // store all component`s roots
        int i = 0;
        while (i!=V){ //labeling all components of graph
            var root = vertices.get(i);
            start.adjacent.add(new AdjacentVert(root));
            labelTree(null,start.adjacent.get(start.adjacent.size()-1));
            i = 0;
            for(var v: vertices){
                if(v.n==0){
                    break;
                }
                i++;
            }
        }
        if(E>(3*V-3)) return false; //euler`s formula
        sortAdjLists();
        blocks = new LinkedList<Block>();
        stack = new int[E+1];//can be E
        next = new int[E+1];
        path = new int[V+1];
        f = new int[(E-V+1)+1];// number of segments = amount of fields
        f[0] = 0;
        f[1] = 1;
        stack[0] = 0;
        next[E] = 0;
        next[0] = 0;
        for(var w: start.adjacent) {
            if(!PlanarEmbedding(w.v)) return false;
        }
        return true;
    }

    boolean testTriang(){
        var root = vertices.get(0);
        var start = new PalmVertex(0);
        start.adjacent = new ArrayList<>();
        start.adjacent.add(new AdjacentVert(root));
        labelTree(null,start.adjacent.get(0));
        sortAdjLists();
        blocks = new LinkedList<Block>();
        stack = new int[E+1];//can be E
        next = new int[E+1];
        path = new int[V+1];
        f = new int[(E-V+1)+1];// number of segments = amount of fields
        f[0] = 0;
        f[1] = 1;
        stack[0] = 0;
        next[E] = 0;
        next[0] = 0;
        return TriangEmbedding(vertices.get(0));
    }
}