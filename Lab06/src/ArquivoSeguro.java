import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ArquivoSeguro implements I_Arquivo<Seguro>{
    private File file;

    public ArquivoSeguro(){
        this.file = new File("../arquivos_csv/seguros.csv");
        // Cria o arquivo
        try {
            file.delete();
            file.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write("ID,DATA_INICIO,DATA_FIM,NOME_SEGURADORA,LISTA_ID_SINISTROS,LISTA_CPF_CONDUTORES,VALOR_MENSAL\n");
            writer.close();
        } catch(Exception e) {
            System.out.println("Ocorreu um erro no gerenciamento do arquivo de Seguro.");
        }
    }
    
    // Grava os dados do seguro no arquivo correspondente
    public boolean gravarArquivo(Seguro seguro){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            String text = seguro.getId() + "," + seguro.getDataInicio() + "," + 
            seguro.getDataFim() + "," + seguro.getSeguradora().getNome() + ",";
            for(Sinistro sinistro : seguro.getListaSinistros())
                text += sinistro.getId() + "  ";
            text += ",";
            for(Condutor condutor : seguro.getListaCondutores())
                text += condutor.getCpf() + "  ";            
            text += "," + seguro.getValorMensal();
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