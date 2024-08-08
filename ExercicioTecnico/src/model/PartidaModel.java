package model;


public class PartidaModel {
    private TimeModel timeA;
    private TimeModel timeB;
    private boolean encerrada;
    TimeModel vencedor;

    public PartidaModel(TimeModel timeA, TimeModel timeB, CampeonatoModel campeonato) {
        this.timeA = timeA;
        this.timeB = timeB;
        this.encerrada = false;
       
    }

    public PartidaModel(TimeModel timeA, TimeModel timeB) {
        this.timeA = timeA;
        this.timeB = timeB;
        this.encerrada = false;
    }

    public TimeModel getTimeA() {
        return timeA;
    }

    public void setTimeA(TimeModel timeA) {
        this.timeA = timeA;
    }

    public TimeModel getTimeB() {
        return timeB;
    }

    public void setTimeB(TimeModel timeB) {
        this.timeB = timeB;
    }

    public boolean isEncerrada() {
        return encerrada;
    }

    public void setEncerrada(boolean encerrada) {
        this.encerrada = encerrada;
    }

    public TimeModel determinarVencedor() {
        if (timeA.getPontos() > timeB.getPontos()) {
            CampeonatoModel.timesVencedores.add(timeA);
            return timeA;
        } else if (timeB.getPontos() > timeA.getPontos()) {
            CampeonatoModel.timesVencedores.add(timeB);
            return timeB;
        } else {
            System.out.println("Houve empate na pontuação!");
            System.out.println("Iremos realizar um Grusht de desempate!");
            System.out.println();
            realizarGrusht();
            return vencedor;
            
        }
    }
       

    private void realizarGrusht() {
        long inicioTempo = System.currentTimeMillis();
        long fimTempo= inicioTempo + 60 * 1000; // 60 segundos em milissegundos
        System.out.println("Início do Grusht em 3,2,1... ");
        System.out.println();
        System.out.println("VALENDOOOOO.");
        System.out.println();
        while (System.currentTimeMillis() < fimTempo) {// repete até acabar o tempo
            System.out.println(timeA.getGritoDeGuerra());
            System.out.println(timeB.getGritoDeGuerra());
            try {
                Thread.sleep(1000); // 1 segundo de delay, nao sobrecarregar
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
        System.out.println("Fim do Grusht!");
        System.out.println();
        escolherCampeao();
    }

   

    public void escolherCampeao() {
        vencedor = Math.random() > 0.5 ? timeA : timeB;
        System.out.println("Time vencedor do Grusht: " + vencedor.getNome());
        CampeonatoModel.timesVencedores.add(vencedor);
    }

    public TimeModel getTimeVencedor() {
        if (timeA.getPontos() > timeB.getPontos()) {
            return timeA;
        } else if (timeB.getPontos() > timeA.getPontos()) {
            return timeB;
        } else {
            return null; // Ou qualquer outra lógica para empates
        }
    }
    
}
