package model;

import java.util.Date;

public class TimeModel {
    private String nome;
    private String gritoDeGuerra;
    private Date anoDeFundacao;
    private int pontos;
    private int blots;
    private int plifs;
    private int advrunghs;

    public TimeModel(String nome, String gritoDeGuerra, Date anoDeFundacao) {
        this.nome = nome;
        this.gritoDeGuerra = gritoDeGuerra;
        this.anoDeFundacao = anoDeFundacao;
        this.pontos = 50; // Cada time começa com 50 pontos
        this.blots = 0;
        this.plifs = 0;
        this.advrunghs = 0;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGritoDeGuerra() {
        return gritoDeGuerra;
    }

    public void setGritoDeGuerra(String gritoDeGuerra) {
        this.gritoDeGuerra = gritoDeGuerra;
    }

    public Date getAnoDeFundacao() {
        return anoDeFundacao;
    }

    public void setAnoDeFundacao(Date anoDeFundacao) {
        this.anoDeFundacao = anoDeFundacao;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public int getBlots() {
        return blots;
    }

    public void setBlots(int blots) {
        this.blots = blots;
    }

    public int getPlifs() {
        return plifs;
    }

    public void setPlifs(int plifs) {
        this.plifs = plifs;
    }

    public int getAdvrunghs() {
        return advrunghs;
    }

    public void setAdvrunghs(int advrunghs) {
        this.advrunghs = advrunghs;
    }

    public void registrarBlot() {
        this.blots += 1;
        this.pontos += 5;
        
    }

    public void registrarPlif() {
        this.plifs += 1;
        this.pontos += 1;
    }
    public void registrarAdvrunghs() {
        this.advrunghs += 1;
        this.pontos -= 10;
      
    }

    public void adicionarPontos(int pontos) {
        this.pontos += pontos;
    }
}
