import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class ArquivoVeiculo implements I_Arquivo<Veiculo>{
    private File file;

    public ArquivoVeiculo(){
        this.file = new File("../arquivos_csv/veiculos.csv");
    }
    
    // Método não utilizado
    public boolean gravarArquivo(Veiculo veiculo){
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
            System.out.println("Ocorreu um erro na leitura do arquivo de Veiculo.");
            return null;
        }
    }
}