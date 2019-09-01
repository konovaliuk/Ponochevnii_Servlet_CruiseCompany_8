package ua.epam.poject.cruise.entity;


import java.io.Serializable;
import java.util.Objects;

public class Ship implements Serializable {

    private static final long serialVersionUID = 7166590378846917289L;

    private int id;
    private String shipName;
    private int nStaff;
    private int nFirstClass;
    private int nSecondClass;
    private int nThirdClass;
    private int nFourthClass;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }


    public int getNStaff() {
        return nStaff;
    }

    public void setNStaff(int nStaff) {
        this.nStaff = nStaff;
    }


    public int getNFirstClass() {
        return nFirstClass;
    }

    public void setNFirstClass(int nFirstClass) {
        this.nFirstClass = nFirstClass;
    }


    public int getNSecondClass() {
        return nSecondClass;
    }

    public void setNSecondClass(int nSecondClass) {
        this.nSecondClass = nSecondClass;
    }


    public int getNThirdClass() {
        return nThirdClass;
    }

    public void setNThirdClass(int nThirdClass) {
        this.nThirdClass = nThirdClass;
    }


    public int getNFourthClass() {
        return nFourthClass;
    }

    public void setNFourthClass(int nFourthClass) {
        this.nFourthClass = nFourthClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ship ship = (Ship) o;
        return id == ship.id &&
                nStaff == ship.nStaff &&
                nFirstClass == ship.nFirstClass &&
                nSecondClass == ship.nSecondClass &&
                nThirdClass == ship.nThirdClass &&
                nFourthClass == ship.nFourthClass &&
                Objects.equals(shipName, ship.shipName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, shipName, nStaff, nFirstClass, nSecondClass, nThirdClass, nFourthClass);
    }

    @Override
    public String toString() {
        return new StringBuffer()
                .append("id: ").append(id)
                .append(", shipName: ").append(shipName)
                .append(", nStaff: ").append(nStaff)
                .append(", nFirstClass: ").append(nFirstClass)
                .append(", nSecondClass: ").append(nSecondClass)
                .append(", nThirdClass: ").append(nThirdClass)
                .append(", nFourthClass: ").append(nFourthClass)
                .toString();
    }

}
