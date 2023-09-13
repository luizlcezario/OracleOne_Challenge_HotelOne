package src.controllers;

import src.models.Hospedes;
import src.models.PaymentTypes;
import src.models.Reservas;
import src.repository.RegisterRepository;

import java.sql.Date;
import java.util.Optional;

public class RegisterController {

    static private final RegisterController controller = new RegisterController();

    static private Reservas reservaTmp;


    static private RegisterRepository registerRepository = RegisterRepository.getInstance();
    private RegisterController() {
    }

    public static RegisterController getInstance() {
        return controller;
    }

    public void setNewReserve(Double value, Date entreDate, Date exitDate, PaymentTypes transactionForm) {
        reservaTmp = new Reservas(null, 0, entreDate ,exitDate, value, transactionForm.getType());
    }

    public String saveNewClient(String firstName,String lastName, Date born, String  nationality, String phone) {
        if (reservaTmp == null) {
            return "";
        }
        Optional<Hospedes> exist = registerRepository.findHospedes(phone);
        Hospedes actual = new Hospedes(null,
                firstName,
                lastName,
                born,
                nationality,
                phone,
                null);
        Hospedes finalActual;
        finalActual = exist.orElseGet(() -> registerRepository.createNewHospedes(actual));
        reservaTmp.setHospede(finalActual);
        return registerRepository.createNewReserva(reservaTmp).getNumeroReserva().toString();
    }
}
