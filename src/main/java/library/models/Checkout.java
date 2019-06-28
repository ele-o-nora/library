package library.models;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "books_students")
public class Checkout {

	@Embeddable
	public static class Key implements Serializable {
		
		static final long serialVersionUID = 1;
		private int bookId;
		private int studentId;

		public Key(int bookId, int studentId) {
			this.bookId = bookId;
			this.studentId = studentId;
		}

		@Override
		public int hashCode() {
			return bookId * 31 + studentId;
		}

		@Override
		public boolean equals(Object that) {
			return (this == that) || ((that instanceof Key) && this.bookId == ((Key) that).bookId
					&& this.studentId == ((Key) that).studentId);
		}

	}

	@EmbeddedId
	private Key id;

	@ManyToOne
	@JoinColumn(name = "book_id")
	@MapsId("bookId")
	private Book book;

	@ManyToOne
	@JoinColumn(name = "student_id")
	@MapsId("studentId")
	private Student student;

	@Column(name = "checkout_date", nullable=false)
	private LocalDate checkoutDate;

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public LocalDate getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(LocalDate checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

}
