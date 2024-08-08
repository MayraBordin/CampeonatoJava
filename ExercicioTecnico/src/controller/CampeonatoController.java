package controller;

import model.CampeonatoModel;
import model.TimeModel;
import view.CampeonatoView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import Database.DatabaseConnection;

public class CampeonatoController {
    private CampeonatoModel campeonatoModel;
    private CampeonatoView campeonatoView;

    public CampeonatoController(CampeonatoModel campeonatoModel, CampeonatoView campeonatoView) {
        this.campeonatoModel = campeonatoModel;
        this.campeonatoView = campeonatoView;
    }

    public void campeonatoIniciado(){
        campeonatoView.campeonatoIniciado();
    }

    public void cadastrarTime(String nome, String gritoDeGuerra, Date anoDeFundacao) {
        TimeModel time = new TimeModel(nome, gritoDeGuerra, anoDeFundacao);
        campeonatoModel.cadastrarTime(time);
        salvarTimeNoBanco(time);
    }

    public void iniciarCampeonato() {
        campeonatoModel.iniciarCampeonato();
    }

    public void exibirTabela() {
        campeonatoView.exibirTabela(campeonatoModel);
    }

    public void exibirGritoDeGuerraDoCampeao() {
        TimeModel campeao = campeonatoModel.getTimes().get(0); // O campeão será o único time restante
        campeonatoView.exibirGritoDeGuerraDoCampeao(campeao);
    }

    public Date dateValidat(String dataFundacaoStr) {
        return campeonatoModel.dateValidat(dataFundacaoStr);
    }

     private void salvarTimeNoBanco(TimeModel time) {
        String sql = "INSERT INTO times (nome, grito_de_guerra, data_fundacao) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, time.getNome());
            pstmt.setString(2, time.getGritoDeGuerra());
            pstmt.setDate(3, new java.sql.Date(time.getAnoDeFundacao().getTime()));
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
