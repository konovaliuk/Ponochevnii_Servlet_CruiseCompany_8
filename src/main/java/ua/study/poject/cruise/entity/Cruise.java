package ua.study.poject.cruise.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * This class is an entity that corresponds to a row in the "cruise" table
 */
public class Cruise implements Serializable {

    private static final long serialVersionUID = 3630956459922839101L;

    private Long id = -1L;
    private Long shipId;
    private double priceFirstClass;
    private double priceSecondClass;
    private double priceThirdClass;
    private double priceFourthClass;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getShipId() {
        return shipId;
    }

    public void setShipId(Long shipId) {
        this.shipId = shipId;
    }


    public double getPriceFirstClass() {
        return priceFirstClass;
    }

    public void setPriceFirstClass(double priceFirstClass) {
        this.priceFirstClass = priceFirstClass;
    }


    public double getPriceSecondClass() {
        return priceSecondClass;
    }

    public void setPriceSecondClass(double priceSecondClass) {
        this.priceSecondClass = priceSecondClass;
    }


    public double getPriceThirdClass() {
        return priceThirdClass;
    }

    public void setPriceThirdClass(double priceThirdClass) {
        this.priceThirdClass = priceThirdClass;
    }


    public double getPriceFourthClass() {
        return priceFourthClass;
    }

    public void setPriceFourthClass(double priceFourthClass) {
        this.priceFourthClass = priceFourthClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cruise cruise = (Cruise) o;
        return id.equals(cruise.id) &&
                shipId.equals(cruise.shipId) &&
                Double.compare(cruise.priceFirstClass, priceFirstClass) == 0 &&
                Double.compare(cruise.priceSecondClass, priceSecondClass) == 0 &&
                Double.compare(cruise.priceThirdClass, priceThirdClass) == 0 &&
                Double.compare(cruise.priceFourthClass, priceFourthClass) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, shipId, priceFirstClass, priceSecondClass, priceThirdClass, priceFourthClass);
    }

    @Override
    public String toString() {
        return "id: " + id +
                ", shipId: " + shipId +
                ", priceFirstClass: " + priceFirstClass +
                ", priceSecondClass: " + priceSecondClass +
                ", priceThirdClass: " + priceThirdClass +
                ", priceFourthClass: " + priceFourthClass;
    }
}
