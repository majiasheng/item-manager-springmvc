package controller;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dbm.ItemManager;
import model.Item;

import java.io.IOException;

@Controller
@ControllerAdvice
public class ItemController {
	
	@RequestMapping(value="/add-item", method = RequestMethod.POST)
	protected ModelAndView handleAddItem(@ModelAttribute("item") Item item) {
 
		ModelAndView modelAndView = null;
		// add item to db
		if(ItemManager.addItem(item) != -1) {
			modelAndView = new ModelAndView("index");
		} else {
			modelAndView = new ModelAndView("/add-item");
			modelAndView.addObject("error", "Failed to add item to database");
		}	
		return modelAndView;
	}
	
	@RequestMapping(value="/edit-item", method = RequestMethod.POST)
	protected ModelAndView handleEditItem(@RequestParam("id") int id) {
		
		ModelAndView modelAndView = new ModelAndView("edit-item");
		System.out.println("++++++++++ editing item : " + id);
		modelAndView.addObject("item", ItemManager.getItemById(id));
		// send model(data) to view(self)
		return modelAndView;
	}

	@RequestMapping(value="/update-item", method = RequestMethod.POST)
	private ModelAndView handleUpdateItem(@ModelAttribute("item") Item item) {
		
		ModelAndView modelAndView = null;
		
		if(ItemManager.updateItem(item)!=-1) {
			modelAndView = new ModelAndView("index");
		} else {
			modelAndView = new ModelAndView("edit-item");
			modelAndView.addObject("error", "Failed to update item");
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/remove-item", method = RequestMethod.GET)
	//TODO:
	protected void handleRemoveItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		if(ItemManager.removeItem(id) != -1) {
			request.getRequestDispatcher("index").forward(request, response);
		} else {
			System.err.println("Failed to update item");
		}
		
	}

}