package controller;

import view.PartidaView;
import model.PartidaModel;

public class PartidaController {
    private PartidaModel partidaModel;
   

    public PartidaController(PartidaModel partidaModel, PartidaView partidaView) {
        this.partidaModel = partidaModel;
    }


    public void registrarBlotTimeA() {
        partidaModel.getTimeA().registrarBlot();
    }

    public void registrarBlotTimeB() {
        partidaModel.getTimeB().registrarBlot();
    }

    public void registrarPlifTimeA() {
        partidaModel.getTimeA().registrarPlif();
    }

    public void registrarPlifTimeB() {
        partidaModel.getTimeB().registrarPlif();
    }
}

