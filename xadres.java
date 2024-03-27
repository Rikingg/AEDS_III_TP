import java.io.*;
import java.util.Scanner;
import java.io.RandomAccessFile.*;

class xadresx{
    private byte lapide;
    private int length;
    private byte[] data;

    xadresx(){

    }
}

class Chess {
    private byte lapide;
    private int size;
    private byte[] data;
    private short game_id;
    private String created_at;
    private String last_move_at;
    private short turns;
    private String victory_status;
    private String winner;
    private String increment_code;
    private String white_id;
    private String black_id;
    private byte qt_of_moves;
    private String moves;
    private String opening_name;


    Chess(){
    }

    public void ReadIni(String text){
        String[] textodiv;
        textodiv = text.split(",");
        game_id = Short.parseShort(textodiv[0].replace("\"",""));
        created_at = textodiv[1].replaceAll("[\"/: ]", "");
        //last_move_at = textodiv[2].replaceAll("[\"/: ]", "");
        turns = Short.parseShort(textodiv[3].replace("\"",""));
        //victory_status = textodiv[4].replace("\"","");
        winner = textodiv[5].replace("\"","");
        //increment_code = textodiv[6].replace("\"","");
        white_id = textodiv[7].replace("\"","");
        black_id = textodiv[8].replace("\"","");
        qt_of_moves = Byte.parseByte(textodiv[9].replace("\"",""));
        moves = textodiv[10].replace("\"","");
        //opening_name = textodiv[11].replace("\"","");         
        
    }

    public void create(String text, RandomAccessFile arquivo){
        ReadIni(text);
        byte[] bt;
        bt = toString().getBytes() ;
        try {
            arquivo.seek(0);
            arquivo.close();
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado\n" + e.toString());
        } catch (IOException e){
            System.err.println("Erro!\n" + e.toString());
        }
    }

    public String calcMultiString(String texto){
       String tam = "";
       int count = 0;
       String[] textodiv =  texto.split(" ");

       while (textodiv.length > count) {
        tam = tam + textodiv[count].length() + textodiv[count];
        count++;
        
       }


        return tam;
    }

    public int calcString(String texto){
        int tam = 0;

        tam = texto.length();

        return tam;

    }

    public int calcSize(){
        int temp;
        temp = 4 + 2 + created_at.getBytes().length + last_move_at.getBytes().length * 8 + 2 + winner.getBytes().length * 8+ white_id.getBytes().length * 8+ black_id.getBytes().length * 8 + 1 + moves.getBytes().length * 8 + opening_name.getBytes().length * 8;

        return temp ;
    }

    public String toString(){
        String texto;
        String temp = String.valueOf(calcSize());
        texto = lapide + temp +  game_id + created_at + last_move_at + turns +  winner  + white_id + black_id + qt_of_moves + moves + opening_name;        
        return texto;
    }

}


public class xadres {
    public static byte[] lerBase(String path){
        byte[] b = null;

        try {
            RandomAccessFile arquivo = new RandomAccessFile(path, "rw");
            arquivo.seek(0);
            arquivo.read(b);
            arquivo.close();
            
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado\n" + e.toString());
        } catch (IOException e){
            System.err.println("Erro!\n" + e.toString());
        }

        return b;
    }

    public static void main(String[] args) {
        String pathArquivo = "text.txt";
        byte[] dados = lerBase(pathArquivo);

        for(byte b : dados){
            
        }

        String nomeArquivo = "games-3-_2_.csv";
        try {
            BufferedReader arq = new BufferedReader(new InputStreamReader(new FileInputStream("\\games-3-_2_.csv")));
            
        } catch (Exception e) {
            System.err.println("Erro!\n" + e.toString());
        }
       
        String texto;
        Scanner ler = new Scanner(System.in);
        texto = ler.nextLine();
        Chess test = new Chess();
        test.ReadIni(texto);

        

        System.out.println(test.toString());

    }
}

