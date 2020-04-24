
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
//            File file = new File("C:\\Users\\Carlos\\Desktop\\input.txt");
            Scanner s = new Scanner(System.in);
            String n_competitor = s.nextLine();
            int total = Integer.valueOf(n_competitor);
            int cont=0;
            while (cont<total){
                String n_cases= s.nextLine();
                String[]num_length_s = n_cases.split(" ");
                LinkedList<LinkedList<Character>>connected_graphs= new LinkedList<LinkedList<Character>>();
                for(int i=0;i<Integer.valueOf(num_length_s[1]);i++){
                    String pair_link_s = s.nextLine();
                    String[] pair_link = pair_link_s.split(" ");
                    char first = pair_link[0].toCharArray()[0];
                    char second = pair_link[1].toCharArray()[0];
                    boolean added_in_existent = false;
                    //Esse pedaço checa se alguma lista existente em connected_graphs, contém o primeiro ou o segundo char, se sim, adiciona os chars na lista
                    for(LinkedList<Character>a : connected_graphs){
                        if(a.contains(first) || a.contains(second)){
                            if(!a.contains(first))
                                a.add(first);
                            if(!a.contains(second))
                                a.add(second);
                            added_in_existent = true;
                        }
                    }
                    //Se nenhuma lista continha qualquer um dos chars, cria uma nova e adiciona eles
                    if(!added_in_existent){
                        LinkedList<Character> a = new LinkedList<>();
                        a.add(first);
                        a.add(second);
                        connected_graphs.add(a);
                    }
                }
                //Adiciona novos chars que não tinham links com quaisquers outros
                for(int i=97;i<97+Integer.valueOf(num_length_s[0]);i++){
                    char test = (char)i;
                    boolean in_graph=false;
                    for(LinkedList<Character>a : connected_graphs){
                        if(a.contains(test)){
                            in_graph=true;
                        }
                    }
                    if(!in_graph){
                        LinkedList<Character> a = new LinkedList<>();
                        a.add(test);
                        connected_graphs.add(a);
                    }
                }
                //Checa se duas linkedlists geradas tem elementos em comum, se sim, leva todos elementos de uma pra outra e limpa a linkedlist que deu elementos
                LinkedList<LinkedList<Character>>connected_graphs2= new LinkedList<LinkedList<Character>>(connected_graphs);
                for(LinkedList<Character>a : connected_graphs2){
                    for(Character an: a){
                        for(LinkedList<Character>b : connected_graphs2){
                            if(a!=b){
                                if(b.contains(an)){
                                    for(Character an1: a){
                                        if(!b.contains(an1)){
                                            b.add(an1);
                                        }
                                    }
                                    a.clear();
                                    connected_graphs.remove(a);
                                }
                            }
                        }
                    }
                }
                //Ordena as linked list em ordem alfabética na horizontal
                for(LinkedList<Character>a : connected_graphs){
                    a.sort(null);
                }
                //Ordena as linked list em ordem alfabética na vertical
                connected_graphs.sort(new ListCompare());
                //Printa os resultados
                System.out.println("Case #"+(cont+1)+":");
                int cont_components =0;
                for(LinkedList<Character> a : connected_graphs){
                    if(a.size()>0){
                        cont_components++;
                        for(Character an : a){
                            System.out.print(an+",");
                        }
                        System.out.println("");
                    }
                }
                System.out.println(cont_components+" connected components\n");
                connected_graphs.clear();
                cont++;
            }
    }
    
}

class ListCompare implements Comparator<LinkedList<Character>> { 
    public int compare(LinkedList<Character> m1, LinkedList<Character> m2){ 
        try{
//            System.out.println(m1.get(0)+" "+m2.get(0));
            if (m1.get(0)>m2.get(0)){
//                System.out.println("return 1");
                return 1;
            }else{
                if (m1.get(0)<m2.get(0)){
//                    System.out.println("return -1");
                    return -1;
                }else{
//                    System.out.println("return 0");
                    return 0;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    } 
} 

