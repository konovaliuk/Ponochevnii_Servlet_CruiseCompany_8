package ua.study.poject.cruise.entity;


import java.io.Serializable;
import java.util.Objects;

public class TicketExcursion implements Serializable {

    private static final long serialVersionUID = 3524891573333897077L;

    private Long id = -1L;
    private Long excurisionId;
    private Long userId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getExcurisionId() {
        return excurisionId;
    }

    public void setExcurisionId(Long excurisionId) {
        this.excurisionId = excurisionId;
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
        TicketExcursion that = (TicketExcursion) o;
        return id.equals(that.id) &&
                excurisionId.equals(that.excurisionId) &&
                userId.equals(that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, excurisionId, userId);
    }

    @Override
    public String toString() {
        return "id: " + id +
                ", excurisionId: " + excurisionId +
                ", userId: " + userId;
    }

}
