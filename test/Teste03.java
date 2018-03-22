
import br.udesc.ceavi.willesonruan.Container;
import br.udesc.ceavi.willesonruan.Relatorio;
import br.udesc.ceavi.willesonruan.RelatorioHTML;
import br.udesc.ceavi.willesonruan.RelatorioPDF;
import br.udesc.ceavi.willesonruan.RelatorioRTF;
import br.udesc.ceavi.willesonruan.TipoRelatorio;
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
public class Teste03 {
    public static void main(String[] args) throws Exception {
        try {
            Container container = new Container("configuracao.xml");            
            ArrayList<Pessoa> lista1 = new ArrayList<>();
            lista1.add(new Pessoa("Albert Einsten", "4000", "Físico"));
            lista1.add(new Pessoa("Issac Newton", "6000", "Físico e Matemático"));
            lista1.add(new Pessoa("Neil armstrong", "9000", "Astronauta"));
            lista1.add(new Pessoa("Willeson", "2000", "Estudante"));
            lista1.add(new Pessoa("Ruan", "1000", "Estudante"));            
            
            TipoRelatorio pdf1 = container.criarRelatorio("600");
            TipoRelatorio rtf1 = container.criarRelatorio("400");
            TipoRelatorio html1 = container.criarRelatorio("1000");
            
            RelatorioPessoa p1 = pdf1.getRel();                      
            RelatorioPessoa p2 = rtf1.getRel();
            RelatorioPessoa p3 = html1.getRel();           
            p1.addColunaTabela("Nome", "Salario", "Profissão");
            for (int i = 0; i < lista1.size(); i++) {
                p1.addLinhaTabela(lista1.get(i).getNome(), 
                lista1.get(i).getSalario(), lista1.get(i).getProfissao());
            }
            
            pdf1.addGrafico(lista1, "nome", "salario", "pizza", "Grafico39", "Nome x Salario");
            pdf1.addGrafico(lista1, "nome", "salario", "barra", "Grafico40", "Nome x Salario");
            pdf1.gerarRelatorio();
            rtf1.addGrafico(lista1, "nome", "salario", "pizza", "Grafico39", "Nome x Salario");
            rtf1.addGrafico(lista1, "nome", "salario", "barra", "Grafico40", "Nome x Salario");
            rtf1.addGrafico(lista1, "nome", "salario", "barra", "Grafico41", "Nome x Salario");
            rtf1.gerarRelatorio();
            html1.addGrafico(lista1, "nome", "salario", "barra", "Grafico43", "Nome x Salario");
            html1.gerarRelatorio();
            
            
            ArrayList<Carro> lista2 = new ArrayList<>();//Adiciona elementos ao Relatorio para gerar Graficos
            lista2.add(new Carro("Carro A", "40000"));
            lista2.add(new Carro("Carro B", "80000"));
            lista2.add(new Carro("Carro C", "100000"));
            
            TipoRelatorio pdf2 = container.criarRelatorio("500");
            TipoRelatorio rtf2 = container.criarRelatorio("200");
            TipoRelatorio html2 = container.criarRelatorio("300");
            RelatorioCarro c1 = pdf2.getRel();                      
            RelatorioCarro c2 = rtf2.getRel();
            RelatorioCarro c3 = html2.getRel();

            
            c1.addColunaTabela("Nome", "Preco");
            for (int i = 0; i < lista2.size(); i++) {
                c1.addLinhaTabela(lista2.get(i).getNome(), lista2.get(i).getPreco());
            }
            //Relatorio pdf
            pdf2.addGrafico(lista2, "nome", "preco", "barra", "Grafico46", "Nome x Preco");
            pdf2.addGrafico(lista2, "nome", "preco", "pizza", "Grafico47", "Nome x Preco");
            pdf2.gerarRelatorio();

            //Relatorio rtf;
            rtf2.addGrafico(lista2, "nome", "preco", "pizza", "Grafico48", "Nome x Preco");
            rtf2.addGrafico(lista2, "nome", "preco", "barra", "Grafico49", "Nome x Preco");
            rtf2.addGrafico(lista2, "nome", "preco", "barra", "Grafico50", "Nome x Preco");
            rtf2.addGrafico(lista2, "nome", "preco", "pizza", "Grafico51", "Nome x Preco");
            rtf2.gerarRelatorio();

            //Relatorio html
            html2.addGrafico(lista2, "nome", "preco", "barra", "Grafico52", "Nome x Preco");
            html2.addGrafico(lista2, "nome", "preco", "pizza", "Grafico53", "Nome x Preco");
            html2.addGrafico(lista2, "nome", "preco", "barra", "Grafico54", "Nome x Preco");
            html2.gerarRelatorio();


        } catch (com.lowagie.text.DocumentException de) {
            System.err.println(de.getMessage());
        }

    }
}
