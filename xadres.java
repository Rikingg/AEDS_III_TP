import java.io.*;
import java.util.Scanner;
import java.io.RandomAccessFile.*;
import java.nio.file.FileAlreadyExistsException;

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
    private short turns;
    private String victory_status;
    private String winner;
    private String white_id;
    private String black_id;
    private byte qt_of_moves;
    private String moves;
    


    Chess(){
    }

    public void ReadIni(String text){
        String[] textodiv;
        textodiv = text.split(",");
        game_id = Short.parseShort(textodiv[0].replace("\"",""));
        created_at = textodiv[1].replaceAll("[\"/: ]", "");
        turns = Short.parseShort(textodiv[3].replace("\"",""));
        victory_status = textodiv[4].replace("\"","");
        winner = textodiv[5].replace("\"","");
        white_id = textodiv[7].replace("\"","");
        black_id = textodiv[8].replace("\"","");
        qt_of_moves = Byte.parseByte(textodiv[9].replace("\"",""));
        moves = textodiv[10].replace("\"","");      
        
    }

    public void create(String text ,RandomAccessFile arquivoCsv){
        
        try{ 
            ReadIni(text);
            byte[] bt;
            bt = toString().getBytes();            
            arquivoCsv.seek(0);
            arquivoCsv.readInt();
            arquivoCsv.seek(arquivoCsv.length());
            arquivoCsv.write(bt);
            arquivoCsv.close();
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
        temp = 4 + 2 + created_at.getBytes().length + 2 + winner.getBytes().length + white_id.getBytes().length + black_id.getBytes().length + 1 + moves.getBytes().length  ;

        return temp ;
    }

    public String toString(){
        String texto;
        String temp = String.valueOf(calcSize());
        texto = lapide + temp +  game_id + calcString(created_at) +created_at + turns + calcString(winner) + winner  + calcString(white_id) + white_id + calcString(black_id) + black_id + qt_of_moves + calcMultiString(moves);        
        return texto;
    }

    public void Read(int id) {
        
    }

}


public class xadres {
    public static void lerBase(String path, String baseDeDados){
  
        try {
            File f = new File(baseDeDados);
            Chess data = new Chess();
            RandomAccessFile arquivo = new RandomAccessFile(path, "r");
            RandomAccessFile arquivoCsv = null;
            
            if (!f.exists()) {
                arquivoCsv = new RandomAccessFile(baseDeDados, "rw");
                arquivoCsv.writeInt(0);

                for(long i=0; i<arquivo.length(); i++){
                    data.create(arquivo.readLine(), arquivoCsv);
                }
            }

            arquivo.close();
            
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado\n" + e.toString());
        } catch (IOException e){
            System.err.println("Erro!\n" + e.toString());
        }
    }

    public static void crudCreate(String path){
        try {
            RandomAccessFile arquivo = new RandomAccessFile(path, "rw");

            
            arquivo.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public static void main(String[] args) {       
        String nomeArquivo = "games-3-_2_.csv";
        String nomeBaseDeDados = "base\\meuarquivo.txt";
        lerBase(nomeArquivo, nomeBaseDeDados);

        int controle = 0;
        System.out.println();
        while(controle != 5){
            switch (controle) {
                case 1:
                    //create
                break;

                case 2:
                    //read
                break;

                case 3:
                    //update
                break;

                case 4:
                    //delete
                break;

                case 5:
                    //sair
                break; 
            
                default:
                    System.out.println("Opção ínvalida.");
                break;
            }
        }
        

        System.out.println(test.toString());

    }
}

