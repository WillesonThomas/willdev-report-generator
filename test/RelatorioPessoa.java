
import br.udesc.ceavi.willesonruan.Relatorio;




/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Willeson Thomas
 */
public class RelatorioPessoa extends Relatorio {

    public RelatorioPessoa() {
    } 

    public RelatorioPessoa(String nomeArquivo, String cabecalho, 
    String data, String rodape, String fontTabelaCabecalho, 
    String fontSizeTabelaCabecalho, String fontTabelaLinha, 
    String fontSizeTabelaLinha, String fontCabecalho, 
    String fontSizeCabecalho, String fontRodape, 
    String fontSizeRodape) {
    
     super(nomeArquivo, cabecalho, data, rodape, fontTabelaCabecalho,
     fontSizeTabelaCabecalho, fontTabelaLinha, fontSizeTabelaLinha, 
     fontCabecalho, fontSizeCabecalho, fontRodape, fontSizeRodape);
    }
     
}
