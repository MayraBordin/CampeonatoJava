package view;

import model.CampeonatoModel;
import model.TimeModel;

public class CampeonatoView{

    public void campeonatoIniciado(){
        System.out.println("BEM VINDO AO JOGO");
        System.out.println("PARA INICIAR VAMOS CADASTRAR OS TIMES");
    }

    public void exibirTabela(CampeonatoModel campeonato) {
        System.out.println("Tabela do Campeonato:");
        for (TimeModel time : campeonato.getTimes()) {
            System.out.println("Time: " + time.getNome() + ", Pontos: " + time.getPontos() + ", Blots: " + time.getBlots() + ", Plifs: " + time.getPlifs() + ", Advrunghs: " + time.getAdvrunghs());
        }
    }

    public void exibirGritoDeGuerraDoCampeao(TimeModel campeao) {
        System.out.println("O grito de guerra do time campeão " + campeao.getNome() + " é: " + campeao.getGritoDeGuerra());
    }
}

