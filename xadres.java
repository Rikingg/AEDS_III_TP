import java.io.*;
import java.io.RandomAccessFile.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.util.Random;
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
    private short turns;
    private String victory_status;
    private String winner;
    private String white_id;
    private String black_id;
    private byte qt_of_moves;
    private String moves;
    


    Chess(){
    }

    public void ReadIni(String text, RandomAccessFile arquivoCsv){
        String[] textodiv;
        textodiv = text.split(",");
        game_id = Short.parseShort(textodiv[0].replace("\"",""));
        if (game_id == 0) {
            try{
                int temp = 0;
                arquivoCsv.seek(0);
                temp = arquivoCsv.readInt();
                temp = temp + 1;
                String tempp = "" + temp;
                game_id = Short.parseShort(tempp);
            }catch(Exception e){

            }
            
        }
        try{
            arquivoCsv.seek(0);
            arquivoCsv.writeInt(game_id);
        }catch(Exception e){
            System.out.println("erro");
        }

        String[] data;
        data = textodiv[1].split(" ");
        created_at = data[0].replace("\"","");
        turns = Short.parseShort(textodiv[2].replace("\"",""));
        victory_status = textodiv[3].replace("\"","");
        winner = textodiv[4].replace("\"","");
        white_id = textodiv[5].replace("\"","");
        black_id = textodiv[6].replace("\"","");
        qt_of_moves = Byte.parseByte(textodiv[7].replace("\"",""));
        moves = textodiv[8].replace("\"","");
    }

    public void create(String text ,RandomAccessFile arquivoCsv){
        
        try{ 
            ReadIni(text,arquivoCsv);
            byte[] bt;
            //bt = this.ToString();         
            arquivoCsv.seek(0);
            arquivoCsv.readInt();
            arquivoCsv.seek(arquivoCsv.length());
            
            arquivoCsv.writeByte(lapide);
            
            arquivoCsv.writeInt(calcSize());
            
            arquivoCsv.writeInt(game_id);
            
            arquivoCsv.writeInt(calcString(created_at));
            arquivoCsv.write(created_at.getBytes());

            arquivoCsv.writeInt(turns);

            arquivoCsv.writeInt(calcString(victory_status));
            arquivoCsv.write(victory_status.getBytes());

            arquivoCsv.writeInt(calcString(winner));
            arquivoCsv.write(winner.getBytes());

            arquivoCsv.writeInt(calcString(white_id));
            arquivoCsv.write(white_id.getBytes());

            arquivoCsv.writeInt(calcString(black_id));
            arquivoCsv.write(black_id.getBytes());

            arquivoCsv.writeInt(qt_of_moves);            
            
            arquivoCsv.write(calcMultiString(moves).getBytes());
            

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
        tam = tam + textodiv[count].getBytes().length + textodiv[count];
        count++;
        
       }


        return tam;
    }

    public int calcString(String texto){
        int tam = 0;

        tam = texto.getBytes().length;

        return tam;

    }

    public int calcSize(){
        int temp;
        temp = 4 + 4 + created_at.getBytes().length + 4 + 4 + victory_status.getBytes().length +  4 + winner.getBytes().length + 4 + white_id.getBytes().length + 4 + black_id.getBytes().length + 4 + calcMultiString(moves).getBytes().length;

        return temp ;
    }

   /* public byte[] ToString(){
        String texto;
        String temp = "";
        int tam = Integer.parseInt(String.valueOf(calcSize()));
        if (tam < 10 ){
            temp = "000" + tam;
        } else if (tam < 100 ){
            temp = "00" + tam;
        }else if (tam < 1000 ){
            temp = "0" + tam;
        }      
        texto = lapide + temp +  game_id + calcString(created_at) +created_at +  turns  + calcString(victory_status) + victory_status + calcString(winner) + winner  + calcString(white_id) + white_id + calcString(black_id) + black_id + qt_of_moves + calcMultiString(moves);
        byte[] tt = texto.getBytes();        
        return tt;
    }*/

   public void Read(int id, RandomAccessFile arquivoCsv) {
       try {
            int idArquivo = 0;
            arquivoCsv.seek(0);
            arquivoCsv.readInt();
            int tamanho = 0;
            for(long i=0; i<arquivoCsv.length(); ){
                if(id == idArquivo){
                    System.out.println("Id do arquivo: " + idArquivo);
                    int count = arquivoCsv.readInt();
                    byte[] b = new byte[count];
                    System.out.print("Criado em: ");
                    arquivoCsv.readFully(b);
                    System.out.println(new String(b, StandardCharsets.UTF_8));
                    System.out.println("Quantidade de Turnos: " + arquivoCsv.readInt());
                    count = arquivoCsv.readInt();
                    b = new byte[count];
                    System.out.print("Status de Vitoria: ");
                    arquivoCsv.readFully(b);
                    System.out.println(new String(b, StandardCharsets.UTF_8));
                    count = arquivoCsv.readInt();
                    b = new byte[count];
                    System.out.print("Vencedor : ");
                    arquivoCsv.readFully(b);
                    System.out.println(new String(b, StandardCharsets.UTF_8));
                    count = arquivoCsv.readInt();
                    b = new byte[count];
                    System.out.print("White Id: ");
                    arquivoCsv.readFully(b);
                    System.out.println(new String(b, StandardCharsets.UTF_8));
                    count = arquivoCsv.readInt();
                    b = new byte[count];
                    System.out.print("Black Id: ");
                    arquivoCsv.readFully(b);
                    System.out.println(new String(b, StandardCharsets.UTF_8));
                    int qt = arquivoCsv.readInt();
                    System.out.println("Quantidade de Movimentos: " + qt );
                    
                    System.out.print("Moves: ");

                    for (int j = 0; j < qt ; j++) {
                        b = new byte[1];
                        arquivoCsv.readFully(b);
                        count = Integer.parseInt((new String(b, StandardCharsets.UTF_8)));
                        b = new byte[count];
                        arquivoCsv.readFully(b);
                        System.out.print(new String(b, StandardCharsets.UTF_8));
                        System.out.print(" ");
                        
                    }
                    break;
                }else{
                    if(idArquivo != 0){
                        arquivoCsv.seek(arquivoCsv.getFilePointer() + tamanho - 4);
                    }
                }
                arquivoCsv.readByte();
                tamanho = arquivoCsv.readInt();
                
                idArquivo = arquivoCsv.readInt();
            }
            
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void Update(int id, RandomAccessFile arquivoCsv){
        try {
            
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public String formatString(){
        String info = "\"";

        return info;
    }

}


public class xadres {
    static Scanner scanner = new Scanner(System.in);
      
    public static void lerBase(String path, String baseDeDados){
  
        try {
            File f = new File(baseDeDados);
            Chess data = new Chess();
            RandomAccessFile arquivo = new RandomAccessFile(path, "r");
            RandomAccessFile arquivoCsv = null;
            
            if (f.createNewFile()) {
                arquivoCsv = new RandomAccessFile(baseDeDados, "rw");
                arquivoCsv.writeInt(0);

                for(long i=0; i < 5; i++){
                    data.create(arquivo.readLine(), arquivoCsv);
                }
            }
            
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado\n" + e.toString());
        } catch (IOException e){
            System.err.println("Erro!\n" + e.toString());
        }
    }

    public static void crudCreate(String baseDeDados){
        try {
            Chess data = new Chess();
            RandomAccessFile arquivoCsv = new RandomAccessFile(baseDeDados, "rw");

            String info = "\"";
            String winner;

            System.out.println("Data do jogo (d/M/yy): ");
            info = info + scanner.nextLine() +"\"" + "," + "\"";
            
            System.out.println("Total de turnos do jogo: ");
            info = info + scanner.nextLine() +"\"" + "," + "\"";

            System.out.println("Condição da vitória: ");
            info = info + scanner.nextLine() +"\"" + "," + "\"";

            System.out.println("Quem ganhou? ('white' ou 'black'): ");
            winner = scanner.nextLine().toLowerCase();
            while(!winner.equals("black") || !winner.equals("white")){
                System.out.println("Favor digitar apenas 'white' ou 'black': ");
                winner = scanner.nextLine();
            }
            info = info + winner +"\"" + "," + "\"";

            System.out.println("Nome do jogador do tabuleiro branco: ");
            info = info + scanner.nextLine() +"\"" + "," + "\"";

            System.out.println("Nome do jogador do tabuleiro negro: ");
            info = info + scanner.nextLine() +"\"" + "," +"\"";

            System.out.println("Quantidade de movimentos: ");
            info = info + scanner.nextLine() +"\"" + ","+ "\"";

            System.out.println("Cite todos movimentos " + 
                            "efetuados na partida, separados por espaços: ");
            info = info + scanner.nextLine() +"\"";


            data.create(info, arquivoCsv);
            
            arquivoCsv.close();
            scanner.close();
        } catch (Exception e) {
            System.err.println("Erro!\n" + e.toString());
        }
    }

    public static void crudRead(int id, String baseDeDados){
        try {
            Chess data = new Chess();
            RandomAccessFile arquivo = new RandomAccessFile(baseDeDados, "rw");

            data.Read(id, arquivo);
            
            arquivo.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public static void crudUpdate(int id, String baseDeDados){
        try {
            Chess data = new Chess();
            RandomAccessFile arquivo = new RandomAccessFile(baseDeDados, "rw");

            data.Read(id, arquivo);
            
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
            System.out.println("\nDigite o número da operação: ");
            controle = scanner.nextInt();
            switch (controle) {
                case 1:
                    //create
                    crudCreate(nomeBaseDeDados);
                break;

                case 2:
                    //read
                    System.out.println("Digite o ID do qual deseja pesquisar: ");
                    int id = scanner.nextInt();
                    crudRead(id, nomeBaseDeDados);
                break;

                case 3:
                    //update
                    crudCreate(nomeBaseDeDados);
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
        scanner.close();
    }
}

