package com.tracker.recordSearch.service;


import com.tracker.recordSearch.domain.MemberShip;
import com.tracker.recordSearch.repository.MemberShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberShipService {
    @Autowired
    private MemberShipRepository memberShipRepository;

    public MemberShip saveMemberShip(MemberShip memberShip) {
        return memberShipRepository.save(memberShip);
    }

    public List<MemberShip> allContacts(){
        return (List<MemberShip>) memberShipRepository.findAll();
    }
}
