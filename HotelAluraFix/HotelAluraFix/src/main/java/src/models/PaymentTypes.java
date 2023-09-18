package src.models;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public enum PaymentTypes {
    CreditCard("Cartão de Crédito", 0),
    DebitCard("Cartão de Débito", 1),

    Money("Dinheiro", 2);

    private String value;
    private Integer type;

    PaymentTypes(String value, Integer i) {
        this.value = value;
        this.type = i;
    }

    static public List<String> getListOfString() {
        return  Arrays.stream(PaymentTypes.values()).map(PaymentTypes::getValue).collect(Collectors.toList());
    }

   static public Optional<PaymentTypes> fromString(String type) {
        return Arrays.stream(PaymentTypes.values()).filter(pt -> pt.getValue().equals(type)).findAny();
    }

    static public Optional<PaymentTypes> fromInt(Integer type) {
        return Arrays.stream(PaymentTypes.values()).filter(pt -> pt.getType().equals(type)).findAny();
    }
        public String getValue() {
        return value;
    }

    public Integer getType() {
        return type;
    }
}
