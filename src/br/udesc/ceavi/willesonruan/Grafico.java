package br.udesc.ceavi.willesonruan;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Willeson Thomas
 */
public class Grafico {

    private Relatorio rel;
    private static ArrayList<String> imgPDF = new ArrayList<>();
    private static ArrayList<String> imgRTF = new ArrayList<>();
    private static ArrayList<String> imgHTML = new ArrayList<>();
   
    public void limparListPDF(){
        imgPDF.clear();
    }
    public void limparListRTF(){
        imgRTF.clear();
    }
    public void limparLisHTML(){
        imgHTML.clear();
    }

    public ArrayList getImgPDF() {
        return imgPDF;
    }

    public void setImgPDF(ArrayList imgPDF) {
        this.imgPDF = imgPDF;
    }

    public ArrayList getImgRTF() {
        return imgRTF;
    }

    public void setImgRTF(ArrayList imgRTF) {
        this.imgRTF = imgRTF;
    }

    public ArrayList getImgHTML() {
        return imgHTML;
    }

    public void setImgHTML(ArrayList imgHTML) {
        this.imgHTML = imgHTML;
    }
 
    // ------- criando conjunto de dados para o gráfico -------
    //metodo que irá receber os dados
    public CategoryDataset creatDataSetBarra(ArrayList<Object> obj, String x, String y, String tipo) throws ClassNotFoundException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        //inserindo os dados no dataSetBarra
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        for (Object o : obj) {

            Class c = Class.forName(obj.get(0).getClass().getName());
            Field horizontal = c.getDeclaredField(x);
            Field vertical = c.getDeclaredField(y);
            horizontal.setAccessible(true);
            vertical.setAccessible(true);

            String xx = horizontal.get(o).toString();
            double yy = Double.parseDouble(vertical.get(o).toString());
            dataSet.addValue(yy, xx, "");
        }

        return dataSet; //retornando o gráfico alimentado
    }

    // ------- criando gráfico de barras -------
    //método que irá receber o retorno do método CategoryDataSet
    public JFreeChart creatBarChartBarra(ArrayList<Object> obj, String x, String y, CategoryDataset dataSet, String tituloGrafico) throws ClassNotFoundException, NoSuchFieldException {

        Class c = Class.forName(obj.get(0).getClass().getName());
        Field horizontal = c.getDeclaredField(x);
        Field vertical = c.getDeclaredField(y);

        //configurando o gráfico e inserindo os dados
        JFreeChart graficoDeBarras = ChartFactory.createBarChart(
                tituloGrafico, //titulo do gráfico
                horizontal.getName(), //nome das linhas
                vertical.getName(), //nome das colunas
                dataSet, //os dados em si
                PlotOrientation.VERTICAL, //orientação das barras
                true, //permitindo legendas
                true, //permitindo informações ao passar o mouse sobre um dado
                true); //permitindo URL

        return graficoDeBarras;
    }

    public PieDataset creatDataSetPizza(ArrayList<Object> obj, String x, String y, String tipo) throws ClassNotFoundException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        //inserindo os dados no dataSetBarra
        DefaultPieDataset pieDataset = new DefaultPieDataset();
        for (Object o : obj) {
            Class c = Class.forName(obj.get(0).getClass().getName());
            Field horizontal = c.getDeclaredField(x);
            Field vertical = c.getDeclaredField(y);

            horizontal.setAccessible(true);
            vertical.setAccessible(true);

            String nome = horizontal.get(o).toString();
            int idade = Integer.parseInt(vertical.get(o).toString());
            //parâmetros do --> dataSetBarra.addValue([valores do gráfico], [valores da linha], [valores da coluna])
            //dataSet.addValue(pessoa.getIdade(), pessoa.getNome(), "");
            pieDataset.setValue(nome, idade);
        }

        return pieDataset; //retornando o gráfico alimentado
    }

    public JFreeChart creatBarChartPizza(PieDataset dataSetPizza, String tituloGrafico) {

        //Cria um objeto JFreeChart passando os seguintes parametros
        JFreeChart grafico = ChartFactory.createPieChart(
                tituloGrafico, //Titulo do graficoBarra
                dataSetPizza, //DataSet
                true, //Para mostrar ou não a legenda
                true, //Para mostrar ou não os tooltips
                false);

        return grafico;
    }

    public void gerarGrafico(ArrayList list, String x, String y, String tipo, String nomeGrafico, String tituloGrafico,String classe) throws ClassNotFoundException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        criarGrafico(list, x, y, tipo, nomeGrafico, tituloGrafico,classe);
    }

    // ------- criando o graficoBarra completo -------
    //método que cria o gráfico físico
    public void criarGrafico(ArrayList<Object> obj, String x, String y, String tipo, String nomeGrafico, String tituloGrafico,String classe) throws ClassNotFoundException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        //chamando os dados
        CategoryDataset dataSetBarra;
        PieDataset dataSetPizza;
        JFreeChart graficoBarra;
        JFreeChart graficoPizza;
        if (tipo.equalsIgnoreCase("pizza")) {
            dataSetPizza = this.creatDataSetPizza(obj, x, y, tipo);
            graficoPizza = this.creatBarChartPizza(dataSetPizza, tituloGrafico);
            gerarImagem(graficoPizza, nomeGrafico,classe);
        } else if (tipo.equalsIgnoreCase("barra")) {
            dataSetBarra = this.creatDataSetBarra(obj, x, y, tipo);
            graficoBarra = this.creatBarChartBarra(obj, x, y, dataSetBarra, tituloGrafico);
            gerarImagem(graficoBarra, nomeGrafico,classe);
        }
    }

    public void gerarImagem(JFreeChart grafico, String nomeGrafico,String classe) {
        try {
            String n = "imagens\\" + nomeGrafico + ".jpg";
             if(classe.equalsIgnoreCase("br.udesc.ceavi.willesonruan.RelatorioPDF")){
                 imgPDF.add(n);
//                 System.out.println(getImgPDF().size());
             } else if(classe.equalsIgnoreCase("br.udesc.ceavi.willesonruan.RelatorioRTF")){
                 imgRTF.add(n);
             } else if(classe.equalsIgnoreCase("br.udesc.ceavi.willesonruan.RelatorioHTML")){
                 imgHTML.add(n);
             }
            
            ChartUtilities.saveChartAsJPEG(
                    new java.io.File(n), //criando o jpg
                    grafico, //inserindo o gráfico
                    300, //largura
                    200); //altura
        } catch (IOException ex) {
            Logger.getLogger(Grafico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
