public class Tempo {
    private int tempoDeCorrida;
    private int tempoDeEntrada;
    
    public Tempo(int tempoDeEntrada){
        this.tempoDeEntrada = tempoDeEntrada;
    }

    public void setTempoDeCorrida(int tempo){
        this.tempoDeCorrida = tempo;
    }

    public void setTempoDeEntrada(int tempo){
        this.tempoDeEntrada = tempo;
    }

    public int getTempoDeCorrida(){
        return tempoDeCorrida;
    }

    public int getTempoDeEntrada(){
        return tempoDeEntrada;
    }
    
    public String toString(){
        return "inicio (" + tempoDeEntrada + ") fim (" + tempoDeCorrida + ")";
    }
}
