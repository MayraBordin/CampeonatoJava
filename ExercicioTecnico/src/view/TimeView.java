package view;

import model.TimeModel;

public class TimeView {
    public void exibirDetalhesTime(TimeModel time) {
        System.out.println("Nome: " + time.getNome());
        System.out.println("Ano de Fundaçao: " + time.getAnoDeFundacao());
        System.out.println("Pontos: " + time.getPontos());
        System.out.println("Blots: " + time.getBlots());
        System.out.println("Plifs: " + time.getPlifs());
        System.out.println("Advrunghs: " + time.getAdvrunghs());
    }
}

