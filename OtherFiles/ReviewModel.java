package com.astro.astro_guruvani_astro.OtherFiles;

public class ReviewModel {

        private int id;
        private String username;
        private String userid;
        private String starRating;
        private String comment;
        private String astroId;
        private String reply;


        // Add getters and setters as needed

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getuserid() {
            return userid;
        }

        public void setuserid(String userid) {
            this.userid = userid;
        }

        public String getStarRating() {
            return starRating;
        }

        public void setStarRating(String starRating) {
            this.starRating = starRating;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getAstroId() {
            return astroId;
        }

        public void setAstroId(String astroId) {
            this.astroId = astroId;
        }

        public String getReply() {
            return reply;
        }

        public void setReply(String reply) {
            this.reply = reply;
        }
}