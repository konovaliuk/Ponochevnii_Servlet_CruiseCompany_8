package ua.study.poject.cruise.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * This class is an entity that corresponds to a row in the "ship" table
 */
public class Ship implements Serializable {

    private static final long serialVersionUID = 8641102167229772472L;

    private Long id = -1L;
    private String shipName;
    private Long nStaff;
    private Long nFirstClass;
    private Long nSecondClass;
    private Long nThirdClass;
    private Long nFourthClass;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }


    public Long getNStaff() {
        return nStaff;
    }

    public void setNStaff(Long nStaff) {
        this.nStaff = nStaff;
    }


    public Long getNFirstClass() {
        return nFirstClass;
    }

    public void setNFirstClass(Long nFirstClass) {
        this.nFirstClass = nFirstClass;
    }


    public Long getNSecondClass() {
        return nSecondClass;
    }

    public void setNSecondClass(Long nSecondClass) {
        this.nSecondClass = nSecondClass;
    }


    public Long getNThirdClass() {
        return nThirdClass;
    }

    public void setNThirdClass(Long nThirdClass) {
        this.nThirdClass = nThirdClass;
    }


    public Long getNFourthClass() {
        return nFourthClass;
    }

    public void setNFourthClass(Long nFourthClass) {
        this.nFourthClass = nFourthClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ship ship = (Ship) o;
        return id.equals(ship.id) &&
                nStaff.equals(ship.nStaff) &&
                nFirstClass.equals(ship.nFirstClass) &&
                nSecondClass.equals(ship.nSecondClass) &&
                nThirdClass.equals(ship.nThirdClass) &&
                nFourthClass.equals(ship.nFourthClass) &&
                Objects.equals(shipName, ship.shipName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, shipName, nStaff, nFirstClass, nSecondClass, nThirdClass, nFourthClass);
    }

    @Override
    public String toString() {
        return "id: " + id +
                ", shipName: " + shipName +
                ", nStaff: " + nStaff +
                ", nFirstClass: " + nFirstClass +
                ", nSecondClass: " + nSecondClass +
                ", nThirdClass: " + nThirdClass +
                ", nFourthClass: " + nFourthClass;
    }

}
