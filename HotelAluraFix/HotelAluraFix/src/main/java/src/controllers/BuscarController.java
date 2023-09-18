package src.controllers;

import src.models.Hospedes;
import src.models.PaymentTypes;
import src.models.Reservas;
import src.repository.RegisterRepository;

import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BuscarController {
    static private final BuscarController controller = new BuscarController();

    private List<Hospedes> hospedes;

    private List<Reservas> reservas;

    public List<Hospedes> getHospedes() {
        return hospedes;
    }

    public void setHospedes(List<Hospedes> hospedes) {
        this.hospedes = hospedes;
    }

    public List<Reservas> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reservas> reservas) {
        this.reservas = reservas;
    }

    private BuscarController() {
    }

    private RegisterRepository registerRepository = RegisterRepository.getInstance();
    public static BuscarController getInstance() {
        return controller;
    }
    public List<Hospedes> getAllHospedes() {
         setHospedes(registerRepository.getAllHospedes());
         return getHospedes();
    }

    public List<Reservas> getAllReservas() {
        setReservas(registerRepository.getAllReservas());
        return getReservas();
    }

    public void deletarResserva(Integer id) {
        registerRepository.deleteReserva(id);
        reservas = reservas.stream().filter(r -> !Objects.equals(r.getId(), id)).collect(Collectors.toList());
    }

    public void deletarHospedes(Integer id) {
        try {
            registerRepository.deleteHospedes(id);
            hospedes = hospedes.stream().filter(h -> !Objects.equals(h.getId(), id)).collect(Collectors.toList());
        }
        catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizarReservas(Date dataEntrada, Date dataSaida, String valor, String formaPagamento, Integer id) {
        this.reservas = registerRepository.atualizarReservas(
                id,
                dataEntrada,
                dataSaida,
                valor,
                PaymentTypes.fromString(formaPagamento).get().getType());
    }

    public void atualizarHospede(String nome, String sobrenome, Date dataNascimento, String nacionalidade, String telefone, Integer id) {
        this.hospedes = registerRepository.atualizarHospedes(id, nome, sobrenome, dataNascimento, nacionalidade, telefone);

    }

    public void filter(String[] split, String table) {
        if (!table.equals("reservas") && !table.equals("hospedes")) {
            return ;
        }
        List<?> list;
        if (split.length == 1) {
            list = registerRepository.findBy("id", split[0], table);
        } else {
            list = registerRepository.findBy(split[0], split[1], table);
        }
        if (table.equals("reservas")) {

            this.reservas = (List<Reservas>) list;
        } else {
            this.hospedes = (List<Hospedes>) list;
        }
    }
}
