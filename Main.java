import java.util.*;
import java.io.*;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  

class item implements Comparable<item>{

    public String name;
    public int price;

    public item(){
        super();
    }

    public item(String str,int n){

        name = str;
        price = n;
    }

    public int compareTo(item that){

        if(this.price < that.price) return -1;
        if(this.price > that.price) return 1;
        return 0;
    }

    public String toString(){

        return this.name+" : "+this.price;
    }
}



public class Main{

    public static void main(String[] args){

        try {

            int m = -1;
            String[] arr;

            File myObj = new File("sample_input.txt");
            Scanner myReader = new Scanner(myObj);

            int count = 0;
            ArrayList<item> items = new ArrayList<item>();

            while (myReader.hasNextLine()) {

              String data = myReader.nextLine();
              arr = data.split(": ");

              if(count == 0){
                m = Integer.parseInt(arr[1]);
               //  System.out.println("m is : "+m);
                count++;
                continue;
              }
               
              if(arr.length <= 1) continue;

              items.add(new item(arr[0],Integer.parseInt(arr[1])));
            }
            
            Collections.sort(items);
            // System.out.println(items);
            myReader.close();

            int index = 0;
            int min_diff = Integer.MAX_VALUE;
            int diff;

            for(int i = 0;i < items.size() - m;i++){

                diff = items.get(i+m-1).price - items.get(i).price;
                
                if(diff < min_diff){

                    min_diff = diff;
                    index = i;
                }
            }

            try {
                
                FileWriter myWriter = new FileWriter("sample_output.txt");
                myWriter.write("The goodies selected for distribution are: \n");

                for(int i = index;i < index + m;i++){
                    myWriter.write(items.get(i).name+" : "+items.get(i).price+"\n");
                }

                myWriter.write("\n And the difference between the chosen goodie with highest price and the lowest price is "+min_diff);
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
              } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
              }

          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    }

}
