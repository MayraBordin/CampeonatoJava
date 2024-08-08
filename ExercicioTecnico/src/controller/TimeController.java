package controller;

import model.TimeModel;
import view.TimeView;

public class TimeController {
    private TimeModel timeModel;
    private TimeView timeView;

    public TimeController(TimeModel timeModel, TimeView timeView) {
        this.timeModel = timeModel;
        this.timeView = timeView;
    }

    public void exibirDetalhes() {
        timeView.exibirDetalhesTime(timeModel);
    }

    public void registrarBlot() {
        timeModel.registrarBlot();
    }

    public void registrarPlif() {
        timeModel.registrarPlif();
    }

   
}
