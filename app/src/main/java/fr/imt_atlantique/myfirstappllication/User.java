package fr.imt_atlantique.myfirstappllication;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

public class User implements Parcelable {
    private String nom;
    private String prenom;
    private String villeNaissance;
    private String dateNaissance;
    private String departementNaissance;
    private List<String> phoneNumbers;

    // Constructor
    public User(String nom, String prenom, String villeNaissance, String dateNaissance, String departementNaissance, List<String> phoneNumbers) {
        this.nom = nom;
        this.prenom = prenom;
        this.villeNaissance = villeNaissance;
        this.dateNaissance = dateNaissance;
        this.departementNaissance = departementNaissance;
        this.phoneNumbers = phoneNumbers;
    }

    // Parcelable implementation
    protected User(Parcel in) {
        nom = in.readString();
        prenom = in.readString();
        villeNaissance = in.readString();
        dateNaissance = in.readString();
        departementNaissance = in.readString();
        phoneNumbers = in.createStringArrayList();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nom);
        dest.writeString(prenom);
        dest.writeString(villeNaissance);
        dest.writeString(dateNaissance);
        dest.writeString(departementNaissance);
        dest.writeStringList(phoneNumbers);
    }

    // Getters
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getVilleNaissance() { return villeNaissance; }
    public String getDateNaissance() { return dateNaissance; }
    public String getDepartementNaissance() { return departementNaissance; }
    public List<String> getPhoneNumbers() { return phoneNumbers; }
}
