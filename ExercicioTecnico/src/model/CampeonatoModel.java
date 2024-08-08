package model;

import java.text.*;
import java.util.*;

import view.PartidaView;
import view.TimeView;

public class CampeonatoModel {
    List<TimeModel> times;
    List<TimeModel> tabelaTimes;
    static List<TimeModel> timesVencedores = new ArrayList<>();
    List<PartidaModel> partidas;
    private boolean iniciado;
    int opcaoDaPartida = 0;
    int fase = 1;
    Scanner tec = new Scanner(System.in);

    //#region Constructor, Getters and Setters

    public CampeonatoModel() {
        this.times = new ArrayList<>();
        this.tabelaTimes = new ArrayList<>();
        this.partidas = new ArrayList<>();
        this.iniciado = false;
    }

    public List<TimeModel> getTimes() {
        return times;
    }

    public void setTimes(List<TimeModel> times) {
        this.times = times;
    }

    public List<PartidaModel> getPartidas() {
        return partidas;
    }

    public void setPartidas(List<PartidaModel> partidas) {
        this.partidas = partidas;
    }

    public boolean isIniciado() {
        return iniciado;
    }

    public void setIniciado(boolean iniciado) {
        this.iniciado = iniciado;
    }

   //#endregion 

    public void cadastrarTime(TimeModel time) {
        if (times.size() < 16) {
            times.add(time);
            tabelaTimes.add(time);
        } else {
            throw new IllegalStateException("O campeonato já atingiu o número máximo de times.");
        }
    }

    public Date dateValidat(String dataFundacaoStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dataFundacao = null;
        Date dataAtual = new Date();
        while (dataFundacao == null) {
            try {
                dataFundacao = dateFormat.parse(dataFundacaoStr); // converte para o formato Date
                if (dataFundacao.after(dataAtual)) {
                    System.out.println("Data de fundacao nao pode ser maior que a data atual. Por favor, tente novamente.");
                    dataFundacao = null;
                    System.out.println("Digite a data de fundacao do time [formato dd/MM/yyyy]:");
                    dataFundacaoStr = tec.nextLine();
                }
            } catch (ParseException e) {
                System.out.println("Data invalida. Por favor, tente novamente.");
                System.out.println();
                System.out.println("Digite a data de fundacao do time [formato dd/MM/yyyy]:");
                dataFundacaoStr = tec.nextLine();
            }
        }
        return dataFundacao;
    }

    public void iniciarCampeonato() {
        sortearPartidas();
        System.out.println("Campeonato iniciado!");
        System.out.println();
        System.out.println(fase+ "° Fase!");
        System.out.println();
        exibirPartidasDisponiveis();
        escolhaPartida();
    }

    public void sortearPartidas() {
        Collections.shuffle(times); // Embaralha a lista de times
        partidas.clear();
        for (int i = 0; i < times.size(); i += 2) {
            PartidaModel partida = new PartidaModel(times.get(i), times.get(i + 1), this);
            partidas.add(partida);
        }
    }

    private void escolhaPartida() {
        int opcao = -1;
        while (true) {
            System.out.println("Qual partida deseja administrar?");
            try {
                if (tec.hasNextInt()) {
                    opcao = tec.nextInt();
                    
                    tec.nextLine(); // Limpar o buffer
                    if (opcao >= 1 && opcao <= partidas.size()) {
                        administrarPartidaEscolhida(opcao);
                        System.out.println();
                        break;
                    } else {
                        System.out.println("Número da partida digitado não existe!");
                        System.out.println();
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Digite um número inteiro.");
                tec.next(); // Limpar a entrada inválida
                System.out.println();
            }
        }
    }

    private List<PartidaModel> gerarPartidasFase(List<TimeModel> vencedores) {
        List<PartidaModel> novasPartidas = new ArrayList<>();
        System.out.println("Formando as partidas da proxima fase!");
        System.out.println();
        for (int i = 0; i < vencedores.size(); i += 2) {
            if (i + 1 < vencedores.size()) {
                PartidaModel partida = new PartidaModel(vencedores.get(i), vencedores.get(i + 1), this);
                novasPartidas.add(partida);
            }
        }
        return novasPartidas;
    }

    public void iniciarNovaFase() {
        if (timesVencedores.size() < 2) {
            System.out.println("Não há times suficientes para iniciar uma nova fase.");
            return;
        }

        // Atualiza a lista de times com os vencedores da fase atual
        times = new ArrayList<>(timesVencedores);
        timesVencedores.clear();

        System.out.println("Iniciando nova fase com os times vencedores:");
        for (TimeModel time : times) {
            time.setPontos(50);// Para iniciar uma nova fase todos os times vencedores tem que ter 50 pontos
            System.out.println(time.getNome());
        }
        fase++;
        partidas = gerarPartidasFase(times);
        System.out.println(fase+ "° Fase!");
        System.out.println();
        exibirPartidasDisponiveis();
       
        escolhaPartida();
    }

    private void administrarPartidaEscolhida(int opcaoDaPartida) {
        boolean encerrar = false;
        while (!encerrar) {
            try {
                if (opcaoDaPartida >= 1 && opcaoDaPartida <= partidas.size()) {
                    PartidaModel partida = partidas.get(opcaoDaPartida - 1);
                    System.out.println(opcaoDaPartida + "° partida.");
                   // PartidaView.exibirDetalhesPartida(partida);
                    String opcao = PartidaView.administrarPontosPartida(partida);
                    pontuarTimes(opcao, partida);
                    if (opcao.equals("G")) {
                        encerrar = true; // Encerrar a partida
                    }
                } else {
                    throw new IndexOutOfBoundsException("Número da partida digitado nao existe!");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                escolhaPartida(); // Repetir a escolha de partida
                return;
            }
        }

        // Após encerrar a partida, determinar o vencedor e remover a partida
        if (opcaoDaPartida >= 1 && opcaoDaPartida <= partidas.size()) {
            PartidaModel partidaModel = partidas.get(opcaoDaPartida - 1);
            TimeModel vencedor = partidaModel.determinarVencedor();
            if (vencedor != null) {
                System.out.println("Time vencedor da partida: " + vencedor.getNome());
                System.out.println();
            } else {
                System.out.println("Não foi possível determinar o vencedor.");
            }
            partidas.remove(opcaoDaPartida - 1);
            if (partidas.isEmpty()) {
                System.out.println("Nao há mais partidas disponíveis nesta fase.");
                System.out.println();
                if (timesVencedores.size() == 1) {
                    System.out.println("Campeonato encerrado! Parabéns ao time vencedor: " + vencedor.getNome());
                    System.out.println();
                    tabelaFinal(tabelaTimes);
                    return;
                }
                iniciarNovaFase(); // Iniciar nova fase com vencedores
            } else {
                exibirPartidasDisponiveis(); // Exibir partidas disponíveis
                escolhaPartida(); // Escolher próxima partida
            }
        } else {
            System.out.println("Número da partida inválido para encerrar.");
        }
    }

    public void pontuarTimes(String opcaoPartida, PartidaModel partida) {
        switch (opcaoPartida) {
            case "A":
                partida.getTimeA().registrarBlot();
                System.out.println("BLOT registrado com sucesso!");
                break;
            case "B":
                partida.getTimeB().registrarBlot();
                System.out.println("BLOT registrado com sucesso!");
                break;
            case "C":
                partida.getTimeA().registrarPlif();
                System.out.println("PLIF registrado com sucesso!");
                break;
            case "D":
                partida.getTimeB().registrarPlif();
                System.out.println("PLIF registrado com sucesso!");
                break;
            case "E":
                System.out.println("Comandos:");
                System.out.println("BLOT: Adiciona 5 pontos ao time.");
                System.out.println("PLIF: Adiciona 1 ponto ao time.");
                break;
                case "F":
                int aux=0;
                System.out.println("Qual time praticou uma irregularidade?");
                for (TimeModel time  : times) {
                    aux++;
                    System.out.println("["+aux+"] "+ time.getNome());
                }
                int opcao= tec.nextInt();
                registrarAdvertencia(opcao);
                break;
            case "G":
                partida.setEncerrada(true);
                break;
            default:
                System.out.println("Opção inválida! Digite novamente a opção desejada!");
                break;
        }
        exibirPontuacaoAtual(partida);
    }

    public void registrarAdvertencia(int opcao) {
        TimeModel timeInfrator = times.get(opcao - 1); // considerando que a opção começa em 1
        timeInfrator.registrarAdvrunghs();
        System.out.println("Time " + timeInfrator.getNome() + " penalizado com um Advrunghs. Pontuaçao atual: " + timeInfrator.getPontos());
        System.out.println();
    }

    private void exibirPontuacaoAtual(PartidaModel partida) {
        System.out.println("Pontuaçao atual:");
        System.out.println("Time "+partida.getTimeA().getNome()+" :" + partida.getTimeA().getPontos());
        System.out.println("Time "+partida.getTimeB().getNome()+" :" + partida.getTimeB().getPontos());
        System.out.println();
    }

    private void exibirPartidasDisponiveis() {

        if (partidas.isEmpty()) {
            System.out.println("Não há partidas disponíveis.");
            return;
        }
        System.out.println("Partidas disponíveis:");
        for (int i = 0; i < partidas.size(); i++) {
            System.out.println((i + 1) + "° partida:");
            PartidaView.exibirDetalhesPartida(partidas.get(i));
        }
    }

    /*[5] FINAL No final do campeonato, o sistema deverá exibir:

a) Uma tabela com cada time, o total de blots, o total de plifs, o total de
advrunghs, e a pontuação total. Ordenar decrescentemente pela pontuação
total.
b) O grito de guerra do time campeão. */

    public void tabelaFinal(List<TimeModel> tabelaTimes) {
        // Ordenar a lista de times por pontos em ordem decrescente usando Bubble Sort
        for (int i = 0; i < tabelaTimes.size() - 1; i++) {
            for (int j = 0; j < tabelaTimes.size() - 1 - i; j++) {
                TimeModel t1 = tabelaTimes.get(j);
                TimeModel t2 = tabelaTimes.get(j + 1);
                
                if (t1.getPontos() < t2.getPontos()) {
                    // Trocar os elementos
                    tabelaTimes.set(j, t2);
                    tabelaTimes.set(j + 1, t1);
                }
            }
        }

        // Exibir os detalhes dos times
        TimeView timeView = new TimeView();
        for (TimeModel time : tabelaTimes) {
            timeView.exibirDetalhesTime(time);// chama o metodo pela instancia 
            System.out.println(); // Linha em branco para separar os detalhes dos times
        }
        System.out.println("Grito de Guerra do primeiro time: " + tabelaTimes.get(0).getGritoDeGuerra());
    }
}

