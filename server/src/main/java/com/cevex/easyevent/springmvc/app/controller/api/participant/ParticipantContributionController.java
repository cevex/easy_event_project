package com.cevex.easyevent.springmvc.app.controller.api.participant;

import com.cevex.easyevent.springmvc.app.model.ContributionOfParticipant;
import com.cevex.easyevent.springmvc.app.service.ParticipantService;
import com.cevex.easyevent.springmvc.share.framework.RestControllerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

@RestController
@RequestMapping(value = "/participants/{participant_id}/contributions")
public class ParticipantContributionController extends RestControllerValidator {

    @Autowired
    private ParticipantService participantService;

    //=============================================================================================
    //                          Retrieve List of Contributions
    //=============================================================================================

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<ContributionOfParticipant>> getContributions(@PathVariable("participant_id") Long participantId) {
        System.out.println("Fetching Contributions for [participant:" + participantId + "]");
        //TODO : Implement resource ParticipantContribution
        throw new NotImplementedException();
    }

}