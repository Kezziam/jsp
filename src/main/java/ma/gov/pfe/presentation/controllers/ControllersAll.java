 package ma.gov.pfe.presentation.controllers;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ma.gov.pfe.modeles.Abonnees;
import ma.gov.pfe.modeles.Echanges;
import ma.gov.pfe.modeles.Professions;
import ma.gov.pfe.modeles.Quartiers;
import ma.gov.pfe.modeles.Rooms;
import ma.gov.pfe.modeles.Visites;
import ma.gov.pfe.services.Iservices;

@Controller
@RequestMapping("/hello")
public class ControllersAll {
	
	@Autowired
	Iservices service;

	
	@RequestMapping(value="/create")
	public ModelAndView open( @ModelAttribute("abonnee") Abonnees abonnee, Quartiers quartier, Professions profession) {
		System.out.println("Call Contoller Method.....");
		if (quartier == null && profession==null && abonnee==null){
			quartier = new Quartiers();
			profession = new Professions();
			abonnee= new Abonnees();
		}
		@SuppressWarnings("unchecked")
		List<Quartiers> listq = (List<Quartiers>) service.selectAll(quartier);
		@SuppressWarnings("unchecked")
		List<Professions> listp = (List<Professions>) service.selectAll(profession);
		
		 Set<Professions> sp= new HashSet<>();
	     for(Professions pf: listp){  	sp.add(pf);    }
	     
	     Set<Quartiers> sq= new HashSet<>();
	     for(Quartiers qrt: listq){  	sq.add(qrt);    }
		
		ModelAndView mv = new ModelAndView("create_abonnee");
		mv.addObject("quartiers", listq);
		mv.addObject("professions", sp);
		return mv;
	}
	
	@RequestMapping(value="/inscrier" , method=RequestMethod.POST)
	public ModelAndView insertabonnee( @ModelAttribute("abonnee") Abonnees abonnee) {
	
		try {
			System.out.println("Call Contoller INSERT ...");
		 
			service.insert(abonnee);
			
			ModelAndView mv = new ModelAndView("login");
			return mv;
		} catch (Exception e) {
			
			System.out.println(e.getStackTrace());
			System.out.println(e.getMessage());
			ModelAndView mv2 = new ModelAndView("redirect:/hello/create");
			return mv2; 
		}
		
	}	
	

		
	@RequestMapping(value="/visiter/{id_room}")
	public ModelAndView insertvisite( @PathVariable("id_room") Long id_room,Rooms room, Principal principal,@ModelAttribute("visite") Visites visite) {
	
		
		System.out.println("=============CALL VISITE===================");
		try {
			String name = principal.getName();
		    
			Abonnees abonnee= service.getAbonnee(name);
			Rooms room1 = (Rooms) service.selectById(room, id_room);
			
			boolean find=service.findByRoomAbonnee(id_room,abonnee.getId_abonnee());

			if(find==true){
				Long idv=service.getIdVisite(id_room,abonnee.getId_abonnee());
				
				visite=new Visites(idv);
				service.update(visite);
				
			}else{
				visite=new Visites(abonnee,room1);
				service.insert(visite);
			}	
			
			ModelAndView mv = new ModelAndView("redirect:/hello/callrom");
			return mv;
		} catch (Exception e) {
			
			System.out.println(e.getStackTrace());
			System.out.println(e.getMessage());
			System.out.println("============= not CALL VISITE--------");
			ModelAndView mv2 = new ModelAndView("redirect:/welcome");
			return mv2; 
		}
		
	}	
	
	@RequestMapping(value = "/callrom", method = RequestMethod.GET)
	public ModelAndView roomAll( ModelMap pModel,Abonnees abonnee, Rooms room, Echanges echange,Principal principal){
		System.out.println("--------------CALL ROOMS--------------");
		
		if (room == null && echange==null && abonnee==null){
			room = new Rooms();
			echange= new Echanges();
			abonnee= new Abonnees();
		}
		
		
		
		ModelAndView mv = new ModelAndView("roomsall");		
		return mv;
	}
	

	
	

}
