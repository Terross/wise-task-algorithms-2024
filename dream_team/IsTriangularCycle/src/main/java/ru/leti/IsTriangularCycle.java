package ru.leti;

import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;
import com.mathsystem.domain.graph.repository.Color;


import java.util.*;


 public class IsTriangularCycle implements GraphProperty {
     @Override
     public boolean execute(Graph graph) {
         ArrayList<Edge> marked = new ArrayList<>();
         HashMap<UUID, Integer> verts = new HashMap<UUID, Integer>();
         int V = graph.getVertexCount();

         ArrayList<ArrayList<Integer>> adj_matr = new ArrayList<ArrayList<Integer>>();


         int n = 0;

         for (Edge e : graph.getEdges()) {//including main edges of cycle to marked[]
             Color c = e.getColor();

             if (c == Color.red) {
                 marked.add(e);
                 UUID from = e.getFromV();
                 UUID to = e.getToV();

                 if (!verts.containsKey(from)) {
                     verts.put(from, n);
                     n = n + 1;
                     adj_matr.add(new ArrayList<Integer>(V));
                     for(int i=0;i<V;i++){
                         adj_matr.get(n-1).add(0);
                     }

                 }
                 if (!verts.containsKey(to)) {
                     verts.put(to, n);
                     n = n + 1;
                     adj_matr.add(new ArrayList<Integer>(V));
                     for(int i=0;i<V;i++){
                         adj_matr.get(n-1).add(0);
                     }
                 }
                 //and add value "1" in adj_matr for them
                 adj_matr.get(verts.get(from)).set(verts.get(to), 1);
                 //adding reverse edge
                 adj_matr.get(verts.get(to)).set(verts.get(from), 1);

             }
         }

         if(marked.size()!=n) return false;

         for (Edge e : graph.getEdges()) {//including internal edges of cycle to marked[]
             //and add value "2" in adj_matr for them
             UUID from = e.getFromV();
             UUID to = e.getToV();
            if ((verts.containsKey(from)) && (verts.containsKey(to))) {
                if(adj_matr.get(verts.get(from)).get(verts.get(to))!=1){
                    marked.add(e);
                    adj_matr.get(verts.get(from)).set(verts.get(to), 2);

                }
            }
         }

         boolean isTrCycle = false;

         int v = n;
         int cur = 0;
         int prev = -1;
         int i;
         int f;
         for(n=0;n<v;n++){//trying to walk through every vertex in N steps(edges)
             i = 0;
             f = 0;
             for(i=0;i<v;i++){
                 if(i==prev) {continue;}//not going back the same edge
                 if(adj_matr.get(cur).get(i)==1){
                     prev = cur;
                     cur=i;
                     f = 1;
                     break;
                 }
             }
             if (f == 0) break;//if we fail to find any edge from cur. vert
         }

         //if some internal edges of cycle was labeled by user, search may be stuck in lesser cycle
         if((n<v)||(cur!=0)){return false;} //if we end up not in start vertex - not a cycle

         var T = new PalmTree();
         T.Builder(marked);
         T.mode = 1;
         isTrCycle = T.testTriang();
         return isTrCycle;
     }
 }