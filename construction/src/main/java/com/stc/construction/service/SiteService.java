package com.stc.construction.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.stc.construction.doa.SiteDoa;
import com.stc.construction.model.Site;

@Service
public class SiteService {
    
    @Autowired
    SiteDoa siteDoa;

    // Get all sites
    public ResponseEntity<List<Site>> getAllSites() {
        try {
           return new ResponseEntity<>(siteDoa.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    // Get site by id
    public ResponseEntity<Site> getSiteById(Integer id) {
        try {
           return new ResponseEntity<>(siteDoa.findById(id).get(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // Add site
    public ResponseEntity<String> addSite(Site site) {
        try {
            siteDoa.save(site);
            return new ResponseEntity<>("Site added successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error couldn't add site", HttpStatus.BAD_REQUEST);
    }

    // Update site
    public ResponseEntity<String> updateSite(Site site, Integer id) {
        try {
            Site existingSite = siteDoa.findById(id).orElseThrow();

            existingSite.setEmail(site.getEmail());
            existingSite.setContact_no(site.getContact_no());
            existingSite.setEnd_date(site.getEnd_date());
            existingSite.setStatus(site.getStatus());

            siteDoa.save(existingSite);
            return new ResponseEntity<>("Site details updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Site not found", HttpStatus.BAD_REQUEST);
    }

    // Delete site
    public ResponseEntity<String> deleteSite(Integer id) {
        try {
           Site existingSite = siteDoa.findById(id).orElseThrow();
           siteDoa.delete(existingSite);

           return new ResponseEntity<>("Site deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Site not found", HttpStatus.BAD_REQUEST);
    }
}
