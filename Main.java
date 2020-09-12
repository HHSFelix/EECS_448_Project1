package Battleship9_3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    //javadoc Main.java;
//  List<List<node>> pc=new ArrayList();
    int pcnum;
    int usernum;
    static int map[][]=new int[105][105];
    static int bak[][]=new int[105][105];
    int count = 0;
//  List<List<node>> user=new ArrayList();
    /**
     * @param The main function
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner s=new Scanner(System.in);
        System.out.println("Player 1: Please select the number of ships you would like (1-5).");
        int num=s.nextInt();
        while(num<1||num>5)
        {
            System.out.println("Player 1: Please select the number of ships you would like (1-5).");
            int numAgain=s.nextInt();
        }
        int count=num;
        System.out.println("Player 2: Please select the number of ships you would like (1-5).");
        int num2=s.nextInt();
        while(num2<1||num2>5)
        {
            System.out.println("Player 1: Please select the number of ships you would like (1-5).");
            int num2Again=s.nextInt();
        }
        int count2=num2;
        System.out.println("Player 1 Map");
        init(num);
        System.out.println("Player 2 Map");
        printmap2();
        while(count!=0)
        {
            System.out.println("Player 1: Where would you like your ship to be placed? Enter row(1-9) then column(A-I)");
            int xEntry=s.nextInt();
            while(xEntry<1||xEntry>9)
            {
                System.out.println("Player 1: Where would you like your ship to be placed? Enter row(1-9).");
                int xEntryAgain=s.nextInt();
            }
            int yEntry=s.nextInt();
            while(yEntry<1||yEntry>9)
            {
                System.out.println("Player 1: Where would you like your ship to be placed? Enter col(A-I)");
                int yEntryAgain=s.nextInt();
            }
            count--;
        }
        while(count2!=0)
        {
            System.out.println("Player 2: Where would you like your ship to be placed? Enter row(1-9) then column(A-I)");
            int xEntry2=s.nextInt();
            while(xEntry2<1||xEntry2>9)
            {
                System.out.println("Player 2: Where would you like your ship to be placed? Enter row(1-9)");
                int xEntry2Again=s.nextInt();
            }
            int yEntry2=s.nextInt();
            while(yEntry2<1||yEntry2>9)
            {
                System.out.println("Player 2: Where would you like your ship to be placed? Enter column(A-I)");
                int yEntry2Again=s.nextInt();
            }
            count2--;
        }
        int op=1;int x,y,re,f;
        int over=gameover();
        int round = 0;
        while(over==0)
        {
            if(op==1)
            {
                round++;
                System.out.println("Round " + round + " Player 1: where would you like to guess your opponent's position? Enter row(1-9) space column(A-I).");
                x=s.nextInt();
                y=s.nextInt();
                f=map[x][y];
                re=judge(x,y,-1);
            }
            else
            {
                round++;
                // x=(int)(Math.random()*9)+1;
                // y=(int)(Math.random()*9)+1;
                // System.out.println("computer:    x="+x+" y="+y);
                // f=map[x][y];
                // re=judge(x,y,1);
                System.out.println("Round " + round + " Player 2: where would you like to guess your opponent's position? Enter row(1-9) space column(A-I).");
                x=s.nextInt();
                y=s.nextInt();
                f=map[x][y];
                re=judge(x,y,-1);
            }
            if(re==0)System.out.println("Miss");
            else if(re==1) System.out.println("Hit the target :"+Math.abs(f));
            else System.out.println("Ship "+Math.abs(f)+" has been destroyed");
            
            op=1-op;
            over=gameover();
        }
        if(over==1)System.out.println("Game over! Player 1 Wins");
        if(over==-1)System.out.println("Game over! Player 2 Wins");
    }
    static int gameover()
    {
        int a=0,b=0;
        for(int i=1;i<=9;i++)
        {
            for(int j=1;j<=9;j++)
            {
                if(map[i][j]>0)a=1;
                if(map[i][j]<0)b=1;
            }
        }
        if(a==1&&b==0)return 1;
        else if(a==0&&b==1)return -1;
        return 0;
    }
    static void init(int x)
    {
        for(int i=x;i>=1;i--)random_boat(i,1);
        for(int i=x;i>=1;i--)random_boat(i,-1);
        printmap();
    }
    static void printmap()
    {
        for(int i=1;i<=9;i++)
        {
            for(int j=1;j<=9;j++)
            {
                if(map[i][j]<0)System.out.print(" 0 ");
                else System.out.print(String.format("%2d ", map[i][j]));
            }
            System.out.println();
        }
    }
    static void printmap2()
    {
        for(int i=1;i<=9;i++)
        {
            for(int j=1;j<=9;j++)
            {
                if(map[i][j]>0)System.out.print(" 0 ");
                else System.out.print(String.format("%2d ", map[i][j]));
            }
            System.out.println();
        }
    }
    static int find(int x)
    {
        for(int i=1;i<=9;i++)
        {
            for(int j=1;j<=9;j++)
            {
                if(map[i][j]==x)return 1;
            }
            
        }
        return 0;
    }
    static int judge(int x,int y,int  type)
    {
//      System.out.println("x="+x+"y="+y+"type="+type+"  "+map[x][y]);
        if(map[x][y]*type>0)
        {
            int c=map[x][y];
            bak[x][y]=map[x][y];
            map[x][y]=0;
            if(find(c)==1)return 1;
            else return 2;
        }
        return 0;
    }
    static void random_boat(int len,int type)
    {
        int dir,x,y,num,flag;
        flag=0;
//      int c=0;
        while(flag==0)
        {
//          c++;
//          if(c>20)break;
            flag=1;
            dir=(int)(Math.random()*2);
            dir=1;
            if(dir==1)
            {
                x=(int)(Math.random()*9)+1;
                y=(int)(Math.random()*(9-len+1))+1;
                for(int i=0;i<len;i++)
                {
                    if(type>0&&x<5||type<0&&x>5)
                    {
                        flag=0;
                        break;
                    }
                    if(map[x][y+i]!=0)
                    {
                        flag=0;
                        break;
                    }
                }
                if(flag==0)continue;
                for(int i=0;i<len;i++)
                {
                    map[x][y+i]=len*type;
                }
            }
            // else
            // {
            //  x=(int)(Math.random()*(9-len+1))+1;
            //  y=(int)(Math.random()*9)+1;
            //  for(int i=0;i<len;i++)
            //  {
            //      if(type>0&&x<4||type<0&&(x+len)>5)
            //      {
            //          flag=0;
            //          break;
            //      }
            //      if(map[x+i][y]!=0)
            //      {
            //          flag=0;
            //          break;
            //      }
            //  }
            //  if(flag==0)continue;
            //  for(int i=0;i<len;i++)
            //  {
            //      map[x+i][y]=len*type;
            //  }
            // }
        }
//      return pos;
    }
    
}