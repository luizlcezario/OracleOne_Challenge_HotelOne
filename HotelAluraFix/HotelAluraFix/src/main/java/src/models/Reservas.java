package src.models;

import java.sql.Date;
import java.util.Objects;

public class Reservas {
   private Integer id;
   private Integer numeroReserva;
   private Date dataEntrada;
   private Date dataSaida;
   private Double valor;
   private Integer formaPagamento;

   private Hospedes hospede;

   public Integer getNumeroReserva() {
      return numeroReserva;
   }

   public void setNumeroReserva(Integer numeroReserva) {
      this.numeroReserva = numeroReserva;
   }

   public Hospedes getHospede() {
      return hospede;
   }

   public void setHospede(Hospedes hospede) {
      this.hospede = hospede;
   }

   public Reservas() {
   }

   public Reservas(Integer id, Integer numberoReserva, Date dataEntrada, Date dataSaida, Double valor, Integer formaPagamento) {
      this.id = id;
      this.numeroReserva = numberoReserva;
      this.dataEntrada = dataEntrada;
      this.dataSaida = dataSaida;
      this.valor = valor;
      this.formaPagamento = formaPagamento;
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public Date getDataEntrada() {
      return dataEntrada;
   }

   public void setDataEntrada(Date dataEntrada) {
      this.dataEntrada = dataEntrada;
   }

   public Date getDataSaida() {
      return dataSaida;
   }

   public void setDataSaida(Date dataSaida) {
      this.dataSaida = dataSaida;
   }

   public Double getValor() {
      return valor;
   }

   public void setValor(Double valor) {
      this.valor = valor;
   }

   public Integer getFormaPagamento() {
      return formaPagamento;
   }

   public void setFormaPagamento(Integer formaPagamento) {
      this.formaPagamento = formaPagamento;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Reservas)) return false;
      Reservas reservas = (Reservas) o;
      return Objects.equals(id, reservas.id) && Objects.equals(numeroReserva, reservas.numeroReserva) && Objects.equals(dataEntrada, reservas.dataEntrada) && Objects.equals(dataSaida, reservas.dataSaida) && Objects.equals(valor, reservas.valor) && Objects.equals(formaPagamento, reservas.formaPagamento);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, numeroReserva, dataEntrada, dataSaida, valor, formaPagamento);
   }

}
