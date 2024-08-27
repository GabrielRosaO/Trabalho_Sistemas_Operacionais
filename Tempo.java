public class Tempo {
    private int tempoDeCorrida;
    private int tempoDeEspera;
    private int tempoDeEntrada;
    
    public Tempo(int tempoDeEntrada){
        this.tempoDeEntrada = tempoDeEntrada;
    }

    //Tempo de corrida total de cada pessoa
    public void setTempoDeCorrida(int tempo){
        this.tempoDeCorrida = tempo;
    }

    //tempo total de espera de cada pessoa
    public void setTempoDeEspera(int tempo){
        this.tempoDeEspera = tempo - tempoDeEntrada;
    }

    //tempo em que a pessoa entrou na fila
    public void setTempoDeEntrada(int tempo){
        this.tempoDeEntrada = tempo;
    }

    public int getTempoDeEspera(){
        return tempoDeEspera;
    }

    public int getTempoDeCorrida(){
        return tempoDeCorrida;
    }

    public int getTempoDeEntrada(){
        return tempoDeEntrada;
    }
}
