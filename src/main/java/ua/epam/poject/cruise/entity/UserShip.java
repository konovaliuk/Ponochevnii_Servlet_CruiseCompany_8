package ua.epam.poject.cruise.entity;


import java.io.Serializable;
import java.util.Objects;

public class UserShip implements Serializable {

  private static final long serialVersionUID = -8580238139874789280L;

  private Long id = -1L;
  private Long shipId;
  private Long userId;


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


  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserShip userShip = (UserShip) o;
    return id.equals(userShip.id) &&
            shipId.equals(userShip.shipId) &&
            userId.equals(userShip.userId);
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
