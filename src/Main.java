import java.util.ArrayList;
import java.util.Scanner;
public class Main extends ArrayList{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the equation you would like me solve.\nExample: 2 + 3 / 4");
        String equation = scanner.nextLine().trim();
        String[] e = equation.split(" ");
        ArrayList<String> equat = new ArrayList<String>();
        for(int x=0;x<e.length;x++)
            equat.add(e[x]);
        equat.set(0,PEMDAS(equat));
        System.out.println("Your answer is "+equat.get(0));

    }
//Testing
    public static String add(String a, String b){
        return Double.parseDouble(a)+Double.parseDouble(b)+"";
    }

    public static String subtract(String a, String b){
        return Double.parseDouble(a)-Double.parseDouble(b)+"";
    }

    public static String multiply(String a, String b){
        return Double.parseDouble(a)*Double.parseDouble(b)+"";
    }

    public static String divide(String a, String b){
        return Double.parseDouble(a)/Double.parseDouble(b)+"";
    }

    public static String expon(String a, String b) {
        return Math.pow(Double.parseDouble(a),Double.parseDouble(b))+"";
    }
    
    public static String PEMDAS(ArrayList<String> eq){
        int a =0;
        int round=0;
        int temp=0;

        while(eq.size()>1){
            switch (round){
                case 0:if(eq.get(a).equals("(")){
                    ArrayList<String> paren = new ArrayList<String>();
                    for(int x=a+1;x<eq.size();x++){
                        paren.add(eq.get(x));
                    }
                    if(paren.indexOf(")")<paren.indexOf("(") || paren.indexOf("(")==-1){
                        int temp=paren.size();
                        for(int c=temp-1;c>=paren.indexOf(")");c--){
                            paren.remove(c);
                        }
                        temp=paren.size()+a;
                        eq.set(a,PEMDAS(paren));
                        for(int d=a;d<temp+1;d++){
                            eq.remove(a+1);
                        }
                        a=0;
                    }else if(paren.indexOf(")")<paren.indexOf("(")){

                    }
                }else if(a==eq.size()-1){
                round++;
                a=0;
                }else
                a++;

                    break;
                case 1:if(eq.get(a).equals("^")){
                        eq.set(a-1,expon(eq.get(a-1),eq.get(a+1)));
                        eq.remove(a);
                        eq.remove(a);
                        a=a-1;

                }else if(a==eq.size()-1){
                    round++;
                    a=0;
                }else
                    a++;break;
                case 2:if(eq.get(a).equals("*")||eq.get(a).equals("/")){
                        if(eq.get(a).equals("*")){
                            eq.set(a-1,multiply(eq.get(a-1),eq.get(a+1)));
                            eq.remove(a);
                            eq.remove(a);
                            a=a-1;
                        }else if(eq.get(a).equals("/")){
                            eq.set(a-1,divide(eq.get(a-1),eq.get(a+1)));
                            eq.remove(a);
                            eq.remove(a);
                            a=a-1;
                        }
                    }else if(a==eq.size()-1){
                        round++;
                        a=0;
                    }else
                        a++;
                    break;
                case 3: if(eq.get(a).equals("+")||eq.get(a).equals("-")){
                        if(eq.get(a).equals("+")){
                            eq.set(a-1,add(eq.get(a-1),eq.get(a+1)));
                            eq.remove(a);
                            eq.remove(a);
                            a=a-1;
                        }else if(eq.get(a).equals("-")){
                            eq.set(a-1,subtract(eq.get(a-1),eq.get(a+1)));
                            eq.remove(a);
                            eq.remove(a);
                            a=a-1;
                        }
                    }else if(a==eq.size()-1) {
                        round++;
                        a = 0;
                    }else
                        a++;
                    break;
            }

        }
        return eq.get(0);
    }



}
