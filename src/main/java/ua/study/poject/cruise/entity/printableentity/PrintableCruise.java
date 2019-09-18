package ua.study.poject.cruise.entity.printableentity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class PrintableCruise implements Serializable {

    private static final long serialVersionUID = 5332992361975215114L;

    private Long cruiseId;
    private Long shipId;
    private String shipName;
    private double priceFirstClass;
    private double priceSecondClass;
    private double priceThirdClass;
    private double priceFourthClass;
    private List<PrintableCruisePort> printableCruisePorts;

    public Long getCruiseId() {
        return cruiseId;
    }

    public void setCruiseId(Long cruiseId) {
        this.cruiseId = cruiseId;
    }

    public Long getShipId() {
        return shipId;
    }

    public void setShipId(Long shipId) {
        this.shipId = shipId;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
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

    public List<PrintableCruisePort> getPrintableCruisePorts() {
        return printableCruisePorts;
    }

    public void setPrintableCruisePorts(List<PrintableCruisePort> printableCruisePorts) {
        this.printableCruisePorts = printableCruisePorts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrintableCruise that = (PrintableCruise) o;
        return Double.compare(that.priceFirstClass, priceFirstClass) == 0 &&
                Double.compare(that.priceSecondClass, priceSecondClass) == 0 &&
                Double.compare(that.priceThirdClass, priceThirdClass) == 0 &&
                Double.compare(that.priceFourthClass, priceFourthClass) == 0 &&
                Objects.equals(cruiseId, that.cruiseId) &&
                Objects.equals(shipId, that.shipId) &&
                Objects.equals(shipName, that.shipName) &&
                Objects.equals(printableCruisePorts, that.printableCruisePorts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cruiseId, shipId, shipName, priceFirstClass, priceSecondClass, priceThirdClass, priceFourthClass, printableCruisePorts);
    }

    public String toString() {
        return "CruiseId: " + cruiseId +
                ", shipId: " + shipId +
                ", shipName: " + shipName +
                ", priceFirstClass: " + priceFirstClass +
                ", priceSecondClass: " + priceSecondClass +
                ", priceThirdClass: " + priceThirdClass +
                ", priceFourthClass: " + priceFourthClass +
                ", printableCruisePorts: " + printableCruisePorts;
    }
}
