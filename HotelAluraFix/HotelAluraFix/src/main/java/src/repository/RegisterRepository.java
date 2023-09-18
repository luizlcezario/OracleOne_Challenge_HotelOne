package src.repository;

import src.db.DatabaseConnection;
import src.models.Hospedes;
import src.models.Reservas;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
                        rst.getInt("id"),
                        rst.getString("nome"),
                        rst.getString("sobrenome"),
                        rst.getDate("datanascimento"),
                        rst.getString("nascionalidade"),
                        rst.getString("telefone"),
                        getReservasByHospedes(rst.getInt("id"))
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
                hp.setId(rst.getInt("id"));
            }
            System.out.println(hp);
            return hp;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Reservas createNewReserva(Reservas reser) {
        try {
            PreparedStatement stmt = db.getConnection().prepareStatement("INSERT INTO reservas(dataentrada, datasaida, valor, formapagamento,  fkidhospede) values( ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            stmt.setDate(1, (Date) reser.getDataEntrada());
            stmt.setDate(2, (Date) reser.getDataSaida());
            stmt.setInt(3, reser.getValor().intValue());
            stmt.setInt(4, reser.getFormaPagamento());
            stmt.setInt(5, reser.getHospede().getId());
            stmt.executeUpdate();
            ResultSet rst = stmt.getGeneratedKeys();
            while (rst.next()) {
                reser.setId(rst.getInt("id"));
                reser.setNumeroReserva(rst.getInt("numeroreserva"));
            }
            System.out.println(reser);
            return reser;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Reservas> getReservasByHospedes(Integer id) {
        try {
            PreparedStatement stmt = db.getConnection().prepareStatement("SELECT * FROM reservas WHERE fkidhospede = ?");
            stmt.setInt(1, id);
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

    public void deleteHospedes(Integer id) {
        try {
            PreparedStatement stmt = db.getConnection().prepareStatement("DELETE FROM hospedes WHERE id = ?;");
            stmt.setInt(1, id);
            stmt.execute();
            ResultSet rst = stmt.getResultSet();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteReserva(Integer id) {
        try {
            PreparedStatement stmt = db.getConnection().prepareStatement("DELETE FROM reservas WHERE id = ?;");
            stmt.setInt(1, id);
            stmt.execute();
            ResultSet rst = stmt.getResultSet();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Hospedes> createNewLisHospedes(ResultSet rst) {
        List<Hospedes> listAll = new ArrayList<>();
        try {
            while(rst.next()) {
                listAll.add(new Hospedes(
                         rst.getInt("id"),
                        rst.getString("nome"),
                        rst.getString("sobrenome"),
                        rst.getDate("datanascimento"),
                        rst.getString("nascionalidade"),
                        rst.getString("telefone"),
                        getReservasByHospedes( rst.getInt("id"))
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
                        rst.getInt("id"),
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

    public List<Reservas> atualizarReservas(Integer id, Date dataEntrada, Date dataSaida, String valor, Integer type) {
            try (PreparedStatement stm = db.getConnection()
                    .prepareStatement("UPDATE reservas SET dataentrada = ?,  datasaida = ?, valor = ? formapagamento = ? WHERE id = ? RETURNING *;")) {
                stm.setDate(1, dataEntrada);
                stm.setDate(2, dataSaida);
                stm.setString(3, valor);
                stm.setInt(4, type);
                stm.setInt(5, id);
                stm.execute();
                ResultSet rst = stm.getResultSet();
                return createNewLisReservas(rst);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }

    public List<Hospedes> atualizarHospedes(Integer id, String nome, String sobrenome, Date dataNascimento, String nacionalidade, String telefone) {
        try (PreparedStatement stm = db.getConnection()
                .prepareStatement("UPDATE hospedes SET nome = ?, sobrenome= ?, datanascimento  = ? ,nascionalidade = ?, telefone = ? WHERE id = ? RETURNING *;")) {
            stm.setString(1, nome);
            stm.setString(2, sobrenome);
            stm.setDate(3, dataNascimento);
            stm.setString(4, nacionalidade);
            stm.setString(5, telefone);
            stm.setInt(6, id);
            stm.execute();
            ResultSet rst = stm.getResultSet();
            return createNewLisHospedes(rst);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<?> findBy(String type, String value, String table) {
        try {
            String sqlQuery = "SELECT * FROM "+ table +" WHERE "+ type + " = ?;";
            PreparedStatement stmt = db.getConnection().prepareStatement(sqlQuery);
            if (type.equals("id")) {
                stmt.setInt(1, Integer.parseInt(value));
            } else {
                stmt.setString(1,  value);
            }
            stmt.execute();
            ResultSet rst = stmt.getResultSet();
            if (Objects.equals(table, "hospedes")) {
                return createNewLisHospedes(rst);
            } else if (Objects.equals(table, "reservas"))  {
                return createNewLisReservas(rst);
            } else {
                return  new ArrayList<>();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
