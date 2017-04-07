package diabetes.diagnosis;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.time.LocalDate;


/**
 * @author kubanowsky
 *
 */

public class Examination {

	private LocalDate examinationDate;
	private DoubleProperty bloodGlucose;
	private BooleanProperty ghb;
	private DoubleProperty sugarLvl;

    /**
     * Konstruktor
     * 
     * @param czy badanie się odbyło
     * @param stężenie glukozy we krwi
     * @param obecność glikowanej hemoglobiny GHB
     * @param poziom cukru w moczu
     */
	public Examination(LocalDate examinationDate, boolean ghb,
					   double bloodGlucose, double sugarLvl) {
		this.examinationDate = examinationDate;
		this.ghb = new SimpleBooleanProperty(ghb);
		this.bloodGlucose = new SimpleDoubleProperty(bloodGlucose);
		this.sugarLvl = new SimpleDoubleProperty(sugarLvl);
	}

	public LocalDate getExaminationDate() { return examinationDate; }
	public void setExaminationDate(LocalDate examinationDate) { this.examinationDate=examinationDate; }
	
	/**
	 * @return stężenie glukozy we krwi
	 */
	public Double getBloodGlucose() {
		return bloodGlucose.get();
	}
	
	/**
	 * @param stężenie glukozy we krwi
	 */
	public void setBloodGlucose(double glucose) {
		this.bloodGlucose.set(glucose);
	}
	
	/**
	 * @return uchwyt na pole ze stężeniem glukozy we krwi
	 */
	public DoubleProperty bloodGlucoseProperty() {
		return bloodGlucose;
	}
	
	/**
	 * @return obecność glikowanej hemoglobiny GHB
	 */
	public Boolean getGhb() {
		return ghb.get();
	}
	
	/**
	 * @param obecność glikowanej hemoglobiny GHB
	 */
	public void setGhb(boolean hemoglobinGhb) {
		this.ghb.set(hemoglobinGhb);
	}
	
	/**
	 * @return uchwyt na pole obecności glikowanej hemoglobiny GHB
	 */
	public BooleanProperty ghbProperty() {
		return ghb;
	}
	
	/**
	 * @return poziom cukru w moczu
	 */
	public Double getSugarLvl() {
		return sugarLvl.get();
	}
	
	/**
	 * @param poziom cukru w moczu
	 */
	public void setSugarLvl(double sugar) {
		this.sugarLvl.set(sugar);
	}
	
	/**
	 * @return uchwyt na pole examined
	 */
	public DoubleProperty sugarLvlProperty() {
		return sugarLvl;
	}
}
