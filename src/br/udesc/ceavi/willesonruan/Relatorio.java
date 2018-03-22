package br.udesc.ceavi.willesonruan;

import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import java.awt.Color;
import java.awt.List;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Willeson Thomas
 */
//Codigo Correto do framework.
public class Relatorio {

    private String nomeArquivo;
    private String tamanhoPagina;
    private String cabecalho;
    private String data;
    private String imagemCabecalho;
    private String imagemRodape;
    private String rodape;
    public static ArrayList listatlinhas = new ArrayList();
    public static ArrayList listacolunas = new ArrayList();

    //Estilos dos Relatorios tabelas
    private String fontTabelaCabecalho;
    private String fontSizeTabelaCabecalho;
    private String negritoTabelaCabecalho;
    private String sublinhadoTabelaCabecalho;
    private String colorTabelaCabecalho;
    private String fontTabelaLinha;
    private String fontSizeTabelaLinha;
    private String negritoTabelaLinha;
    private String sublinhadoTabelaLinha;
    private String colorTabelaLinha;
    private String backgroundColorCabecalhoHTML;
    private String backgroundColorRodapeHTML;

    //Estilos rodape e cabecalho;
    private String fontCabecalho;
    private String fontSizeCabecalho;
    private String negritoCabecalho;
    private String sublinhadoCabecalho;
    private String colorCabecalho;
    private String fontRodape;
    private String fontSizeRodape;
    private String negritoRodape;
    private String sublinhadoRodape;
    private String colorRodape;
    private String borderCabecalhoHTML;
    private String borderRodapeHTML;

    //Estilos pdf e rtf;
    private int oNegritoSublinhadoCabecalho;
    private int oNegritoSublinhadoRodape;
    private int oNegritoSublinhadoTabelaCabecalho;
    private int oNegritoSublinhadoTabelaLinha;

    private Color oColorCabecalho;
    private Color oColorRodape;
    private Color oColorTabelaCabecalho;
    private Color oColorTabelaLinha;
    private Color oBackgroundColorCabecalho;
    private Color oBackgroundColorRodape;
    private Color borderCabecalho;
    private Color borderRodape;

    //Relatorio pdf e rtf
    public Relatorio(String nomeArquivo, String cabecalho, String data, String rodape,
            String fontTabelaCabecalho, String fontSizeTabelaCabecalho,
            String fontTabelaLinha, String fontSizeTabelaLinha,
            String fontCabecalho, String fontSizeCabecalho,
            String fontRodape, String fontSizeRodape) {
        this.nomeArquivo = nomeArquivo;
        this.cabecalho = cabecalho;
        this.data = data;
        this.rodape = rodape;

        this.fontTabelaCabecalho = fontTabelaCabecalho;
        this.fontSizeTabelaCabecalho = fontSizeTabelaCabecalho;

        this.fontTabelaLinha = fontTabelaLinha;
        this.fontSizeTabelaLinha = fontSizeTabelaLinha;
        this.fontCabecalho = fontCabecalho;

        this.fontSizeCabecalho = fontSizeCabecalho;

        this.fontRodape = fontRodape;
        this.fontSizeRodape = fontSizeRodape;

    }

    public Relatorio() {
        this.nomeArquivo = "Relatorio - Teste 01";
        this.tamanhoPagina = "A4";
        this.cabecalho = "Cabecalho - Teste 01";
        DateFormat relogioFormatado = new SimpleDateFormat("dd/MM/yyyy");
        Date relogio = new Date();
        this.data = relogioFormatado.format(relogio);
        this.imagemCabecalho = "udesc.jpg";
        this.imagemRodape = "udesc.jpg";
        this.rodape = "Rodape - Teste 01";

        this.fontTabelaCabecalho = "Courier";
        this.fontSizeTabelaCabecalho = "10";
        this.negritoTabelaCabecalho = "bold";
        this.sublinhadoTabelaCabecalho = "underline";
        this.colorTabelaCabecalho = "blue";
        this.fontTabelaLinha = "Courier";
        this.fontSizeTabelaLinha = "10";
        this.negritoTabelaLinha = "bold";
        this.sublinhadoTabelaLinha = "";
        this.colorTabelaLinha = "red";

        this.fontCabecalho = "Helvetica";
        this.fontSizeCabecalho = "20";
        this.negritoCabecalho = "bold";
        this.sublinhadoCabecalho = "UNDERLINE";
        this.colorCabecalho = "red";
        this.fontRodape = "Helvetica";
        this.fontSizeRodape = "20";
        this.negritoRodape = "bold";
        this.sublinhadoRodape = "underline";
        this.colorRodape = "blue";
    }

    public void addStyleCabecalhoHTML(String fontFamily, String fontSize, String color, String borderCabecalhoHTML, String backgroundColorCabecalhoHTML, String negrito, String sublinhado) {
        this.fontCabecalho = fontFamily;
        this.fontSizeCabecalho = fontSize;
        this.colorCabecalho = color;
        this.backgroundColorCabecalhoHTML = backgroundColorCabecalhoHTML;
        this.negritoCabecalho = negrito;
        this.sublinhadoCabecalho = sublinhado;
        this.borderCabecalhoHTML = borderCabecalhoHTML;
    }

    public void addStyleRodapeHTML(String fontFamily, String fontSize, String color, String borderRodapeHTML, String backgroundColorRodapeHTML, String negrito, String sublinhado) {
        this.fontRodape = fontFamily;
        this.fontSizeRodape = fontSize;
        this.colorRodape = color;
        this.backgroundColorRodapeHTML = backgroundColorRodapeHTML;
        this.borderRodapeHTML = borderRodapeHTML;
        this.sublinhadoRodape = sublinhado;
        this.negritoRodape = negrito;
    }

    public void addStyleTabelaColunaHTML(String fontFamily, String fontSize, String color, String negrito, String sublinhado) {
        this.fontTabelaCabecalho = fontFamily;
        this.fontSizeTabelaCabecalho = fontSize;
        this.colorTabelaCabecalho = color;
        this.sublinhadoTabelaCabecalho = sublinhado;
        this.negritoTabelaCabecalho = negrito;
    }

    public void addStyleTabelaLinhaHTML(String fontFamily, String fontSize, String color, String negrito, String sublinhado) {
        this.fontTabelaLinha = fontFamily;
        this.fontSizeTabelaLinha = fontSize;
        this.colorTabelaLinha = color;
        this.sublinhadoTabelaLinha = sublinhado;
        this.negritoTabelaLinha = negrito;
    }

    public void addStyleCabecalho(String fontFamily, String fontSize, Color color, Color borderRodape, Color oBackgroundColorCabecalho, int negritoSublinhado) {
        this.fontCabecalho = fontFamily;
        this.fontSizeCabecalho = fontSize;
        this.borderRodapeHTML = borderRodapeHTML;
        this.oColorCabecalho = color;
        this.oNegritoSublinhadoCabecalho = negritoSublinhado;
        this.oBackgroundColorCabecalho = oBackgroundColorCabecalho;
        this.borderCabecalho = borderRodape;
    }

    public void addStyleRodape(String fontFamily, String fontSize, Color color, Color borderRodape, Color oBackgroundColorRodape, int negritoSublinhado) {
        this.fontRodape = fontFamily;
        this.fontSizeRodape = fontSize;
        this.oColorRodape = color;
        this.oNegritoSublinhadoRodape = negritoSublinhado;
        this.oBackgroundColorRodape = oBackgroundColorRodape;
        this.borderRodape = borderRodape;
    }

    public void addStyleTabelaColuna(String fontFamily, String fontSize, Color color, int negritoSublinhado) {
        this.fontTabelaCabecalho = fontFamily;
        this.fontSizeTabelaCabecalho = fontSize;
        this.oColorTabelaCabecalho = color;
        this.oNegritoSublinhadoTabelaCabecalho = negritoSublinhado;
    }

    public void addStyleTabelaLinha(String fontFamily, String fontSize, Color color, int negritoSublinhado) {
        this.fontTabelaLinha = fontFamily;
        this.fontSizeTabelaLinha = fontSize;
        this.oColorTabelaLinha = color;
        this.oNegritoSublinhadoTabelaLinha = negritoSublinhado;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public String getTamanhoPagina() {
        return tamanhoPagina;
    }

    public void setTamanhoPagina(String tamanhoPagina) {
        this.tamanhoPagina = tamanhoPagina;
    }

    public String getCabecalho() {
        return cabecalho;
    }

    public void setCabecalho(String cabecalho) {
        this.cabecalho = cabecalho;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getImagemCabecalho() {
        return imagemCabecalho;
    }

    public void setImagemCabecalho(String imagemCabecalho) {
        this.imagemCabecalho = imagemCabecalho;
    }

    public String getImagemRodape() {
        return imagemRodape;
    }

    public void setImagemRodape(String imagemRodape) {
        this.imagemRodape = imagemRodape;
    }

    public String getRodape() {
        return rodape;
    }

    public void setRodape(String rodape) {
        this.rodape = rodape;
    }

    public static ArrayList getListatlinhas() {
        return listatlinhas;
    }

    public static void setListatlinhas(ArrayList listatlinhas) {
        Relatorio.listatlinhas = listatlinhas;
    }

    public static ArrayList getListacolunas() {
        return listacolunas;
    }

    public static void setListacolunas(ArrayList listacolunas) {
        Relatorio.listacolunas = listacolunas;
    }

    public String getFontTabelaCabecalho() {
        return fontTabelaCabecalho;
    }

    public void setFontTabelaCabecalho(String fontTabelaCabecalho) {
        this.fontTabelaCabecalho = fontTabelaCabecalho;
    }

    public String getFontSizeTabelaCabecalho() {
        return fontSizeTabelaCabecalho;
    }

    public void setFontSizeTabelaCabecalho(String fontSizeTabelaCabecalho) {
        this.fontSizeTabelaCabecalho = fontSizeTabelaCabecalho;
    }

    public String getNegritoTabelaCabecalho() {
        return negritoTabelaCabecalho;
    }

    public void setNegritoTabelaCabecalho(String negritoTabelaCabecalho) {
        this.negritoTabelaCabecalho = negritoTabelaCabecalho;
    }

    public String getSublinhadoTabelaCabecalho() {
        return sublinhadoTabelaCabecalho;
    }

    public String getBorderCabecalhoHTML() {
        return borderCabecalhoHTML;
    }

    public void setBorderCabecalhoHTML(String borderCabecalhoHTML) {
        this.borderCabecalhoHTML = borderCabecalhoHTML;
    }

    public String getBorderRodapeHTML() {
        return borderRodapeHTML;
    }

    public void setBorderRodapeHTML(String borderRodapeHTML) {
        this.borderRodapeHTML = borderRodapeHTML;
    }

    public Color getBorderCabecalho() {
        return borderCabecalho;
    }

    public void setBorderCabecalho(Color borderCabecalho) {
        this.borderCabecalho = borderCabecalho;
    }

    public Color getBorderRodape() {
        return borderRodape;
    }

    public void setBorderRodape(Color borderRodape) {
        this.borderRodape = borderRodape;
    }

    public void setSublinhadoTabelaCabecalho(String sublinhadoTabelaCabecalho) {
        this.sublinhadoTabelaCabecalho = sublinhadoTabelaCabecalho;
    }

    public String getColorTabelaCabecalho() {
        return colorTabelaCabecalho;
    }

    public void setColorTabelaCabecalho(String colorTabelaCabecalho) {
        this.colorTabelaCabecalho = colorTabelaCabecalho;
    }

    public String getFontTabelaLinha() {
        return fontTabelaLinha;
    }

    public void setFontTabelaLinha(String fontTabelaLinha) {
        this.fontTabelaLinha = fontTabelaLinha;
    }

    public String getFontSizeTabelaLinha() {
        return fontSizeTabelaLinha;
    }

    public void setFontSizeTabelaLinha(String fontSizeTabelaLinha) {
        this.fontSizeTabelaLinha = fontSizeTabelaLinha;
    }

    public String getNegritoTabelaLinha() {
        return negritoTabelaLinha;
    }

    public void setNegritoTabelaLinha(String negritoTabelaLinha) {
        this.negritoTabelaLinha = negritoTabelaLinha;
    }

    public String getSublinhadoTabelaLinha() {
        return sublinhadoTabelaLinha;
    }

    public void setSublinhadoTabelaLinha(String sublinhadoTabelaLinha) {
        this.sublinhadoTabelaLinha = sublinhadoTabelaLinha;
    }

    public String getBackgroundColorCabecalhoHTML() {
        return backgroundColorCabecalhoHTML;
    }

    public void setBackgroundColorCabecalhoHTML(String backgroundColorCabecalhoHTML) {
        this.backgroundColorCabecalhoHTML = backgroundColorCabecalhoHTML;
    }

    public String getBackgroundColorRodapeHTML() {
        return backgroundColorRodapeHTML;
    }

    public void setBackgroundColorRodapeHTML(String backgroundColorRodapeHTML) {
        this.backgroundColorRodapeHTML = backgroundColorRodapeHTML;
    }

    public String getColorTabelaLinha() {
        return colorTabelaLinha;
    }

    public void setColorTabelaLinha(String colorTabelaLinha) {
        this.colorTabelaLinha = colorTabelaLinha;
    }

    public String getFontCabecalho() {
        return fontCabecalho;
    }

    public void setFontCabecalho(String fontCabecalho) {
        this.fontCabecalho = fontCabecalho;
    }

    public String getFontSizeCabecalho() {
        return fontSizeCabecalho;
    }

    public void setFontSizeCabecalho(String fontSizeCabecalho) {
        this.fontSizeCabecalho = fontSizeCabecalho;
    }

    public String getNegritoCabecalho() {
        return negritoCabecalho;
    }

    public void setNegritoCabecalho(String negritoCabecalho) {
        this.negritoCabecalho = negritoCabecalho;
    }

    public String getSublinhadoCabecalho() {
        return sublinhadoCabecalho;
    }

    public void setSublinhadoCabecalho(String sublinhadoCabecalho) {
        this.sublinhadoCabecalho = sublinhadoCabecalho;
    }

    public String getColorCabecalho() {
        return colorCabecalho;
    }

    public void setColorCabecalho(String colorCabecalho) {
        this.colorCabecalho = colorCabecalho;
    }

    public String getFontRodape() {
        return fontRodape;
    }

    public void setFontRodape(String fontRodape) {
        this.fontRodape = fontRodape;
    }

    public String getFontSizeRodape() {
        return fontSizeRodape;
    }

    public void setFontSizeRodape(String fontSizeRodape) {
        this.fontSizeRodape = fontSizeRodape;
    }

    public String getNegritoRodape() {
        return negritoRodape;
    }

    public void setNegritoRodape(String negritoRodape) {
        this.negritoRodape = negritoRodape;
    }

    public String getSublinhadoRodape() {
        return sublinhadoRodape;
    }

    public void setSublinhadoRodape(String sublinhadoRodape) {
        this.sublinhadoRodape = sublinhadoRodape;
    }

    public String getColorRodape() {
        return colorRodape;
    }

    public void setColorRodape(String colorRodape) {
        this.colorRodape = colorRodape;
    }

    public int getoNegritoSublinhadoCabecalho() {
        return oNegritoSublinhadoCabecalho;
    }

    public void setoNegritoSublinhadoCabecalho(int oNegritoSublinhadoCabecalho) {
        this.oNegritoSublinhadoCabecalho = oNegritoSublinhadoCabecalho;
    }

    public int getoNegritoSublinhadoRodape() {
        return oNegritoSublinhadoRodape;
    }

    public void setoNegritoSublinhadoRodape(int oNegritoSublinhadoRodape) {
        this.oNegritoSublinhadoRodape = oNegritoSublinhadoRodape;
    }

    public int getoNegritoSublinhadoTabelaCabecalho() {
        return oNegritoSublinhadoTabelaCabecalho;
    }

    public void setoNegritoSublinhadoTabelaCabecalho(int oNegritoSublinhadoTabelaCabecalho) {
        this.oNegritoSublinhadoTabelaCabecalho = oNegritoSublinhadoTabelaCabecalho;
    }

    public int getoNegritoSublinhadoTabelaLinha() {
        return oNegritoSublinhadoTabelaLinha;
    }

    public void setoNegritoSublinhadoTabelaLinha(int oNegritoSublinhadoTabelaLinha) {
        this.oNegritoSublinhadoTabelaLinha = oNegritoSublinhadoTabelaLinha;
    }

    public Color getoColorCabecalho() {
        return oColorCabecalho;
    }

    public void setoColorCabecalho(Color oColorCabecalho) {
        this.oColorCabecalho = oColorCabecalho;
    }

    public Color getoColorRodape() {
        return oColorRodape;
    }

    public void setoColorRodape(Color oColorRodape) {
        this.oColorRodape = oColorRodape;
    }

    public Color getoColorTabelaCabecalho() {
        return oColorTabelaCabecalho;
    }

    public void setoColorTabelaCabecalho(Color oColorTabelaCabecalho) {
        this.oColorTabelaCabecalho = oColorTabelaCabecalho;
    }

    public Color getoColorTabelaLinha() {
        return oColorTabelaLinha;
    }

    public void setoColorTabelaLinha(Color oColorTabelaLinha) {
        this.oColorTabelaLinha = oColorTabelaLinha;
    }

    public Color getoBackgroundColorCabecalho() {
        return oBackgroundColorCabecalho;
    }

    public void setoBackgroundColorCabecalho(Color oBackgroundColorCabecalho) {
        this.oBackgroundColorCabecalho = oBackgroundColorCabecalho;
    }

    public Color getoBackgroundColorRodape() {
        return oBackgroundColorRodape;
    }

    public void setoBackgroundColorRodape(Color oBackgroundColorRodape) {
        this.oBackgroundColorRodape = oBackgroundColorRodape;
    }

    public void addLinhaTabela(String... l) {
        for (String ll : l) {
            listatlinhas.add(ll);
        }
    }

    public void addColunaTabela(String... c) {
        for (String cc : c) {
            listacolunas.add(cc);
        }
    }
    public boolean addTabela(){
        boolean verifica = true;
        if(getListacolunas().size()==0 && getListatlinhas().size()==0){
            verifica = false;
        }
        return verifica;
    }

    public void limpar() {
        listatlinhas.clear();
        listacolunas.clear();
    }
}
