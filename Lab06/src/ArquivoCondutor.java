import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class ArquivoCondutor implements I_Arquivo<Condutor>{
    private File file;

    public ArquivoCondutor(){
        this.file = new File("arquivos_csv/condutores.csv");
    }
    
    // Método não utilizado
    public boolean gravarArquivo(Condutor condutor){
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
            System.out.println("Ocorreu um erro na leitura do arquivo de Condutor.");
            return null;
        }
    }
}