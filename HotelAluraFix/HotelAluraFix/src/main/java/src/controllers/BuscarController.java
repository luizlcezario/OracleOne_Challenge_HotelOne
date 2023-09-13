package src.controllers;

import src.models.Hospedes;
import src.models.Reservas;
import src.repository.RegisterRepository;

import java.util.List;

public class BuscarController {
    static private final BuscarController controller = new BuscarController();
    private BuscarController() {
    }

    private RegisterRepository registerRepository = RegisterRepository.getInstance();
    public static BuscarController getInstance() {
        return controller;
    }
    public List<Hospedes> getAllHospedes() {
        return registerRepository.getAllHospedes();
    }

    public List<Reservas> getAllReservas() {
        return registerRepository.getAllReservas();
    }
}
