package org.pahappa.systems.views;

import org.pahappa.systems.models.Incident;
import org.pahappa.systems.enums.Type;
import org.pahappa.systems.controllers.IncidentServiceImplementation;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.Arrays;
import java.util.List;


@ManagedBean( name = "ireporterView" )
@SessionScoped
public class IReporter {

    private IncidentServiceImplementation serviceHelper =new IncidentServiceImplementation();
    private Incident incident = new Incident();
    private int code;
    private Incident editIncident = new Incident();
    private List<Incident> redflagIncidents;
    private List<Incident> interventionIncidents;
    private List<Incident> incidents;
    private List<Type> types;

    @PostConstruct
    public void init() {
        System.out.println("Initialising ireporter view bean...");
        this.serviceHelper = new IncidentServiceImplementation();
        this.incidents = fetchIncidents();
        this.redflagIncidents = fetchRedflagIncidents();
        this.interventionIncidents = fetchInterventionIncidents();
        this.types= Arrays.asList(Type.values());
        this.incident = new Incident();
    }


    public List<Incident> fetchIncidents() {
        System.out.println("Calling getAllIncidents");
        return serviceHelper.getAllIncidents();
    }

    public List<Incident> fetchRedflagIncidents() {
        System.out.println("Calling getRedflagIncidents");
        return serviceHelper.getRedflagIncidents();
    }

    public List<Incident> fetchInterventionIncidents() {
        System.out.println("Calling getInterventionIncidents");
        return serviceHelper.getInterventionIncidents();
    }

    public void save(Incident incident) {
        System.out.println("Calling saveIncident");
        try {
            serviceHelper.saveIncident(incident);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.incident = new Incident();
        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "allIncidents.xhtml");
    }

    public void gettingUpdate(){
        editIncident = serviceHelper.getAllIncidents().get(this.code-1);
        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "updater.xhtml");
    }

    public void update(Incident incident) {
        System.out.println("Calling updateIncident");
        try {
            serviceHelper.updateIncident(incident);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.code = 0;
        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "updateIncident.xhtml");
    }

    public void delete() {
        System.out.println("Calling deleteIncident");
        serviceHelper.deleteIncident(serviceHelper.getAllIncidents().get(this.code-1));
        this.code = 0;
        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "allIncidents.xhtml");
    }

    public int incidentNumber() {
        System.out.println("Calling countIncidents");
        return serviceHelper.countIncidents();
    }

    public IncidentServiceImplementation getServiceHelper() {
        return serviceHelper;
    }

    public void setServiceHelper(IncidentServiceImplementation serviceHelper) {
        this.serviceHelper = serviceHelper;
    }

    public List<Incident> getIncidents() {
        return incidents;
    }

    public void setIncidents(List<Incident> incidents) {
        this.incidents = incidents;
    }

    public List<Incident> getRedflagIncidents() {
        return redflagIncidents;
    }

    public void setRedflagIncidents(List<Incident> redflagIncidents) {
        this.redflagIncidents = redflagIncidents;
    }

    public List<Incident> getInterventionIncidents() {
        return interventionIncidents;
    }

    public void setInterventionIncidents(List<Incident> interventionIncidents) {
        this.interventionIncidents = interventionIncidents;
    }

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }

    public Incident getIncident() {
        return incident;
    }

    public void setIncident(Incident incident) {
        this.incident = incident;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Incident getEditIncident() {
        return editIncident;
    }

    public void setEditIncident(Incident editIncident) {
        this.editIncident = editIncident;
    }
}