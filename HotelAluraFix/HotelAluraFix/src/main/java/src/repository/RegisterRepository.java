package src.repository;

import src.db.DatabaseConnection;
import src.models.Hospedes;
import src.models.Reservas;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class RegisterRepository {
    static private final RegisterRepository instance = new RegisterRepository();

    private DatabaseConnection db = DatabaseConnection.getInstance();
    private RegisterRepository() {}

    static public RegisterRepository getInstance() {
        return instance;
    }

    public Optional<Hospedes>findHospedes(String phone) {
        try {
            PreparedStatement stmt = db.getConnection().prepareStatement("SELECT * FROM hospedes WHERE telefone = ?");
            stmt.setString(1, phone);
            stmt.execute();
            ResultSet rst = stmt.getResultSet();
            Hospedes hp = null;
            while (rst.next()) {
                hp = new Hospedes(
                        UUID.fromString(rst.getString("id")),
                        rst.getString("nome"),
                        rst.getString("sobrenome"),
                        rst.getDate("datanascimento"),
                        rst.getString("nascionalidade"),
                        rst.getString("telefone"),
                        getReservasByHospedes(UUID.fromString(rst.getString("id")))
                );
            }
            System.out.println(hp);
            return Optional.ofNullable(hp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Hospedes createNewHospedes(Hospedes hp) {
        try {
            PreparedStatement stmt = db.getConnection().prepareStatement("INSERT INTO hospedes(nome, sobrenome, datanascimento, nascionalidade, telefone) values (?, ?, ?, ?, ?);",
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, hp.getNome());
            stmt.setString(2, hp.getSobreNome());
            stmt.setDate(3, hp.getDataNascimento());
            stmt.setString(4, hp.getNascionalidade());
            stmt.setString(5, hp.getTelefone());
            stmt.executeUpdate();
            ResultSet rst = stmt.getGeneratedKeys();
            while (rst.next()) {
                hp.setId(UUID.fromString(rst.getString("id")));
            }
            System.out.println(hp);
            return hp;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Reservas createNewReserva(Reservas reser) {
        try {
            PreparedStatement stmt = db.getConnection().prepareStatement("INSERT INTO reservas(dataentrada, datasaida, valor, formapagamento,  fkidhospede) values( ?, ?, ?, ?, uuid(?));", Statement.RETURN_GENERATED_KEYS);
            stmt.setDate(1, (Date) reser.getDataEntrada());
            stmt.setDate(2, (Date) reser.getDataSaida()
            );
            stmt.setInt(3, reser.getValor().intValue());
            stmt.setInt(4, reser.getFormaPagamento());
            stmt.setString(5, UUID.fromString(reser.getHospede().getId().toString()).toString());
            stmt.executeUpdate();
            ResultSet rst = stmt.getGeneratedKeys();
            while (rst.next()) {
                reser.setId(UUID.fromString(rst.getString("id")));
                reser.setNumeroReserva(rst.getInt("numeroreserva"));
            }
            System.out.println(reser);
            return reser;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Reservas> getReservasByHospedes(UUID id) {
        try {
            PreparedStatement stmt = db.getConnection().prepareStatement("SELECT * FROM reservas WHERE fkidhospede = ?");
            stmt.setString(1, id.toString());
            stmt.execute();
            ResultSet rst = stmt.getResultSet();
            return createNewLisReservas(rst);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Hospedes> getAllHospedes() {
        try {
            PreparedStatement stmt = db.getConnection().prepareStatement("SELECT * FROM hospedes;");
            stmt.execute();
            ResultSet rst = stmt.getResultSet();
            return  createNewLisHospedes(rst);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Reservas> getAllReservas() {
        try {
            PreparedStatement stmt = db.getConnection().prepareStatement("SELECT * FROM reservas;");
            stmt.execute();
            ResultSet rst = stmt.getResultSet();
            return createNewLisReservas(rst);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private List<Hospedes> createNewLisHospedes(ResultSet rst) {
        List<Hospedes> listAll = new ArrayList<>();
        try {
            while(rst.next()) {
                listAll.add(new Hospedes(
                        UUID.fromString(rst.getString("id")),
                        rst.getString("nome"),
                        rst.getString("sobrenome"),
                        rst.getDate("datanascimento"),
                        rst.getString("nascionalidade"),
                        rst.getString("telefone"),
                        getReservasByHospedes( UUID.fromString(rst.getString("id")))
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listAll;
    }

    private List<Reservas> createNewLisReservas(ResultSet rst) {
        List<Reservas> listAll = new ArrayList<>();
        try {
            while(rst.next()) {
                listAll.add(new Reservas(
                        UUID.fromString(rst.getString("id")),
                        rst.getInt("numeroreserva"),
                        rst.getDate("dataentrada"),
                        rst.getDate("datasaida"),
                        rst.getDouble("valor"),
                        rst.getInt("formapagamento")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listAll;
    }
}
