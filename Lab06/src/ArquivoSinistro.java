import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ArquivoSinistro implements I_Arquivo<Sinistro>{
    private File file;

    public ArquivoSinistro(){
        this.file = new File("../arquivos_csv/sinistros.csv");
        // Cria o arquivo
        try {
            file.delete();
            file.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write("ID,DATA,ENDERECO,CPF_CONDUTOR,ID_SEGURADORA\n");
            writer.close();
        } catch(Exception e) {
            System.out.println("Ocorreu um erro no gerenciamento do arquivo de Sinistro.");
        }
    }
    
    // Grava os dados do sinistro no arquivo correspondente
    public boolean gravarArquivo(Sinistro sinistro){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            String text = sinistro.getId() + "," + sinistro.getData() + "," + 
            sinistro.getEndereco() + "," + sinistro.getCondutor().getCpf() + "," + sinistro.getSeguradora().getCnpj();
            writer.write(text + '\n');
            writer.close();
            return true;
        } catch (IOException e){
            System.out.println("Ocorreu um erro na gravação do arquivo de Sinistro.");
            return false;
        }
    }

    // Método não utilizado
    public ArrayList<String> lerArquivo(){
        return null;
    }
}