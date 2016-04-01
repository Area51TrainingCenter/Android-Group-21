package pe.area51.parcelableexample;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;

public class Address implements Parcelable {

    private final String street;
    private final String city;
    private final String country;
    private final Location location;

    public Address(String street, String city, String country, Location location) {
        this.street = street;
        this.city = city;
        this.country = country;
        this.location = location;
    }

    protected Address(Parcel in) {
        street = in.readString();
        city = in.readString();
        country = in.readString();
        location = in.readParcelable(Location.class.getClassLoader());
    }

    public static final Creator<Address> CREATOR = new Creator<Address>() {
        @Override
        public Address createFromParcel(Parcel in) {
            return new Address(in);
        }

        @Override
        public Address[] newArray(int size) {
            return new Address[size];
        }
    };

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", location=" + location +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(street);
        dest.writeString(city);
        dest.writeString(country);
        dest.writeParcelable(location, flags);
    }
}
