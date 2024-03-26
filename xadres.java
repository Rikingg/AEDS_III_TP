import java.io.*;
import java.util.Scanner;

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

    public void Read(String text){
        //Tratamento dos Dados
        String[] textodiv;
        textodiv = text.split(",");
        game_id = Short.parseShort(textodiv[0].replace("\"",""));
        created_at = textodiv[1].replace("\"","");
        last_move_at = textodiv[2].replace("\"","");
        turns = Short.parseShort(textodiv[3].replace("\"",""));
        victory_status = textodiv[4].replace("\"","");
        winner = textodiv[5].replace("\"","");
        increment_code = textodiv[6].replace("\"","");
        white_id = textodiv[7].replace("\"","");
        black_id = textodiv[8].replace("\"","");
        qt_of_moves = Byte.parseByte(textodiv[9].replace("\"",""));
        moves = textodiv[10].replace("\"","");
        opening_name = textodiv[11].replace("\"",""); 

    }

    public String toString(){
        String texto;
        texto = "o id é: " + game_id +" Turnos: " + turns;
        return texto;
    }

}


public class xadres {
    public static byte[] lerBase(String path){
        byte[] b = null;

        try {
            RandomAccessFile arquivo = new RandomAccessFile(path, "r");
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
        String pathArquivo = "games-3-_2_.csv";
        byte[] dados = lerBase(pathArquivo);

        for(byte b : dados){
            
        }

        //String nomeArquivo = "./games-3-_2_.csv";
        //BufferedReader arq = new BufferedReader(new InputStreamReader(new FileInputStream("\\games-3-_2_.csv")));
        String texto;
        Scanner ler = new Scanner(System.in);
        texto = ler.nextLine();
        Chess test = new Chess();
        test.Read(texto);

        

        System.out.println(test.toString());

    }
}

