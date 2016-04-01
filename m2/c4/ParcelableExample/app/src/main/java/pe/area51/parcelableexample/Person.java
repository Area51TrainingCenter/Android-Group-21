package pe.area51.parcelableexample;

import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable {

    private final String firstName;
    private final String lastName;
    private final int age;
    private final Address address;
    private final String phone;
    private final String email;
    private final String idCard;

    public Person(final Parcel parcel) {
        this.firstName = parcel.readString();
        this.lastName = parcel.readString();
        this.age = parcel.readInt();
        this.address = parcel.readParcelable(Address.class.getClassLoader());
        this.phone = parcel.readString();
        this.email = parcel.readString();
        this.idCard = parcel.readString();
    }

    public Person(String firstName, String lastName, int age, Address address, String phone, String email, String idCard) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.idCard = idCard;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public Address getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getIdCard() {
        return idCard;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", address=" + address +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", idCard='" + idCard + '\'' +
                '}';
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {

        @Override
        public Person createFromParcel(Parcel source) {
            return new Person(source);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }

    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeInt(age);
        dest.writeParcelable(address, flags);
        dest.writeString(phone);
        dest.writeString(email);
        dest.writeString(idCard);
    }
}
