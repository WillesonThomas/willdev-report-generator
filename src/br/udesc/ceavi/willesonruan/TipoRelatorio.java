/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceavi.willesonruan;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 *
 * @author Willeson Thomas
 */
public abstract class TipoRelatorio {
    
    public abstract <T> T getRel();

    public abstract void addGrafico(ArrayList list, String x, String y, String tipo, String nomeGrafico, String tituloGrafico);

    public abstract void gerarRelatorio();
}
