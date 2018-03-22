
import br.udesc.ceavi.willesonruan.RelatorioHTML;
import br.udesc.ceavi.willesonruan.RelatorioPDF;
import br.udesc.ceavi.willesonruan.RelatorioRTF;
import br.udesc.ceavi.willesonruan.TipoRelatorio;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Font;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Willeson Thomas
 */
public class Teste02 {

    public static void main(String[] args) throws BadElementException, IOException,
            FileNotFoundException, ClassNotFoundException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {

        ArrayList<Carro> lista2 = new ArrayList<>();//Adiciona elementos ao Relatorio para gerar Graficos
        lista2.add(new Carro("Carro A", "40000"));
        lista2.add(new Carro("Carro B", "80000"));
        lista2.add(new Carro("Carro C", "100000"));
        RelatorioCarro p1 = new RelatorioCarro();
        p1.addColunaTabela("Nome", "Preco");
        for (int i = 0; i < lista2.size(); i++) {
            p1.addLinhaTabela(lista2.get(i).getNome(), lista2.get(i).getPreco());
        }
        p1.setNomeArquivo("Relatorio - Teste 02 (Carro 02)");
        p1.setCabecalho("Cabecalho - Teste 02 (Carro 02)");
        p1.setData("29/12/2017");
        p1.setTamanhoPagina("A4");
        p1.setRodape("Rodape - Teste 02 (Carro 02)");
        p1.setImagemCabecalho("udesc.jpg");
        p1.setImagemRodape("udesc.jpg");

        //Aqui tem-se o estilo de PDF E RTF.
        p1.addStyleCabecalho("Courier", "15", Color.RED, Color.BLACK, Color.YELLOW, Font.BOLD);
        p1.addStyleRodape("Courier", "15", Color.BLUE, Color.BLACK, Color.PINK, Font.BOLD);
        p1.addStyleTabelaColuna("Courier", "10", Color.RED, Font.UNDERLINE);
        p1.addStyleTabelaLinha("Courier", "10", Color.BLUE, Font.NORMAL);

        TipoRelatorio pdf2 = new RelatorioPDF(p1);
        pdf2.addGrafico(lista2, "nome", "preco", "barra", "Grafico82", "Nome x Preco");
        pdf2.addGrafico(lista2, "nome", "preco", "barra", "Grafico28", "Nome x Preco");
        pdf2.addGrafico(lista2, "nome", "preco", "pizza", "Grafico90", "Nome x Preco");
        pdf2.addGrafico(lista2, "nome", "preco", "pizza", "Grafico90", "Nome x Preco");
        pdf2.gerarRelatorio();

        //Relatorio rtf;
        TipoRelatorio rtf2 = new RelatorioRTF(p1);
        rtf2.addGrafico(lista2, "nome", "preco", "pizza", "Grafico30", "Nome x Preco");
        rtf2.addGrafico(lista2, "nome", "preco", "barra", "Grafico31", "Nome x Preco");
        rtf2.addGrafico(lista2, "nome", "preco", "barra", "Grafico32", "Nome x Preco");
        rtf2.addGrafico(lista2, "nome", "preco", "barra", "Grafico32", "Nome x Preco");
        rtf2.gerarRelatorio();

        p1.addStyleCabecalhoHTML("Cooper", "30", "blue", "5px solid black", "yellow", "bold", "underline");
        p1.addStyleRodapeHTML("Cooper", "30", "red", "5px solid black", "grey", "bold", "");
        p1.addStyleTabelaColunaHTML("Arial", "20", "blue", "", "underline");
        p1.addStyleTabelaLinhaHTML("Arial", "20", "red", "", "");

        //Relatorio html
        TipoRelatorio html2 = new RelatorioHTML(p1);
        html2.addGrafico(lista2, "nome", "preco", "barra", "Grafico34", "Nome x Preco");
        html2.addGrafico(lista2, "nome", "preco", "pizza", "Grafico35", "Nome x Preco");
        html2.addGrafico(lista2, "nome", "preco", "barra", "Grafico36", "Nome x Preco");
        html2.gerarRelatorio();

    }
}
