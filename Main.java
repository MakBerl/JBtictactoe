package tictactoe;
import java.util.Scanner;


public class Main {

    public static void Print(String str){
        System.out.println(" ---------");
        for(int i=0;i<3;i++){
            System.out.println("|"+" "+str.charAt(i*3)+" "+str.charAt(i*3+1)+" "+str.charAt(i*3+2)+" "+"|");
        }
        System.out.println(" ---------");
    }

    public static String Check(char sign, String input) {
        if(sign=='X')
            input.replace("O","*");
        else
            input.replace("X","*");

        String sign_str=Character.toString(sign);
        String word=sign_str+sign_str+sign_str;

        if(input.regionMatches(0,word,0,3)||
                input.regionMatches(3,word,0,3)||
                input.regionMatches(6,word,0,3))
            return sign_str;
        else if(input.charAt(0)==sign && input.charAt(4)==sign && input.charAt(8)==sign)
            return sign_str;
        else if (input.charAt(2)==sign && input.charAt(4)==sign && input.charAt(6)==sign)
            return sign_str;
        else if (input.charAt(0)==sign && input.charAt(3)==sign && input.charAt(6)==sign)
            return sign_str;
        else if (input.charAt(1)==sign && input.charAt(4)==sign && input.charAt(7)==sign)
            return sign_str;
        else if(input.charAt(2)==sign && input.charAt(5)==sign && input.charAt(8)==sign)
            return sign_str;
        else
            return "";
    }//Смотрит есть ли выигрыш и у кого он

    public static int count (char sign,String input){//Считает кол-во символов
        int count=0;
        for(int i=0;i<9;i++) {
            if (input.charAt(i) == sign)
                count++;
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str="         ";
        Print(str);

        boolean end=false;
        char player='X';

        int x;
        int y;
        do{
            //Input data
            while(true){
                do{
                    while(!sc.hasNextInt()){
                        System.out.println("You should enter numbers!");
                    }
                    x=sc.nextInt();

                    while(!sc.hasNextInt()){
                        System.out.println("You should enter numbers!");
                    }
                    y=sc.nextInt();

                    if((x<1||x>3||y<1||y>3))
                        System.out.println("Coordinates should be from 1 to 3!");
                }
                while(x<1||x>3||y<1||y>3);

                if(str.charAt((x-1)*3+(y-1))==' ')
                    break;
            }

            //Make turn
            str=str.substring(0,(x-1)*3+(y-1))+player+str.substring((x-1)*3+(y-1)+1);

            //Print field
            Print(str);

            //Switch X on O or O on X
            if(player=='X')
                player='O';
            else
                player='X';

            //Check stage of game
            int count_X=count('X',str);
            int count_O=count('O',str);
            int count__=count(' ',str);
            int d= count_X-count_O;
            String res=Check('X',str)+Check('O',str);

            if(res.equals("XO") || d>1 || d<-1){
                System.out.println("Impossible");
                end=true;
            }
            else if (res.equals("")&&count__==0){
                System.out.println("Draw");
                end=true;
            }
            else if (res.equals("X")){
                System.out.println("X wins");
                end=true;
            }
            else if (res.equals("O")){
                System.out.println("O wins");
                end=true;
            }
        }
        while (!end);
    }
}
