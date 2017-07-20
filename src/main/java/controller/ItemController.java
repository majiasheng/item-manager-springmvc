package controller;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dbm.ItemManager;
import model.Item;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

@Controller
@ControllerAdvice
public class ItemController {
	
	@InitBinder
	public void InitBinder(WebDataBinder binder) {
		
		// can use binder.setDisallowedFields() to un-bind a property
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");

		// use a customized date format for "dateAcquired" request param
		binder.registerCustomEditor(Date.class, "dateAcquired" ,new CustomDateEditor(simpleDateFormat, false));
		
	}
	
	@RequestMapping(value="/add-item", method = RequestMethod.POST)
	protected ModelAndView handleAddItem(@ModelAttribute("item") Item item, BindingResult result) {
		
		// validate form input 
		if(result.hasErrors()) {
			return new ModelAndView("add-item");
		}
		
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
		modelAndView.addObject("item", ItemManager.getItemById(id));
		// send model(data) to view(self)
		return modelAndView;
	}

	@RequestMapping(value="/update-item", method = RequestMethod.POST)
	private ModelAndView handleUpdateItem(@ModelAttribute("item") Item item, BindingResult result) {
		
		// validate form input 
		if(result.hasErrors()) {
			return new ModelAndView("edit-item").addObject(item);
		}
		
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
	protected ModelAndView handleRemoveItem(@RequestParam("id") int id) {
		
		ModelAndView modelAndView = new ModelAndView("index");
		
		if(ItemManager.removeItem(id) == -1) {
			
			modelAndView.addObject("error", "Failed to delete item");
			
			System.err.println("Failed to delete item");
		}
		return modelAndView;
		
	}

}