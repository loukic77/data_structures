import org.w3c.dom.ls.LSOutput;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class NetProfit {
    public static void main(String[] args) throws FileNotFoundException {

        int temp = 0; // (des meta) thn xrhsimopoiw gia na elegxw an to palaiotero bunch metoxwn eparkei apo mono tou gia thn pwlhsh
        // kai gia na meinei sthn mnhmh to ypoloipo amount apo metoxes ekeinh thn stigmh gia melodikh pwlhsh


        try {   // elegxw katarxas an borw na akoiksw to arxeio
            BufferedReader reader = new BufferedReader(new FileReader(args[0]));  //anoigw to arxeio
            DoubleQueue<Integer> amounts = new DoubleQueueImpl<>(); // exw mia oura gia tis posothtes twn metoxwn
            DoubleQueue<Double> prices = new DoubleQueueImpl<>();  // exw mia allh oura gia tis times twn metoxwn kai kinoudai kapws parallhla

            String line;
            while ((line = reader.readLine()) !=null){ // elegxw na exei alles grammes to file mou kai an exei thn bazw sto string "line"
                String parts[] = line.split(" ");   // xwrizw ta epimerous part ths seiras(buy//sell, posothta, leksh "price", actual price)

                System.out.println(line);
                if (parts[0].equals("buy")){    // an h prwth leksh ths seiras einai buy topothetw price,amount stous oures tous.
                    amounts.put(Integer.parseInt(parts[1]));
                    prices.put(Double.parseDouble(parts[3]));
                }
                else if(parts[0].equals("sell")){
                    int withdraw_amount = Integer.parseInt(parts[1]); // h posothta pou thelei o xrhsths na poulhsei
                    double withdraw_price = Double.parseDouble(parts[3]); // h timh pou thelei o xrhsths na poulhsei
                    double esoda = withdraw_price*withdraw_amount;  // ti esoda tha exei apo thn pwlhsh thn dedomenh xronikh stigmh

                    double eksoda=0;


                    
                    if (temp<=0) {
                        temp = amounts.get();

                    }
                    if (temp>withdraw_amount){    // an to ypoloipo twn metoxwn apo to palaiotero bunch arkei asxolhsou mono me auto
                        eksoda += withdraw_amount*prices.peek();     //xrhsimopoiw peek anti gia get epeidh exw akoma metoxes tou palaioterou chunk kai thelw na kraths thn timh tous
                        temp -= withdraw_amount;    // meiwnetai to palaiotero bunch alla menei sthn mnhmh

                    }
                    else if (temp==withdraw_amount){
                        eksoda += withdraw_amount*prices.get();      //xrhsimopoiw edw get giati teleiwse to bunch
                        temp =0;
                    }
                    else {
                        while (temp<withdraw_amount){
                            if (amounts.isEmpty()){    // elegxos an yparxoun alles metoxes sthn periptwsh pou den ftanoun me to prohgoumeno bunch
                                System.out.println("Den exete eparkeis metoxes na poulhsete!");
                                return;
                            }
                            eksoda += temp*prices.get();
                            withdraw_amount -=temp;

                            temp = amounts.get();   // teleiwse to prohgoumeno bunch kai pernw to epomeno
                            if (temp>withdraw_amount){          //paromoia diadhkasia me pio panw
                                eksoda += withdraw_amount*prices.peek();
                                temp -= withdraw_amount;
                                break;
                            }
                            else if (temp==withdraw_amount){
                                eksoda += withdraw_amount*prices.get();      //xrhsimopoiw edw get giati teleiwse to bunch
                                temp =0;
                                break;
                            }
                        
                    }
                    }
                    double kerdos  = esoda - eksoda;
                    //System.out.println(esoda);
                    //System.out.println(eksoda);
                    System.out.println("Ta kerdh ths pwlhshs einai: " + kerdos);


                    

                }
            }
            reader.close();
        }
        catch (IOException err){   // periptwsh poou den borw na anoiksw to arxeio
            System.out.println("Den brethike to arxeio gia anagnwsh");
        }

    }
}
