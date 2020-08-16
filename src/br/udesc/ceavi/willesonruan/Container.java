/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceavi.willesonruan;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 *
 * @author Willeson Thomas da Silva
 */
//Framework Correto
public class Container {

    private Map<String, Object> objetosCriados = new HashMap<>();
    private Map<String, Class> singleton = new HashMap<>();
    private Map<String, Class> classesLidas = new HashMap<>();
    private Map<String, Class> relatorios = new HashMap<>();
    private Map<String, Element> parametros = new HashMap<>();
    private String nomeArquivo;

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public void lerXml(String nomeArquivo) {
        Class x;
        try {
            File inputFile = new File(getNomeArquivo());
            SAXReader reader = new SAXReader();
            Document doc = reader.read(inputFile);
            Element root = doc.getRootElement();

            for (Element obj : root.elements()) {
                Element e = obj;
                if (e.getName().equals("gerarRelatorio")) {
                    x = Class.forName("br.udesc.ceavi.willesonruan." + e.attributeValue("class"));
                    relatorios.put(e.attributeValue("id"), x);
                } else {
                    x = Class.forName(e.attributeValue("class"));
                    classesLidas.put(e.attributeValue("id"), x);
                    if (e.attributeValue("singleton").equalsIgnoreCase("true")) {
                        Class clazz = Class.forName(e.attributeValue("class"));
                        String nomeClasse = clazz.getName();
                        singleton.put(e.attributeValue("id"), clazz);
                    }
                }
                parametros.put(e.attributeValue("id"), e);
            }

        } catch (Exception e) {
            System.err.println("Não foi possível ler o xml");
        }
    }

    public Container(String nomeArquivo) throws Exception {
        setNomeArquivo(nomeArquivo);
        lerXml(nomeArquivo);
    }

    public <T> T criar(String id) throws Exception {

        Class clazz;
        Object o = null;

        clazz = singleton.get(id);
        if (clazz == null) {
            Class c = classesLidas.get(id);
            o = contrutor(c, id);
        } else {
            o = objetosCriados.get(id);
            if (o == null) {
                o = contrutor(clazz, id);
                objetosCriados.put(id, o);
            }
        }
        Relatorio rel = new Relatorio();
        rel.limpar();
        return (T) o;
    }

    public <T> T contrutor(Class clazz, String id) throws InstantiationException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, ClassNotFoundException, NoSuchFieldException {
        Element ret = parametros.get(id);
        Object obj = null;
        Object[] vetor = new Object[ret.elements().size()];
        boolean verifica = false;

        Constructor<?>[] constrs = clazz.getDeclaredConstructors();
        for (Constructor<?> constr : constrs) {
            if (constr.getParameterCount() == ret.elements().size()) {
                for (int i = 0; i < vetor.length; i++) {
                    vetor[i] = ret.elements().get(i).getData().toString();
                }
                obj = constr.newInstance(vetor);
                verifica = true;
                break;
            }
        }
        if (verifica == false) {
            String nomeClasse = ret.attributeValue("class");
            obj = clazz.newInstance();
            for (Element element : ret.elements()) {
                Class classeFilha = Class.forName(nomeClasse);
                Class superClasse = classeFilha.getSuperclass();
                if (clazz.getName().equalsIgnoreCase(nomeClasse)) {
                    for (Element element1 : ret.elements()) {
                        Field f = superClasse.getDeclaredField(element1.getName().toString());
                        f.setAccessible(true);
                        f.set(obj, element1.getData().toString());
                    }
                }
                objetosCriados.put(element.attributeValue("id"), obj);
            }
        }
        objetosCriados.put(id, obj);
        return (T) obj;
    }

    public <T> T criarRelatorio(String id) throws DocumentException, ClassNotFoundException, InstantiationException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, Exception {
        Class clazz;
        Object o;
        Object objet = null;
        String idGerar = "";
        boolean achou = false;

        File inputFile = new File(getNomeArquivo());
        SAXReader reader = new SAXReader();
        Document doc = reader.read(inputFile);
        Element root = doc.getRootElement();
        for (Element obj : root.elements()) {
            Element e = obj;
            if (e.getName().equals("gerarRelatorio")) {
                if (e.attributeValue("id").equalsIgnoreCase(id)) {
                    idGerar = e.attributeValue("idGerar");
                    o = criar(idGerar);
                    clazz = Class.forName("br.udesc.ceavi.willesonruan." + e.attributeValue("class"));
                    Constructor<?>[] constrs = clazz.getDeclaredConstructors();
                    for (Constructor<?> constr : constrs) {
                        objet = constr.newInstance(o);
                        achou = true;
                        break;
                    }

                }
            }
            if (achou == true) {
                break;
            }
        }
        return (T) objet;
    }

}
