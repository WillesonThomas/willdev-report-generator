
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
public class Teste01 {

    public static void main(String[] args) {
        //Relatorio Padrão
        try {
            ArrayList<Pessoa> lista1 = new ArrayList<>();
            lista1.add(new Pessoa("Albert Einsten", "4000", "Físico"));
            lista1.add(new Pessoa("Issac Newton", "6000", "Físico e Matemático"));
            lista1.add(new Pessoa("Neil armstrong", "9000", "Astronauta"));
            lista1.add(new Pessoa("Willeson", "2000", "Estudante"));
            lista1.add(new Pessoa("Ruan", "1000", "Estudante"));
            RelatorioPessoa p1 = new RelatorioPessoa();
            p1.addColunaTabela("Nome", "Salario", "Profissão");
            for (int i = 0; i < lista1.size(); i++) {
                p1.addLinhaTabela(lista1.get(i).getNome(),
                        lista1.get(i).getSalario(), lista1.get(i).getProfissao());
            }
            //Relatorio pdf
            TipoRelatorio pdf1 = new RelatorioPDF(p1);
            pdf1.addGrafico(lista1, "nome", "salario", "barra", "Grafico1", "Nome x Salario");
            pdf1.addGrafico(lista1, "nome", "salario", "pizza", "Grafico2", "Nome x Salario");
            pdf1.gerarRelatorio();
            //Relatorio rtf;
            TipoRelatorio rtf1 = new RelatorioRTF(p1);
            rtf1.addGrafico(lista1, "nome", "salario", "pizza", "Grafico3", "Nome x Salario");
            rtf1.addGrafico(lista1, "nome", "salario", "barra", "Grafico4", "Nome x Salario");
            rtf1.gerarRelatorio();
            //Relatorio html
            TipoRelatorio html1 = new RelatorioHTML(p1);
            html1.addGrafico(lista1, "nome", "salario", "barra", "Grafico7", "Nome x Salario");
            html1.gerarRelatorio();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

}
