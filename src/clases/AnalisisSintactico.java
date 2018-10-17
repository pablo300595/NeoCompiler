/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 *
 * @author pablo
 */
public class AnalisisSintactico {
    ArrayList<String[]> tokenList,errorList,sentencias,inicializaciones;
    CreadorAutomatas automatas;

    String id="\\_([a-zA-Z0-9])+";
    String num="(([0-9])+.([0-9])+)|([0-9])+";
    String cad="'([a-zA-Z0-9])+'";
    String asignacion0=id+" == ("+num+") \\$ ";
    String asignacion1=id+" == ("+cad+") \\$ ";
    String asignacion2=id+" == ("+id+") \\$ ";
    String inicializacion0="int "+id+" == ("+num+") \\$ ";
    String inicializacion1="int "+id+" \\$ ";
    String inicializacion2="text "+id+" == ("+cad+") \\$ ";
    String inicializacion3="int "+id+" == ("+cad+") \\$ ";
    String servo0="servo "+"\\( "+(id)+" , "+(id)+" \\) \\$ ";
    String servo1="servo "+"\\( "+(id)+" , "+(num)+" \\) \\$ ";
    String servo2="servo "+"\\( "+(num)+" , "+(id)+" \\) \\$ ";
    String servo3="servo "+"\\( "+(num)+" , "+(num)+" \\) \\$ ";
    
    public AnalisisSintactico(ArrayList<String[]> tokenList,CreadorAutomatas automatas){
        sentencias=new ArrayList();
        inicializaciones=new ArrayList();
        this.tokenList=tokenList;
        this.errorList=new ArrayList();
        this.automatas=automatas;
        validateProductionTask();
    }

    private void validateProductionTask() {
        boolean isValid=false,keyFound=false;
        String error[];
        for(int i=0;i<7;i++){
            if(i==0 && !tokenList.get(i)[0].equals("task")){
                error=("Elemento: "+tokenList.get(i)[0]+"-Numero de linea"+tokenList.get(i)[1]+"-Error Sintactico-Id ES000001: Secuencia task no valida").split("-");
                errorList.add(error);
                return;
            }else if(i==1 && !(Pattern.compile(id).matcher(tokenList.get(i)[0]).matches())){
                error=("Elemento: "+tokenList.get(i)[0]+"-Numero de linea"+tokenList.get(i)[1]+"-Error Sintactico-Id ES000001: Secuencia task no valida").split("-");
                errorList.add(error);
                return;
            }else if(i==2 && !tokenList.get(i)[0].equals("params")){
                error=("Elemento: "+tokenList.get(i)[0]+"-Numero de linea"+tokenList.get(i)[1]+"-Error Sintactico-Id ES000001: Secuencia task no valida").split("-");
                errorList.add(error);
                return;
            }else if(i==3 && !tokenList.get(i)[0].equals("none")){
                error=("Elemento: "+tokenList.get(i)[0]+"-Numero de linea"+tokenList.get(i)[1]+"-Error Sintactico-Id ES000001: Secuencia task no valida").split("-");
                errorList.add(error);
                return;
            }else if(i==4 && !tokenList.get(i)[0].equals("{")){
                error=("Elemento: "+tokenList.get(i)[0]+"-Numero de linea"+tokenList.get(i)[1]+"-Error Sintactico-Id ES000001: Secuencia task no valida").split("-");
                errorList.add(error);
                return;
            }else if(i==4 && tokenList.get(i)[0].equals("{")){
                keyFound=true;
            }else if(i==5 && keyFound){//validar sentencias
                validateSentences(i);
            }else if(i==6 && !tokenList.get(i)[0].equals("}")){
                error=("Elemento: "+tokenList.get(i)[0]+"-Numero de linea"+tokenList.get(i)[1]+"-Error Sintactico-Id ES000001: Secuencia task no valida").split("-");
                errorList.add(error);
                return;
            }
            
        }
    }
    
    /**
     * Método que permite validar sentencias
     * @return nada
     */
    private void validateSentences(int lastTokenPosition) {
        boolean foundValidSequence=false;
        int lastLineAnalyzed=1;
        String sentencia="";

        String error[];
        int cantOprSentencia=0;
        boolean foundAtLeastOne=false;
        
        if(foundAtLeastOne){
                lastLineAnalyzed=Integer.parseInt(tokenList.get(lastTokenPosition)[1]);
                if(!(tokenList.get(lastTokenPosition)[0].equals("}") && lastTokenPosition==tokenList.size()-1)){
                    foundAtLeastOne=false;
                }
            }
        
        for(int i=lastTokenPosition;!tokenList.get(i)[0].equals("}");i++){
            sentencia+=tokenList.get(i)[0]+" ";
            cantOprSentencia++;
            if(cantOprSentencia==3){
                if(Pattern.compile(asignacion1).matcher(sentencia).matches()){
                    String[] sent=(tokenList.get(i)[1]+"@"+sentencia+"@asignacion ").split("@");
                    sentencias.add(sent);cantOprSentencia=0;sentencia="";foundAtLeastOne=true;
                }else if(Pattern.compile(inicializacion1).matcher(sentencia).matches()){
                    String[] sent=(tokenList.get(i)[1]+"@"+sentencia+"@inicializacion ").split("@");
                    sentencias.add(sent);cantOprSentencia=0;sentencia="";foundAtLeastOne=true;
                }
                else{
                    foundAtLeastOne=false;
                }
            }
        }
        
        if(cantOprSentencia!=0){
            error=("Error de secuencia a partir de "+tokenList.get(posicionarIndiceTokenAultimaLinea(lastLineAnalyzed))[0]+"-|Numero de linea "+tokenList.get(posicionarIndiceTokenAultimaLinea(lastLineAnalyzed))[1]+"-|Error Sintactico-|Id ES000003: Secuencia bloque sentencial").split("-");
            errorList.add(error);
        }
    
    }
    
    /**
     * Método que permite obtener la lista de tokens
     * @return tokens
     */
    public String printErrorTokens(){
        String tokenInfo="";
        for(int i=0;i<errorList.size();i++){
            tokenInfo+="Token: "+errorList.get(i)[0]+"\n";
            tokenInfo+="No linea: "+errorList.get(i)[1]+"\n";
            tokenInfo+= errorList.get(i)[2]+"\n";
            tokenInfo+="C. Lexico: "+errorList.get(i)[3]+"\n";
            tokenInfo+="___________________________"+"\n";
        }
        return tokenInfo;
    }
    
    public int posicionarIndiceTokenAultimaLinea(int pos){
        int indice=0;
        String posicion=pos+"";
        System.out.println("pos= "+pos);
        for(int i=0;i<tokenList.size();i++){
            System.out.println("tokenList["+i+"][1]= "+tokenList.get(i)[1]);
            if(tokenList.get(i)[1].equals(posicion)){
                indice=i;
                break;
            }
        }
        return indice;
    }
}
