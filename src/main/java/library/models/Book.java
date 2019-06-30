package library.models;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="books")
public class Book implements Comparable<Book> {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="available")
	private int available;
	
	@ManyToMany
	@JoinTable(name="books_authors", joinColumns=@JoinColumn(name="book_id"), inverseJoinColumns=@JoinColumn(name="author_id"))
	private Set<Author> authors;
	
	@ManyToMany
	@JoinTable(name="books_genres", joinColumns=@JoinColumn(name="book_id"), inverseJoinColumns=@JoinColumn(name="genre_id"))
	private Set<Genre> genres;
	
	@OneToMany(mappedBy="book")
	private List<Checkout> checkedList;

	@Override
	public int compareTo(Book o) {
		return title.compareTo(o.title);
	}
}
