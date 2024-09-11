
package com.qa.ouath2.pojo;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@Builder
@Jacksonized
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Playlist {

    @JsonProperty("amc_order_id")
    private Integer amcOrderId;
    @JsonProperty("status")
    private String status;
    @JsonProperty("plan")
    private String plan;
    @JsonProperty("firstname")
    private String firstname;
    @JsonProperty("middlename")
    private String middlename;
    @JsonProperty("lastname")
    private String lastname;
    @JsonProperty("type")
    private String type;
    @JsonProperty("contact")
    private String contact;

    Playlist(Integer amcOrderId, String status, String plan, String firstname, String middlename, String lastname, String type, String contact) {
        this.amcOrderId = amcOrderId;
        this.status = status;
        this.plan = plan;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.type = type;
        this.contact = contact;
    }

    public static PlaylistBuilder builder() {
        return new PlaylistBuilder();
    }

    @JsonProperty("middlename")
    public String getMiddlename() {
        return middlename;
    }

    @JsonProperty("middlename")
    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    @JsonProperty("lastname")
    public String getLastname() {
        return lastname;
    }

    @JsonProperty("lastname")
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("contact")
    public String getContact() {
        return contact;
    }

    @JsonProperty("contact")
    public void setContact(String contact) {
        this.contact = contact;
    }

    public static class PlaylistBuilder {
        private Integer amcOrderId;
        private String status;
        private String plan;
        private String firstname;
        private String middlename;
        private String lastname;
        private String type;
        private String contact;

        PlaylistBuilder() {
        }

        @JsonProperty("amc_order_id")
        public PlaylistBuilder amcOrderId(Integer amcOrderId) {
            this.amcOrderId = amcOrderId;
            return this;
        }

        @JsonProperty("status")
        public PlaylistBuilder status(String status) {
            this.status = status;
            return this;
        }

        @JsonProperty("plan")
        public PlaylistBuilder plan(String plan) {
            this.plan = plan;
            return this;
        }

        @JsonProperty("firstname")
        public PlaylistBuilder firstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        @JsonProperty("middlename")
        public PlaylistBuilder middlename(String middlename) {
            this.middlename = middlename;
            return this;
        }

        @JsonProperty("lastname")
        public PlaylistBuilder lastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        @JsonProperty("type")
        public PlaylistBuilder type(String type) {
            this.type = type;
            return this;
        }

        @JsonProperty("contact")
        public PlaylistBuilder contact(String contact) {
            this.contact = contact;
            return this;
        }

        public Playlist build() {
            return new Playlist(this.amcOrderId, this.status, this.plan, this.firstname, this.middlename, this.lastname, this.type, this.contact);
        }

        public String toString() {
            return "Playlist.PlaylistBuilder(amcOrderId=" + this.amcOrderId + ", status=" + this.status + ", plan=" + this.plan + ", firstname=" + this.firstname + ", middlename=" + this.middlename + ", lastname=" + this.lastname + ", type=" + this.type + ", contact=" + this.contact + ")";
        }
    }
}
