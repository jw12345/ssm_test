package com.jw.ssm.pojo;

public class Book {
   
	private Integer bookId;

    private String name;

    private String description;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
    
    @Override
	public String toString() {
		return "Book [bookId=" + bookId + ", description=" + description
				+ ", name=" + name + "]";
	}
}