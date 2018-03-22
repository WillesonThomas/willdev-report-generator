/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceavi.willesonruan;

import java.io.FileWriter;
import java.util.ArrayList;

/**
 *
 * @author Willeson Thomas
 */
public class RelatorioHTML extends TipoRelatorio {

    private String paginaRelatorio;

    //Propriedades html.
    private final String htmlOpen = "<!DOCTYPE html>\n";
    private final String htmlClose = "</html>";

    private final String language = "<html lang=\"pt-br\">\n";
    private final String meta = "<meta charset=\"UTF-8\">\n";

    private final String headOpen = "<head>";
    private final String headClose = "</head>";

    private final String titleOpen = "<title>";
    private final String titleClose = "</title>";

    private final String bodyOpen = "<body>";
    private final String bodyClose = "</body>";

    private String tabela;
    private boolean verificaGrafico;
    public boolean verificaTabela;
    private String grafico;
    private Relatorio rel;

    public RelatorioHTML(Relatorio relatorio) {
        this.rel = relatorio;
    }
    
    public String getTabela() {
        return tabela;
    }

    public void setTabela(String tabela) {
        this.tabela = tabela;
    }

    public String getPaginaRelatorio() {
        return paginaRelatorio;
    }

    public void setPaginaRelatorio(String paginaRelatorio) {
        this.paginaRelatorio = paginaRelatorio;
    }

    public boolean isVerificaGrafico() {
        return verificaGrafico;
    }

    public void setVerificaGrafico(boolean verificaGrafico) {
        this.verificaGrafico = verificaGrafico;
    }

    public boolean isVerificaTabela() {
        return verificaTabela;
    }

    public void setVerificaTabela(boolean verificaTabela) {
        this.verificaTabela = verificaTabela;
    }

    public String getGrafico() {
        return grafico;
    }

    public void setGrafico(String grafico) {
        this.grafico = grafico;
    }

    @Override
    public void gerarRelatorio() {
        String paginaHTML = "";
        if (rel.addTabela() == true) {
            addTabela();

            if (isVerificaGrafico() == false) {
                paginaHTML = htmlOpen + language + headOpen + meta + headClose + titleOpen + titleClose + headClose + bodyOpen + addCabecalho() + getTabela() + addRodape() + bodyClose + htmlClose;
            } else {
                paginaHTML = htmlOpen + language + headOpen + meta + headClose + titleOpen + titleClose + headClose + bodyOpen + addCabecalho() + getTabela() + "<br/><br/>" + anexarGraficos() + addRodape() + bodyClose + htmlClose;
                
            }
        } else {
            paginaHTML = "";
            if (isVerificaGrafico() == false) {
                paginaHTML = htmlOpen + language + headOpen + meta + headClose + titleOpen + titleClose + headClose + bodyOpen + addCabecalho() + addRodape() + bodyClose + htmlClose;
            } else {
                paginaHTML = htmlOpen + language + headOpen + meta + headClose + titleOpen + titleClose + headClose + bodyOpen + addCabecalho() + "<br/><br/>" + anexarGraficos() + addRodape() + bodyClose + htmlClose;

            }
        }
        setPaginaRelatorio(paginaHTML);
        gravar();

    }

    public String styleTabelaCabecalho() {
        String style = "style=\" " + "font-family:" + rel.getFontTabelaCabecalho() + "; " + "color:" + rel.getColorTabelaCabecalho() + "; " + "font-size:" + rel.getFontSizeTabelaCabecalho() + "px" + "; " + "font-weight:" + rel.getNegritoTabelaCabecalho() + "; " + "text-decoration:" + rel.getSublinhadoTabelaCabecalho() + "\"";
        return style;
    }

    public String styleTabelaLinha() {
        String style = "style=\" " + "font-family:" + rel.getFontTabelaLinha() + "; " + "color:" + rel.getColorTabelaLinha() + "; " + "font-size:" + rel.getFontSizeTabelaLinha() + "px" + "; " + "font-weight:" + rel.getNegritoTabelaLinha() + "; " + "text-decoration:" + rel.getSublinhadoTabelaLinha() + "\"";
        return style;
    }

    public String styleCabecalho() {
        String style = "style=\" " + "border:" + rel.getBorderCabecalhoHTML() + "; " + " background-color:" + rel.getBackgroundColorCabecalhoHTML() + "; " + " font-family:" + rel.getFontCabecalho() + "; " + "color:" + rel.getColorCabecalho() + "; " + "font-size:" + rel.getFontSizeCabecalho() + "px" + "; " + "font-weight:" + rel.getNegritoCabecalho() + "; " + "text-decoration:" + rel.getSublinhadoCabecalho() + " \"";
        return style;
    }

    public String styleRodape() {
        String style = "style=\" " + "border:" + rel.getBorderRodapeHTML() + "; " + " background-color:" + rel.getBackgroundColorRodapeHTML() + "; " + "font-family:" + rel.getFontRodape() + "; " + "color:" + rel.getColorRodape() + "; " + "font-size:" + rel.getFontSizeRodape() + "px" + "; " + "font-weight:" + rel.getNegritoRodape() + "; " + "text-decoration:" + rel.getSublinhadoRodape() + "\"";
        return style;
    }

    public String addCabecalho() {
        String cabecalho = "";
        if (rel.getImagemCabecalho() == null) {
            cabecalho = "<header " + styleCabecalho() + ">" + rel.getCabecalho() + "<br/>" + rel.getData() + "</header><br/>";
        } else {
            cabecalho = "<header " + styleCabecalho() + ">" + "<img src=\"" + rel.getImagemCabecalho() + "\" " + "height=\"50\"" + "  " + "width=\"200\"" + " " + "align=\"left\"" + "/>" + rel.getCabecalho() + "<br/>" + rel.getData() + "</header><br/>";
        }
        return cabecalho;
    }

    public String addRodape() {
        String rodape = "";
        if (rel.getImagemRodape() == null) {
            rodape = "<br/><br/><footer " + styleRodape() + ">" + rel.getRodape() + "</footer>";
        } else {
            rodape = "<br/><br/><footer " + styleRodape() + "><img src=\"" + rel.getImagemRodape() + "\" " + "height=\"50\"" + "  " + "width=\"200\"" + " " + "align=\"left\"" + "/>" + rel.getRodape() + "</footer>";
        }
        return rodape;
    }

    @Override
    public void addGrafico(ArrayList list, String x, String y, String tipo, String nomeGrafico, String tituloGrafico) {
        String imagem = "";
        try {
            String classe = this.getClass().getName();
            Grafico grafico = new Grafico();
            grafico.criarGrafico(list, x, y, tipo, nomeGrafico, tituloGrafico, classe);
        } catch (Exception e) {
            System.out.println("Erro em Adicionar o Grafico");
        }
        setVerificaGrafico(true);
    }

    public String anexarGraficos() {
        Grafico graf = new Grafico();
        String img = "";
        for (int i = 0; i < graf.getImgHTML().size(); i++) {
        img += "<img src=\"" + graf.getImgHTML().get(i).toString() + "\"" + "/><br/>";
        }
        graf.limparLisHTML();
        return img;

    }

    public void addTabela() {
        //adiciona colunas
        String tabela = "<table border=\"1\">\n"
                + "<tr " + styleTabelaCabecalho() + ">";
        for (int i = 0; i < rel.getListacolunas().size(); i++) {
            tabela += "<td " + styleTabelaCabecalho() + ">" + rel.getListacolunas().get(i) + "</td>\n";
        }
        tabela += "</tr>\n";

        //adiciona linhas nas colunas
        tabela += "<tr " + styleTabelaLinha() + ">";
        int numeroLinhas = rel.getListatlinhas().size() / rel.getListacolunas().size();
        int p = 0;
        for (int i = 0; i < numeroLinhas; i++) {
            tabela += "<tr " + styleTabelaLinha() + ">";
            for (int j = 0; j < rel.getListacolunas().size(); j++) {
                tabela += "<td " + styleTabelaLinha() + ">" + rel.getListatlinhas().get(p) + "</td>\n";
                p++;
            }
            tabela += "</tr>\n";
        }
        tabela += "</table>";
        setTabela(tabela);
        setVerificaTabela(true);
    }

    public void gravar() {
        try {
            FileWriter fw = new FileWriter(rel.getNomeArquivo() + ".html", false);//abertura do arquivo.
            fw.write(getPaginaRelatorio());//grava linha.
            fw.close();//Fecha Arquivo.            
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public <T> T getRel() {
        return (T) rel;
    }

}
