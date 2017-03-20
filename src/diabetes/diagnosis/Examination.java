package diabetes.diagnosis;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;


/**
 * @author kubanowsky
 *
 */

public class Examination {

	private BooleanProperty examined;
	private DoubleProperty bloodGlucose;
	private BooleanProperty ghb;
	private DoubleProperty sugarInUrine;
	
    /**
     * Konstruktor domyślny
     */
	public Examination () {
		this(false, 0.0, false, 0.0);
	}

    /**
     * Konstruktor
     * 
     * @param czy badanie się odbyło
     * @param stężenie glukozy we krwi
     * @param obecność glikowanej hemoglobiny GHB
     * @param poziom cukru w moczu
     */
	public Examination(boolean examined, double bloodGlucose,
			boolean ghb, double sugarInUrine) {
		this.examined = new SimpleBooleanProperty(examined);
		this.bloodGlucose = new SimpleDoubleProperty(bloodGlucose);
		this.ghb = new SimpleBooleanProperty(ghb);
		this.sugarInUrine = new SimpleDoubleProperty(sugarInUrine);
	}
	
	/**
	 * @return czy badanie się odbyło
	 */
	public Boolean getExamined() {
		return examined.get();
	}
	
	/**
	 * @param czy badanie się odbyło
	 */
	public void setExamined(boolean exam) {
		this.examined.set(exam);
	}
	
	/**
	 * @return uchwyt na pole czy badanie się odbyło
	 */
	public BooleanProperty examinedProperty() {
		return examined;
	}
	
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
	public Double getSugarInUrine() {
		return sugarInUrine.get();
	}
	
	/**
	 * @param poziom cukru w moczu
	 */
	public void setSugarInUrine(double sugar) {
		this.sugarInUrine.set(sugar);
	}
	
	/**
	 * @return uchwyt na pole examined
	 */
	public DoubleProperty sugarInUrineProperty() {
		return sugarInUrine;
	}
}
