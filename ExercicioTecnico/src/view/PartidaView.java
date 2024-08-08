package view;

import model.PartidaModel;
import java.util.*;

public class PartidaView {

    static Scanner tec = new Scanner(System.in);

    public static void exibirDetalhesPartida(PartidaModel partida) {
        System.out.println(partida.getTimeA().getNome() + " vs " + partida.getTimeB().getNome());
        System.out.println("Pontos do time " + partida.getTimeA().getNome() + ": " + partida.getTimeA().getPontos());
        System.out.println("Pontos do time " + partida.getTimeB().getNome() + ": " + partida.getTimeB().getPontos());
        System.out.println();
    }

    public static String administrarPontosPartida(PartidaModel partida){
        System.out.println("[A] Registrar um 'BLOT' para o time "+partida.getTimeA().getNome() + "." );
        System.out.println("[B] Registrar um 'BLOT' para o time "+partida.getTimeB().getNome() + ".");
        System.out.println("[C] Registrar um 'PLIF' para o time "+partida.getTimeA().getNome() + "." );
        System.out.println("[D] Registrar um 'PLIF' para o time "+partida.getTimeB().getNome() + ".");
        System.out.println("[E] Informaçao dos comandos (PLIF e BLOT).");
        System.out.println("[F] Denunciar uma irregularidade.");
        System.out.println("[G] Encerrar partida.");
        System.out.println();
        String opcao= tec.nextLine().toUpperCase(); //converte p maisculo a entrada
        return opcao;
    }
}
