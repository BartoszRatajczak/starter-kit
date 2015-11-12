package pl.spring.demo.web.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

@Controller
public class BookController {
	@Autowired
	private BookService bookService;

	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public String bookList(Map<String, Object> params) {
		final List<BookTo> allBooks = bookService.findAllBooks();
		params.put("books", allBooks);
		return "bookList";
	}

	@RequestMapping(value = "/books/delete", method = RequestMethod.GET)
	public String deleteBook(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) throws UnsupportedEncodingException {
		List<BookTo> bookList = bookService.findAllBooks();
		BookTo book = new BookTo();
		for (BookTo bookTo : bookList) {
			if (bookTo.getId().equals(id)) {
				book = bookService.deleteBook(id);
			}
		}
		if (book.getId() == null) {
			redirectAttributes.addAttribute("id", id);
			return "redirect:/bookDeleteError";
		}
		redirectAttributes.addAttribute("title", URLEncoder.encode(book.getTitle(), "UTF-8"));
		return "redirect:/bookDelete";
	}
	
	@RequestMapping(value = "/bookDelete", method = RequestMethod.GET)
	public String bookDeleteInfo(@RequestParam("title") String title, Map<String, Object> params) throws UnsupportedEncodingException {
		params.put("title", URLDecoder.decode(title, "UTF-8"));
		return "bookDelete";
	}
	
	@RequestMapping(value = "/bookDeleteError", method = RequestMethod.GET)
	public String bookDeleteError(@RequestParam("id") Long id, Map<String, Object> params) throws UnsupportedEncodingException {
		params.put("id", id);
		return "wrongBookId";
	}
}
