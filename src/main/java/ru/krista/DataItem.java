package ru.krista;

import jakarta.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement
public class DataItem 
{
    private Integer id;
    private String info;
    private String activities;
    private String authorities;
    private String heads;
    private String facialAccounts;
    private String foAccounts;
    private String contracts;
    private String participantPermissions;
    private String nonParticipantPermissions;
    private String procurementPermissions;
    private String acceptAuths;
    private String transfauth;
    private String ubptransfauthbp;
    private String successions;
    private String contacts;
    private String ubpfinfku;
    private String ubpfin;
    private String ubptransfauthbu;
    private String ksaccounts;
    private String attachment;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getInfo() { return info; }
    public void setInfo(String info) { this.info = info; }

    public String getActivities() { return activities; }
    public void setActivities(String activities) { this.activities = activities; }

    public String getaAthorities() { return authorities; }
    public void setAuthorities(String authorities) { this.authorities = authorities; }

    public String getHeads() { return heads; }
    public void setHeads(String heads) { this.heads = heads; }
    
    public String getFacialAccounts() { return facialAccounts; }
    public void setFacialAccounts(String facialAccounts) { this.facialAccounts = facialAccounts; }

    public String getFoAccounts() { return foAccounts; }
    public void setFoAccounts(String foAccounts) { this.foAccounts = foAccounts; }

    public String getContracts() { return contracts; }
    public void setContracts(String contracts) { this.contracts = contracts; }
    
    public String getParticipantPermissions() { return participantPermissions; }
    public void setParticipantPermissions(String participantPermissions) { this.participantPermissions = participantPermissions; }

    public String getNonParticipantPermissions() { return nonParticipantPermissions; }
    public void setNonParticipantPermissions(String nonParticipantPermissions) { this.nonParticipantPermissions = nonParticipantPermissions; }
    
    public String getProcurementPermissions() { return procurementPermissions; }
    public void setProcurementPermissions(String procurementPermissions) { this.procurementPermissions = procurementPermissions; }

    public String getAcceptAuths() { return acceptAuths; }
    public void setAcceptAuths(String acceptAuths) { this.acceptAuths = acceptAuths; }

    public String getTransfauth() { return transfauth; }
    public void setTransfauth(String transfauth) { this.transfauth = transfauth; }

    public String getUbptransfauthbp() { return ubptransfauthbp; }
    public void setUbptransfauthbp(String ubptransfauthbp) { this.ubptransfauthbp = ubptransfauthbp; }

    public String getSuccessions() { return successions; }
    public void setSuccessions(String successions) { this.successions = successions; }

    public String getContacts() { return contacts; }
    public void setContacts(String contacts) { this.contacts = contacts; }

    public String getUbpfinfku() { return ubpfinfku; }
    public void setUbpfinfku(String ubpfinfku) { this.ubpfinfku = ubpfinfku; }

    public String getUbpfin() { return ubpfin; }
    public void setUbpfin(String ubpfin) { this.ubpfin = ubpfin; }

    public String getUbptransfauthbu() { return ubptransfauthbu; }
    public void setUbptransfauthbu(String ubptransfauthbu) { this.ubptransfauthbu = ubptransfauthbu; }

    public String getKsaccounts() { return ksaccounts; }
    public void setKsaccounts(String ksaccounts) { this.ksaccounts = ksaccounts; }

    public String getAttachment() { return attachment; }
    public void setAttachment(String attachment) { this.attachment = attachment; }


}