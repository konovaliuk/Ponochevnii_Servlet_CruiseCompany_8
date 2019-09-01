package ua.epam.poject.cruise.entity;


import java.io.Serializable;
import java.util.Objects;

public class UserShip implements Serializable {

  private static final long serialVersionUID = 4080231475093746548L;

  private int id;
  private int shipId;
  private int userId;


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }


  public int getShipId() {
    return shipId;
  }

  public void setShipId(int shipId) {
    this.shipId = shipId;
  }


  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserShip userShip = (UserShip) o;
    return id == userShip.id &&
            shipId == userShip.shipId &&
            userId == userShip.userId;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, shipId, userId);
  }

  @Override
  public String toString() {
    return new StringBuffer()
            .append("id: ").append(id)
            .append(", shipId: ").append(shipId)
            .append(", userId: ").append(userId)
            .toString();
  }
}