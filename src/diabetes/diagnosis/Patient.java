package diabetes.diagnosis;

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;


/**
 * @author kubanowsky
 *
 */
public class Patient {
	
	private StringProperty firstName;
	private StringProperty lastName;
	private StringProperty gender;
	private StringProperty peselCode;
	private StringProperty insurance;
	private Examination examination;
	
	/**
	 * Konstruktor domyślny
	 */
	public Patient() {
		this(null, null, null, null, null, false, 0.0, false, 0.0);
	}
	/**
	 * Konstruktor
	 * 
	 * @param imię
	 * @param nazwisko
	 * @param płeć
	 * @param pesel
	 * @param ubezpieczenie
     * @param czy badanie się odbyło
     * @param stężenie glukozy we krwi
     * @param obecność glikowanej hemoglobiny GHB
     * @param poziom cukru w moczu
	 */
	public Patient(String firstName, String lastName, String gender,
			String peselCode, String insurance, boolean examined,
			double bloodGlucose, boolean ghb, double sugarInUrine) {
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		this.gender = new SimpleStringProperty(gender);
		this.peselCode = new SimpleStringProperty(peselCode);
		this.insurance = new SimpleStringProperty(insurance);
		this.examination = new Examination(examined, bloodGlucose, ghb, sugarInUrine);
	}
	
	/**
	 * @return imię
	 */
	public String getFirstName() {
		return firstName.get();
	}
	
	/**
	 * @param imię
	 */
	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}
	
	/**
	 * @return uchwyt na pole imię
	 */
	public StringProperty firstNameProperty() {
		return firstName;
	}
	
	/**
	 * @return nazwisko
	 */
	public String getLastName() {
		return firstName.get();
	}
	
	/**
	 * @param nazwisko
	 */
	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}
	
	/**
	 * @return uchwyt na pole nazwisko
	 */
	public StringProperty lastNameProperty() {
		return lastName;
	}
	
	/**
	 * @return płeć
	 */
	public String getGender() {
		return gender.get();
	}
	
	/**
	 * @param płeć
	 */
	public void setGender(String gender) {
		this.gender.set(gender);
	}
	
	/**
	 * @return uchwyt na pole płeć
	 */
	public StringProperty genderProperty() {
		return gender;
	}
	
	/**
	 * @return pesel
	 */
	public String getPeselCode() {
		return peselCode.get();
	}
	
	/**
	 * @param pesel
	 */
	public void setPeselCode(String peselCode) {
		this.peselCode.set(peselCode);
	}
	
	/**
	 * @return uchwyt na pole płeć
	 */
	public StringProperty peselCodeProperty() {
		return peselCode;
	}
	
	/**
	 * @return ubezpieczenie
	 */
	public String getInsurance() {
		return insurance.get();
	}
	
	/**
	 * @param imię
	 */
	public void setInsurance(String insurance) {
		this.insurance.set(insurance);
	}
	
	/**
	 * @return uchwyt na pole imię
	 */
	public StringProperty insuranceProperty() {
		return insurance;
	}
	
	/**
	 * @return uchwyt na pole badanie
	 */
	public Examination getExamination() {
		return examination;
	}
	
	/**
     * @param czy badanie się odbyło
     * @param stężenie glukozy we krwi
     * @param obecność glikowanej hemoglobiny GHB
     * @param poziom cukru w moczu
	 */
	public void setExamination(boolean examined, double bloodGlucose,
			boolean ghb, double sugarInUrine) {
		this.examination.setExamined(examined);
		this.examination.setBloodGlucose(bloodGlucose);
		this.examination.setGhb(ghb);
		this.examination.setSugarInUrine(sugarInUrine);
	}
}
