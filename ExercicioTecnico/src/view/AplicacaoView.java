package view;

import controller.CampeonatoController;
import model.CampeonatoModel;
import java.util.*;

public class AplicacaoView {

    Scanner tec = new Scanner(System.in);

    CampeonatoModel campeonatoModel = new CampeonatoModel();
    CampeonatoView campeonatoView = new CampeonatoView();
    CampeonatoController campeonatoController = new CampeonatoController(campeonatoModel, campeonatoView);

    public void executar() {

        boolean campeonatoIniciado = false;
        campeonatoController.campeonatoIniciado();

        while (!campeonatoIniciado) {
            System.out.println("Digite o nome do time ou 'sair' para terminar:");
            String nomeTime = tec.nextLine();
            if (nomeTime.equalsIgnoreCase("sair")) {
                break;
            }

            System.out.println("Digite o grito de guerra do time:");
            String grito = tec.nextLine();

            System.out.println("Digite a data de fundacao do time [formato dd/MM/yyyy]:");
            String dataFundacaoStr = tec.nextLine();
            Date dataFundacao = campeonatoController.dateValidat(dataFundacaoStr);

            campeonatoController.cadastrarTime(nomeTime, grito, dataFundacao);
            System.out.println();
            System.out.println("Time cadastrado com sucesso!");
            System.out.println();

            int numeroDeTimes = campeonatoModel.getTimes().size();
            if (numeroDeTimes >= 8 && numeroDeTimes % 2 == 0) {
                System.out.println("Número atual de times: " + numeroDeTimes);
                System.out.println("Número adequado de times cadastrados.");
                System.out.println("[1] Desejo cadastrar outro time.");
                System.out.println("[2] Desejo iniciar o campeonato.");
                System.out.println();
                int opcao= tec.nextInt();
                tec.nextLine();
                if(opcao==1){
                    campeonatoIniciado = false;
                }else if (opcao==2) {
                    campeonatoIniciado = true;
                } 
            }else if(numeroDeTimes==16){// começa o campeonato quando a quantidade dos times chegar no limite 16!
                System.out.println("Número atual de times: " + numeroDeTimes);
                System.out.println("A quantidade de times cadastrados chegou no limite!");
                System.out.println("Campeonato será iniciado, boa sorte aos times!");
                campeonatoIniciado=true;
            }else {
                System.out.println("Número atual de times: " + numeroDeTimes);
                System.out.println();
                System.out.println("Continue cadastrando os times até ter entre 8 e 16 times.");
                System.out.println("E a quantidade de times for um número par!");
                System.out.println();
            }
        }

        if (campeonatoIniciado) {
            campeonatoController.iniciarCampeonato();
        }
    }
}
