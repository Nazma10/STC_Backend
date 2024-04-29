package com.stc.construction.controller;

import org.springframework.web.bind.annotation.RestController;

import com.stc.construction.model.Site;
import com.stc.construction.service.SiteService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("sites")
public class SiteController {
    
    @Autowired
    SiteService siteService;

    // Get all sites
    @GetMapping("allSites")
    public ResponseEntity<List<Site>> getAllSites() {
        return siteService.getAllSites();
    }

    // Get site by id
    @GetMapping("get/{id}")
    public ResponseEntity<Site> getSiteById(@RequestParam Integer id) {
        return siteService.getSiteById(id);
    }
    
    // Add site
    @PostMapping("add")
    public ResponseEntity<String> addSite(@RequestBody Site site) {
        return siteService.addSite(site);
    }
    
    // Update site
    @PutMapping("update/{id}")
    public ResponseEntity<String> updateSite(@RequestBody Site site, @PathVariable Integer id) {
        return siteService.updateSite(site, id);
    }

    // Delete site
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteSite(@PathVariable Integer id) {
        return siteService.deleteSite(id);
    }

    // Get site documents

    // Get site photos

    // Add site document

    // Add site photo

    // Delete site document

    // Delete site photo

    // Update site document

    // Update site photo

    // Get site document by id and site id

    // Get site photo by id and site id

    
}
