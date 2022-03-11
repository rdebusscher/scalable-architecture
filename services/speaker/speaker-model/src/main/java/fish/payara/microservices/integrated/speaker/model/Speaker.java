package fish.payara.microservices.integrated.speaker.model;


import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Locale;

public class Speaker implements Serializable {


    private String id;
    @NotNull
    private String nameFirst;
    @NotNull
    private String nameLast;
    @NotNull
    private String organization;

    private String biography;
    private String picture;
    private String twitterHandle;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameFirst() {
        return this.nameFirst;
    }

    public void setNameFirst(String nameFirst) {
        this.nameFirst = nameFirst;
    }

    public String getNameLast() {
        return this.nameLast;
    }

    public void setNameLast(String nameLast) {
        this.nameLast = nameLast;
    }

    public String getOrganization() {
        return this.organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getBiography() {
        return this.biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getPicture() {
        return this.picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getTwitterHandle() {
        return this.twitterHandle;
    }

    public void setTwitterHandle(String twitterHandle) {
        this.twitterHandle = twitterHandle;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Speaker)) {
            return false;
        }

        Speaker speaker = (Speaker) o;

        if (!nameFirst.equalsIgnoreCase(speaker.nameFirst)) {
            return false;
        }
        if (!nameLast.equalsIgnoreCase(speaker.nameLast)) {
            return false;
        }
        return organization.equalsIgnoreCase(speaker.organization);
    }

    @Override
    public int hashCode() {
        int result = nameFirst.toLowerCase(Locale.ENGLISH).hashCode();
        result = 31 * result + nameLast.toLowerCase(Locale.ENGLISH).hashCode();
        result = 31 * result + organization.toLowerCase(Locale.ENGLISH).hashCode();
        return result;
    }
}
