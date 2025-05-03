package java_core_api.api_java_core.dtos;


import java.math.BigDecimal;

public class CoordenadasDTO {

    private BigDecimal latitude;
    private BigDecimal longitude;

    public CoordenadasDTO(BigDecimal latitude, BigDecimal longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "CoordenadasDTO{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
