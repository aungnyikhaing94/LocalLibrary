package toppan.library.DTOs;


import java.util.List;

import lombok.Data;

@Data
public class BookDTO {
	private String author;
	private String name;
	private List<String> borrowers;
}
