import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class ArquivoClientePJ implements I_Arquivo<ClientePJ>{
    private File file;

    public ArquivoClientePJ(){
        this.file = new File("arquivos_csv/clientesPJ.csv");
    }
    
    // Método não utilizado
    public boolean gravarArquivo(ClientePJ cliente){
        return false;
    }

    // Lê o arquivo e retorna o conteúdo
    public ArrayList<String> lerArquivo(){
        try {
            Scanner scan = new Scanner(file);
            scan.nextLine();
            ArrayList<String> res = new ArrayList<String>();
            while(scan.hasNextLine())
                res.add(scan.nextLine());
            scan.close();
            return res;
        } catch (IOException e) {
            System.out.println("Ocorreu um erro na leitura do arquivo de ClientePJ.");
            return null;
        }
    }
}