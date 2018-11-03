package com.apap.tutorial7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tutorial7.model.PilotModel;
import com.apap.tutorial7.service.PilotService;

/**
 * 
 * PilotController
 * @author ivanabdurrahman
 *
 */
@Controller
public class PilotController {
	@Autowired
	private PilotService pilotService;
	
	@PostMapping(value = "/add")
	public PilotModel addPilotSubmit (@RequestBody PilotModel pilot) {
		return pilotService.addPilot(pilot);
	}
	
	@GetMapping(value = "view/{licenseNumber}")
	public PilotModel pilotView(@PathVariable("licenseNumber") String licenseNumber) {
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber).get();
		return pilot;
	}
	
	@DeleteMapping(value = "/delete")
	public String deletePilot(@RequestParam("pilotId") long pilotId) {
		PilotModel pilot = pilotService.getPilotDetailById(pilotId).get();
		pilotService.deletePilot(pilot);
		return "success";
	}
	
    @PutMapping(value = "/update/{pilotId}")
    public String updatePilotSubmit(@PathVariable("pilotId") long pilotId,
    								@RequestParam("name") String name,
    								@RequestParam("flyHour") int flyHour) {
    	PilotModel pilot = pilotService.getPilotDetailById(pilotId).get();
    	if (pilot.equals(null)) {
    		return "Couldn't find your pilot";
    	}
    	
    	pilot.setName(name);
    	pilot.setFlyHour(flyHour);
    	pilotService.updatePilot(pilotId, pilot);
    	return "update";
    }
}
