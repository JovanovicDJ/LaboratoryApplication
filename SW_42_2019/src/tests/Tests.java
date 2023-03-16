package tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import entities.other.Analysis;
import entities.other.MedicalResult;
import entities.users.User;
import main.service.AnalyseLoader;
import main.service.MedResultLoader;
import main.service.UserLoader;
import main.service.laborant.AnalyzerForMedResult;
import main.service.owner.AddAnalysis;
import main.service.owner.AddWorker;
import main.service.owner.BonusChanger;
import main.service.owner.CoefficientChanger;
import main.service.owner.VisitPriceChanger;
import main.service.patient.CreatingRequestPatient;
import main.service.patient.ResultsReviewer;
import main.service.technician.AddPatient;
import main.service.technician.CreatingRequestMedTech;
import main.service.technician.SamplesPickingReview;

public class Tests {
	
	String path = ".\\src\\data\\typesOfAnalysis.txt";
	AnalyseLoader al = new AnalyseLoader(path);
	String pathMedRes = ".\\src\\data\\medicalResults.txt";
	MedResultLoader mrl = new MedResultLoader(pathMedRes);
	String pathUsers = ".\\src\\data\\users.txt";
	UserLoader userload = new UserLoader(pathUsers);


	@Test
	public void testGetAnalysisByID() {
		assertEquals("0009", al.getAnalysisByID("0009").getId());
	}
	
	@Test
	public void testUpdateAnalysis() {
		boolean b = al.updateAnalysis(al.getAnalyses(), path);
		assertEquals("true", String.valueOf(b));
		
	}
	
	@Test
	public void testGetAnalysis() {
		List<Analysis> list = al.getAnalyses();
		assertEquals(list, al.getAnalyses());
	}
	
	@Test
	public void testGetMedicalResults() {
		List<MedicalResult> mrs = mrl.getMedicalResults();
		assertEquals(mrs, mrl.getMedicalResults());
	}
	
	@Test 
	public void testUpdateMedicalResults() {
		assertEquals(true, mrl.updateMedResults(mrl.getMedicalResults(), pathMedRes));
	}
	
	@Test
	public void testGetMedResultByID() {
		assertEquals("00005", mrl.getMedResultById("00005").getID());
	}
	
	@Test
	public void testGetUsers() {
		List<User> users = userload.getUsers();
		assertEquals(users, userload.getUsers());
	}
	
	@Test 
	public void testUptadeUsers() {
		assertEquals(true, userload.updateUsers(userload.getUsers(), pathUsers));
	}
	
	@Test
	public void testFindPatientByLBO() {
		assertEquals("15987532600", userload.findPatientByLBO("15987532600").getLBO());
	}
	
	@Test
	public void testFindLaboranByUsername() {
		assertEquals("rankec", userload.findLaboranByUsername("rankec").getUsername());
	}
	
	@Test
	public void testGetUserByUsername() {
		assertEquals("djomla", userload.getUserByUsername("djomla").getUsername());
	}
	
	@Test
	public void testAddAnalysis() {
		AddAnalysis aa = new AddAnalysis();
		assertEquals(true, aa.addAnalysis("Iguadala", "viruslogija", "35", "55", "scoville", "3600"));
	}
	
	@Test
	public void testAddWorker() {
		AddWorker aw = new AddWorker();
		assertEquals(true, aw.addWorker("Laborant", "Veljko", "Jovanovic", "leca", "ljubovija", "5", "virusologija,vitamini"));
	}
	
	@Test
	public void testChangeBonus() {
		BonusChanger bc = new BonusChanger();
		assertEquals(true, bc.changeBonus("2000", "500"));
	}
	
	@Test 
	public void testChangeCoeff() {
		CoefficientChanger cc = new CoefficientChanger();
		assertEquals(true, cc.changeCoeff("0.4", "0.5", "0.6", "0.7", "0.75", "0.8", "0.85", "0.9", "8000.0"));
	}
	
	@Test 
	public void testChangeVisitPrice() {
		VisitPriceChanger vpc = new VisitPriceChanger();
		assertEquals(true, vpc.changeVisitPrice("800", "600"));
	}
	
	@Test
	public void testAddingPatient() {
		AddPatient ap = new AddPatient();
		assertEquals(true, ap.addingPatient("Sara", "Jovanovic", "sarita", "crnagora", "22336655220", false, "02.15.2008.", "0645588996", "Karadjordjeva 11"));
	}
	
	@Test
	public void testCreatingRequestMedTech() {
		CreatingRequestMedTech crmt = new CreatingRequestMedTech();
		String id = String.format("%05d", mrl.getMedicalResults().size());
		assertEquals(true, crmt.creatingRequestMT(id, "uzorak uzet", "0035:0.0,0047:0.0", "hematologija,hormoni", "14400032779", "", "26.08.2020. 00:00", "false", "false", "1820"));
	}
	
	@Test
	public void testCheckingPicked() {
		SamplesPickingReview spr = new SamplesPickingReview();
		assertEquals(true, spr.checkingPicked("gocin", "00003"));
	}
	
	@Test
	public void testCreatingRequestPatient() {
		CreatingRequestPatient crp = new CreatingRequestPatient();
		assertEquals(true, crp.creatingRequestPatient("uzorak nije uzet", "0020:0.0,0013:0.0", "imunologija,bakteriologija", "14400032778", "", "02.09.2020. 17:30", "true", "true", "7600"));
		
	}
	
	@Test
	public void testPrintingResults() {
		ResultsReviewer rr = new ResultsReviewer();
		assertEquals(true, rr.printingResults("andri", "00002"));
	}
	
	@Test
	public void testAnalyzingResult() {
		AnalyzerForMedResult afmr = new AnalyzerForMedResult();
		assertEquals(true, afmr.analyzingRequest("tarmi", "00004", "0022", 4.0));
	}

}
