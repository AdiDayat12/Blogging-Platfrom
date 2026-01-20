package com.linan.blogging_platform.dao;


import java.sql.Timestamp;
import java.util.Set;

public class BlogResponse {
        private Long id;
        private String title;
        private String content;
        private String category;
        private Set<String> tags;
        private Timestamp createdAt;
        private Timestamp updatedAt;

        public BlogResponse() {
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getTitle() {
                return title;
        }

        public void setTitle(String title) {
                this.title = title;
        }

        public String getContent() {
                return content;
        }

        public void setContent(String content) {
                this.content = content;
        }

        public String getCategory() {
                return category;
        }

        public void setCategory(String category) {
                this.category = category;
        }

        public Set<String> getTags() {
                return tags;
        }

        public void setTags(Set<String> tags) {
                this.tags = tags;
        }

        public Timestamp getCreatedAt() {
                return createdAt;
        }

        public void setCreatedAt(Timestamp createdAt) {
                this.createdAt = createdAt;
        }

        public Timestamp getUpdatedAt() {
                return updatedAt;
        }

        public void setUpdatedAt(Timestamp updatedAt) {
                this.updatedAt = updatedAt;
        }
}
