package library.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="genres")
public class Genre {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name", unique=true, nullable=false)
	private String name;
	
	@ManyToMany
	@JoinTable(name="books_genres", joinColumns=@JoinColumn(name="genre_id"), inverseJoinColumns=@JoinColumn(name="book_id"))
	private Set<Book> books;

}
